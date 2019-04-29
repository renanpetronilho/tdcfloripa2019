package io.platformbuilders.accountapi.api.account;

import io.platformbuilders.accountapi.api.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest implements Serializable {

    private String name;
    private String cpf;
    private Status status;


    public Account toAccount(){
        return Account.builder()
                .name(this.name).cpf(this.cpf).status(this.status).build();
    }
}
