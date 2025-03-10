package com.realestate.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String makePayment(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount. Amount must be greater than zero.");
        }
        // Simulating payment processing (Replace with actual API integration)
        return "Payment of $" + amount + " is successful!";
    }
}
