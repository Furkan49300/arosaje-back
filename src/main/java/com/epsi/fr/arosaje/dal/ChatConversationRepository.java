package com.epsi.fr.arosaje.dal;

import com.epsi.fr.arosaje.bo.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
@RepositoryRestResource
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public interface ChatConversationRepository extends JpaRepository<ChatConversation, Long> {
    Optional<ChatConversation> findByUserId1AndUserId2(Long userId1, Long userId2);
    List<ChatConversation> findByUserId1OrUserId2(Long userId1, Long userId2);
}
