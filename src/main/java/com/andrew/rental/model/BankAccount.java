package com.andrew.rental.model;

import com.andrew.rental.AddBankAccountRequest;
import com.andrew.rental.BankAccountResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bank_account")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public final class BankAccount {
    @Id
    @GeneratedValue
    private UUID id;

    private String iban;
    private int balance;

    @NonNull
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private UUID userId;

    public static BankAccount fromAddBankAccountRequest(
            AddBankAccountRequest bankAccountRequest) {
        return new BankAccountBuilder().
                iban(bankAccountRequest.getIban()).
                balance((int) bankAccountRequest.getBalance()).
                userId(UUID.fromString(bankAccountRequest.getUserId())).
                build();
    }

    public BankAccountResponse fromBankAccount () {
        return BankAccountResponse.newBuilder().
                setId(id.toString()).
                setBalance(balance).
                setIban(iban).
                setUserId(userId.toString()).
                build();
    }
}