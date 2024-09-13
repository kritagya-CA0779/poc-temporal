package com.example.poc.temporal.bre.bulkjob;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulk-job")
@RequiredArgsConstructor
public class BulkJobController {

  private final BulkJobService bulkJobService;

  @PostMapping
  public ResponseEntity<Void> startBulkJob() {
    bulkJobService.startBulkJob();
    return ResponseEntity.ok().build();
  }

}
