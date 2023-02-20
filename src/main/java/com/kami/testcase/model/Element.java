package com.kami.testcase.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Element implements Comparable<Element> {
  private final String colWord; 
  private final long charsBeforeWord; 
  private final long charsBeforeRow;
  private final int rowLength;

  @Override
  public int compareTo(Element o) {
    return this.colWord.replace("\"", "").compareTo(o.colWord.replace("\"", ""));
  }
}
