package com.changxindata.partnersmanagement.repository;

import com.changxindata.partnersmanagement.domain.system.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole, String> {

    void deleteAllByRoleId(String roleId);

    List<PermissionRole> findAllByRoleId(String roleId);
}
