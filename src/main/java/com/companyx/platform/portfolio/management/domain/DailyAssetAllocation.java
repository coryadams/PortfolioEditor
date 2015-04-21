package com.companyx.platform.portfolio.management.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
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

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @OneToMany
    @JoinTable(name="daily_equity",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="equity_id"))
    List<Equity> equities;

    @OneToMany
    @JoinTable(name="daily_option",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="option_id"))
    List<Option> options;

    @OneToMany
    @JoinTable(name="daily_future",
            joinColumns=@JoinColumn(name="daily_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="future_id"))
    List<Future> futures;

    @Override
    public String toString() {
        return "DailyAssetAllocation{" +
                "id=" + id +
                ", date=" + date +
                ", portfolio=" + portfolio +
                ", equities=" + equities +
                ", options=" + options +
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

    public List<Equity> getEquities() {
        return equities;
    }

    public void setEquities(List<Equity> equities) {
        this.equities = equities;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Future> getFutures() {
        return futures;
    }

    public void setFutures(List<Future> futures) {
        this.futures = futures;
    }
}
