package com.example.dassample.upload;

import java.io.File;
import java.io.FileInputStream;
import java.lang.*;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dassample.bank.BankRepository;
import com.example.dassample.bank.entity.Bank;

@Service
public class UploadService {

    @Autowired
    private BankRepository bankRepository;

    public ResponseEntity<Object> upload() {
        try {
            String filePath = "src/main/resources/data/sample.xlsx";

            File file = new File(filePath);
            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }

            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = xssfWorkbook.getSheet("Sheet1");

            // AddBankDTO bankDTO = new AddBankDTO();
            // bankDTO.setBranchCode((int) row.getCell(31).getNumericCellValue());
            // bankDTO.setBranchName((String) row.getCell(32).getStringCellValue());
            // System.out.println(bankDTO);
            // Bank data = bankRepository.save(bankDTO);
            // }

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Row row = worksheet.getRow(i);

                Bank bank;
                int branchCode = (int) row.getCell(31).getNumericCellValue();
                Optional<Bank> optionalBank = bankRepository.findByBranchCode(branchCode);
                if (optionalBank.isPresent()) {
                    bank = optionalBank.get();
                } else {
                    Bank newBank = new Bank();
                    newBank.setBranchCode(branchCode);
                    newBank.setBranchName((String) row.getCell(32).getStringCellValue());
                    bank = bankRepository.save(newBank);
                }
                System.out.println(bank);
            }

            // // Create a new Excel workbook
            // Workbook workbook = new XSSFWorkbook();
            // CreationHelper createHelper = workbook.getCreationHelper();

            // // Create a new sheet
            // Sheet sheet = workbook.createSheet("Customers");

            // // Create a header row
            // Row headerRow = sheet.createRow(0);
            // headerRow.createCell(0).setCellValue("ID");
            // headerRow.createCell(1).setCellValue("Name");
            // headerRow.createCell(2).setCellValue("Email");
            // headerRow.createCell(3).setCellValue("Mobile Number");

            xssfWorkbook.close();
            // fileInputStream.close();
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}
