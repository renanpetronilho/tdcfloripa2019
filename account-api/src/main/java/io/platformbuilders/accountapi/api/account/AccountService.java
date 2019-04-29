package io.platformbuilders.accountapi.api.account;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public ResponseEntity<?> createAccount(final AccountRequest request, final UriComponentsBuilder builder) {

        log.info("[AccountService][createAccount][request = {}]", request);
        if (repository.findByCpf(request.getCpf()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Account account = request.toAccount();
        account.setBalance(BigDecimal.valueOf(0));
        account = repository.save(account);
        log.info("[AccountService][createAccount][accountId = {}]", account.getId());
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(account.getId()).toUri()).build();
    }

    public ResponseEntity<?> updateAccount(final AccountRequest request, final String id) {

        log.info("[AccountService][updateAccount][request = {}][id = {}]", request, id);
        Optional<Account> optional = repository.findById(id);

        if (optional.isPresent()) {
            Account account = optional.get();
            account.setName(request.getName());
            account.setStatus(request.getStatus());

            repository.save(account);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAccountById(final String id) {
        log.info("[AccountService][findAccountById][id = {}]",id);
        Optional<Account> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(AccountResponse.toResponse(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> patchAccount(final AccountRequest request, final String id) {
        log.info("[AccountService][patchAccount][request = {}][id = {}]", request, id);

        Optional<Account> optional = repository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            if (Objects.nonNull(request.getName())) {
                account.setName(request.getName());
            }

            if (Objects.nonNull(request.getStatus())) {
                account.setStatus(request.getStatus());
            }
            repository.save(account);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();


    }

    public ResponseEntity<?> depositToAccount(final TransactionRequest request, final String id) {
        log.info("[AccountService][depositToAccount][request = {}][id = {}]", request, id);
        Optional<Account> optional = repository.findById(id);

        if (optional.isPresent()) {
            Account account = optional.get();
            account.setBalance(account.getBalance().add(request.getAmount()));
            repository.save(account);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> withdrawToAccount(final TransactionRequest request, final String id) {
        log.info("[AccountService][withdrawToAccount][request = {}][id = {}]", request, id);
        Optional<Account> optional = repository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            account.setBalance(account.getBalance().subtract(request.getAmount()));
            repository.save(account);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
