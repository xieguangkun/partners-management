package com.changxindata.partnersmanagement.repository;

import com.changxindata.partnersmanagement.domain.system.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole, String> {

    void deleteAllByRoleId(String roleId);
}
