package io.platformbuilders.paymentapi.api.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TDC_TRANSFER")
public class Transfer implements Serializable {

    @Id
    private String id;

    private String originAccount;

    private String destinationAccount;

    private Double amount;

    private Date dateTransaction;
}
