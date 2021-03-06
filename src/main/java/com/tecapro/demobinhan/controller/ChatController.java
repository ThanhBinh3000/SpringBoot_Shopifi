package com.tecapro.demobinhan.controller;

import com.tecapro.demobinhan.model.Chat;
import com.tecapro.demobinhan.service.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private IChatService chatService;

    @GetMapping
    public ResponseEntity<Iterable<Chat>> getAllChat(@RequestParam("userId1") Long userId1,
                                                     @RequestParam("userId2") Long userId2,
                                                     @RequestParam("size") Integer size) {
        return new ResponseEntity<>(chatService.getAllHistoryBetweenTwoUser(userId1, userId2, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Chat> createNewChat(@RequestBody Chat chat) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        chat.setTime(date);
        return new ResponseEntity<>(chatService.save(chat), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChat(@PathVariable Long id) {
        Optional<Chat> categoryOptional = chatService.findById(id);
        return categoryOptional.map(chat -> new ResponseEntity<>(chat, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable Long id, @RequestBody Chat chat) {
        Optional<Chat> categoryOptional = chatService.findById(id);
        return categoryOptional.map(chat1 -> {
            chat.setId(chat1.getId());
            return new ResponseEntity<>(chatService.save(chat), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Chat> deleteChat(@PathVariable Long id) {
        Optional<Chat> chatOptional = chatService.findById(id);
        return chatOptional.map(chat -> {
            chatService.remove(id);
            return new ResponseEntity<>(chat, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
