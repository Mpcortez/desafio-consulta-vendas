package com.devsuperior.dsmeta.mapper;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;

public class SaleReportDTOMapper {

    public static SaleReportDTO mapper(Sale sale) {
        return new SaleReportDTO(
                sale.getId(),
                sale.getDate().toString(),
                sale.getAmount(),
                sale.getSeller().getName()
        );
    }
}
