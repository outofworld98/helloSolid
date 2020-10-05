package com.lselectric.solid.partner.shinhan;

import com.lselectric.solid.partner.payment.CardType;
import com.lselectric.solid.partner.payment.CardPaymentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShinhanCardPaymentServiceTest {

    @InjectMocks
    private ShinhanCardPaymentService shinhanCardPaymentService;

    @Mock
    private  ShinhanCardApi shinhanCardApi;

    @Test
    public void pay() {
        final CardPaymentDto.PaymentRequest request = CardPaymentDto.PaymentRequest.builder()
                .cardNumber("card")
                .csv("csv")
                .type(CardType.SHINHAN)
                .build();

        shinhanCardPaymentService.pay(request);

        verify(shinhanCardApi, atLeastOnce()).pay(any());
    }

    @Test
    public void payOverseas() {
        final CardPaymentDto.PaymentRequest request = CardPaymentDto.PaymentRequest.builder()
                .cardNumber("card")
                .csv("csv")
                .type(CardType.SHINHAN)
                .build();

        shinhanCardPaymentService.payOverseas(request);

        verify(shinhanCardApi, atLeastOnce()).pay(any());
    }
}