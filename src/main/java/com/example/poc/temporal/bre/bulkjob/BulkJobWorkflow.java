package com.example.poc.temporal.bre.bulkjob;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface BulkJobWorkflow {

  @WorkflowMethod
  void executeBulkJob(BulkJob bulkJob);

}
