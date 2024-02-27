package com.lab4.demo.report;

import org.springframework.stereotype.Service;

import static com.lab4.demo.report.ReportType.PDF;

@Service
public class PdfReportService implements ReportService {
  @Override
  public String generateReport() {
    return "PDF";
  }

  @Override
  public ReportType getType() {
    return PDF;
  }
}
