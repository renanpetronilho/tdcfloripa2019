package io.platformbuilders.accountapi.api.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/accounts")
public class AccountResource {

    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody final AccountRequest request, final UriComponentsBuilder builder) {
        return service.createAccount(request, builder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@RequestBody final AccountRequest request, @PathVariable final String id) {
        return service.updateAccount(request, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        return service.findAccountById(id);
    }

    @PostMapping("/{id}/deposits")
    public ResponseEntity<?> deposit(@RequestBody final TransactionRequest request, @PathVariable final String id){
        return service.depositToAccount(request, id);
    }

    @PostMapping("/{id}/withdrawals")
    public ResponseEntity<?> withdraw(@RequestBody final TransactionRequest request, @PathVariable final String id){
        return service.withdrawToAccount(request, id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartialAccount(@RequestBody final AccountRequest request, @PathVariable final String id){
        return service.patchAccount(request, id);
    }
}
