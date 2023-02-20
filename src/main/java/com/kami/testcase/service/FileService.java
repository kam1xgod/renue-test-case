package com.kami.testcase.service;

import com.kami.testcase.model.Element;
import com.kami.testcase.model.SearchOptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileService {
  public BufferedReader openFile() throws FileNotFoundException;

  public void closeFile() throws IOException;

  public List<Element> readFileOnce(SearchOptions so) throws IOException;

  public String getDataFromFile(long beforeRow) throws IOException;

  public void resetBufReader() throws IOException;
  
  public BufferedReader getBufferedReader();
}
