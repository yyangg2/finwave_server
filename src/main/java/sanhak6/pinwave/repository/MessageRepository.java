package sanhak6.pinwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanhak6.pinwave.domain.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoomId(String chatRoomId);
}
