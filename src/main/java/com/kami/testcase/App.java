package com.kami.testcase;

import com.kami.testcase.controller.MainController;

public class App {
  public static void main(String[] args) {
    MainController mc = new MainController();

    mc.mainTask(args);
    // on start - open file, count lines.
    // take 25% of it and work with first batch.
    // stream through batch, find every row that matches pattern.
    // split row with ','.
    // check if we're searching for a string or number - try parse int/doulbe.
    // actually, there should be another way with regex.
    // if we searching for a string - start with second character to ignore '/"'.
    // if nubmer - just grab it.
    // store those rows.
    // print rows, count and time.
    // ask for another input.
    // on another itteration we can start from last batch we've stored.
    // on '!quit' finish app.
  }
}
