package sanhak6.pinwave.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import sanhak6.pinwave.domain.Message;
import sanhak6.pinwave.service.MessageService;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private MessageService messageService;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/finwaveChat").withSockJS();
    }

    @MessageMapping("/send/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, Message message) {
        // 여기서는 데이터베이스에서 메시지를 가져올 필요가 없으므로 주석 처리
        // ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(roomId);

        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // if (userDetails instanceof Mentor) {
        //     message.setSenderMentor((Mentor) userDetails);
        // } else if (userDetails instanceof Mentee) {
        //     message.setSenderMentee((Mentee) userDetails);
        // }

        // message.setChatRoom(chatRoom);

        // db에 메시지 저장
        message = messageService.saveMessage(message);

        // 특정 채팅룸에서 db로부터 메시지 가져옴
        List<Message> messages = messageService.getMessagesByChatRoomId(roomId);

        return (Message) messages;
    }
}