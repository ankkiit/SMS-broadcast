package in.main.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SMS {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long id;
	private String message;
	private String campaign;
	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	private String phoneNumber;
	@Column(name="scheduledTime")
	private LocalDateTime scheduledTime;
	
	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public SMS() {
		
	}
	
	
	public SMS(String message, String phoneNumber, LocalDateTime scheduledDateTime) {
		super();
		this.message = message;
		this.phoneNumber = phoneNumber;
		this.scheduledTime = scheduledDateTime;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Enumerated(EnumType.STRING)
    private Status status;

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	

	public enum Status {
		PENDING,SENT
		
	}

}
