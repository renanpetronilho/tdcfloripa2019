package io.platformbuilders.accountapi.api.account;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface AccountRepository  extends MongoRepository<Account, String> {

    Optional<Account> findByCpf(final String cpf);
}
