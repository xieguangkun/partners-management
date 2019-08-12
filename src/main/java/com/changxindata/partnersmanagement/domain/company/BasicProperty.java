package com.changxindata.partnersmanagement.domain.company;

import com.changxindata.partnersmanagement.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "co_basic_property")
@Getter
@Setter
public class BasicProperty extends BaseDomain {

    @Column
    private String name = "";   //公司名

    @Column
    private String url = "";   //公司网址

    @Column
    private String phoneNumber = "";        //联系电话

    @Column
    private String email = "";      //电子邮件

    @Column
    private String operName = "";   //法人

    @Column
    private String registCapi = "";      //注册资本

    @Column
    private String recCap = "";      //实缴资本

    @Column
    private String status = "";     //经营状态

    @Column
    private String startDate = "";      //成立日期

    @Column
    private String creditCode = "";     //信用代码

    @Column
    private String number = "";     //注册号

    @Column
    private String entType = "";        //企业类型

    @Column
    private String industry = "";       //所属行业

    @Column
    private String checkDate = "";      //核准日期

    @Column
    private String belongOrg = "";      //登记机关

    @Column
    private String area = "";       //区域信息

    @Column
    private String ersonScope = "";       //区域信息

    @Column
    private String term = "";       //营业期限

    @Column
    private String address = "";        //地址

    @Column(columnDefinition = "text")
    private String scope = "";      //营业范围
}
