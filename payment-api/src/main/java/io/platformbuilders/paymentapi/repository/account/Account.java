package io.platformbuilders.paymentapi.repository.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private String id;

    private String name;

    private Double balance;
}
