package com.example.booking.payment;

import org.springframework.web.client.RestTemplate;

import com.example.booking.exception.PaymentException;


public class PaymentGatewayCallable {
	public static String callPaymentService(double amount)
	{
	    final String uri = "http://localhost:8080/pay?price="+amount;
	    String result ="";
	    try {
	    	RestTemplate restTemplate = new RestTemplate();
		    result = restTemplate.getForObject(uri, String.class);
		    if(result.isBlank())
		    {
		    	throw new PaymentException("Payment process failed , payment service would be down");
		    }
	    }catch(Exception ex) {
	    	throw new PaymentException("Payment process failed"+ex.getMessage());
	    }	    

	    return result;
	}
}
