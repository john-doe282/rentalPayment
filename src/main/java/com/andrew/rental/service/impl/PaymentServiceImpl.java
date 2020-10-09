package com.andrew.rental.service.impl;

import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.PaymentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public void transaction(PaymentDTO paymentDTO) throws NotFoundException {
        int amount = paymentDTO.getAmount();
        bankAccountService.increaseBalanceById(paymentDTO.getSenderId(), -amount);
        bankAccountService.increaseBalanceById(paymentDTO.getReceiverId(), amount);
    }

}
