package com.andrew.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PaymentDTO {
    private UUID senderId;
    private UUID receiverId;
    private int amount;
}
