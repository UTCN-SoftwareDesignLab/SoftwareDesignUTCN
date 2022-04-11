package com.lab4.demo.report;

import org.springframework.stereotype.Service;

import static com.lab4.demo.report.ReportType.XML;

@Service
public class XmlReportService implements ReportService {
  @Override
  public String generateReport() {
    return "XML";
  }

  @Override
  public ReportType getType() {
    return XML;
  }
}
