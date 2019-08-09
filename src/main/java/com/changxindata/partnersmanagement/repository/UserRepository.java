package com.changxindata.partnersmanagement.repository;

import com.changxindata.partnersmanagement.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Transactional
    @Query(value = "update User u set u.roleId = ?1 where u.id = ?2")
    int updateRole(String roleId, String id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
