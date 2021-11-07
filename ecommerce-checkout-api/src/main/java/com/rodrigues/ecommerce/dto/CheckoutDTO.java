package com.rodrigues.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO implements Serializable {
	@NotEmpty
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String address;
	private String address2;
	private String country;
	private String state;
	private String zip;
	private Boolean sameAddress;
	private Boolean saveInfo;
	private String paymentMethod;
	private String cardName;
	private String cardNumber;
	private String cardExpiration;
	private String cardCvv;
}
