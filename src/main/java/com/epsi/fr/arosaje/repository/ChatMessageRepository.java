package com.epsi.fr.arosaje.repository;

import com.epsi.fr.arosaje.bo.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@RepositoryRestResource
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
    List<ChatMessage> findByRecipientIdAndSenderId(Long recipientId, Long senderId);
}