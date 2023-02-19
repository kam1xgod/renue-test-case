package com.kami.testcase.service;

import com.kami.testcase.model.Row;
import com.kami.testcase.model.SearchOptions;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {
  private FileInputStream inputStream = null;
  private BufferedReader bufferedReader = null;
  private static final String path = "../airports.csv";
  private Map<SearchOptions, List<Row>> memo = new HashMap<>();

  // private TrackerService trackerService = new TrackerServiceImpl();
  private UIService uiService = new UIServiceImpl();
  // private MatchingService matchingService = new MatchingServiceImpl();

  @Override
  public void openFile() throws FileNotFoundException {
    inputStream = new FileInputStream(path);
    inputStream = new FileInputStream(path);
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
  }

  @Override
  public List<Row> scanBatch(SearchOptions opt) {
    if (memo.containsKey(opt)) {
      return memo.get(opt);
    }
    List<Row> matchedRows = new ArrayList<>();
    Stream<String> linesStream = bufferedReader.lines();
    linesStream
        .filter(
            r ->
                r.split(",")[opt.getSearchCol()].startsWith("\"")
                    ? r.split(",")[opt.getSearchCol()]
                        .substring(1)
                        .startsWith(opt.getSearchPattern())
                    : r.split(",")[opt.getSearchCol()].startsWith(opt.getSearchPattern()))
        .forEach(r -> matchedRows.add(Row.of(r, opt.getSearchCol())));
    Collections.sort(matchedRows);
    memo.put(opt, matchedRows);
    return matchedRows;
  }

  @Override
  public void scanFile(SearchOptions opt) throws FileNotFoundException {
    Instant start = Instant.now();
    openFile();
    scanBatch(opt);
    Instant finish = Instant.now();
    uiService.printResult(memo.get(opt), opt, Duration.between(start, finish).toMillis());
  }
}
