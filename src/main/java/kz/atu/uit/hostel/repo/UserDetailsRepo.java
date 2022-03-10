package kz.atu.uit.hostel.repo;

import kz.atu.uit.hostel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User,String> {
}
