package com.kami.testcase.service;

public interface MatchingService {
  public boolean isMatching(String pattern, String value);
  public boolean isStringMatching(String pattern, String value);
  public boolean isNumberMatching(int pattern, int value);
  public boolean isNumberMatching(double pattern, double value);
}
