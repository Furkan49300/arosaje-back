package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.ChatConversation;
import com.epsi.fr.arosaje.bo.ChatMessage;
import com.epsi.fr.arosaje.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/message")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessage message) {
        ChatMessage savedMessage = chatService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@RequestParam Long userId1, @RequestParam Long userId2) {
        List<ChatMessage> messages = chatService.getMessages(userId1, userId2);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversation")
    public ResponseEntity<ChatConversation> getOrCreateConversation(@RequestParam Long userId1, @RequestParam Long userId2) {
        ChatConversation conversation = chatService.getOrCreateConversation(userId1, userId2);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/conversations/{userId}")
    public ResponseEntity<List<ChatConversation>> getUserConversations(@PathVariable Long userId) {
        List<ChatConversation> conversations = chatService.getUserConversations(userId);
        return ResponseEntity.ok(conversations);
    }
}
