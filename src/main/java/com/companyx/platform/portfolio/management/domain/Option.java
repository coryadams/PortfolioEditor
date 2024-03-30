package com.companyx.platform.portfolio.management.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "option_asset")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "contract_name", length = 35)
    private String contractName;

    @Column(name = "underlying_symbol", length = 8)
    private String underlyingSymbol;

    @Column(name = "expiration_date", length = 15)
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "option_type")
    private OptionType optionType;

    @Column(name = "strike_price")
    private BigDecimal strikePrice;

    @Column(name = "allocation_percentage")
    private BigDecimal allocationPercentage;

    @OneToOne
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contractName='" + contractName + '\'' +
                ", underlyingSymbol='" + underlyingSymbol + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", optionType=" + optionType +
                ", strikePrice=" + strikePrice +
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

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
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
