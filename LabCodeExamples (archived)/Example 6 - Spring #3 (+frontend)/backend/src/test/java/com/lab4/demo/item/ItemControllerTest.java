package com.lab4.demo.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab4.demo.item.model.Item;
import com.lab4.demo.report.CSVReportService;
import com.lab4.demo.report.PdfReportService;
import com.lab4.demo.report.ReportServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.lab4.demo.UrlMapping.EXPORT_REPORT;
import static com.lab4.demo.UrlMapping.ITEMS;
import static com.lab4.demo.report.ReportType.CSV;
import static com.lab4.demo.report.ReportType.PDF;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest {

    protected MockMvc mockMvc;

    @InjectMocks
    private ItemController controller;

    @Mock
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ItemController(itemService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allItems() throws Exception {
        List<Item> items = new ArrayList<>();
        int nrItems = 10;
        for (int i = 0; i < nrItems; i++) {
            items.add(Item.builder().name(String.valueOf(i)).build());
        }

        when(itemService.findAll()).thenReturn(items);

        ResultActions response = mockMvc.perform(get(ITEMS));

        String expectedJsonContent = new ObjectMapper().writeValueAsString(items);
        response.andExpect(status().isOk())
                .andExpect(content().json(expectedJsonContent, true));

    }

    @Test
    void exportReport() throws Exception {
        final String csv = "csv";
        final String pdf = "pdf";
        when(itemService.export(CSV)).thenReturn(csv);
        when(itemService.export(PDF)).thenReturn(pdf);

        ResultActions responseCsv = mockMvc.perform(get(ITEMS + EXPORT_REPORT, CSV.name()));
        ResultActions responsePdf = mockMvc.perform(get(ITEMS + EXPORT_REPORT, PDF.name()));

        responseCsv.andExpect(status().isOk())
                .andExpect(content().string(csv));
        responsePdf.andExpect(status().isOk())
                .andExpect(content().string(pdf));
    }
}
