package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "call getAllChatBetweenTwoUser(?1, ?2, ?3,)", nativeQuery = true)
    Iterable<Chat> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id, Integer size);
}
