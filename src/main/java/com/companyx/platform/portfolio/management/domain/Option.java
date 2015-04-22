package com.companyx.platform.portfolio.management.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "option_asset")
public class Option {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "contract_name", length = 35)
    private String contractName;

    @Column(name = "underlying_symbol", length = 8)
    private String underlyingSymbol;

    @Column(name = "expiration_date", length = 15)
    private String expirationDate;

    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    @Column(name = "strike_price")
    private Double strikePrice;

    @Column(name = "allocation_percentage")
    private Double allocationPercentage;

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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
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
