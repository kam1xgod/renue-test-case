package com.kami.testcase.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.kami.testcase.model.Element;
import com.kami.testcase.model.SearchOptions;

import lombok.Getter;

public class FileServiceImpl implements FileService {
  private FileInputStream inputStream;
  @Getter
  private BufferedReader bufferedReader;
  private static final String path = "../airports.csv";
  private SearchService searchService = new SearchServiceImpl();

  @Override
  public BufferedReader openFile() throws FileNotFoundException {
    inputStream = new FileInputStream(path);
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    return bufferedReader;
  }

  @Override
  public void closeFile() throws IOException {
    inputStream.close();
    bufferedReader.close();
  }

  @Override
  public List<Element> readFileOnce(SearchOptions so) throws IOException {
    List<Element> batch = searchService.searchColsInFile(bufferedReader, so);
    resetBufReader();
    return batch;
  }

  @Override
  public String getDataFromFile(long beforeRow) throws IOException {
    return searchService.searchInFile(bufferedReader, beforeRow);
  }

  @Override
  public void resetBufReader() throws IOException {
    inputStream.getChannel().position(0);
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
  }
}
