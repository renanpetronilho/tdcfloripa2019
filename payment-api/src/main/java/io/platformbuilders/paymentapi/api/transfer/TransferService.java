package io.platformbuilders.paymentapi.api.transfer;

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

@Service
@Slf4j
public class TransferService {

    @Autowired
    private TransferRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<?> transfer(final TransferRequest request) {
        log.info("[TransferService][transfer][request= {}]", request);

        ResponseEntity<Account> origin = accountRepository.findById(request.getOriginAccount());
        ResponseEntity<Account> destination = accountRepository.findById(request.getDestinationAccount());

        if (origin.getStatusCode().isError()) {
            log.info("[TransferService][transfer][origin not found][accountId= {}]", request.getOriginAccount());
            return new ResponseEntity<>(ErrorInfo.builder().message("origin not found"), HttpStatus.NOT_FOUND);
        }

        if (destination.getStatusCode().isError()) {
            log.info("[TransferService][transfer][destination not found][accountId= {}]", request.getDestinationAccount());
            return new ResponseEntity<>(ErrorInfo.builder().message("destination not found"), HttpStatus.NOT_FOUND);
        }

        accountRepository.withdraw(TransactionRequest.builder().amount(request.getAmount()).build(), request.getOriginAccount());
        accountRepository.deposit(TransactionRequest.builder().amount(request.getAmount()).build(), request.getDestinationAccount());

        Transfer transfer = request.toTransfer();
        transfer.setDateTransaction(new Date());

        repository.save(transfer);

        return ResponseEntity.ok(TransferResponse.builder().transactionId(transfer.getId()).build());
    }


}
