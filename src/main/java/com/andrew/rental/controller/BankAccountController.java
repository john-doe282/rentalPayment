package com.andrew.rental.controller;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bank")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/user/{id}")
    List<BankAccount> getBankAccounts(@PathVariable("id") UUID id) {
        return bankAccountService.getBankAccountByUserId(id);
    }

    @DeleteMapping("{id}")
    void deleteBankAccount(@PathVariable("id") UUID id) throws NotFoundException {
        bankAccountService.deleteBankAccountById(id);
    }
}
