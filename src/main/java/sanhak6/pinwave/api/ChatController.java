package sanhak6.pinwave.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sanhak6.pinwave.domain.ChatRoom;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Message;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.service.ChatRoomService;
import sanhak6.pinwave.service.MessageService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/send/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @Payload Message message) {
        ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(roomId);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails instanceof Mentor) {
            message.setSenderMentor((Mentor) userDetails);
        } else if (userDetails instanceof Mentee) {
            message.setSenderMentee((Mentee) userDetails);
        }

        message.setChatRoom(chatRoom);

        // db에 메시지 저장
        message = messageService.saveMessage(message);

        return message;
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable String roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(roomId);

        if (chatRoom != null) {
            return ResponseEntity.ok(chatRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createRoom/{roomId}")
    public ResponseEntity<ChatRoom> createChatRoom(@PathVariable String roomId) {
        // 채팅방이 존재하는지 확인
        if (chatRoomService.getChatRoomById(roomId) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 채팅방이 이미 존재
        }

        // 채팅방 생성
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setRoomId(roomId);
        // db에 저장
        chatRoomService.saveChatRoom(newChatRoom);

        return ResponseEntity.status(HttpStatus.CREATED).body(newChatRoom);
    }
}
