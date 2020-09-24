package com.mycloud.order.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import com.mycloud.order.domain.enumeration.SalesOrderStatus;

/**
 * A DTO for the {@link com.mycloud.order.domain.SalesOrder} entity.
 */
public class SalesOrderDTO implements Serializable {

    private Long id;

    @NotNull
    private Long orderNo;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private String dealerCode;

    @NotNull
    private BigDecimal amount;

    private SalesOrderStatus status;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal total;

    private Boolean isInvoicing;

    private Long invoiceNo;

    private Instant placedDate;

    private String payChannel;

    private Instant paydate;

    private String outTradeNo;

    private String settlementStatus;

    private Instant settlementDate;

    private Instant deliveriedDate;

    private Long customerNo;

    private String customerRemark;
    public Set<SalesOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<SalesOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    private Set<SalesOrderItemDTO> orderItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public void setStatus(SalesOrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean isIsInvoicing() {
        return isInvoicing;
    }

    public void setIsInvoicing(Boolean isInvoicing) {
        this.isInvoicing = isInvoicing;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Instant getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Instant placedDate) {
        this.placedDate = placedDate;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public Instant getPaydate() {
        return paydate;
    }

    public void setPaydate(Instant paydate) {
        this.paydate = paydate;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Instant getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Instant getDeliveriedDate() {
        return deliveriedDate;
    }

    public void setDeliveriedDate(Instant deliveriedDate) {
        this.deliveriedDate = deliveriedDate;
    }

    public Long getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(Long customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((SalesOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderDTO{" +
            "id=" + getId() +
            ", orderNo=" + getOrderNo() +
            ", deliveryAddress='" + getDeliveryAddress() + "'" +
            ", dealerCode='" + getDealerCode() + "'" +
            ", amount=" + getAmount() +
            ", status='" + getStatus() + "'" +
            ", total=" + getTotal() +
            ", isInvoicing='" + isIsInvoicing() + "'" +
            ", invoiceNo=" + getInvoiceNo() +
            ", placedDate='" + getPlacedDate() + "'" +
            ", payChannel='" + getPayChannel() + "'" +
            ", paydate='" + getPaydate() + "'" +
            ", outTradeNo='" + getOutTradeNo() + "'" +
            ", settlementStatus='" + getSettlementStatus() + "'" +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", deliveriedDate='" + getDeliveriedDate() + "'" +
            ", customerNo=" + getCustomerNo() +
            ", customerRemark='" + getCustomerRemark() + "'" +
            "}";
    }
}
