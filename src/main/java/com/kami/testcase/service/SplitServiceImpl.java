package com.kami.testcase.service;

public class SplitServiceImpl implements SplitService {
  private final static int MAX_COL = 14;

  @Override
  public String[] split(String row, String delimiter) {
    String[] result = new String[MAX_COL];
    result[0] = row.substring(0, row.indexOf(','));


    for (int i = 0; i < MAX_COL - 1; i++) {
      int indexOfDelimiter = row.indexOf(delimiter);
      if (row.charAt(0) == '\"' && row.charAt(indexOfDelimiter - 1) != '\"') {
          indexOfDelimiter += row.substring(indexOfDelimiter + 1).indexOf(delimiter);
      }
      result[i] = row.substring(0, indexOfDelimiter);
      row = row.substring(indexOfDelimiter + 1);
    }

    return result;
  }
}
