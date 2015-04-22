package com.companyx.platform.portfolio.management.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="current_equity",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="equity_id"))
    Set<Equity> equities;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="current_option",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="option_id"))
    Set<Option> options;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="current_future",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="future_id"))
    Set<Future> futures;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="current_bond",
            joinColumns=@JoinColumn(name="current_asset_allocation_id"),
            inverseJoinColumns=@JoinColumn(name="bond_id"))
    Set<Bond> bonds;

    @Override
    public String toString() {
        return "CurrentAssetAllocation{" +
                "id=" + id +
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

    public Set<Future> getFutures() {
        return futures;
    }

    public void setFutures(Set<Future> futures) {
        this.futures = futures;
    }

    public Set<Bond> getBonds() {
        return bonds;
    }

    public void setBonds(Set<Bond> bonds) {
        this.bonds = bonds;
    }
}
