package com.example.poc.temporal.bre.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

  private String id;
  private String product;
  private String subProduct;

}
