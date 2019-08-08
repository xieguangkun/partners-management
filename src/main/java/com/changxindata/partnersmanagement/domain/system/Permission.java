package com.changxindata.partnersmanagement.domain.system;

import com.changxindata.partnersmanagement.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_permission")
@Getter
@Setter
public class Permission extends BaseDomain {
    @Column
    private String mask = "";

    @Column
    private String name = "";

    @Column
    private String parentId = "";

    @Column
    private String sequence = "";

    @Column
    private String url = "#";
}
