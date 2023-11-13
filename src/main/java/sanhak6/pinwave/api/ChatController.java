package sanhak6.pinwave.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanhak6.pinwave.domain.ChatRoom;
import sanhak6.pinwave.domain.Mentee;
import sanhak6.pinwave.domain.Message;
import sanhak6.pinwave.domain.Mentor;
import sanhak6.pinwave.service.ChatRoomService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/send/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @Payload Message message) {
        ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(roomId);

        // Set sender information based on your authentication logic
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails instanceof Mentor) {
            message.setSenderMentor((Mentor) userDetails);
        } else if (userDetails instanceof Mentee) {
            message.setSenderMentee((Mentee) userDetails);
        }

        message.setChatRoom(chatRoom);
        chatRoom.getMessages().add(message);

        // Save the message and chat room to the database using your service/repository
        // messageService.save(message);
        // chatRoomService.save(chatRoom);

        return message;
    }
}

