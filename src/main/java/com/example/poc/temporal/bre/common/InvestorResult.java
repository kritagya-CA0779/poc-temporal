package com.example.poc.temporal.bre.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestorResult {

  private String investorId;
  private String policyId;
  private List<String> dealIds;

}
