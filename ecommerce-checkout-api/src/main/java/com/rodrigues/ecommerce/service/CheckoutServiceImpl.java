package com.rodrigues.ecommerce.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.rodrigues.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.rodrigues.ecommerce.dto.CheckoutDTO;
import com.rodrigues.ecommerce.entity.Checkout;
import com.rodrigues.ecommerce.repository.CheckoutRepository;
import com.rodrigues.ecommerce.streaming.CheckoutCreatedSource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

	private final CheckoutRepository checkoutRepository;
	private final CheckoutCreatedSource checkoutCreatedSource;

	@Override
	public Optional<Checkout> create(CheckoutDTO checkoutDTO) {
		final Checkout checkout = Checkout.builder().code(UUID.randomUUID().toString()).status(Checkout.Status.CREATED)
				.build();
		final Checkout entity = checkoutRepository.save(checkout);
		final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
				.setCheckoutCode(entity.getCode()).setStatus(entity.getStatus().name()).build();

		checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());
		return Optional.of(entity);
	}
}
