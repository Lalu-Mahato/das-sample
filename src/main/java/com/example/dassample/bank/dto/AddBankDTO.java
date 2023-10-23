package com.example.dassample.bank.dto;

import lombok.Data;

@Data
public class AddBankDTO {
    private Integer branchCode;
    private String branchName;
    private String address;
    private String city;
    private Integer pincode;
}
