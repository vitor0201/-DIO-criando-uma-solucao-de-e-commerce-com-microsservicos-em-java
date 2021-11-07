package com.rodrigues.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigues.ecommerce.dto.CheckoutDTO;
import com.rodrigues.ecommerce.dto.CheckoutResponse;
import com.rodrigues.ecommerce.entity.Checkout;
import com.rodrigues.ecommerce.service.CheckoutService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {

	private final CheckoutService checkoutService;

	@PostMapping
	public ResponseEntity<CheckoutResponse> create(@RequestBody CheckoutDTO checkoutRequest) {
		final Checkout checkout = checkoutService.create(checkoutRequest).orElseThrow();
		final CheckoutResponse checkoutResponse = CheckoutResponse.builder().code(checkout.getCode()).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
	}

}
