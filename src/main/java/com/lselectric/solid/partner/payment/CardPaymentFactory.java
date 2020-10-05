package com.lselectric.solid.partner.payment;

import com.lselectric.solid.partner.shinhan.ShinhanCardPaymentService;
import com.lselectric.solid.partner.woori.WooriCardPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CardPaymentFactory {

    private final WooriCardPaymentService wooriCardPaymentService;
    private final ShinhanCardPaymentService shinhanCardPaymentService;

    public CardPaymentService getType(CardType type) {

        final CardPaymentService cardPaymentService;
        switch (type) {
            case WOORI:
                cardPaymentService = wooriCardPaymentService;
                break;
            case SHINHAN:
                cardPaymentService = shinhanCardPaymentService;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return cardPaymentService;
    }
}
