package KistlerAuthorChallenge.example.KistlerAuthorChallenge.Repository;

import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
