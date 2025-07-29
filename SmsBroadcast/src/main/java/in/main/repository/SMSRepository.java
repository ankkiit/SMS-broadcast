package in.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.main.entity.SMS;
import in.main.entity.SMS.Status;



public interface SMSRepository extends JpaRepository<SMS,Long> {
 List<SMS> findByStatus(SMS.Status status);
	long countByStatus(Status status);
	//List<SMS> findByScheduledTimeBetween(LocalDateTime start,LocalDateTime end);

	
	
	
@Query( value="Select s.phoneNumber FROM SMS s")
List<String> findAllNumbers();
}
