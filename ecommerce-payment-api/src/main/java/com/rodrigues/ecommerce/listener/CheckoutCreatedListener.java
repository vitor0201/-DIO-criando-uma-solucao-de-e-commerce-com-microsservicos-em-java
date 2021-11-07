package com.rodrigues.ecommerce.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.rodrigues.ecommerce.streaming.CheckoutProcessor;

@Component
public class CheckoutCreatedListener {

	@StreamListener(CheckoutProcessor.INPUT)
	public void handler(CheckoutCreatedListener checkoutCreatedListener) {

		// processa pagamento
		// salvar os dados do pagamento
		// envia o evendo do pagamentoprocessado

	}
}
