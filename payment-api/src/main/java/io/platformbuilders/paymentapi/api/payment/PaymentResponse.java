package io.platformbuilders.paymentapi.api.payment;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PaymentResponse implements Serializable {

    private String transactionId;
}
