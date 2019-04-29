package io.platformbuilders.paymentapi.api.payment;

import io.platformbuilders.paymentapi.exception.ErrorInfo;
import io.platformbuilders.paymentapi.repository.account.Account;
import io.platformbuilders.paymentapi.repository.account.AccountRepository;
import io.platformbuilders.paymentapi.repository.account.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository repository;

    public ResponseEntity<?> pay(final PaymentRequest request) {
        log.info("[PaymentService][pay][request = {}]", request);

        ResponseEntity<Account> origin = accountRepository.findById(request.getAccountId());
        if (origin.getStatusCode().isError()) {
            log.error("[PaymentService][pay][origin not found][accountId= {}]", request.getAccountId());
            return new ResponseEntity<>(ErrorInfo.builder().message("origin not found"), HttpStatus.NOT_FOUND);
        }
        ResponseEntity<?> response = accountRepository.withdraw(TransactionRequest.builder().amount(request.getAmount()).build(), request.getAccountId());
        if (response.getStatusCode().is2xxSuccessful()) {
            Payment payment = request.toPayment();
            payment.setDateTransaction(new Date());
            repository.save(payment);
            return ResponseEntity.ok(PaymentResponse.builder().transactionId(payment.getId()).build());
        } else {
            log.error("[PaymentService][pay][problem to pay][request= {}]", request);
            return new ResponseEntity<>(ErrorInfo.builder().message("Problem to Pay"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
