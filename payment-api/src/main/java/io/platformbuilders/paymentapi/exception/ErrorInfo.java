package io.platformbuilders.paymentapi.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ErrorInfo implements Serializable {

    private String message;
}
