package com.example.poc.temporal.bre.bulkjob;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.List;

@ActivityInterface
public interface BulkJobActivities {

  @ActivityMethod
  void syncData();

  @ActivityMethod
  List<String> getEligibleInvestors(BulkJob bulkJob);

  @ActivityMethod
  void callDS();

}
