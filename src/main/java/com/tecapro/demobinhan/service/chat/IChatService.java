package com.tecapro.demobinhan.service.chat;


import com.tecapro.demobinhan.model.Chat;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IChatService extends IGeneralService<Chat> {
    Iterable<Chat> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id, Integer size);
}
