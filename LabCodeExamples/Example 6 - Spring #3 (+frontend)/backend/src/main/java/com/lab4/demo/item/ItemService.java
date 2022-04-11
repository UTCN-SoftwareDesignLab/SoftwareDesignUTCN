package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ReportServiceFactory reportServiceFactory;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public String export(ReportType type) {
        return reportServiceFactory.getReportService(type).export();
    }
}
