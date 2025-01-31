package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LotDTO {
    private String lotName;
    private String customerCode;
    private BigDecimal price;
    private String currencyCode;
    private String ndsRate;
    private String placeDelivery;
    private LocalDateTime dateDelivery;


    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        validateField(lotName, "Lot name is required.");
        this.lotName = lotName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        validateField(customerCode, "Customer code is required.");
        this.customerCode = customerCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        validateField(price, "Price is required.");
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        validateField(currencyCode, "Currency code is required.");
        this.currencyCode = currencyCode;
    }

    public String getNdsRate() {
        return ndsRate;
    }

    public void setNdsRate(String ndsRate) {
        validateField(ndsRate, "NDS rate is required.");
        this.ndsRate = ndsRate;
    }

    public String getPlaceDelivery() {
        return placeDelivery;
    }

    public void setPlaceDelivery(String placeDelivery) {
        validateField(placeDelivery, "Place of delivery is required.");
        this.placeDelivery = placeDelivery;
    }

    public LocalDateTime getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDateTime dateDelivery) {
        if (dateDelivery == null) {
            throw new IllegalArgumentException("Date delivery is required.");
        }
        this.dateDelivery = dateDelivery;
    }

    private void validateField(Object field, String errorMessage) {
        if (field == null || (field instanceof String && ((String) field).isEmpty())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
