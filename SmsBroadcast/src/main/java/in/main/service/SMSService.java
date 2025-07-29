package in.main.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import in.main.entity.Dndmessage;
import in.main.entity.SMS;
import in.main.entity.SMS.Status;
import in.main.repository.DndNumberRepository;
import in.main.repository.DndmessageRepository;
import in.main.repository.SMSRepository;
import io.jsonwebtoken.io.IOException;

@Service
public class SMSService {

	@Autowired
	private DndmessageRepository dndmessageRepository;

	@Autowired
	private DndNumberRepository dndnumberRepository;

	@Autowired
	private SMSRepository smsRepository;

	public void processSms(String phoneNumber, String message, String campagin, LocalDateTime scheduledDateTime) {
		if (dndnumberRepository.existsByPhoneNumber(phoneNumber)) {
			Dndmessage dndMessage = new Dndmessage();
			dndMessage.setPhoneNumber(phoneNumber);
			dndMessage.setMessageContent(message);
			dndMessage.setTimestamp(LocalDateTime.now());
			dndMessage.setCampaign(campagin);
			dndmessageRepository.save(dndMessage);
		} else {
			SMS sms = new SMS(message, phoneNumber, scheduledDateTime);
			sms.setStatus(Status.PENDING);
			sms.setPhoneNumber(phoneNumber);
			sms.setScheduledTime(scheduledDateTime);
			;
			sms.setMessage(message);
			sms.setCampaign(campagin);
			smsRepository.save(sms);
		}
	}

	public SMS saveSMS(SMS sms) {
		sms.setStatus(SMS.Status.PENDING);// By default, set the status as PENDING
		return smsRepository.save(sms);
	}

	public void sendCustomMessageToAll(String customMessage, MultipartFile file, String campagin,
			LocalDateTime scheduledDateTime) throws java.io.IOException {
		List<String> numbers = extractNumbersFromFile(file);
		for (String number : numbers) {
			processSms(number, customMessage, campagin, scheduledDateTime);
		}

	}

	// for extract phone number from the uploaded file
	private List<String> extractNumbersFromFile(MultipartFile file) throws java.io.IOException {
		List<String> numbers = new ArrayList<>();

		if (file.isEmpty()) {
			System.out.println("File is empty!");
			return numbers;
		}
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (!line.isEmpty()) {
					numbers.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numbers;
	}
	public long countPendingSMS() {
		return smsRepository.countByStatus(Status.PENDING);

	}

	public long countSentSMS() {
		return smsRepository.countByStatus(Status.SENT);

	}

	public long countDndMessage() {
		return dndmessageRepository.count();
	}

	// here we only print the data in console instead of using api services like
	// twilio

	public void sendSms(String phoneNumber, String message) {
		System.out.println("Sending SMS to " + phoneNumber + ":" + message);
	}
}
