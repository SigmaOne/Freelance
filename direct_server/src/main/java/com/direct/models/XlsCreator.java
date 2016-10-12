package com.direct.models;

import com.direct.models.repositories.CustomerDataRepository;
import com.direct.models.repositories.CustomerRepository;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XlsCreator {
    @Autowired private CustomerRepository customerRepository;
    @Autowired private CustomerDataRepository dataRepository;

    public HSSFWorkbook createXlsWithAllEntities() {
        HSSFWorkbook workbook = new HSSFWorkbook();

        writeCustomerSheet(workbook);
        writeCustomerDataSheet(workbook);

        return workbook;
    }

    private void writeCustomerSheet(HSSFWorkbook workbook) {
        HSSFSheet customerSheet = workbook.createSheet("CustomerSheet");
        int rowCounter = 0;

        // write header
        int columnCounter = 0;
        HSSFRow rowHead = customerSheet.createRow(rowCounter++);
        Field[] fields = Customer.class.getDeclaredFields();
        for (Field field: fields)
            rowHead.createCell(columnCounter++).setCellValue(field.getName());

        // write data rows
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.findAll();
        for(Customer customer: Lists.reverse(customers)) { // Todo: move reverse logic to repository
            HSSFRow row = customerSheet.createRow(rowCounter++);

            columnCounter = 0;
            row.createCell(columnCounter++).setCellValue(customer.getId());
            row.createCell(columnCounter++).setCellValue(new SimpleDateFormat("MM/dd/yyyy").format(customer.getCreationDate()));
            row.createCell(columnCounter++).setCellValue(customer.getName());
        }
    }
    private void writeCustomerDataSheet(HSSFWorkbook workbook) {
        HSSFSheet customerSheet = workbook.createSheet("CustomerDataSheet");
        int rowCounter = 0;

        // write header
        int columnCounter = 0;
        HSSFRow rowHead = customerSheet.createRow(rowCounter++);
        Field[] fields = CustomerData.class.getDeclaredFields();
        for (Field field: fields)
            rowHead.createCell(columnCounter++).setCellValue(field.getName());

        // write data rows
        ArrayList<CustomerData> dataList = (ArrayList<CustomerData>) dataRepository.findAll();
        for(CustomerData data: Lists.reverse(dataList)) {   // Todo: moce reverse logic to repository
            HSSFRow row = customerSheet.createRow(rowCounter++);

            columnCounter = 0;
            row.createCell(columnCounter++).setCellValue(data.getId());
            row.createCell(columnCounter++).setCellValue(new SimpleDateFormat("MM/dd/yyyy").format(data.getCreationDate()));
            row.createCell(columnCounter++).setCellValue(data.getPhrase());
            row.createCell(columnCounter++).setCellValue(data.getWebsite());
            row.createCell(columnCounter++).setCellValue(data.getRegions());
            row.createCell(columnCounter++).setCellValue(data.getAveragePrice());
            row.createCell(columnCounter++).setCellValue(data.getMarginality());
            row.createCell(columnCounter++).setCellValue(data.getCloseConversation());
            row.createCell(columnCounter++).setCellValue(data.getWebsiteConversation());
            row.createCell(columnCounter++).setCellValue(data.getPlannedProfit());
        }
    }
}
