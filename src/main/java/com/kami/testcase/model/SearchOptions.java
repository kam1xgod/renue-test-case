package com.kami.testcase.model;

public class SearchOptions {
  private int searchCol;
  private String searchPattern;

  public SearchOptions(int searchCol, String searchPattern) {
    this.searchCol = searchCol;
    this.searchPattern = searchPattern;
  }

  public int getSearchCol() {
    return searchCol;
  }

  public void setSearchCol(int searchCol) {
    this.searchCol = searchCol;
  }

  public String getSearchPattern() {
    return searchPattern;
  }

  public void setSearchPattern(String searchPattern) {
    this.searchPattern = searchPattern;
  }
}
