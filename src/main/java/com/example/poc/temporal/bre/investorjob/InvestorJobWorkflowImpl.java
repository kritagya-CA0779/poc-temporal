package com.example.poc.temporal.bre.investorjob;

import com.example.poc.temporal.bre.common.Deal;
import com.example.poc.temporal.bre.common.InvestorResult;
import com.example.poc.temporal.bre.common.Policy;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.temporal.workflow.Workflow.sleep;

@Slf4j
@NoArgsConstructor
public class InvestorJobWorkflowImpl implements InvestorJobWorkflow {

  private final InvestorJobActivities activities = Workflow.newActivityStub(
    InvestorJobActivities.class,
    ActivityOptions.newBuilder()
      .setScheduleToCloseTimeout(Duration.ofMinutes(5))
      .build()
  );

  @Override
  public List<InvestorResult> executeInvestorJob(InvestorJob investorJob) {

    List<Policy> policies = activities.fetchPoliciesForInvestor(investorJob.getInvestorId());

    List<InvestorResult> investorResults = new ArrayList<>();
    for (Policy policy : policies) {
      List<String> dealIds = activities.getDealsForInvestorFromElasticsearch(policy).stream()
        .map(Deal::getId)
        .toList();
      InvestorResult investorResult = InvestorResult.builder()
        .investorId(investorJob.getInvestorId())
        .dealIds(dealIds)
        .policyId(policy.getId())
        .build();
      investorResults.add(investorResult);
    }

    sleep(5000);

    activities.sendInvestorResultsToORS(investorJob.getInvestorId(), investorResults);

    return investorResults;
  }

}
