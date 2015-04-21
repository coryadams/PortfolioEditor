package com.companyx.platform.portfolio.management.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * This represents the current allocation of Assets by Portfolio.
 *
 * At the end of each trading day the task will run which will snapshot
 * the state of each Portfolio's asset allocation into the DailyAssetAllocation.
 *
 */
@Entity
@Table(name = "current_asset_allocation")
public class CurrentAssetAllocation {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @OneToMany
    @JoinTable(name="current_equity",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="equity_id"))
    List<Equity> equities;

    @OneToMany
    @JoinTable(name="current_option",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="option_id"))
    List<Option> options;

    @OneToMany
    @JoinTable(name="current_future",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="future_id"))
    List<Future> futures;

    @OneToMany
    @JoinTable(name="current_bond",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="bond_id"))
    List<Bond> bonds;

    @Override
    public String toString() {
        return "CurrentAssetAllocation{" +
                "id=" + id +
                ", portfolio=" + portfolio +
                ", equities=" + equities +
                ", options=" + options +
                ", futures=" + futures +
                ", bonds=" + bonds +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Bond> getBonds() {
        return bonds;
    }

    public void setBonds(List<Bond> bonds) {
        this.bonds = bonds;
    }
}
