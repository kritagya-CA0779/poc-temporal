package com.example.poc.temporal.bre.investorjob;

import com.example.poc.temporal.bre.common.Deal;
import com.example.poc.temporal.bre.common.InvestorResult;
import com.example.poc.temporal.bre.common.Policy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class InvestorJobActivitiesImpl implements InvestorJobActivities {

  @Override
  public List<Deal> getDealsForInvestorFromElasticsearch(Policy policy) {
    log.info("Getting Deals for Investor from Elasticsearch for Policy: {}", policy);
    return List.of(
      Deal.builder().id("DealId1").product("p1").subProduct("sp1").amount(100).build(),
      Deal.builder().id("DealId2").product("p2").subProduct("sp2").amount(200).build(),
      Deal.builder().id("DealId3").product("p3").subProduct("sp3").amount(300).build(),
      Deal.builder().id("DealId4").product("p4").subProduct("sp4").amount(400).build()
    );
  }

  @Override
  public List<Policy> fetchPoliciesForInvestor(String investorId) {
    log.info("Fetching Policies for Investor: {}", investorId);
    return List.of(
      Policy.builder().id("PolicyId1").product("p1").subProduct("sp1").build(),
      Policy.builder().id("PolicyId2").product("p2").subProduct("sp2").build(),
      Policy.builder().id("PolicyId3").product("p3").subProduct("sp3").build(),
      Policy.builder().id("PolicyId4").product("p4").subProduct("sp4").build()
    );
  }

  @Override
  public void sendInvestorResultsToORS(String investorId, List<InvestorResult> investorResult) {
    log.info("Investor Results sent to ORS for Investor: {}", investorId);
  }

}
