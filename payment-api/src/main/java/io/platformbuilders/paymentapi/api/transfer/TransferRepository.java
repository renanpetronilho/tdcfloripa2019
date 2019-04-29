package io.platformbuilders.paymentapi.api.transfer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransferRepository extends MongoRepository<Transfer, String> {
}
