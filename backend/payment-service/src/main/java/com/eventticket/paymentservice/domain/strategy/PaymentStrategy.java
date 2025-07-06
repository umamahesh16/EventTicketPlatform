package com.eventticket.paymentservice.domain.strategy;

import com.eventticket.paymentservice.domain.model.PaymentRequest;
import com.eventticket.paymentservice.domain.model.PaymentResponse;

public interface PaymentStrategy {
    
    String getProviderName();
    
    PaymentResponse processPayment(PaymentRequest request);
    
    PaymentResponse refundPayment(String transactionId, double amount);
    
    boolean isAvailable();
} 