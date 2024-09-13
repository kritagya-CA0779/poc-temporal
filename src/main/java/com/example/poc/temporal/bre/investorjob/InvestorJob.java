package com.example.poc.temporal.bre.investorjob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestorJob {

  private String investorId;
  private List<String> dealIds;
  private List<String> customerIds;
  private String product;
  private String subProduct;

}
