package com.example.poc.temporal.bre.bulkjob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BulkJobActivitiesImpl implements BulkJobActivities {

  @Override
  public void syncData() {
    log.info("Data Synced from MongoDB to Elasticsearch");
  }

  @Override
  public List<String> getEligibleInvestors(BulkJob bulkJob) {
    log.info("Getting Eligible Investors for Bulk Job: {}", bulkJob);
    return List.of("InvestorId1", "InvestorId2", "InvestorId3");
  }

  @Override
  public void callDS() {
    log.info("Called DS team to start recommendation engine");
  }

}
