package io.platformbuilders.accountapi.api.account;

import io.platformbuilders.accountapi.api.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {

    private String id;

    private String name;

    private String cpf;

    private Status status;

    private BigDecimal balance;

    public static AccountResponse toResponse(Account account){
        return AccountResponse.builder()
                .name(account.getName()).cpf(account.getCpf())
                .balance(account.getBalance())
                .id(account.getId()).status(account.getStatus()).build();
    }
}
