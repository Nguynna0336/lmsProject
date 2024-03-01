package lhnthoi.lmsProject.Repositories;

import lhnthoi.lmsProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsById(int id);
    boolean existsByUserName(String userName);
   Optional<User> findByUserId(int id);
    Optional<User> findByUserName(String userName);
}
