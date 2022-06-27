package com.tecapro.demobinhan.service.user;

import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.model.query.IUserChat;
import com.tecapro.demobinhan.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByEmail(String email);
    Iterable<IUserChat> getAllUserHasRoleUser();
    IUserChat getUserChatInfo(Long id);
}
