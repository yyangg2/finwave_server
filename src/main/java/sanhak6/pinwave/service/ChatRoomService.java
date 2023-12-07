package sanhak6.pinwave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sanhak6.pinwave.domain.ChatRoom;
import sanhak6.pinwave.repository.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom getOrCreateChatRoom(String roomId) {
        Long roomIdLong = Long.parseLong(roomId);
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomIdLong);

        return optionalChatRoom.orElseGet(() -> {
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setId(roomIdLong);
            return chatRoomRepository.save(newChatRoom);
        });
    }
}
