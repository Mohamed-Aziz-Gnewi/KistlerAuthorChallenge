package KistlerAuthorChallenge.example.KistlerAuthorChallenge.Repository;

import KistlerAuthorChallenge.example.KistlerAuthorChallenge.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(
            value = "SELECT * FROM message WHERE author_id = ?1",
            nativeQuery = true)
    List<Message> getMessageByAuthorId(Long author_id);
}
