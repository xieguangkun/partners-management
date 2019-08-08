package com.changxindata.partnersmanagement.domain.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_permission_role")
@Getter
@Setter
public class PermissionRole {

    @Id
    @Column(length = 36)
    private String permissionId = "";

    @Column
    private String roleId = "";

}
