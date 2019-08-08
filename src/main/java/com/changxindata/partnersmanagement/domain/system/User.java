package com.changxindata.partnersmanagement.domain.system;

import com.changxindata.partnersmanagement.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
@Getter
@Setter
public class User extends BaseDomain {

    private static final long serialVersionUID = -5670381959764596931L;

    @Column
    private String username = "";

    @Column
    private String password = "";

    @Column
    private String name = "";

    @Column
    private String groupId = "";

    @Column
    private String roleId = "";

    @Column
    private Boolean enable = false;
}
