package com.kami.testcase.service;

import java.util.List;

import com.kami.testcase.model.Element;

public interface UIService {
  public void askForInput();

  public int parseArgs(String[] args);

  public String parseQueue();

  public void printResult(List<Element> rows, List<String> strs);

  public void printMeasurements(List<Element> rows);

  public void closeScanner();
}
