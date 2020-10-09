package com.andrew.rental.service;

import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.model.BankAccount;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    void transaction(PaymentDTO paymentDTO) throws IllegalAccessException, NotFoundException;
}
