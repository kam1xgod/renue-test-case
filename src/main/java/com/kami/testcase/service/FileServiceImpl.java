package com.kami.testcase.service;

import java.io.BufferedReader;
import java.io.File;
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
  private SearchService searchService = new SearchServiceImpl();
  private static final String fileName = "airports.csv";

  @Override
  public BufferedReader openFile() throws FileNotFoundException {
    StringBuilder path = new StringBuilder(System.getProperty("user.dir")).append('/');
    if (isFileExist(path.append(fileName).toString())) {
      inputStream = new FileInputStream(path.toString());
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      return bufferedReader;
    }
      inputStream = new FileInputStream(path.toString().replace("target/", ""));
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

  private boolean isFileExist(String path) {
    File f = new File(path);
    return f.exists() && !f.isDirectory();

  }
}
