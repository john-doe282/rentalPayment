package com.andrew.rental.dto;

import com.andrew.rental.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private UUID senderId;
    private UUID receiverId;
    private int amount;

    public static PaymentDTO fromTransactionRequest (TransactionRequest request) {
        return new PaymentDTOBuilder().
                senderId(UUID.fromString(request.getSenderId())).
                receiverId(UUID.fromString(request.getReceiverId())).
                amount((int) request.getAmount()).
                build();
    }
}
