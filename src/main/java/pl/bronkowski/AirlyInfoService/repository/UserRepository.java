package pl.bronkowski.AirlyInfoService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.bronkowski.AirlyInfoService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{



}
	