package com.kami.testcase.service;

// import java.time.Duration;
// import java.time.Instant;
// import java.time.temporal.ChronoUnit;
//
// import org.apache.commons.lang3.time.StopWatch;

@Deprecated
public class TrackerServiceImpl implements TrackerService {
  // private StopWatch sw = new StopWatch();
  // private Instant start;
  // private Instant finish;
  private Long start;
  private Long finish;

  @Override
  public void startTimeTracking() {
    start = System.nanoTime();
  }

  @Override
  public void finishTimeTracking() {
    finish = System.nanoTime();
  }

  @Override
  public Long calcTime() {
    return finish - start;
  }
}
