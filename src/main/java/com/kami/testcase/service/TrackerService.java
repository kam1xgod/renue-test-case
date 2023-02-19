package com.kami.testcase.service;

public interface TrackerService {
  public void startTimeTracking();
  public void finishTimeTracking();
  public Long calcTime();
}
