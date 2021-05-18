package com.lab4.demo.item;

import com.lab4.demo.item.model.dto.ItemDTO;
import com.lab4.demo.item.model.dto.ItemFilterRequestDto;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import com.lab4.demo.review.ReviewService;
import com.lab4.demo.review.model.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ReportServiceFactory reportServiceFactory;
    private final ReviewService reviewService;

    @GetMapping
    public List<ItemDTO> allItems() {
        return itemService.findAll();
    }

    @GetMapping(FILTERED)
    public Page<ItemDTO> filteredItems(@ModelAttribute("filter") ItemFilterRequestDto filter, @PageableDefault(sort
            = {"name"}) Pageable pageable) {
        return itemService.findAllFiltered(filter, pageable);
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

    @GetMapping(ENTITY + REVIEWS)
    public Set<ReviewDTO> reviewsForItem(@PathVariable Long id) {
        return reviewService.getReviewsForItem(id);
    }

    @GetMapping(ENTITY)
    public ItemDTO getItem(@PathVariable Long id) {
        return itemService.get(id);
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) {
        return reportServiceFactory.getReportService(type).export();
    }
}
