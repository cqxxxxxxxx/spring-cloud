package com.cqx.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.*;

/**
 * Created by BG307435 on 2018/2/5.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Bean
    public WebSocketListener webSocketListener() {
        return new WebSocketListener();
    }

    /**
     * Register STOMP endpoints mapping each to a specific URL and (optionally)
     * enabling and configuring SockJS fallback options.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }

    /**
     * Configure message broker options.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//      STOMP messages whose destination header begins with "/app" are routed to @MessageMapping methods in @Controller classes.
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/queue");


        /**
         * Configure the prefix used to identify user destinations. User destinations
         * provide the ability for a user to subscribe to queue names unique to their
         * session as well as for others to send messages to those unique,
         * user-specific queues.
         * <p>For example when a user attempts to subscribe to "/user/queue/position-updates",
         * the destination may be translated to "/queue/position-updatesi9oqdfzo" yielding a
         * unique queue name that does not collide with any other user attempting to do the same.
         * Subsequently when messages are sent to "/user/{username}/queue/position-updates",
         * the destination is translated to "/queue/position-updatesi9oqdfzo".
         * <p>The default prefix used to identify such destinations is "/user/".
         */
        config.setUserDestinationPrefix("/user");
    }


    /**
     * 添加拦截器
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new WebSocketChannelInterceptor());
    }

    /**
     * 监听器 监听socket连接之类操作
     */
    private class WebSocketListener {

        @EventListener
        public void onSessionConnect(SessionConnectEvent sessionConnectEvent) {
            System.out.println("session connect received ~~~~~~~~~");
            System.out.println(sessionConnectEvent.getSource());
            System.out.println(sessionConnectEvent.getMessage());
            System.out.println(sessionConnectEvent.getUser());
            System.out.println(sessionConnectEvent.getTimestamp());
            System.out.println(sessionConnectEvent.toString());

            GenericMessage message = (GenericMessage) sessionConnectEvent.getMessage();
            System.out.println(message.toString());

            MessageHeaders headers = message.getHeaders();
            System.out.println(headers.toString());
        }

        @EventListener
        public void onSessionConnected(SessionConnectedEvent sessionConnectedEvent) {
            System.out.println("session connected ~~~~~~~~~");
            System.out.println(sessionConnectedEvent.getSource());
            System.out.println(sessionConnectedEvent.getMessage());
            System.out.println(sessionConnectedEvent.getUser());
            System.out.println(sessionConnectedEvent.getTimestamp());
            System.out.println(sessionConnectedEvent.toString());
        }

        @EventListener
        public void onSessionDisconnect(SessionDisconnectEvent sessionDisconnectEvent) {
            System.out.println("session disconnect ~~~~~~~~~");
            System.out.println(sessionDisconnectEvent.getSource());
            System.out.println(sessionDisconnectEvent.getMessage());
            System.out.println(sessionDisconnectEvent.getUser());
            System.out.println(sessionDisconnectEvent.getTimestamp());
            System.out.println(sessionDisconnectEvent.toString());
        }

        @EventListener
        public void onSessionSubscribe(SessionSubscribeEvent sessionSubscribeEvent) {
            System.out.println("session subscribe ~~~~~~~~~");
            System.out.println(sessionSubscribeEvent.getSource());
            System.out.println(sessionSubscribeEvent.getMessage());
            System.out.println(sessionSubscribeEvent.getUser());
            System.out.println(sessionSubscribeEvent.getTimestamp());
            System.out.println(sessionSubscribeEvent.toString());
        }


        @EventListener
        public void onSessionUnsubscribe(SessionUnsubscribeEvent sessionUnsubscribeEvent) {
            System.out.println("session unsubscribe ~~~~~~~~~");
            System.out.println(sessionUnsubscribeEvent.getSource());
            System.out.println(sessionUnsubscribeEvent.getMessage());
            System.out.println(sessionUnsubscribeEvent.getUser());
            System.out.println(sessionUnsubscribeEvent.getTimestamp());
            System.out.println(sessionUnsubscribeEvent.toString());
        }
    }

    /**
     * 拦截器
     */
    private class WebSocketChannelInterceptor extends ChannelInterceptorAdapter {

        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            return null;
        }

        @Override
        public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

        }

        @Override
        public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {

        }

        @Override
        public boolean preReceive(MessageChannel channel) {
            return false;
        }

        @Override
        public Message<?> postReceive(Message<?> message, MessageChannel channel) {
            return null;
        }

        @Override
        public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {

        }
    }


}
