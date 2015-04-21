package com.companyx.platform.portfolio.management.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "exchange")
public class Exchange {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name= "identifier", length = 35)
    private String identifier;

    @Override
    public String toString() {
        return "Exchange{" +
                ", identifier='" + identifier + '\'' +
                ", id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
