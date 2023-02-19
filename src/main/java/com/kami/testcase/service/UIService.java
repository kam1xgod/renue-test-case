package com.kami.testcase.service;

import com.kami.testcase.model.Row;
import com.kami.testcase.model.SearchOptions;
import java.util.List;

public interface UIService {
  public void askForInput();

  public int parseArgs(String[] args);

  public String parseQueue();

  public void printResult(List<Row> rows, SearchOptions opt, Long ms);

  public void printMeasurements(List<Row> rows, Long ms);

  public void closeScanner();
}
