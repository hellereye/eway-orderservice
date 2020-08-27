package com.mycloud.order.web.rest;

import com.mycloud.order.OrderServiceApp;
import com.mycloud.order.domain.SalesOrder;
import com.mycloud.order.repository.SalesOrderRepository;
import com.mycloud.order.service.SalesOrderService;
import com.mycloud.order.service.dto.SalesOrderDTO;
import com.mycloud.order.service.mapper.SalesOrderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycloud.order.domain.enumeration.SalesOrderStatus;
/**
 * Integration tests for the {@link SalesOrderResource} REST controller.
 */
@SpringBootTest(classes = OrderServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SalesOrderResourceIT {

    private static final Long DEFAULT_ORDER_NO = 1L;
    private static final Long UPDATED_ORDER_NO = 2L;

    private static final String DEFAULT_DELIVERY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DEALER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEALER_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final SalesOrderStatus DEFAULT_STATUS = SalesOrderStatus.PENDING;
    private static final SalesOrderStatus UPDATED_STATUS = SalesOrderStatus.CANCELLED;

    private static final BigDecimal DEFAULT_TOTAL = new BigDecimal(0);
    private static final BigDecimal UPDATED_TOTAL = new BigDecimal(1);

    private static final Boolean DEFAULT_IS_INVOICING = false;
    private static final Boolean UPDATED_IS_INVOICING = true;

    private static final Long DEFAULT_INVOICE_NO = 1L;
    private static final Long UPDATED_INVOICE_NO = 2L;

    private static final Instant DEFAULT_PLACED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PLACED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PAY_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_PAY_CHANNEL = "BBBBBBBBBB";

    private static final Instant DEFAULT_PAYDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAYDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_OUT_TRADE_NO = "AAAAAAAAAA";
    private static final String UPDATED_OUT_TRADE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SETTLEMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_SETTLEMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SETTLEMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DELIVERIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIVERIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CUSTOMER_NO = 1L;
    private static final Long UPDATED_CUSTOMER_NO = 2L;

    private static final String DEFAULT_CUSTOMER_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_REMARK = "BBBBBBBBBB";

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesOrderMockMvc;

    private SalesOrder salesOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrder createEntity(EntityManager em) {
        SalesOrder salesOrder = new SalesOrder()
            .orderNo(DEFAULT_ORDER_NO)
            .deliveryAddress(DEFAULT_DELIVERY_ADDRESS)
            .dealerCode(DEFAULT_DEALER_CODE)
            .amount(DEFAULT_AMOUNT)
            .status(DEFAULT_STATUS)
            .total(DEFAULT_TOTAL)
            .isInvoicing(DEFAULT_IS_INVOICING)
            .invoiceNo(DEFAULT_INVOICE_NO)
            .placedDate(DEFAULT_PLACED_DATE)
            .payChannel(DEFAULT_PAY_CHANNEL)
            .paydate(DEFAULT_PAYDATE)
            .outTradeNo(DEFAULT_OUT_TRADE_NO)
            .settlementStatus(DEFAULT_SETTLEMENT_STATUS)
            .settlementDate(DEFAULT_SETTLEMENT_DATE)
            .deliveriedDate(DEFAULT_DELIVERIED_DATE)
            .customerNo(DEFAULT_CUSTOMER_NO)
            .customerRemark(DEFAULT_CUSTOMER_REMARK);
        return salesOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrder createUpdatedEntity(EntityManager em) {
        SalesOrder salesOrder = new SalesOrder()
            .orderNo(UPDATED_ORDER_NO)
            .deliveryAddress(UPDATED_DELIVERY_ADDRESS)
            .dealerCode(UPDATED_DEALER_CODE)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .total(UPDATED_TOTAL)
            .isInvoicing(UPDATED_IS_INVOICING)
            .invoiceNo(UPDATED_INVOICE_NO)
            .placedDate(UPDATED_PLACED_DATE)
            .payChannel(UPDATED_PAY_CHANNEL)
            .paydate(UPDATED_PAYDATE)
            .outTradeNo(UPDATED_OUT_TRADE_NO)
            .settlementStatus(UPDATED_SETTLEMENT_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .deliveriedDate(UPDATED_DELIVERIED_DATE)
            .customerNo(UPDATED_CUSTOMER_NO)
            .customerRemark(UPDATED_CUSTOMER_REMARK);
        return salesOrder;
    }

    @BeforeEach
    public void initTest() {
        salesOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesOrder() throws Exception {
        int databaseSizeBeforeCreate = salesOrderRepository.findAll().size();
        // Create the SalesOrder
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);
        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesOrder in the database
        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrder testSalesOrder = salesOrderList.get(salesOrderList.size() - 1);
        assertThat(testSalesOrder.getOrderNo()).isEqualTo(DEFAULT_ORDER_NO);
        assertThat(testSalesOrder.getDeliveryAddress()).isEqualTo(DEFAULT_DELIVERY_ADDRESS);
        assertThat(testSalesOrder.getDealerCode()).isEqualTo(DEFAULT_DEALER_CODE);
        assertThat(testSalesOrder.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSalesOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrder.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testSalesOrder.isIsInvoicing()).isEqualTo(DEFAULT_IS_INVOICING);
        assertThat(testSalesOrder.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testSalesOrder.getPlacedDate()).isEqualTo(DEFAULT_PLACED_DATE);
        assertThat(testSalesOrder.getPayChannel()).isEqualTo(DEFAULT_PAY_CHANNEL);
        assertThat(testSalesOrder.getPaydate()).isEqualTo(DEFAULT_PAYDATE);
        assertThat(testSalesOrder.getOutTradeNo()).isEqualTo(DEFAULT_OUT_TRADE_NO);
        assertThat(testSalesOrder.getSettlementStatus()).isEqualTo(DEFAULT_SETTLEMENT_STATUS);
        assertThat(testSalesOrder.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testSalesOrder.getDeliveriedDate()).isEqualTo(DEFAULT_DELIVERIED_DATE);
        assertThat(testSalesOrder.getCustomerNo()).isEqualTo(DEFAULT_CUSTOMER_NO);
        assertThat(testSalesOrder.getCustomerRemark()).isEqualTo(DEFAULT_CUSTOMER_REMARK);
    }

    @Test
    @Transactional
    public void createSalesOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesOrderRepository.findAll().size();

        // Create the SalesOrder with an existing ID
        salesOrder.setId(1L);
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesOrder in the database
        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOrderNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesOrderRepository.findAll().size();
        // set the field null
        salesOrder.setOrderNo(null);

        // Create the SalesOrder, which fails.
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);


        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeliveryAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesOrderRepository.findAll().size();
        // set the field null
        salesOrder.setDeliveryAddress(null);

        // Create the SalesOrder, which fails.
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);


        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDealerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesOrderRepository.findAll().size();
        // set the field null
        salesOrder.setDealerCode(null);

        // Create the SalesOrder, which fails.
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);


        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesOrderRepository.findAll().size();
        // set the field null
        salesOrder.setAmount(null);

        // Create the SalesOrder, which fails.
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);


        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesOrderRepository.findAll().size();
        // set the field null
        salesOrder.setTotal(null);

        // Create the SalesOrder, which fails.
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);


        restSalesOrderMockMvc.perform(post("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSalesOrders() throws Exception {
        // Initialize the database
        salesOrderRepository.saveAndFlush(salesOrder);

        // Get all the salesOrderList
        restSalesOrderMockMvc.perform(get("/api/sales-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderNo").value(hasItem(DEFAULT_ORDER_NO.intValue())))
            .andExpect(jsonPath("$.[*].deliveryAddress").value(hasItem(DEFAULT_DELIVERY_ADDRESS)))
            .andExpect(jsonPath("$.[*].dealerCode").value(hasItem(DEFAULT_DEALER_CODE)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].isInvoicing").value(hasItem(DEFAULT_IS_INVOICING.booleanValue())))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO.intValue())))
            .andExpect(jsonPath("$.[*].placedDate").value(hasItem(DEFAULT_PLACED_DATE.toString())))
            .andExpect(jsonPath("$.[*].payChannel").value(hasItem(DEFAULT_PAY_CHANNEL)))
            .andExpect(jsonPath("$.[*].paydate").value(hasItem(DEFAULT_PAYDATE.toString())))
            .andExpect(jsonPath("$.[*].outTradeNo").value(hasItem(DEFAULT_OUT_TRADE_NO)))
            .andExpect(jsonPath("$.[*].settlementStatus").value(hasItem(DEFAULT_SETTLEMENT_STATUS)))
            .andExpect(jsonPath("$.[*].settlementDate").value(hasItem(DEFAULT_SETTLEMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].deliveriedDate").value(hasItem(DEFAULT_DELIVERIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].customerNo").value(hasItem(DEFAULT_CUSTOMER_NO.intValue())))
            .andExpect(jsonPath("$.[*].customerRemark").value(hasItem(DEFAULT_CUSTOMER_REMARK)));
    }
    
    @Test
    @Transactional
    public void getSalesOrder() throws Exception {
        // Initialize the database
        salesOrderRepository.saveAndFlush(salesOrder);

        // Get the salesOrder
        restSalesOrderMockMvc.perform(get("/api/sales-orders/{id}", salesOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesOrder.getId().intValue()))
            .andExpect(jsonPath("$.orderNo").value(DEFAULT_ORDER_NO.intValue()))
            .andExpect(jsonPath("$.deliveryAddress").value(DEFAULT_DELIVERY_ADDRESS))
            .andExpect(jsonPath("$.dealerCode").value(DEFAULT_DEALER_CODE))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.isInvoicing").value(DEFAULT_IS_INVOICING.booleanValue()))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO.intValue()))
            .andExpect(jsonPath("$.placedDate").value(DEFAULT_PLACED_DATE.toString()))
            .andExpect(jsonPath("$.payChannel").value(DEFAULT_PAY_CHANNEL))
            .andExpect(jsonPath("$.paydate").value(DEFAULT_PAYDATE.toString()))
            .andExpect(jsonPath("$.outTradeNo").value(DEFAULT_OUT_TRADE_NO))
            .andExpect(jsonPath("$.settlementStatus").value(DEFAULT_SETTLEMENT_STATUS))
            .andExpect(jsonPath("$.settlementDate").value(DEFAULT_SETTLEMENT_DATE.toString()))
            .andExpect(jsonPath("$.deliveriedDate").value(DEFAULT_DELIVERIED_DATE.toString()))
            .andExpect(jsonPath("$.customerNo").value(DEFAULT_CUSTOMER_NO.intValue()))
            .andExpect(jsonPath("$.customerRemark").value(DEFAULT_CUSTOMER_REMARK));
    }
    @Test
    @Transactional
    public void getNonExistingSalesOrder() throws Exception {
        // Get the salesOrder
        restSalesOrderMockMvc.perform(get("/api/sales-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesOrder() throws Exception {
        // Initialize the database
        salesOrderRepository.saveAndFlush(salesOrder);

        int databaseSizeBeforeUpdate = salesOrderRepository.findAll().size();

        // Update the salesOrder
        SalesOrder updatedSalesOrder = salesOrderRepository.findById(salesOrder.getId()).get();
        // Disconnect from session so that the updates on updatedSalesOrder are not directly saved in db
        em.detach(updatedSalesOrder);
        updatedSalesOrder
            .orderNo(UPDATED_ORDER_NO)
            .deliveryAddress(UPDATED_DELIVERY_ADDRESS)
            .dealerCode(UPDATED_DEALER_CODE)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .total(UPDATED_TOTAL)
            .isInvoicing(UPDATED_IS_INVOICING)
            .invoiceNo(UPDATED_INVOICE_NO)
            .placedDate(UPDATED_PLACED_DATE)
            .payChannel(UPDATED_PAY_CHANNEL)
            .paydate(UPDATED_PAYDATE)
            .outTradeNo(UPDATED_OUT_TRADE_NO)
            .settlementStatus(UPDATED_SETTLEMENT_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .deliveriedDate(UPDATED_DELIVERIED_DATE)
            .customerNo(UPDATED_CUSTOMER_NO)
            .customerRemark(UPDATED_CUSTOMER_REMARK);
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(updatedSalesOrder);

        restSalesOrderMockMvc.perform(put("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isOk());

        // Validate the SalesOrder in the database
        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeUpdate);
        SalesOrder testSalesOrder = salesOrderList.get(salesOrderList.size() - 1);
        assertThat(testSalesOrder.getOrderNo()).isEqualTo(UPDATED_ORDER_NO);
        assertThat(testSalesOrder.getDeliveryAddress()).isEqualTo(UPDATED_DELIVERY_ADDRESS);
        assertThat(testSalesOrder.getDealerCode()).isEqualTo(UPDATED_DEALER_CODE);
        assertThat(testSalesOrder.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSalesOrder.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrder.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testSalesOrder.isIsInvoicing()).isEqualTo(UPDATED_IS_INVOICING);
        assertThat(testSalesOrder.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testSalesOrder.getPlacedDate()).isEqualTo(UPDATED_PLACED_DATE);
        assertThat(testSalesOrder.getPayChannel()).isEqualTo(UPDATED_PAY_CHANNEL);
        assertThat(testSalesOrder.getPaydate()).isEqualTo(UPDATED_PAYDATE);
        assertThat(testSalesOrder.getOutTradeNo()).isEqualTo(UPDATED_OUT_TRADE_NO);
        assertThat(testSalesOrder.getSettlementStatus()).isEqualTo(UPDATED_SETTLEMENT_STATUS);
        assertThat(testSalesOrder.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testSalesOrder.getDeliveriedDate()).isEqualTo(UPDATED_DELIVERIED_DATE);
        assertThat(testSalesOrder.getCustomerNo()).isEqualTo(UPDATED_CUSTOMER_NO);
        assertThat(testSalesOrder.getCustomerRemark()).isEqualTo(UPDATED_CUSTOMER_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesOrder() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderRepository.findAll().size();

        // Create the SalesOrder
        SalesOrderDTO salesOrderDTO = salesOrderMapper.toDto(salesOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesOrderMockMvc.perform(put("/api/sales-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesOrder in the database
        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesOrder() throws Exception {
        // Initialize the database
        salesOrderRepository.saveAndFlush(salesOrder);

        int databaseSizeBeforeDelete = salesOrderRepository.findAll().size();

        // Delete the salesOrder
        restSalesOrderMockMvc.perform(delete("/api/sales-orders/{id}", salesOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        assertThat(salesOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
