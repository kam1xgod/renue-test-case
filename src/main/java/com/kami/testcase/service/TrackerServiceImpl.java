package com.kami.testcase.service;

import java.time.Duration;
import java.time.Instant;

public class TrackerServiceImpl implements TrackerService {
  private static Instant start;
  private static Instant finish;

  @Override
  public void startTimeTracking() {
    start = Instant.now();
  }

  @Override
  public void finishTimeTracking() {
    finish = Instant.now();
  }

  @Override
  public Long calcTime() {
    return Duration.between(start, finish).toMillis();
  }
}
