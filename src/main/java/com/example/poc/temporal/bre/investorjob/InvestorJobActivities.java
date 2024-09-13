package com.example.poc.temporal.bre.investorjob;

import com.example.poc.temporal.bre.common.Deal;
import com.example.poc.temporal.bre.common.InvestorResult;
import com.example.poc.temporal.bre.common.Policy;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.List;

@ActivityInterface
public interface InvestorJobActivities {

  @ActivityMethod
  List<Policy> fetchPoliciesForInvestor(String investorId);

  @ActivityMethod
  List<Deal> getDealsForInvestorFromElasticsearch(Policy policy);

  @ActivityMethod
  void sendInvestorResultsToORS(String investorId, List<InvestorResult> investorResults);

}
