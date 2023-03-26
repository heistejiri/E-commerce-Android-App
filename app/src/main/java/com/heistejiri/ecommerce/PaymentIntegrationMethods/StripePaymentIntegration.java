package com.heistejiri.ecommerce.PaymentIntegrationMethods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.heistejiri.ecommerce.R;

public class StripePaymentIntegration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_payment_integration);
    }
}