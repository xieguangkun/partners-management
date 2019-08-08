package com.changxindata.partnersmanagement.repository;

import com.changxindata.partnersmanagement.domain.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {

}
