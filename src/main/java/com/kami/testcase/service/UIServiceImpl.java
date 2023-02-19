package com.kami.testcase.service;

import com.kami.testcase.model.Row;
import com.kami.testcase.model.SearchOptions;
import java.util.List;
import java.util.Scanner;

public class UIServiceImpl implements UIService {

  // private TrackerService trackerService = new TrackerServiceImpl();
  private Scanner s = new Scanner(System.in);
  // better to write some method for counting it, but for now this looks ok.
  private static final int AMOUNT_OF_COLS = 14;

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
  public void printResult(List<Row> rows, SearchOptions opt, Long ms) {
    rows.forEach(r -> System.out.println(r.getFieldByIndex() + r.toString()));
    printMeasurements(rows, ms);
  }

  @Override
  public void printMeasurements(List<Row> rows, Long ms) {
    StringBuilder sb = new StringBuilder();
    sb.append("Found: ").append(rows.size()).append(" rows and ").append(ms).append("ms");
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
