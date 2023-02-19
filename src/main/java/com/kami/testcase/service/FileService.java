package com.kami.testcase.service;

import com.kami.testcase.model.Row;
import com.kami.testcase.model.SearchOptions;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {
  public void openFile() throws FileNotFoundException;

  public List<Row> scanBatch(SearchOptions opt);

  public void scanFile(SearchOptions opt) throws FileNotFoundException;
}
