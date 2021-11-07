package com.rodrigues.ecommerce.service;

import java.util.Optional;

import com.rodrigues.ecommerce.dto.CheckoutDTO;
import com.rodrigues.ecommerce.entity.Checkout;

public interface CheckoutService {

	Optional<Checkout> create(CheckoutDTO checkoutDTO);
}
