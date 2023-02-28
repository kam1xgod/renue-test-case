package com.kami.testcase.service;

import com.kami.testcase.model.Element;
import com.kami.testcase.model.SearchOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SearchServiceImpl implements SearchService {
  private final SplitService splitService = new SplitServiceImpl();
  private Instant start;
  private Instant finish;

  @Override
  public List<Element> searchColsInFile(BufferedReader br, SearchOptions so) {
    Stream<String> linesStream = br.lines();
    List<Element> batch = new ArrayList<>();
    linesStream.forEach(
        r -> {
          batch.add(
              new Element(
                  splitService.split(r, ",")[so.getSearchCol()],
                  getCharsBeforeWord(r, so.getSearchCol()),
                  batch.isEmpty() ? 0 : getCharsBeforeRow(batch),
                  r.length()));
        });
    return batch;
  }

  @Override
  public String searchInFile(BufferedReader br, long beforeRow) throws IOException {
    br.skip(beforeRow);
    return br.readLine();
  }

  @Override
  public List<Element> searchInBatch(List<Element> batch, SearchOptions so) {
    List<Element> matchedRows = new ArrayList<>();

    batch.stream()
        .filter(
            r ->
                r.getColWord()
                    .replace("\"", "")
                    .toLowerCase()
                    .startsWith(so.getSearchPattern().toLowerCase()))
        .forEach(
            r -> {
              matchedRows.add(r);
            });

    return matchedRows;
  }

  private int getCharsBeforeWord(String r, int col) {
    int result = 0;
    String[] arr = splitService.split(r, ",");
    for (int i = 0; i < arr.length; i++) {
      if (i == col) {
        return result;
      }
      result += arr[i].length() + 1;
    }
    return result;
  }

  private int getCharsBeforeRow(List<Element> batch) {
    int result = 0;
    for (Element row : batch) {
      result += row.getRowLength() + 1;
    }
    return result;
  }

  @Override
  public long getSearchTime() {
    return Duration.between(start, finish).toMillis();
  }
}
