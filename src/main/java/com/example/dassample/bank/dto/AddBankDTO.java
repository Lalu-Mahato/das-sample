package com.example.dassample.bank.dto;

import lombok.Data;

@Data
public class AddBankDTO {
    private Integer code;
    private String name;
    private String address;
    private String city;
    private Integer pincode;
}
