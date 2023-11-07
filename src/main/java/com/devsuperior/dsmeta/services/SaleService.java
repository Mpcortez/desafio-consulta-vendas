package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.mapper.SaleReportDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getReport(String name, String minDate, String maxDate, Pageable pageable) {
		var handledMaxDate = handleMaxDate(maxDate);
		var handledMinDate = handleMinDate(minDate, handledMaxDate);

		return repository.generateReport(name, handledMinDate, handledMaxDate, pageable).map(SaleReportDTOMapper::mapper);
	}

	public List<SaleSummaryDTO> getSummary(String minDate, String maxDate) {
		var handledMaxDate = handleMaxDate(maxDate);
		var handledMinDate = handleMinDate(minDate, handledMaxDate);

		return repository.generateSummary(handledMinDate, handledMaxDate);
	}

	private LocalDate handleMaxDate(String maxDate) {
		return Objects.nonNull(maxDate) ? LocalDate.parse(maxDate) : LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	}

	private LocalDate handleMinDate(String minDate, LocalDate maxDate) {
		return Objects.nonNull(minDate) ? LocalDate.parse(minDate) : maxDate.minusYears(1L);
	}
}
