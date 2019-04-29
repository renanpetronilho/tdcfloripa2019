package io.platformbuilders.accountapi.api.account;

import io.platformbuilders.accountapi.api.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "TDC_ACCOUNT")
public class Account implements Serializable {

    @Id
    private String id;

    private String name;

    private String cpf;

    private Status status;

    private BigDecimal balance;
}
