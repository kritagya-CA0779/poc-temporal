package com.example.poc.temporal.bre.investorjob;

import com.example.poc.temporal.bre.common.InvestorResult;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.List;

@WorkflowInterface
public interface InvestorJobWorkflow {

  @WorkflowMethod
  List<InvestorResult> executeInvestorJob(InvestorJob investorJob);

}
