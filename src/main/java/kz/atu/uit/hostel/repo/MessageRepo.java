package kz.atu.uit.hostel.repo;

import kz.atu.uit.hostel.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long> {
}
