package com.lselectric.solid.partner.payment;

public interface CardPaymentService {

    void pay(CardPaymentDto.PaymentRequest req);

    void payOverseas(CardPaymentDto.PaymentRequest req);

}
