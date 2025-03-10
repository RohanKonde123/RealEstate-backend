package com.realestate.controller;

import com.realestate.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Allows frontend calls
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<String> makePayment(@RequestParam Double amount) {
        // ✅ Validate input
        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid payment amount");
        }

        String result = paymentService.makePayment(amount);
        return ResponseEntity.ok(result);
    }
}
