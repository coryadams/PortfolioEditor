package com.companyx.platform.portfolio.management.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "bond_asset")
public class Bond {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "cusip", length = 15)
    private String cusip;

    @Column(name = "description", length = 256)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "bond_type")
    private BondType bondType;

    @Column(name = "coupon")
    private BigDecimal coupon;

    @Column(name = "maturity_date")
    private Date maturityDate;

    @Column(name = "rating", length = 15)
    private String rating;

    @Column(name = "allocation_percentage")
    private BigDecimal allocationPercentage;

    @OneToOne
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @Override
    public String toString() {
        return "Bond{" +
                "id=" + id +
                ", cusip='" + cusip + '\'' +
                ", description='" + description + '\'' +
                ", bondType=" + bondType +
                ", coupon=" + coupon +
                ", maturityDate=" + maturityDate +
                ", rating='" + rating + '\'' +
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

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BondType getBondType() {
        return bondType;
    }

    public void setBondType(BondType bondType) {
        this.bondType = bondType;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
