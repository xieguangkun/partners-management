package com.changxindata.partnersmanagement.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PermissionRoleBean {
    private String operator;

    private String roleId;
    private List<String> permissionIds;

}
