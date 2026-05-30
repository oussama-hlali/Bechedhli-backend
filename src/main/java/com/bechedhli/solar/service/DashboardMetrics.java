package com.bechedhli.solar.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor @Builder
public class DashboardMetrics {

    private int totalEmployees;
    private int activeEmployees;
    private double totalStockValue;
    private int lowStockCount;
    private int totalDeliveryNotes;
    private int deliveredDeliveryNotes;
    private int stegApproved;
    private int stegTotal;
    private double totalBilled;
    private double totalCollected;
    private double totalOutstanding;
}
