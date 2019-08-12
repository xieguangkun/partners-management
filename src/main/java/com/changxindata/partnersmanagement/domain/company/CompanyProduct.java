package com.changxindata.partnersmanagement.domain.company;

import com.changxindata.partnersmanagement.domain.BaseDomain;

import javax.persistence.Column;

public class CompanyProduct extends BaseDomain {

    @Column
    private String name = "";       //产品名称

    @Column
    private String domain = "";     //产品领域

    @Column
    private String description = "";     //产品描述

    @Column
    private String category = "";     //产品类型
}
