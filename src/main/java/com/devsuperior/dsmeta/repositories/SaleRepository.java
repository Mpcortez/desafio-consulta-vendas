package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(name = "Sale.generateReport", countName = "Sale.generateReportCount")
    Page<Sale> generateReport(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);

    @Query(name = "Sale.generateSummary")
    List<SaleSummaryDTO> generateSummary(LocalDate minDate, LocalDate maxDate);

}
