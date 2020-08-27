package com.mycloud.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.mycloud.order.domain.enumeration.SalesOrderItemStatus;

import com.mycloud.order.domain.enumeration.UnitOfMeasurement;

/**
 * A SalesOrderItem.
 */
@Entity
@Table(name = "sales_order_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SalesOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "name")
    private String name;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "sprec")
    private String sprec;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SalesOrderItemStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_unit")
    private UnitOfMeasurement salesUnit;

    @Column(name = "discount_rate")
    private Double discountRate;

    @Column(name = "discount_amount", precision = 21, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "quantity", precision = 21, scale = 2)
    private BigDecimal quantity;

    @Column(name = "subtotal", precision = 21, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "shipped")
    private LocalDate shipped;

    @Column(name = "delivered")
    private LocalDate delivered;

    @ManyToOne
    @JsonIgnoreProperties(value = "orderItems", allowSetters = true)
    private SalesOrder salesOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public SalesOrderItem barcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public SalesOrderItem skuCode(String skuCode) {
        this.skuCode = skuCode;
        return this;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getName() {
        return name;
    }

    public SalesOrderItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SalesOrderItem price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSprec() {
        return sprec;
    }

    public SalesOrderItem sprec(String sprec) {
        this.sprec = sprec;
        return this;
    }

    public void setSprec(String sprec) {
        this.sprec = sprec;
    }

    public SalesOrderItemStatus getStatus() {
        return status;
    }

    public SalesOrderItem status(SalesOrderItemStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(SalesOrderItemStatus status) {
        this.status = status;
    }

    public UnitOfMeasurement getSalesUnit() {
        return salesUnit;
    }

    public SalesOrderItem salesUnit(UnitOfMeasurement salesUnit) {
        this.salesUnit = salesUnit;
        return this;
    }

    public void setSalesUnit(UnitOfMeasurement salesUnit) {
        this.salesUnit = salesUnit;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public SalesOrderItem discountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public SalesOrderItem discountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        return this;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public SalesOrderItem quantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public SalesOrderItem subtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public SalesOrderItem shipped(LocalDate shipped) {
        this.shipped = shipped;
        return this;
    }

    public void setShipped(LocalDate shipped) {
        this.shipped = shipped;
    }

    public LocalDate getDelivered() {
        return delivered;
    }

    public SalesOrderItem delivered(LocalDate delivered) {
        this.delivered = delivered;
        return this;
    }

    public void setDelivered(LocalDate delivered) {
        this.delivered = delivered;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public SalesOrderItem salesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
        return this;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderItem)) {
            return false;
        }
        return id != null && id.equals(((SalesOrderItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderItem{" +
            "id=" + getId() +
            ", barcode='" + getBarcode() + "'" +
            ", skuCode='" + getSkuCode() + "'" +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", sprec='" + getSprec() + "'" +
            ", status='" + getStatus() + "'" +
            ", salesUnit='" + getSalesUnit() + "'" +
            ", discountRate=" + getDiscountRate() +
            ", discountAmount=" + getDiscountAmount() +
            ", quantity=" + getQuantity() +
            ", subtotal=" + getSubtotal() +
            ", shipped='" + getShipped() + "'" +
            ", delivered='" + getDelivered() + "'" +
            "}";
    }
}
