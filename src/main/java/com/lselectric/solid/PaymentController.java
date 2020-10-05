package com.lselectric.solid;

import com.lselectric.solid.partner.payment.CardPaymentDto;
import com.lselectric.solid.partner.payment.CardPaymentFactory;
import com.lselectric.solid.partner.payment.CardPaymentService;
import com.lselectric.solid.partner.shinhan.ShinhanCardPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {

    private CardPaymentFactory cardPaymentFactory;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void pay(@RequestBody CardPaymentDto.PaymentRequest req) {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(req.getType());
        cardPaymentService.pay(req);
    }

    @RequestMapping(value = "/overseas-payment", method = RequestMethod.POST)
    public void payOverseas(@RequestBody CardPaymentDto.PaymentRequest req) {
        final CardPaymentService cardPaymentService = cardPaymentFactory.getType(req.getType());
        if (cardPaymentService instanceof ShinhanCardPaymentService) {
            cardPaymentService.payOverseas(req);
        }
    }
}
