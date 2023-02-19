package com.kami.testcase.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Row implements Comparable<Row> {
  private ArrayList<String> fields;
  private final int col;

  private Row(ArrayList<String> fields, int col) {
    this.fields = fields;
    this.col = col;
  }

  public static Row of(String str, int col) {
    ArrayList<String> al = new ArrayList<>();
    Arrays.stream(str.split(",")).forEach(field -> al.add(field));

    return new Row(al, col);
  }

  public String getFieldByIndex() {
    return fields.get(col);
  }

  public ArrayList<String> getFields() {
    return this.fields;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder().append("[");
    fields.forEach(
        c -> {
          result.append(c);
          if (fields.indexOf(c) < fields.size() - 1) {
            result.append(',');
          }
        });
    return result.append("]").toString();
  }

  @Override
  public int compareTo(Row o) {
    return this.fields.get(col).compareTo(o.getFields().get(col));
  }
}
