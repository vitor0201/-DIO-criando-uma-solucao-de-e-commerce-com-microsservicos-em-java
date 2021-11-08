package com.rodrigues.ecommerce.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.rodrigues.ecommerce.streaming.CheckoutCreatedSource;
import com.rodrigues.ecommerce.streaming.PaymentPaidSink;

@Configuration
@EnableBinding({ CheckoutCreatedSource.class
		, PaymentPaidSink.class 
})
public class StreamingConfig {

}
