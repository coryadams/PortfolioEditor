package com.companyx.platform.portfolio.management.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

/**
 * This represents a daily log of Assets by Portfolio and Date.
 * At the end of each trading day a Task will run to take a snapshot
 * of Asset allocations for each Portfolio and copy them to this data structure
 * to then store in the SOR.
 *
 * The combination of Date and Portfolio should be unique for a given day.
 *
 */
@Entity
@Table(name = "daily_asset_allocation")
public class DailyAssetAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="daily_equity",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="equity_id"))
    Set<Equity> equities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="daily_option",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="option_id"))
    Set<Option> options;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="daily_bond",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="bond_id"))
    Set<Bond> bonds;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="daily_future",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="future_id"))
    Set<Future> futures;

    @Override
    public String toString() {
        return "DailyAssetAllocation{" +
                "id=" + id +
                ", date=" + date +
                ", equities=" + equities +
                ", options=" + options +
                ", bonds=" + bonds +
                ", futures=" + futures +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Set<Equity> getEquities() {
        return equities;
    }

    public void setEquities(Set<Equity> equities) {
        this.equities = equities;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<Bond> getBonds() {
        return bonds;
    }

    public void setBonds(Set<Bond> bonds) {
        this.bonds = bonds;
    }

    public Set<Future> getFutures() {
        return futures;
    }

    public void setFutures(Set<Future> futures) {
        this.futures = futures;
    }
}
