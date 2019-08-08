package com.changxindata.partnersmanagement.domain.system;

import com.changxindata.partnersmanagement.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
@Getter
@Setter
public class Role extends BaseDomain {

    @Column
    private String mask = "";

    @Column
    private String name = "";
}
