package com.changxindata.partnersmanagement.repository;

import com.changxindata.partnersmanagement.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Transactional
    @Query(value = "update User u set u.roleId = ?1 where u.id = ?2")
    int updateRole(String roleId, String id);

    @Modifying
    @Transactional
    @Query("update User u set " +
            "u.password = CASE WHEN :#{#application.password} = ''  OR :#{#application.password} IS NULL THEN u.name ELSE :#{#application.password} END ," +
            "u.name = CASE WHEN :#{#application.name} = '' OR :#{#application.name} IS NULL THEN u.name ELSE :#{#application.name} END ," +
            "u.groupId = CASE WHEN :#{#application.groupId} = '' OR :#{#application.groupId} IS NULL THEN u.groupId ELSE :#{#application.groupId} END ," +
            "u.roleId =  CASE WHEN :#{#application.roleId} = '' OR :#{#application.roleId} IS NULL THEN u.roleId ELSE :#{#application.roleId} END " +
            "where u.id = :#{#application.id}")
    int updateUserInfo(@Param("application") User application);


    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
