package io.platformbuilders.paymentapi.repository.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Lazy
@Service
public class AccountRepository {


    private RestTemplate restTemplate;

    private String host;

    @Autowired
    public AccountRepository(@Value("${app.account.host}") String host, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.host = host;
    }

    public ResponseEntity<Account> findById(final String accountId) {
        UriComponents uri = UriComponentsBuilder.fromUri(URI.create(host + accountId)).build();
        return restTemplate.getForEntity(uri.toUriString(), Account.class);
    }

    public ResponseEntity<Void> deposit(TransactionRequest transactionRequest, String accountId) {
        UriComponents uri = UriComponentsBuilder.fromUri(URI.create(host + accountId + "/deposits")).build();
        return restTemplate.postForEntity(uri.toUriString(), transactionRequest, Void.class);
    }

    public ResponseEntity<Void> withdraw(TransactionRequest transactionRequest, String accountId) {

        UriComponents uri = UriComponentsBuilder.fromUri(URI.create(host + accountId + "/withdrawals")).build();
        return restTemplate.postForEntity(uri.toUriString(), transactionRequest, Void.class);
    }
}
