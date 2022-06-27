package com.tecapro.demobinhan.repository;

import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.model.query.IUserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "select users.id, users.email, users.full_name, users.password " +
            "from users left join users_roles ur on users.id = ur.user_id " +
            "left join roles r on ur.role_id = r.id " +
            "where r.name = 'ROLE_USER'", nativeQuery = true)
    Iterable<User> getAllUserHasRoleUser();

    @Query(value = "select users.id, users.email, users.full_name," +
            " chat.content, chat.time " +
            "from users " +
            "left join chat on users.id = chat.sender_id " +
            "where users.id = ?1 " +
            "order by time desc " +
            "limit 1;", nativeQuery = true)
    IUserChat getUserChatInfo(Long id);
}
