package com.kami.testcase.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.kami.testcase.model.Element;
import com.kami.testcase.model.SearchOptions;

public interface SearchService {
  public List<Element> searchColsInFile(BufferedReader br, SearchOptions so);
  public String searchInFile(BufferedReader br, long beforeRow) throws IOException;
  public List<Element> searchInBatch(List<Element> batch, SearchOptions so);
  public long getSearchTime();
}
