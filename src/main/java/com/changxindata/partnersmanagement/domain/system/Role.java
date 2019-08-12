package com.changxindata.partnersmanagement.domain.system;

import com.changxindata.partnersmanagement.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sys_role")
@Getter
@Setter
public class Role extends BaseDomain {

    @Column
    private String mask = "";

    @Column
    private String name = "";

    @Override
    public void setDateCreated(Date dateCreated) {
        super.setDateCreated(dateCreated);
    }

    @Override
    public void setLastUpdated(Date lastUpdated) {
        super.setLastUpdated(lastUpdated);
    }

    @Override
    public Date getLastUpdated() {
        return super.getLastUpdated();
    }

    @Override
    public Date getDateCreated() {
        return super.getDateCreated();
    }
}
