package com.andrew.rental.controller.rest;

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
    List<BankAccount> getBankAccountsForUser(@PathVariable("id") UUID id) {
        return bankAccountService.getBankAccountByUserId(id);
    }

    @GetMapping("{id}")
    BankAccount getBankAccount(@PathVariable("id") UUID id) throws NotFoundException {
        return bankAccountService.getBankAccountById(id);
    }

    @DeleteMapping("{id}")
    void deleteBankAccount(@PathVariable("id") UUID id) throws NotFoundException {
        bankAccountService.deleteBankAccountById(id);
    }

    @PostMapping
    void addBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.addBankAccount(bankAccount);
    }
}
