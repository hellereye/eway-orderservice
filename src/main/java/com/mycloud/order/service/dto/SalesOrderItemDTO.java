package com.mycloud.order.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import com.mycloud.order.domain.enumeration.SalesOrderItemStatus;
import com.mycloud.order.domain.enumeration.UnitOfMeasurement;

/**
 * A DTO for the {@link com.mycloud.order.domain.SalesOrderItem} entity.
 */
public class SalesOrderItemDTO implements Serializable {
    
    private Long id;

    private String barcode;

    private String skuCode;

    private String name;

    private BigDecimal price;

    private String sprec;

    private SalesOrderItemStatus status;

    private UnitOfMeasurement salesUnit;

    private Double discountRate;

    private BigDecimal discountAmount;

    private BigDecimal quantity;

    private BigDecimal subtotal;

    private LocalDate shipped;

    private LocalDate delivered;


    private Long salesOrderId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSprec() {
        return sprec;
    }

    public void setSprec(String sprec) {
        this.sprec = sprec;
    }

    public SalesOrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(SalesOrderItemStatus status) {
        this.status = status;
    }

    public UnitOfMeasurement getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(UnitOfMeasurement salesUnit) {
        this.salesUnit = salesUnit;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public void setShipped(LocalDate shipped) {
        this.shipped = shipped;
    }

    public LocalDate getDelivered() {
        return delivered;
    }

    public void setDelivered(LocalDate delivered) {
        this.delivered = delivered;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderItemDTO)) {
            return false;
        }

        return id != null && id.equals(((SalesOrderItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderItemDTO{" +
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
            ", salesOrderId=" + getSalesOrderId() +
            "}";
    }
}
