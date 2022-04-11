package com.lab4.demo.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.lab4.demo.report.ReportType.CSV;
import static com.lab4.demo.report.ReportType.PDF;
import static com.lab4.demo.report.ReportType.XML;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReportServiceFactoryTest {

  @Autowired
  private ReportServiceFactory reportServiceFactory;

  @Test
  void getByType() {
    for (ReportType value : ReportType.values()) {
      assertEquals(value, reportServiceFactory.getByType(value).getType());
    }
  }
}
