package com.example.poc.temporal.bre.bulkjob;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkJobService {

  private final WorkflowClient workflowClient;

  public void startBulkJob() {
    BulkJobWorkflow bulkJobWorkflow = workflowClient.newWorkflowStub(
      BulkJobWorkflow.class,
      WorkflowOptions.newBuilder()
        .setTaskQueue("BulkJobTaskQueue")
        .build()
    );
    WorkflowClient.start(
      bulkJobWorkflow::executeBulkJob,
      BulkJob.builder()
        .investorIds(List.of("InvestorId1", "InvestorId2", "InvestorId3"))
        .customerIds(List.of("CustomerId1", "CustomerId2", "CustomerId3"))
        .dealIds(List.of("DealId1", "DealId2", "DealId3"))
        .product("Product")
        .subProduct("SubProduct")
        .build()
    );
  }

}
