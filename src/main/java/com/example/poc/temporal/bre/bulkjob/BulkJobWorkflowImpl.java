package com.example.poc.temporal.bre.bulkjob;

import com.example.poc.temporal.bre.common.InvestorResult;
import com.example.poc.temporal.bre.investorjob.InvestorJob;
import com.example.poc.temporal.bre.investorjob.InvestorJobWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.ChildWorkflowOptions;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class BulkJobWorkflowImpl implements BulkJobWorkflow {

  private final BulkJobActivities activities = Workflow.newActivityStub(
    BulkJobActivities.class,
    ActivityOptions.newBuilder()
      .setScheduleToCloseTimeout(Duration.ofMinutes(5))
      .build()
  );

  @Override
  public void executeBulkJob(BulkJob bulkJob) {

    LocalDateTime startTime = LocalDateTime.now();

    activities.syncData();

    List<String> investors = activities.getEligibleInvestors(bulkJob);

    List<Promise<List<InvestorResult>>> investorResultsPromises = new ArrayList<>();
    for (String investor : investors) {
      InvestorJobWorkflow childWorkflow = Workflow.newChildWorkflowStub(
        InvestorJobWorkflow.class,
        ChildWorkflowOptions.newBuilder()
          .setWorkflowId(String.format("%s_%s",Workflow.getInfo().getWorkflowId(), investor))
          .setTaskQueue("InvestorJobTaskQueue")
          .build()
      );
      Promise<List<InvestorResult>> promise = Async.function(
        childWorkflow::executeInvestorJob,
        InvestorJob.builder()
          .investorId(investor)
          .dealIds(bulkJob.getDealIds())
          .customerIds(bulkJob.getCustomerIds())
          .product(bulkJob.getProduct())
          .subProduct(bulkJob.getSubProduct())
          .build()
      );
      investorResultsPromises.add(promise);
    }

    List<List<InvestorResult>> investorResultsList = Promise.allOf(investorResultsPromises)
      .thenApply(v -> investorResultsPromises.stream()
        .map(Promise::get)
        .toList()
      )
      .get();

    log.info("Investor results: {}", investorResultsList);

    activities.callDS();

    LocalDateTime endTime = LocalDateTime.now();
    log.info("Bulk job execution time: {}", Duration.between(startTime, endTime));
  }

}
