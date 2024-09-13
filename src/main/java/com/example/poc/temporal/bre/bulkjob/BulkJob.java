package com.example.poc.temporal.bre.bulkjob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkJob {

  private List<String> investorIds;
  private List<String> dealIds;
  private List<String> customerIds;
  private String product;
  private String subProduct;

}
