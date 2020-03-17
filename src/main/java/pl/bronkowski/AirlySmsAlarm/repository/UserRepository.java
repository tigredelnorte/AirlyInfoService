package pl.bronkowski.AirlySmsAlarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.bronkowski.AirlySmsAlarm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{



}
	