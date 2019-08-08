package com.changxindata.partnersmanagement.repository;

import com.changxindata.partnersmanagement.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
