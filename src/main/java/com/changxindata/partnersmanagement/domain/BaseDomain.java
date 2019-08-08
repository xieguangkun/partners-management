package com.changxindata.partnersmanagement.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = -5670381959764596931L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 36)
    private String id;

    @CreatedDate
    @Column
    private Date dateCreated;

    @LastModifiedDate
    @Column
    private Date lastUpdated;

    @Column
    private boolean deleted = false;

    @Column
    private String createdBy = "";

    @Column
    private String updatedBy = "";

    @Version
    private Long version;
}
