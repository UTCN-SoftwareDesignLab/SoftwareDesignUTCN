package com.lab4.demo.report;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportServiceFactory {

  private final Map<ReportType, ReportService> reportServices;

  public ReportServiceFactory(List<ReportService> serviceList) {
    reportServices = serviceList.stream()
        .collect(Collectors.toMap(
            ReportService::getType, service -> service
        ));
  }

  public String export(ReportType type) {
    return reportServices.get(type).generateReport();
  }

  public ReportService getByType(ReportType type) {
    return reportServices.get(type);
  }
}
