package com.example.poc.temporal.bre.configs;

import com.example.poc.temporal.bre.bulkjob.BulkJobActivitiesImpl;
import com.example.poc.temporal.bre.bulkjob.BulkJobWorkflowImpl;
import com.example.poc.temporal.bre.investorjob.InvestorJobActivitiesImpl;
import com.example.poc.temporal.bre.investorjob.InvestorJobWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

  @Bean
  public WorkflowClient workflowClient() {
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    return WorkflowClient.newInstance(service);
  }

  @Bean
  public WorkerFactory workerFactory(WorkflowClient workflowClient) {
    return WorkerFactory.newInstance(workflowClient);
  }

  @Bean
  public ApplicationRunner startWorkers(WorkerFactory workerFactory, BulkJobActivitiesImpl bulkJobActivities, InvestorJobActivitiesImpl investorJobActivities) {
    Worker bulkJobWorker = workerFactory.newWorker("BulkJobTaskQueue");
    bulkJobWorker.registerWorkflowImplementationTypes(BulkJobWorkflowImpl.class);
    bulkJobWorker.registerActivitiesImplementations(bulkJobActivities);

    Worker investorJobWorker = workerFactory.newWorker("InvestorJobTaskQueue");
    investorJobWorker.registerWorkflowImplementationTypes(InvestorJobWorkflowImpl.class);
    investorJobWorker.registerActivitiesImplementations(investorJobActivities);

    return args -> workerFactory.start();
  }

}
