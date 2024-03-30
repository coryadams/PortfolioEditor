package com.companyx.platform.portfolio.management.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "equity_asset")
public class Equity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "ticker", length = 8)
    private String ticker;

    @Column(name = "allocation_percentage")
    private BigDecimal allocationPercentage;

    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @Override
    public String toString() {
        return "Equity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", allocationPercentage=" + allocationPercentage +
                ", exchange=" + exchange +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(BigDecimal allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
}
