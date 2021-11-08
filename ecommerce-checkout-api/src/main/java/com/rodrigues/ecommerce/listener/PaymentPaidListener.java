package com.rodrigues.ecommerce.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.rodrigues.ecommerce.checkout.event.PaymentCreatedEvent;
import com.rodrigues.ecommerce.entity.Checkout;
import com.rodrigues.ecommerce.entity.Checkout.Status;
import com.rodrigues.ecommerce.repository.CheckoutRepository;
import com.rodrigues.ecommerce.streaming.PaymentPaidSink;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

	private CheckoutRepository checkoutRepository;

	@StreamListener(PaymentPaidSink.INPUT)
	public void handler(PaymentCreatedEvent event) {
		final Checkout checkout = checkoutRepository.findByCode(event.getCheckoutCode()).orElseThrow();
		checkout.setStatus(Status.APPROVED);
		checkoutRepository.save(checkout);
	}
}
