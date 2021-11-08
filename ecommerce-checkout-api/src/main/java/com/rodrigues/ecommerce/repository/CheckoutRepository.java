package com.rodrigues.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigues.ecommerce.entity.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	Optional<Checkout> findByCode(String code);
}
