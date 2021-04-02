package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.Item;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.EXPORT_REPORT;
import static com.lab4.demo.UrlMapping.FRONT_OFFICE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(FRONT_OFFICE)
@RequiredArgsConstructor
public class FrontOfficeController {

    private final ItemService itemService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<Item> allItems() {
        return itemService.findAll();
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) {
        return reportServiceFactory.getReportService(type).export();
    }
}
