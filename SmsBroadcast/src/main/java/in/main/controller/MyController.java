package in.main.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.main.service.SMSService;

@Controller
public class MyController {
	@Autowired
	private SMSService smsService;
	
//	@Autowired
//	 private TwilioService twilioService;
	
	@GetMapping("/login")

	public String login() {
		return "login";
	
	}
	@GetMapping("/home")

	public String home() {
		return "home";
	
	}
	
	  @PostMapping("/send-sms")
	
	    public String sendSMS(@RequestParam("phoneNumber") String phoneNumber,
	    		@RequestParam("campaign") String campaign ,@RequestParam("message") String message,@RequestParam( value="scheduleDate",required=false) String scheduleDate,@RequestParam(value="scheduleTime",required=false) String scheduleTime) {
	        
		//  System.out.println(phoneNumber);
		  System.out.println(campaign);
		  
		  LocalDateTime scheduleDateTime;
		  if (scheduleDate.isEmpty() || scheduleTime.isEmpty()) {
		        scheduleDateTime = LocalDateTime.now();
		    } else {
		        LocalDate date = LocalDate.parse(scheduleDate);
		        LocalTime time = LocalTime.parse(scheduleTime);
		        scheduleDateTime = LocalDateTime.of(date, time);
		    }
		  
		 
	      
	        
		  smsService.processSms(phoneNumber, message, campaign,scheduleDateTime);
		
		  System.out.println("sms send successfully");
	    return "redirect:/home";        
	    }
	
	  @GetMapping("/sms-status")
	    public String smsStatus(Model model) {
	        model.addAttribute("pendingSMSCount", smsService.countPendingSMS());
	        model.addAttribute("sentSMSCount", smsService.countSentSMS());
	        model.addAttribute("dndcount",smsService.countDndMessage());
	        return "sms-status";  
	    }
	  
	  
	  
	  
	  @GetMapping("/sendall")
	    public String smsStatus() {
	       
	        return "sendall";  
	    }
	  
	  @PostMapping("/sendall")
	    public String sendSmsToAll(@RequestParam String message, @RequestParam("campaign") String campaign,@RequestParam("fileUpload") MultipartFile file,@RequestParam( value="scheduleDate",required=false) String scheduleDate,@RequestParam(value="scheduleTime",required=false) String scheduleTime) throws IOException {
		  
		  LocalDateTime scheduleDateTime;
		  if (scheduleDate.isEmpty() || scheduleTime.isEmpty()) {
		        scheduleDateTime = LocalDateTime.now();
		    } else {
		        LocalDate date = LocalDate.parse(scheduleDate);
		        LocalTime time = LocalTime.parse(scheduleTime);
		        scheduleDateTime = LocalDateTime.of(date, time);
		    }
		  
	      
		  
	  smsService.sendCustomMessageToAll(message,file,campaign,scheduleDateTime);
	        return "redirect:/home";
	    }
	  
	  
	}

	
	
	
	
	
	
	
	
	

//	@PostMapping("/sendsms")
//	public String sendSmss(@RequestParam("numbers") String numbers, 
//            @RequestParam("message") String message) {
//// Split the comma-separated phone numbers and send SMS to each
//String[] phoneNumbers = numbers.split(",");
//for (String phoneNumber : phoneNumbers) {
//twilioService.sendSms(phoneNumber.trim(), message);
//}
//System.out.println("work");
//return "redirect:/home";
//}
	
	

	

