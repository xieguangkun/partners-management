package com.changxindata.partnersmanagement.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//POST请求的三种请求方式都包含在内
public class ApplicationBean {
    private String operator;

    private String username;
    private String password;
    private String groupId;
    private String roleId;
    private String userId;
    private String permissionId;

    private String name;
    private String mask;

    private String parentId;
    private String sequence;
    private String url;
}
