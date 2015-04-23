package com.companyx.platform.portfolio.management.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @OneToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER, mappedBy =  "portfolio")
    private CurrentAssetAllocation currentAssetAllocation;

    @OneToMany(cascade={CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "portfolio")
    private List<DailyAssetAllocation> dailyAssetAllocations;

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentAssetAllocation=" + currentAssetAllocation +
                ", dailyAssetAllocations=" + dailyAssetAllocations +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CurrentAssetAllocation getCurrentAssetAllocation() {
        return currentAssetAllocation;
    }

    public void setCurrentAssetAllocation(CurrentAssetAllocation currentAssetAllocation) {
        this.currentAssetAllocation = currentAssetAllocation;
    }

    public List<DailyAssetAllocation> getDailyAssetAllocations() {
        return dailyAssetAllocations;
    }

    public void setDailyAssetAllocations(List<DailyAssetAllocation> dailyAssetAllocations) {
        this.dailyAssetAllocations = dailyAssetAllocations;
    }
}
