package com.andrew.rental.controller.rest;

import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.service.PaymentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    void payment(@RequestBody PaymentDTO paymentDTO) throws NotFoundException, IllegalAccessException {
        paymentService.transaction(paymentDTO);
    }
}
