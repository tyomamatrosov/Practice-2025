package com.example.service;

import com.example.dto.CustomersDTO;
import jooqdata.tables.Customer;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import java.util.List;


import static jooqdata.tables.Customer.CUSTOMER;

@Service
public class CustomerService {
    private final DSLContext dsl;

    public CustomerService(DSLContext dsl) {
        this.dsl = dsl;
    }


    public void addCustomer(CustomersDTO customerDTO) {
        Customer customer = jooqdata.Tables.CUSTOMER;
        dsl.insertInto(customer)
                .set(customer.CUSTOMER_CODE, customerDTO.getCustomerCode())
                .set(customer.CUSTOMER_NAME, customerDTO.getCustomerName())
                .set(customer.CUSTOMER_INN, customerDTO.getCustomerInn())
                .set(customer.CUSTOMER_KPP, customerDTO.getCustomerKpp())
                .set(customer.CUSTOMER_LEGAL_ADDRESS, customerDTO.getCustomerLegalAddress())
                .set(customer.CUSTOMER_POSTAL_ADDRESS, customerDTO.getCustomerPostalAddress())
                .set(customer.CUSTOMER_EMAIL, customerDTO.getCustomerEmail())
                .set(customer.CUSTOMER_CODE_MAIN, customerDTO.getCustomerCodeMain())
                .set(customer.IS_ORGANIZATION, customerDTO.getIsOrganization())
                .set(customer.IS_PERSON, customerDTO.getIsPerson())
                .execute();
    }


    public List<CustomersDTO> getAllCustomers() {
        return dsl.selectFrom(CUSTOMER)
                .fetch()
                .map(record -> {
                    CustomersDTO dto = new CustomersDTO();
                    dto.setCustomerCode(record.get(CUSTOMER.CUSTOMER_CODE));
                    dto.setCustomerName(record.get(CUSTOMER.CUSTOMER_NAME));
                    dto.setCustomerInn(record.get(CUSTOMER.CUSTOMER_INN));
                    dto.setCustomerKpp(record.get(CUSTOMER.CUSTOMER_KPP));
                    dto.setCustomerLegalAddress(record.get(CUSTOMER.CUSTOMER_LEGAL_ADDRESS));
                    dto.setCustomerPostalAddress(record.get(CUSTOMER.CUSTOMER_POSTAL_ADDRESS));
                    dto.setCustomerEmail(record.get(CUSTOMER.CUSTOMER_EMAIL));
                    dto.setCustomerCodeMain(record.get(CUSTOMER.CUSTOMER_CODE_MAIN));
                    dto.setIsOrganization(record.get(CUSTOMER.IS_ORGANIZATION));
                    dto.setIsPerson(record.get(CUSTOMER.IS_PERSON));
                    return dto;
                });
    }

    public CustomersDTO getCustomerByCodeMain(String customerCodeMain) {
        Customer customer = jooqdata.Tables.CUSTOMER;
        Record record = dsl.select()
                .from(customer)
                .where(customer.CUSTOMER_CODE_MAIN.eq(customerCodeMain))
                .fetchOne();

        if (record == null) {
            return null;
        }

        CustomersDTO dto = new CustomersDTO();
        dto.setCustomerCode(record.get(customer.CUSTOMER_CODE));
        dto.setCustomerName(record.get(customer.CUSTOMER_NAME));
        dto.setCustomerInn(record.get(customer.CUSTOMER_INN));
        dto.setCustomerKpp(record.get(customer.CUSTOMER_KPP));
        dto.setCustomerLegalAddress(record.get(customer.CUSTOMER_LEGAL_ADDRESS));
        dto.setCustomerPostalAddress(record.get(customer.CUSTOMER_POSTAL_ADDRESS));
        dto.setCustomerEmail(record.get(customer.CUSTOMER_EMAIL));
        dto.setCustomerCodeMain(record.get(customer.CUSTOMER_CODE_MAIN));
        dto.setIsOrganization(record.get(customer.IS_ORGANIZATION));
        dto.setIsPerson(record.get(customer.IS_PERSON));

        return dto;
    }



    public void updateCustomerByCodeMain(String customerCodeMain, CustomersDTO customerDTO) {
        Customer customer = jooqdata.Tables.CUSTOMER;
        int updated = dsl.update(customer)
                .set(customer.CUSTOMER_CODE, customerDTO.getCustomerCode())
                .set(customer.CUSTOMER_NAME, customerDTO.getCustomerName())
                .set(customer.CUSTOMER_INN, customerDTO.getCustomerInn())
                .set(customer.CUSTOMER_KPP, customerDTO.getCustomerKpp())
                .set(customer.CUSTOMER_LEGAL_ADDRESS, customerDTO.getCustomerLegalAddress())
                .set(customer.CUSTOMER_POSTAL_ADDRESS, customerDTO.getCustomerPostalAddress())
                .set(customer.CUSTOMER_EMAIL, customerDTO.getCustomerEmail())
                .set(customer.CUSTOMER_CODE_MAIN, customerDTO.getCustomerCodeMain())
                .set(customer.IS_ORGANIZATION, customerDTO.getIsOrganization())
                .set(customer.IS_PERSON, customerDTO.getIsPerson())
                .where(customer.CUSTOMER_CODE_MAIN.eq(customerCodeMain))
                .execute();

        if (updated == 0) {
            throw new IllegalArgumentException("Клиент с CUSTOMER_CODE_MAIN " + customerCodeMain + " не найден.");
        }
    }


    public void deleteCustomerByCodeMain(String customerCodeMain) {
        Customer customer = jooqdata.Tables.CUSTOMER;
        int deleted = dsl.deleteFrom(customer)
                .where(customer.CUSTOMER_CODE_MAIN.eq(customerCodeMain))
                .execute();

        if (deleted == 0) {
            throw new IllegalArgumentException("Клиент с CUSTOMER_CODE_MAIN " + customerCodeMain + " не найден.");
        }
    }
}
