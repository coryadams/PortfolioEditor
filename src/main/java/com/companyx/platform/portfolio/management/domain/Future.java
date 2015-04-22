package com.companyx.platform.portfolio.management.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "future_asset")
public class Future {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "category", length = 15)
    private String category;

    @Column(name = "product", length = 55)
    private String product;

    @Column(name = "symbol", length = 6)
    private String symbol;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "allocation_percentage")
    private Double allocationPercentage;

    @OneToOne
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @Override
    public String toString() {
        return "Future{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", product='" + product + '\'' +
                ", symbol='" + symbol + '\'' +
                ", expirationDate=" + expirationDate +
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(Double allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
}
