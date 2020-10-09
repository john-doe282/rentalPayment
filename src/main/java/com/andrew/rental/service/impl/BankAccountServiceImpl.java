package com.andrew.rental.service.impl;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.repository.BankAccountRepository;
import com.andrew.rental.service.BankAccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccountById(UUID id) throws NotFoundException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
        if (bankAccountOptional.isPresent()) {
            return bankAccountOptional.get();
        }

        throw new NotFoundException("Bank account does not exist");
    }

    @Override
    public void deleteBankAccountById(UUID id) throws NotFoundException {
        if (bankAccountRepository.existsById(id)) {
            throw new NotFoundException("Bank account does not exist");
        }

        bankAccountRepository.deleteById(id);
    }

    @Override
    public void increaseBalanceById(UUID userId, int amount) throws NotFoundException {
        BankAccount bankAccount = getBankAccountByUserId(userId).get(0);


//        if (!bankAccountOptional.isPresent()) {
//            throw new NotFoundException("Bank account does not exist");
//        }

        bankAccount.setBalance(bankAccount.getBalance() + amount);

        bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> getBankAccountByUserId(UUID userId) {
        return bankAccountRepository.findBankAccountByUserId(userId);
    }
}
