package com.andrew.rental.controller.grpc;

import com.andrew.rental.*;
import com.andrew.rental.AddBankAccountRequest;
import com.andrew.rental.BankAccountResponse;
import com.andrew.rental.BankAccountServiceGrpc;
import com.andrew.rental.BankResponse;
import com.andrew.rental.GetBankAccountRequest;
import com.andrew.rental.GetBankAccountResponse;
import com.andrew.rental.TransactionRequest;
import com.andrew.rental.TransactionResponse;
import com.andrew.rental.dto.PaymentDTO;
import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.PaymentService;
import io.grpc.stub.StreamObserver;
import javassist.NotFoundException;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class GrpcPaymentController extends
        BankAccountServiceGrpc.BankAccountServiceImplBase {
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public void addBankAccount(AddBankAccountRequest request, StreamObserver<BankResponse> responseObserver) {
        bankAccountService.addBankAccount(BankAccount.
                fromAddBankAccountRequest(request));
        responseObserver.onNext(BankResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getByUserId(GetBankAccountRequest request, StreamObserver<GetBankAccountResponse> responseObserver) {
        List<BankAccount> bankAccounts = bankAccountService.
                getBankAccountByUserId(UUID.
                        fromString(request.getUserId()));
        List<BankAccountResponse> convertedBankAccounts = bankAccounts.
                stream().
                map(BankAccount::fromBankAccount).
                collect(Collectors.toList());

        GetBankAccountResponse response = GetBankAccountResponse.
                newBuilder().
                addAllBankAccounts(convertedBankAccounts).
                build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void transaction(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) throws NotFoundException, IllegalAccessException {
        PaymentDTO paymentDTO = PaymentDTO.fromTransactionRequest(request);
        paymentService.transaction(paymentDTO);
        responseObserver.onNext(TransactionResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
