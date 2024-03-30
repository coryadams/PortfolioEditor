package com.companyx.platform.portfolio.management.domain;

import jakarta.persistence.*;
@Entity
@Table(name = "exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
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
