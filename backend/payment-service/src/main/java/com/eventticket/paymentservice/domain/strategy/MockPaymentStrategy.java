package com.eventticket.paymentservice.domain.strategy;

import com.eventticket.paymentservice.domain.model.PaymentRequest;
import com.eventticket.paymentservice.domain.model.PaymentResponse;
import com.eventticket.paymentservice.domain.model.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class MockPaymentStrategy implements PaymentStrategy {
    
    @Override
    public String getProviderName() {
        return "MOCK";
    }
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        log.info("Processing mock payment for order: {}", request.getOrderId());
        
        // Simulate payment processing delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate 95% success rate
        boolean isSuccess = Math.random() > 0.05;
        
        if (isSuccess) {
            return PaymentResponse.builder()
                    .transactionId("MOCK_" + UUID.randomUUID().toString().replace("-", ""))
                    .status(PaymentStatus.COMPLETED)
                    .amount(request.getAmount())
                    .currency(request.getCurrency())
                    .paymentDate(LocalDateTime.now())
                    .message("Payment processed successfully")
                    .build();
        } else {
            return PaymentResponse.builder()
                    .transactionId("MOCK_FAILED_" + UUID.randomUUID().toString().replace("-", ""))
                    .status(PaymentStatus.FAILED)
                    .amount(request.getAmount())
                    .currency(request.getCurrency())
                    .paymentDate(LocalDateTime.now())
                    .message("Payment failed - insufficient funds")
                    .build();
        }
    }
    
    @Override
    public PaymentResponse refundPayment(String transactionId, double amount) {
        log.info("Processing mock refund for transaction: {}", transactionId);
        
        // Simulate refund processing delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return PaymentResponse.builder()
                .transactionId("REFUND_" + transactionId)
                .status(PaymentStatus.REFUNDED)
                .amount(amount)
                .currency("USD")
                .paymentDate(LocalDateTime.now())
                .message("Refund processed successfully")
                .build();
    }
    
    @Override
    public boolean isAvailable() {
        return true; // Mock is always available
    }
} 