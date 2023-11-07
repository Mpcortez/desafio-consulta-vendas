package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public Page<SaleReportDTO> getReport(@RequestParam(value = "name", defaultValue = "") String name,
                                         @RequestParam(value = "minDate", required = false) String minDate,
                                         @RequestParam(value = "maxDate", required = false) String maxDate,
                                         Pageable pageable) {
        return service.getReport(name, minDate, maxDate, pageable);
    }

    @GetMapping(value = "/summary")
    public List<SaleSummaryDTO> getSummary(@RequestParam(value = "minDate", required = false) String minDate,
                                           @RequestParam(value = "maxDate", required = false) String maxDate) {
        return service.getSummary(minDate, maxDate);
    }
}
