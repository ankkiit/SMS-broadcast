//package in.main.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//
//@Service
//public class TwilioService {
//	 @Value("${twilio.account.sid}")
//	    private String accountSid;
//
//	    @Value("${twilio.auth.token}")
//	    private String authToken;
//
//	    @Value("${twilio.phone.number}")
//	    private String fromPhoneNumber;
//
//	    public void sendSms(String toPhoneNumber, String messageBody) {
//	        // Initialize Twilio SDK with credentials
//	        Twilio.init(accountSid, authToken);
//System.out.println(authToken);
//System.out.println(accountSid);
//	      
//	        Message.creator(
//	               new PhoneNumber(toPhoneNumber),   
//	        	    // new PhoneNumber("+919034286092"), 
//	                new PhoneNumber(fromPhoneNumber), 
//	                messageBody    
//	               // "hii"
//	        ).create();
//	    }
//
//}
