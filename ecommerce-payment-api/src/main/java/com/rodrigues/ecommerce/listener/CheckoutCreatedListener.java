package com.rodrigues.ecommerce.listener;

import java.util.UUID;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.rodrigues.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.rodrigues.ecommerce.checkout.event.PaymentCreatedEvent;
import com.rodrigues.ecommerce.streaming.CheckoutProcessor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckoutCreatedListener {

	private final CheckoutProcessor checkoutProcessor;

	@StreamListener(CheckoutProcessor.INPUT)
	public void handler(CheckoutCreatedEvent event) {

		// processa pagamento
		// salvar os dados do pagamento
		// envia o evento do pagamento processado

		final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
				.setCheckoutCode(event.getCheckoutCode()).setPaymentCode(UUID.randomUUID().toString()).setCheckoutStatus(event.getStatus()).build();
		checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
	}
}
