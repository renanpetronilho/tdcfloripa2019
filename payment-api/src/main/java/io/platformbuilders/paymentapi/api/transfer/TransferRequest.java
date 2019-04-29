package io.platformbuilders.paymentapi.api.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest implements Serializable {

    private String originAccount;

    private String destinationAccount;

    private Double amount;

    public Transfer toTransfer(){
        return Transfer.builder()
                .destinationAccount(this.destinationAccount)
                .originAccount(this.originAccount)
                .amount(amount).build();
    }
}
