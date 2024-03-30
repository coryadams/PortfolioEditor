package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.*;
import com.companyx.platform.portfolio.management.service.DailyAssetAllocationService;
import com.companyx.platform.portfolio.management.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    private Logger log = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    DailyAssetAllocationService dailyAssetAllocationService;

    /**
     * Populate model and show daily allocation view
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/allocation-by-day", method = RequestMethod.GET)
    public String showDaily(Model model) {
        Portfolio portfolio = portfolioService.findById(1L); // Hardcoded for 1 Portfolio
        model.addAttribute("portfolio", portfolio);
        return "report-daily";
    }

    /**
     * Populate model and show average allocation view
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/allocation-avg", method = RequestMethod.GET)
    public String showAvg(Model model) {
        Portfolio portfolio = portfolioService.findById(1L); // Hardcoded for 1 Portfolio
        model.addAttribute("portfolio", portfolio);
        return "report-avg";
    }

    /**
     * Generate / show on the UI, a report view with the portfolio allocations over a series of
     * X days starting from date Y.
     *
     * @param portfolio
     * @param model
     * @return
     */
    @RequestMapping(value = "/allocation-by-day/submit", method = RequestMethod.POST)
    public String showDailySubmit(@ModelAttribute Portfolio portfolio, Model model, @RequestParam("days") int days,
                                  @RequestParam("date_picker") String strStartDate) {
        // Update from DB
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        // Parse input
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        // Coming in as dd/mm/yyyy
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(strStartDate));
            startDate = new java.sql.Date(c.getTimeInMillis());
            // roll by number of days
            c.add(Calendar.DATE, days);
            endDate = new java.sql.Date(c.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<DailyAssetAllocation> dailyAssetAllocations = dailyAssetAllocationService.findByPortfolioAndDateBetween(portfolio, startDate, endDate);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("dailyAssetAllocations", dailyAssetAllocations);
        return "report-daily";
    }

    /**
     * Generate / show on the UI a report view with the average allocation within a date range.
     *
     * @param portfolio
     * @param model
     * @return
     */
    @RequestMapping(value = "/allocation-avg/submit", method = RequestMethod.POST)
    public String showAvgSubmit(@ModelAttribute Portfolio portfolio, Model model,
                                @RequestParam("start_date_picker") String strStartDate,
                                @RequestParam("end_date_picker") String strEndDate) {
        // Update from DB
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        // Parse input
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;
        // Coming in as dd/mm/yyyy
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(strStartDate));

            startDate = new java.sql.Date(calendar.getTimeInMillis());

            calendar.setTime(sdf.parse(strEndDate));

            endDate = new java.sql.Date(calendar.getTimeInMillis());
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        List<DailyAssetAllocation> dailyAssetAllocations = dailyAssetAllocationService.findByPortfolioAndDateBetween(portfolio, startDate, endDate);

        double totalEquityAllocationPercentage = 0,
               totalOptionAllocationPercentage = 0,
               totalBondAllocationPercentage = 0,
               totalFutureAllocaitonPercentage = 0;

        int equityAllocationCounter = 0,
            optionAllocationCounter = 0,
            bondAllocationCounter = 0,
            futureAllocationCounter = 0;

        for (DailyAssetAllocation dailyAssetAllocation : portfolioExisting.getDailyAssetAllocations())
        {
            for (Equity equity : dailyAssetAllocation.getEquities()) {
                totalEquityAllocationPercentage += equity.getAllocationPercentage().doubleValue();
                equityAllocationCounter++;
            }
            for (Option option : dailyAssetAllocation.getOptions()) {
                totalOptionAllocationPercentage += option.getAllocationPercentage().doubleValue();
                optionAllocationCounter++;
            }
            for (Bond bond : dailyAssetAllocation.getBonds()) {
                totalBondAllocationPercentage += bond.getAllocationPercentage().doubleValue();
                bondAllocationCounter++;
            }
            for (Future future : dailyAssetAllocation.getFutures()) {
                totalFutureAllocaitonPercentage += future.getAllocationPercentage().doubleValue();
                futureAllocationCounter++;
            }
        }
        double equityAvg = totalEquityAllocationPercentage / equityAllocationCounter;
        equityAvg = Math.round(equityAvg * 100.0) / 100.0;
        double optionAvg = totalOptionAllocationPercentage / optionAllocationCounter;
        optionAvg = Math.round(optionAvg * 100.0) / 100.0;
        double bondAvg = totalBondAllocationPercentage / bondAllocationCounter;
        bondAvg = Math.round(bondAvg * 100.0) / 100.0;
        double futureAvg = totalFutureAllocaitonPercentage / futureAllocationCounter;
        futureAvg = Math.round(futureAvg * 100.0) / 100.0;

        List<String> reportItems = new ArrayList<String>();
        reportItems.add("Average Equity Allocation: " + equityAvg + "%");
        reportItems.add("Average Option Allocation: " + optionAvg + "%");
        reportItems.add("Average Bond Allocation: " + bondAvg + "%");
        reportItems.add("Average Future Allocation: " + futureAvg + "%");

        model.addAttribute("startDate", strStartDate);
        model.addAttribute("endDate", strEndDate);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("reportItems", reportItems);
        return "report-avg";
    }
}