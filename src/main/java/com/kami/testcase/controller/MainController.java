package com.kami.testcase.controller;

import com.kami.testcase.model.Element;
import com.kami.testcase.model.SearchOptions;
import com.kami.testcase.service.FileService;
import com.kami.testcase.service.FileServiceImpl;
import com.kami.testcase.service.SearchService;
import com.kami.testcase.service.SearchServiceImpl;
import com.kami.testcase.service.TrackerService;
import com.kami.testcase.service.TrackerServiceImpl;
import com.kami.testcase.service.UIService;
import com.kami.testcase.service.UIServiceImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainController {
  private final UIService uiService;
  private final FileService fileService;
  private final SearchService searchService;
  private final TrackerService trackerService;
  private List<Element> batch = new ArrayList<>();

  public MainController() {
    this.uiService = new UIServiceImpl();
    this.fileService = new FileServiceImpl();
    this.searchService = new SearchServiceImpl();
    this.trackerService = new TrackerServiceImpl();
  }

  public void mainTask(String[] args) {
    try {
      int gotArgs = uiService.parseArgs(args);
      fileService.openFile();
      String choice = uiService.parseQueue();
      while (!choice.equals("!quit")) {
        SearchOptions so = new SearchOptions(gotArgs, choice);
        batch = fileService.readFileOnce(so);
        trackerService.startTimeTracking();
        List<Element> matchedRows = new ArrayList<>();
        matchedRows = searchService.searchInBatch(batch, so);
        Collections.sort(matchedRows);
        trackerService.finishTimeTracking();

        List<String> strs = new ArrayList<>();
        matchedRows.forEach(
            r -> {
              try {
                fileService.resetBufReader();
                strs.add(
                    searchService.searchInFile(
                        fileService.getBufferedReader(), r.getCharsBeforeRow()));
              } catch (IOException e) {
                System.err.println("Something is wrong with your file, try again.");
                System.exit(1);
              }
            });

        uiService.printResult(matchedRows, strs);
        fileService.resetBufReader();

        choice = uiService.parseQueue();
      }

      fileService.closeFile();
      uiService.closeScanner();
    } catch (NumberFormatException numExc) {
      System.err.println("Input one number bigger than 0 and lower than 14 as an argument.");
      System.exit(1);
    } catch (FileNotFoundException fileExc) {
      System.err.println("Make sure .csv file placed in project's root directory.");
      System.exit(1);
    } catch (IOException ioExc) {
      System.err.println("Something is wrong with your file, try again.");
      System.exit(1);
    }
  }
}
