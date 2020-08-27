package com.mycloud.order.service.impl;

import com.mycloud.order.service.SalesOrderService;
import com.mycloud.order.domain.SalesOrder;
import com.mycloud.order.repository.SalesOrderRepository;
import com.mycloud.order.service.dto.SalesOrderDTO;
import com.mycloud.order.service.mapper.SalesOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SalesOrder}.
 */
@Service
@Transactional
public class SalesOrderServiceImpl implements SalesOrderService {

    private final Logger log = LoggerFactory.getLogger(SalesOrderServiceImpl.class);

    private final SalesOrderRepository salesOrderRepository;

    private final SalesOrderMapper salesOrderMapper;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository, SalesOrderMapper salesOrderMapper) {
        this.salesOrderRepository = salesOrderRepository;
        this.salesOrderMapper = salesOrderMapper;
    }

    @Override
    public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
        log.debug("Request to save SalesOrder : {}", salesOrderDTO);
        SalesOrder salesOrder = salesOrderMapper.toEntity(salesOrderDTO);
        salesOrder = salesOrderRepository.save(salesOrder);
        return salesOrderMapper.toDto(salesOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesOrders");
        return salesOrderRepository.findAll(pageable)
            .map(salesOrderMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SalesOrderDTO> findOne(Long id) {
        log.debug("Request to get SalesOrder : {}", id);
        return salesOrderRepository.findById(id)
            .map(salesOrderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesOrder : {}", id);
        salesOrderRepository.deleteById(id);
    }
}
