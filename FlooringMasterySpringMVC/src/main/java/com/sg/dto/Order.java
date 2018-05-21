package com.sg.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private int orderId;
    private LocalDate orderDate;
    private String customerName;
    private Tax taxObject;
    private Product productObject;
    private BigDecimal area;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalLaborCost;
    private BigDecimal totalTax;
    private BigDecimal totalCost;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getTaxObject() {
        return taxObject;
    }

    public void setTaxObject(Tax taxObject) {
        this.taxObject = taxObject;
    }

    public Product getProductObject() {
        return productObject;
    }

    public void setProductObject(Product productObject) {
        this.productObject = productObject;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(customerName, order.customerName) &&
                Objects.equals(taxObject, order.taxObject) &&
                Objects.equals(productObject, order.productObject) &&
                Objects.equals(area, order.area) &&
                Objects.equals(totalMaterialCost, order.totalMaterialCost) &&
                Objects.equals(totalLaborCost, order.totalLaborCost) &&
                Objects.equals(totalTax, order.totalTax) &&
                Objects.equals(totalCost, order.totalCost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, orderDate, customerName, taxObject, productObject, area, totalMaterialCost, totalLaborCost, totalTax, totalCost);
    }
}
