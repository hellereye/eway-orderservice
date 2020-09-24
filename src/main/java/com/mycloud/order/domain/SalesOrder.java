package com.mycloud.order.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.mycloud.order.domain.enumeration.SalesOrderStatus;

/**
 * A SalesOrder.
 */
@Entity
@Table(name = "sales_order")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedEntityGraph(name = "order.orderItems",
    attributeNodes = @NamedAttributeNode("orderItems")) //解决N+1,使orderItems成为了left outer join并急迫加载；
public class SalesOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "order_no", nullable = false)
    private Long orderNo;

    @NotNull
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @NotNull
    @Column(name = "dealer_code", nullable = false)
    private String dealerCode;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SalesOrderStatus status;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "is_invoicing")
    private Boolean isInvoicing;

    @Column(name = "invoice_no")
    private Long invoiceNo;

    @Column(name = "placed_date")
    private Instant placedDate;

    @Column(name = "pay_channel")
    private String payChannel;

    @Column(name = "paydate")
    private Instant paydate;

    @Column(name = "out_trade_no")
    private String outTradeNo;

    @Column(name = "settlement_status")
    private String settlementStatus;

    @Column(name = "settlement_date")
    private Instant settlementDate;

    @Column(name = "deliveried_date")
    private Instant deliveriedDate;

    @Column(name = "customer_no")
    private Long customerNo;

    @Column(name = "customer_remark")
    private String customerRemark;

    //@OneToMany(mappedBy = "salesOrder")
    @OneToMany(mappedBy = "salesOrder", fetch=FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SalesOrderItem> orderItems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public SalesOrder orderNo(Long orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public SalesOrder deliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public SalesOrder dealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
        return this;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SalesOrder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public SalesOrder status(SalesOrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(SalesOrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public SalesOrder total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean isIsInvoicing() {
        return isInvoicing;
    }

    public SalesOrder isInvoicing(Boolean isInvoicing) {
        this.isInvoicing = isInvoicing;
        return this;
    }

    public void setIsInvoicing(Boolean isInvoicing) {
        this.isInvoicing = isInvoicing;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public SalesOrder invoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Instant getPlacedDate() {
        return placedDate;
    }

    public SalesOrder placedDate(Instant placedDate) {
        this.placedDate = placedDate;
        return this;
    }

    public void setPlacedDate(Instant placedDate) {
        this.placedDate = placedDate;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public SalesOrder payChannel(String payChannel) {
        this.payChannel = payChannel;
        return this;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public Instant getPaydate() {
        return paydate;
    }

    public SalesOrder paydate(Instant paydate) {
        this.paydate = paydate;
        return this;
    }

    public void setPaydate(Instant paydate) {
        this.paydate = paydate;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public SalesOrder outTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public SalesOrder settlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
        return this;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public Instant getSettlementDate() {
        return settlementDate;
    }

    public SalesOrder settlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
        return this;
    }

    public void setSettlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Instant getDeliveriedDate() {
        return deliveriedDate;
    }

    public SalesOrder deliveriedDate(Instant deliveriedDate) {
        this.deliveriedDate = deliveriedDate;
        return this;
    }

    public void setDeliveriedDate(Instant deliveriedDate) {
        this.deliveriedDate = deliveriedDate;
    }

    public Long getCustomerNo() {
        return customerNo;
    }

    public SalesOrder customerNo(Long customerNo) {
        this.customerNo = customerNo;
        return this;
    }

    public void setCustomerNo(Long customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public SalesOrder customerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
        return this;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public Set<SalesOrderItem> getOrderItems() {
        return orderItems;
    }

    public SalesOrder orderItems(Set<SalesOrderItem> salesOrderItems) {
        this.orderItems = salesOrderItems;
        return this;
    }

    public SalesOrder addOrderItems(SalesOrderItem salesOrderItem) {
        this.orderItems.add(salesOrderItem);
        salesOrderItem.setSalesOrder(this);
        return this;
    }

    public SalesOrder removeOrderItems(SalesOrderItem salesOrderItem) {
        this.orderItems.remove(salesOrderItem);
        salesOrderItem.setSalesOrder(null);
        return this;
    }

    public void setOrderItems(Set<SalesOrderItem> salesOrderItems) {
        this.orderItems = salesOrderItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrder)) {
            return false;
        }
        return id != null && id.equals(((SalesOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrder{" +
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
