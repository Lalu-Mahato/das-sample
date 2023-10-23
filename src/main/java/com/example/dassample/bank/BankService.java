package com.example.dassample.bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dassample.bank.dto.AddBankDTO;
import com.example.dassample.bank.entity.Bank;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public ResponseEntity<Object> findAll() {
        List<Bank> banks = bankRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(banks);
    }

    public ResponseEntity<Object> create(AddBankDTO addBankDTO) {
        Bank bank = new Bank();
        bank.setCode(addBankDTO.getCode());
        bank.setName(addBankDTO.getName());
        bank.setAddress(addBankDTO.getAddress());
        bank.setCity(addBankDTO.getCity());
        bank.setPincode(addBankDTO.getPincode());

        Bank response = bankRepository.save(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
