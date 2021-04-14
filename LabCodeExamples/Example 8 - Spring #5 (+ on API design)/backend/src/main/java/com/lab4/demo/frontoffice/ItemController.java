package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.dto.ItemDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<ItemDTO> allItems() {
        return itemService.findAll();
    }

    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO item) {
        return itemService.create(item);
    }

    @PatchMapping(ENTITY)
    public ItemDTO changeName(@PathVariable Long id, @RequestBody String newName) {
        return itemService.changeName(id, newName);
    }

    @PutMapping(ENTITY)
    public ItemDTO edit(@PathVariable Long id, @RequestBody ItemDTO item) {
        return itemService.edit(id, item);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) {
        return reportServiceFactory.getReportService(type).export();
    }
}
