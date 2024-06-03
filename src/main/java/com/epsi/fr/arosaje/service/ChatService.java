package com.epsi.fr.arosaje.service;

import com.epsi.fr.arosaje.Entity.ChatConversation;
import com.epsi.fr.arosaje.Entity.ChatMessage;
import com.epsi.fr.arosaje.repository.ChatConversationRepository;
import com.epsi.fr.arosaje.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatConversationRepository chatConversationRepository;

    public ChatMessage saveMessage(ChatMessage message) {
        // Enregistrer le message
        ChatMessage savedMessage = chatMessageRepository.save(message);

        // Assurer que la conversation existe
        getOrCreateConversation(message.getSenderId(), message.getRecipientId());

        return savedMessage;
    }

    public List<ChatMessage> getMessages(Long userId1, Long userId2) {
        List<ChatMessage> messages = new ArrayList<>();
        messages.addAll(chatMessageRepository.findBySenderIdAndRecipientId(userId1, userId2));
        messages.addAll(chatMessageRepository.findByRecipientIdAndSenderId(userId1, userId2));
        messages.sort(Comparator.comparing(ChatMessage::getTimestamp));
        return messages;
    }

    public ChatConversation getOrCreateConversation(Long userId1, Long userId2) {
        Optional<ChatConversation> existingConversation = chatConversationRepository.findByUserId1AndUserId2(userId1, userId2);
        if (!existingConversation.isPresent()) {
            existingConversation = chatConversationRepository.findByUserId1AndUserId2(userId2, userId1);
        }

        if (existingConversation.isPresent()) {
            return existingConversation.get();
        } else {
            ChatConversation conversation = new ChatConversation(userId1, userId2);
            return chatConversationRepository.save(conversation);
        }
    }

    public List<ChatConversation> getUserConversations(Long userId) {
        return chatConversationRepository.findByUserId1OrUserId2(userId, userId);
    }
}
