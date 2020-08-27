package com.mycloud.order.service.mapper;


import com.mycloud.order.domain.*;
import com.mycloud.order.service.dto.SalesOrderItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderItem} and its DTO {@link SalesOrderItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {SalesOrderMapper.class})
public interface SalesOrderItemMapper extends EntityMapper<SalesOrderItemDTO, SalesOrderItem> {

    @Mapping(source = "salesOrder.id", target = "salesOrderId")
    SalesOrderItemDTO toDto(SalesOrderItem salesOrderItem);

    @Mapping(source = "salesOrderId", target = "salesOrder")
    SalesOrderItem toEntity(SalesOrderItemDTO salesOrderItemDTO);

    default SalesOrderItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesOrderItem salesOrderItem = new SalesOrderItem();
        salesOrderItem.setId(id);
        return salesOrderItem;
    }
}
