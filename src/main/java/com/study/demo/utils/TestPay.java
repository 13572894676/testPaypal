package com.study.demo.utils;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;

public class TestPay {

	public static void main(String[] args) {
		
		String clientId = "Aa3xoCbBrfQBxqqZFPMVmRbSDFU4iE8kGp_SyC6TL5Jgxfq0aT5sZP2iVHFAuQdFDTNSMro7d3i66vx2";
		String clientSecret = "EIqH6tR_toHXaxshzlopQz9QK2kSQEzCvahyuoiNneVLAXkNJ3dnMfx3mGLrhHSb9tKRh48OPV4RBdVR";
//		String clientId = "Ac6NI33M6XmGSjplWtytzv3ic9YVwvpmXbPMes17QsLyxjHg6bSfl2KVJ5vHfB3NtRjFmiSCyRb72icH";
//		String clientSecret = "EOkr6B3MXjGdUp3xAqJ89EoLHDLHjZcCqjjwz7cVq7ceuwz0tr1XriUHhg-zGpBtBUlL8RbyjRa2jWaD";

		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal("10.00");

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("https://example.com/cancel");
		redirectUrls.setReturnUrl("https://example.com/return");
		payment.setRedirectUrls(redirectUrls);
		
		try {
		    APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
		    Payment createdPayment = payment.create(apiContext);
		    // For debug purposes only: System.out.println(createdPayment.toString());
		    System.out.println(createdPayment.toString());
		} catch (PayPalRESTException e) {
		    // Handle errors
		} catch (Exception ex) {
		    // Handle errors
		}

	}

}
