package com.lselectric.solid.partner.woori;

import com.lselectric.solid.partner.payment.CardPaymentDto;
import com.lselectric.solid.partner.payment.CardType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WooriCardPaymentServiceTest {

    @InjectMocks
    private WooriCardPaymentService wooriCardPaymentService;

    @Mock
    private WooriCardApi wooriCardApi;

    @Test
    public void pay() {

        final CardPaymentDto.PaymentRequest request = CardPaymentDto.PaymentRequest.builder()
                .cardNumber("card")
                .csv("csv")
                .type(CardType.WOORI)
                .build();

        wooriCardPaymentService.pay(request);

        verify(wooriCardApi, atLeastOnce()).pay(any());
    }

    @Test
    public void payOverseas() {

        final CardPaymentDto.PaymentRequest request = CardPaymentDto.PaymentRequest.builder()
                .cardNumber("card")
                .csv("csv")
                .type(CardType.WOORI)
                .build();

        wooriCardPaymentService.payOverseas(request);


    }
}