package com.lab4.demo.report;

import org.springframework.stereotype.Service;

import static com.lab4.demo.report.ReportType.CSV;

@Service
public class CsvReportService implements ReportService {
  @Override
  public String generateReport() {
    return "CSV";
  }

  @Override
  public ReportType getType() {
    return CSV;
  }
}
