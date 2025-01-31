package com.example.service;

import com.example.dto.LotDTO;
import jooqdata.tables.Lot;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import org.jooq.Record;
import org.jooq.Result;
import java.util.stream.Collectors;

import static jooqdata.tables.Lot.LOT;

@Service
public class LotService {
    private final DSLContext dsl;

    public LotService(DSLContext dsl) {
        this.dsl = dsl;
    }


    public void addLot(LotDTO lotDTO) {
        Lot lot = jooqdata.Tables.LOT;

        dsl.insertInto(lot)
                .set(lot.LOT_NAME, lotDTO.getLotName())
                .set(lot.CUSTOMER_CODE, lotDTO.getCustomerCode())
                .set(lot.PRICE, lotDTO.getPrice())  // Прямое использование BigDecimal
                .set(lot.CURRENCY_CODE, lotDTO.getCurrencyCode())
                .set(lot.NDS_RATE, lotDTO.getNdsRate())
                .set(lot.PLACE_DELIVERY, lotDTO.getPlaceDelivery())
                .set(lot.DATE_DELIVERY, lotDTO.getDateDelivery())
                .execute();
    }


    public List<LotDTO> getAllLots() {
        return dsl.selectFrom(LOT)
                .fetch()
                .map(record -> {
                    LotDTO dto = new LotDTO();
                    dto.setLotName(record.get(LOT.LOT_NAME));
                    dto.setCustomerCode(record.get(LOT.CUSTOMER_CODE));
                    dto.setPrice(BigDecimal.valueOf(record.get(LOT.PRICE).doubleValue()));  // Конвертация BigDecimal в double
                    dto.setCurrencyCode(record.get(LOT.CURRENCY_CODE));
                    dto.setNdsRate(record.get(LOT.NDS_RATE));
                    dto.setPlaceDelivery(record.get(LOT.PLACE_DELIVERY));
                    dto.setDateDelivery(record.get(LOT.DATE_DELIVERY));
                    return dto;
                });
    }


    public List<LotDTO> getLotsByName(String lotName) {
        Lot lot = jooqdata.Tables.LOT;

        Result<Record> records = dsl.select()
                .from(lot)
                .where(lot.LOT_NAME.eq(lotName))
                .fetch();

        return records.stream().map(record -> {
            LotDTO dto = new LotDTO();
            dto.setLotName(record.get(lot.LOT_NAME));
            dto.setCustomerCode(record.get(lot.CUSTOMER_CODE));
            dto.setPrice(BigDecimal.valueOf(record.get(LOT.PRICE).doubleValue()));  // Преобразование в BigDecimal
            dto.setCurrencyCode(record.get(lot.CURRENCY_CODE));
            dto.setNdsRate(record.get(lot.NDS_RATE));
            dto.setPlaceDelivery(record.get(lot.PLACE_DELIVERY));
            dto.setDateDelivery(record.get(lot.DATE_DELIVERY));
            return dto;
        }).collect(Collectors.toList());
    }


    public void updateLotByName(String lotName, LotDTO lotDTO) {
        Lot lot = jooqdata.Tables.LOT;

        int updated = dsl.update(lot)
                .set(lot.LOT_NAME, lotDTO.getLotName())
                .set(lot.CUSTOMER_CODE, lotDTO.getCustomerCode())
                .set(lot.PRICE, lotDTO.getPrice())
                .set(lot.CURRENCY_CODE, lotDTO.getCurrencyCode())
                .set(lot.NDS_RATE, lotDTO.getNdsRate())
                .set(lot.PLACE_DELIVERY, lotDTO.getPlaceDelivery())
                .set(lot.DATE_DELIVERY, lotDTO.getDateDelivery())
                .where(lot.LOT_NAME.eq(lotName))
                .execute();

        if (updated == 0) {
            throw new IllegalArgumentException("Лот с именем " + lotName + " не найден.");
        }
    }


    public void deleteLotByName(String lotName) {
        Lot lot = jooqdata.Tables.LOT;

        int deleted = dsl.deleteFrom(lot)
                .where(lot.LOT_NAME.eq(lotName))
                .execute();

        if (deleted == 0) {
            throw new IllegalArgumentException("Лот с именем " + lotName + " не найден.");
        }
    }
}
