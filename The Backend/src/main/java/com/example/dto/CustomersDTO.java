package com.example.dto;


public class CustomersDTO {
    private String customerCode;
    private String customerName;
    private String customerInn;
    private String customerKpp;
    private String customerLegalAddress;
    private String customerPostalAddress;
    private String customerEmail;
    private String customerCodeMain;
    private Boolean isOrganization;
    private Boolean isPerson;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        validateField(customerCode, "Customer code is required.");
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        validateField(customerName, "Customer name is required.");
        this.customerName = customerName;
    }

    public String getCustomerInn() {
        return customerInn;
    }

    public void setCustomerInn(String customerInn) {
        validateField(customerInn, "Customer INN is required.");
        this.customerInn = customerInn;
    }

    public String getCustomerKpp() {
        return customerKpp;
    }

    public void setCustomerKpp(String customerKpp) {
        validateField(customerKpp, "Customer KPP is required.");
        this.customerKpp = customerKpp;
    }

    public String getCustomerLegalAddress() {
        return customerLegalAddress;
    }

    public void setCustomerLegalAddress(String customerLegalAddress) {
        validateField(customerLegalAddress, "Customer legal address is required.");
        this.customerLegalAddress = customerLegalAddress;
    }

    public String getCustomerPostalAddress() {
        return customerPostalAddress;
    }

    public void setCustomerPostalAddress(String customerPostalAddress) {
        validateField(customerPostalAddress, "Customer postal address is required.");
        this.customerPostalAddress = customerPostalAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        validateField(customerEmail, "Customer email is required.");
        this.customerEmail = customerEmail;
    }

    public String getCustomerCodeMain() {
        return customerCodeMain;
    }

    public void setCustomerCodeMain(String customerCodeMain) {
        validateField(customerCodeMain, "Customer email is required.");
        this.customerCodeMain = customerCodeMain;
    }

    public Boolean getIsOrganization() {
        return isOrganization;
    }

    public void setIsOrganization(Boolean isOrganization) {
        this.isOrganization = isOrganization;
        validateTypeConsistency();
    }

    public Boolean getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(Boolean isPerson) {
        this.isPerson = isPerson;
        validateTypeConsistency();
    }


    private void validateField(String field, String errorMessage) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateTypeConsistency() {
        if (isOrganization == null && isPerson == null) {
            throw new IllegalArgumentException("At least one type (organization or person) must be specified.");
        }
        if (Boolean.TRUE.equals(isOrganization) && Boolean.TRUE.equals(isPerson)) {
            throw new IllegalArgumentException("A customer cannot be both organization and person at the same time.");
        }
    }
}
