package com.mycloud.order.web.rest;

import com.mycloud.order.OrderServiceApp;
import com.mycloud.order.domain.SalesOrderItem;
import com.mycloud.order.repository.SalesOrderItemRepository;
import com.mycloud.order.service.SalesOrderItemService;
import com.mycloud.order.service.dto.SalesOrderItemDTO;
import com.mycloud.order.service.mapper.SalesOrderItemMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycloud.order.domain.enumeration.SalesOrderItemStatus;
import com.mycloud.order.domain.enumeration.UnitOfMeasurement;
/**
 * Integration tests for the {@link SalesOrderItemResource} REST controller.
 */
@SpringBootTest(classes = OrderServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SalesOrderItemResourceIT {

    private static final String DEFAULT_BARCODE = "AAAAAAAAAA";
    private static final String UPDATED_BARCODE = "BBBBBBBBBB";

    private static final String DEFAULT_SKU_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SKU_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final String DEFAULT_SPREC = "AAAAAAAAAA";
    private static final String UPDATED_SPREC = "BBBBBBBBBB";

    private static final SalesOrderItemStatus DEFAULT_STATUS = SalesOrderItemStatus.AVAILABLE;
    private static final SalesOrderItemStatus UPDATED_STATUS = SalesOrderItemStatus.OUT_OF_STOCK;

    private static final UnitOfMeasurement DEFAULT_SALES_UNIT = UnitOfMeasurement.PIECE;
    private static final UnitOfMeasurement UPDATED_SALES_UNIT = UnitOfMeasurement.KILOGRAM;

    private static final Double DEFAULT_DISCOUNT_RATE = 1D;
    private static final Double UPDATED_DISCOUNT_RATE = 2D;

    private static final BigDecimal DEFAULT_DISCOUNT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SUBTOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUBTOTAL = new BigDecimal(2);

    private static final LocalDate DEFAULT_SHIPPED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SHIPPED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DELIVERED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERED = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SalesOrderItemRepository salesOrderItemRepository;

    @Autowired
    private SalesOrderItemMapper salesOrderItemMapper;

    @Autowired
    private SalesOrderItemService salesOrderItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesOrderItemMockMvc;

    private SalesOrderItem salesOrderItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderItem createEntity(EntityManager em) {
        SalesOrderItem salesOrderItem = new SalesOrderItem()
            .barcode(DEFAULT_BARCODE)
            .skuCode(DEFAULT_SKU_CODE)
            .name(DEFAULT_NAME)
            .price(DEFAULT_PRICE)
            .sprec(DEFAULT_SPREC)
            .status(DEFAULT_STATUS)
            .salesUnit(DEFAULT_SALES_UNIT)
            .discountRate(DEFAULT_DISCOUNT_RATE)
            .discountAmount(DEFAULT_DISCOUNT_AMOUNT)
            .quantity(DEFAULT_QUANTITY)
            .subtotal(DEFAULT_SUBTOTAL)
            .shipped(DEFAULT_SHIPPED)
            .delivered(DEFAULT_DELIVERED);
        return salesOrderItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesOrderItem createUpdatedEntity(EntityManager em) {
        SalesOrderItem salesOrderItem = new SalesOrderItem()
            .barcode(UPDATED_BARCODE)
            .skuCode(UPDATED_SKU_CODE)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .sprec(UPDATED_SPREC)
            .status(UPDATED_STATUS)
            .salesUnit(UPDATED_SALES_UNIT)
            .discountRate(UPDATED_DISCOUNT_RATE)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT)
            .quantity(UPDATED_QUANTITY)
            .subtotal(UPDATED_SUBTOTAL)
            .shipped(UPDATED_SHIPPED)
            .delivered(UPDATED_DELIVERED);
        return salesOrderItem;
    }

    @BeforeEach
    public void initTest() {
        salesOrderItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesOrderItem() throws Exception {
        int databaseSizeBeforeCreate = salesOrderItemRepository.findAll().size();
        // Create the SalesOrderItem
        SalesOrderItemDTO salesOrderItemDTO = salesOrderItemMapper.toDto(salesOrderItem);
        restSalesOrderItemMockMvc.perform(post("/api/sales-order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderItemDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesOrderItem in the database
        List<SalesOrderItem> salesOrderItemList = salesOrderItemRepository.findAll();
        assertThat(salesOrderItemList).hasSize(databaseSizeBeforeCreate + 1);
        SalesOrderItem testSalesOrderItem = salesOrderItemList.get(salesOrderItemList.size() - 1);
        assertThat(testSalesOrderItem.getBarcode()).isEqualTo(DEFAULT_BARCODE);
        assertThat(testSalesOrderItem.getSkuCode()).isEqualTo(DEFAULT_SKU_CODE);
        assertThat(testSalesOrderItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSalesOrderItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSalesOrderItem.getSprec()).isEqualTo(DEFAULT_SPREC);
        assertThat(testSalesOrderItem.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSalesOrderItem.getSalesUnit()).isEqualTo(DEFAULT_SALES_UNIT);
        assertThat(testSalesOrderItem.getDiscountRate()).isEqualTo(DEFAULT_DISCOUNT_RATE);
        assertThat(testSalesOrderItem.getDiscountAmount()).isEqualTo(DEFAULT_DISCOUNT_AMOUNT);
        assertThat(testSalesOrderItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testSalesOrderItem.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testSalesOrderItem.getShipped()).isEqualTo(DEFAULT_SHIPPED);
        assertThat(testSalesOrderItem.getDelivered()).isEqualTo(DEFAULT_DELIVERED);
    }

    @Test
    @Transactional
    public void createSalesOrderItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesOrderItemRepository.findAll().size();

        // Create the SalesOrderItem with an existing ID
        salesOrderItem.setId(1L);
        SalesOrderItemDTO salesOrderItemDTO = salesOrderItemMapper.toDto(salesOrderItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesOrderItemMockMvc.perform(post("/api/sales-order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderItem in the database
        List<SalesOrderItem> salesOrderItemList = salesOrderItemRepository.findAll();
        assertThat(salesOrderItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSalesOrderItems() throws Exception {
        // Initialize the database
        salesOrderItemRepository.saveAndFlush(salesOrderItem);

        // Get all the salesOrderItemList
        restSalesOrderItemMockMvc.perform(get("/api/sales-order-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesOrderItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].barcode").value(hasItem(DEFAULT_BARCODE)))
            .andExpect(jsonPath("$.[*].skuCode").value(hasItem(DEFAULT_SKU_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].sprec").value(hasItem(DEFAULT_SPREC)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].salesUnit").value(hasItem(DEFAULT_SALES_UNIT.toString())))
            .andExpect(jsonPath("$.[*].discountRate").value(hasItem(DEFAULT_DISCOUNT_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].discountAmount").value(hasItem(DEFAULT_DISCOUNT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.intValue())))
            .andExpect(jsonPath("$.[*].shipped").value(hasItem(DEFAULT_SHIPPED.toString())))
            .andExpect(jsonPath("$.[*].delivered").value(hasItem(DEFAULT_DELIVERED.toString())));
    }
    
    @Test
    @Transactional
    public void getSalesOrderItem() throws Exception {
        // Initialize the database
        salesOrderItemRepository.saveAndFlush(salesOrderItem);

        // Get the salesOrderItem
        restSalesOrderItemMockMvc.perform(get("/api/sales-order-items/{id}", salesOrderItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesOrderItem.getId().intValue()))
            .andExpect(jsonPath("$.barcode").value(DEFAULT_BARCODE))
            .andExpect(jsonPath("$.skuCode").value(DEFAULT_SKU_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.sprec").value(DEFAULT_SPREC))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.salesUnit").value(DEFAULT_SALES_UNIT.toString()))
            .andExpect(jsonPath("$.discountRate").value(DEFAULT_DISCOUNT_RATE.doubleValue()))
            .andExpect(jsonPath("$.discountAmount").value(DEFAULT_DISCOUNT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.intValue()))
            .andExpect(jsonPath("$.shipped").value(DEFAULT_SHIPPED.toString()))
            .andExpect(jsonPath("$.delivered").value(DEFAULT_DELIVERED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSalesOrderItem() throws Exception {
        // Get the salesOrderItem
        restSalesOrderItemMockMvc.perform(get("/api/sales-order-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesOrderItem() throws Exception {
        // Initialize the database
        salesOrderItemRepository.saveAndFlush(salesOrderItem);

        int databaseSizeBeforeUpdate = salesOrderItemRepository.findAll().size();

        // Update the salesOrderItem
        SalesOrderItem updatedSalesOrderItem = salesOrderItemRepository.findById(salesOrderItem.getId()).get();
        // Disconnect from session so that the updates on updatedSalesOrderItem are not directly saved in db
        em.detach(updatedSalesOrderItem);
        updatedSalesOrderItem
            .barcode(UPDATED_BARCODE)
            .skuCode(UPDATED_SKU_CODE)
            .name(UPDATED_NAME)
            .price(UPDATED_PRICE)
            .sprec(UPDATED_SPREC)
            .status(UPDATED_STATUS)
            .salesUnit(UPDATED_SALES_UNIT)
            .discountRate(UPDATED_DISCOUNT_RATE)
            .discountAmount(UPDATED_DISCOUNT_AMOUNT)
            .quantity(UPDATED_QUANTITY)
            .subtotal(UPDATED_SUBTOTAL)
            .shipped(UPDATED_SHIPPED)
            .delivered(UPDATED_DELIVERED);
        SalesOrderItemDTO salesOrderItemDTO = salesOrderItemMapper.toDto(updatedSalesOrderItem);

        restSalesOrderItemMockMvc.perform(put("/api/sales-order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderItemDTO)))
            .andExpect(status().isOk());

        // Validate the SalesOrderItem in the database
        List<SalesOrderItem> salesOrderItemList = salesOrderItemRepository.findAll();
        assertThat(salesOrderItemList).hasSize(databaseSizeBeforeUpdate);
        SalesOrderItem testSalesOrderItem = salesOrderItemList.get(salesOrderItemList.size() - 1);
        assertThat(testSalesOrderItem.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testSalesOrderItem.getSkuCode()).isEqualTo(UPDATED_SKU_CODE);
        assertThat(testSalesOrderItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSalesOrderItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSalesOrderItem.getSprec()).isEqualTo(UPDATED_SPREC);
        assertThat(testSalesOrderItem.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSalesOrderItem.getSalesUnit()).isEqualTo(UPDATED_SALES_UNIT);
        assertThat(testSalesOrderItem.getDiscountRate()).isEqualTo(UPDATED_DISCOUNT_RATE);
        assertThat(testSalesOrderItem.getDiscountAmount()).isEqualTo(UPDATED_DISCOUNT_AMOUNT);
        assertThat(testSalesOrderItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testSalesOrderItem.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testSalesOrderItem.getShipped()).isEqualTo(UPDATED_SHIPPED);
        assertThat(testSalesOrderItem.getDelivered()).isEqualTo(UPDATED_DELIVERED);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesOrderItem() throws Exception {
        int databaseSizeBeforeUpdate = salesOrderItemRepository.findAll().size();

        // Create the SalesOrderItem
        SalesOrderItemDTO salesOrderItemDTO = salesOrderItemMapper.toDto(salesOrderItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesOrderItemMockMvc.perform(put("/api/sales-order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesOrderItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesOrderItem in the database
        List<SalesOrderItem> salesOrderItemList = salesOrderItemRepository.findAll();
        assertThat(salesOrderItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesOrderItem() throws Exception {
        // Initialize the database
        salesOrderItemRepository.saveAndFlush(salesOrderItem);

        int databaseSizeBeforeDelete = salesOrderItemRepository.findAll().size();

        // Delete the salesOrderItem
        restSalesOrderItemMockMvc.perform(delete("/api/sales-order-items/{id}", salesOrderItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesOrderItem> salesOrderItemList = salesOrderItemRepository.findAll();
        assertThat(salesOrderItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
