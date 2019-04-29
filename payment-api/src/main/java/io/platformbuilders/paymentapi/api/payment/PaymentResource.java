package io.platformbuilders.paymentapi.api.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @PostMapping
    public ResponseEntity<?> pay(@RequestBody final PaymentRequest request) {
        return service.pay(request);
    }
}
