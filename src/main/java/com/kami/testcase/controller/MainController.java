package com.kami.testcase.controller;

import com.kami.testcase.model.SearchOptions;
import com.kami.testcase.service.FileService;
import com.kami.testcase.service.FileServiceImpl;
import com.kami.testcase.service.UIService;
import com.kami.testcase.service.UIServiceImpl;
import java.io.FileNotFoundException;

public class MainController {
  private final UIService uiService;
  private final FileService fileService;

  public MainController() {
    this.uiService = new UIServiceImpl();
    this.fileService = new FileServiceImpl();
  }

  public MainController(UIService uiService, FileService fileService) {
    this.uiService = uiService;
    this.fileService = fileService;
  }

  public void mainTask(String[] args) {
    try {
      String choice = uiService.parseQueue();
      while (!choice.equals("!quit")) {
        SearchOptions so = new SearchOptions(uiService.parseArgs(args), choice);
        fileService.scanFile(so);
        choice = uiService.parseQueue();
      }

      uiService.closeScanner();
    } catch (NumberFormatException numExc) {
      System.err.println("Input one number bigger than 0 and lower than 14.");
      System.exit(1);
    } catch (FileNotFoundException fileExc) {
      System.err.println("Make sure .csv file placed in project's root directory.");
      System.exit(1);
    }
  }
}
