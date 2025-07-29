package in.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.DndNumber;

public interface DndNumberRepository  extends JpaRepository<DndNumber,Long>{
boolean existsByPhoneNumber(String phoneNumber);
}

