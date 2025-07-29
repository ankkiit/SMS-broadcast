package in.main.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.main.entity.SMS;
import in.main.entity.SMS.Status;
import in.main.repository.SMSRepository;

@Service
public class SmsScheduleService {
	
	
	@Autowired
	private SMSRepository smsRepository;
	@Autowired
	private SMSService smsService;
	
	@Scheduled(fixedRate=86400000) // after 24 hours
	public void sendScheduledMessage() {
		LocalDateTime now =LocalDateTime.now();
		List<SMS> messagesToSend = smsRepository.findByStatus(Status.PENDING);
		for (SMS sms:messagesToSend) {
			if(sms.getScheduledTime().isBefore(now)|| sms.getScheduledTime().isEqual(now)) {
				smsService.sendSms(sms.getPhoneNumber(), sms.getMessage());
				 sms.setStatus(Status.SENT);
				 smsRepository.save(sms);	
			} }		
	}
}
