package io.platformbuilders.paymentapi.api.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TDC_PAYMENT")
public class Payment implements Serializable {

    private String id;

    private Date dateTransaction;

    private String accountId;

    private String barcode;

    private Double amount;
}
