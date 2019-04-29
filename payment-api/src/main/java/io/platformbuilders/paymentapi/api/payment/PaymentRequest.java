package io.platformbuilders.paymentapi.api.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest implements Serializable {

    private String accountId;

    private String barcode;

    private Double amount;

    public Payment toPayment(){
        return Payment.builder()
                .accountId(this.accountId).amount(this.amount)
                .barcode(this.barcode).build();
    }
}
