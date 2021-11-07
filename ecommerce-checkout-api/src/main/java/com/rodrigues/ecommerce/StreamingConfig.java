package com.rodrigues.ecommerce;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.rodrigues.ecommerce.streaming.CheckoutCreatedSource;

@Configuration
@EnableBinding(value = CheckoutCreatedSource.class)
public class StreamingConfig {

}
