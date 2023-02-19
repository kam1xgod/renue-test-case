package com.kami.testcase.service;

import java.util.regex.Pattern;

@Deprecated
public class MatchingServiceImpl implements MatchingService {

  private Pattern numPat = Pattern.compile("-?\\d+(\\.\\d+)?");

  @Override
  public boolean isMatching(String pattern, String value) {
    if (numPat.matcher(pattern).matches()) {
      try {
        return isNumberMatching(Double.parseDouble(pattern), Double.parseDouble(value));
      } catch (NumberFormatException e) {
        try {
          return isNumberMatching(Integer.parseInt(pattern), Integer.parseInt(value));
        } catch (NumberFormatException ex) {
          return false;
        }
      }
    } else {
      if (isStringMatching(pattern, value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isStringMatching(String pattern, String value) {
    if (value.toLowerCase().substring(1).startsWith(pattern.toLowerCase())) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isNumberMatching(int pattern, int value) {
    if (pattern == value) {
      return true;
    }
    int patternDigits = String.valueOf(pattern).length();
    int valueDigits = String.valueOf(value).length();
    // System.out.println(pattern);
    // System.out.println((int)(value / Math.pow(10, valueDigits - patternDigits)));
    if (pattern == (int) (value / Math.pow(10, valueDigits - patternDigits))) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isNumberMatching(double pattern, double value) {
    if (Double.compare(pattern, value) == 0) {
      return true;
    }
    int patternPointIndex = String.valueOf(pattern).indexOf(".");
    int valuePointIndex = String.valueOf(value).indexOf(".");
    if (patternPointIndex != valuePointIndex) {
      return false;
    }
    int patternAfterPoint = String.valueOf(pattern).length() - patternPointIndex;
    int valueAfterPoint = String.valueOf(value).length() - valuePointIndex;
    value *= Math.pow(10, valueAfterPoint);
    pattern *= Math.pow(10, patternAfterPoint);
    return isNumberMatching((int) pattern, (int) value);
  }
}
