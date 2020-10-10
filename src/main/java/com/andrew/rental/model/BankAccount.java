package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public final class BankAccount {
    @Id
    @GeneratedValue
    private UUID id;

    private String iban;
    private int balance;

    @NonNull
    @Column(name = "user_id")
    private UUID userId;
}