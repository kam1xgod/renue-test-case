package com.kami.testcase.service;

import com.kami.testcase.model.Element;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class UIServiceImpl implements UIService {

  private Scanner s = new Scanner(System.in);
  // better to write some method for counting it, but for now this looks ok.
  private static final int AMOUNT_OF_COLS = 14;
  private final TrackerService trackerService;

  public UIServiceImpl() {
    this.trackerService = new TrackerServiceImpl();
  }

  @Override
  public int parseArgs(String[] args) throws NumberFormatException {
    if (args.length != 1) {
      throw new NumberFormatException();
    }
    if (Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[0]) > AMOUNT_OF_COLS) {
      throw new NumberFormatException();
    }
    return Integer.parseInt(args[0]) - 1;
  }

  @Override
  public String parseQueue() {
    askForInput();
    String line = s.nextLine();
    return line;
  }

  @Override
  public void printResult(List<Element> rows, List<String> strs) {
    int strIndex = 0;
    for (Element row : rows) {
      System.out.println(row.getColWord() + "[" + strs.get(strIndex) + "]");
      strIndex++;
    }
    printMeasurements(rows);
  }

  @Override
  public void printMeasurements(List<Element> rows) {
    StringBuilder sb = new StringBuilder();
    sb.append("Found: ").append(rows.size()).append(" rows in ").append(trackerService.calcTime()).append("ms");
    System.out.println(sb.toString());
  }

  @Override
  public void closeScanner() {
    s.close();
  }

  @Override
  public void askForInput() {
    System.out.print("Type your search queue (or \"!quit\" to exit): ");
  }
}
