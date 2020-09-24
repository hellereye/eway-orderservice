package com.mycloud.order;

import com.mycloud.order.config.ApplicationProperties;

import com.mycloud.order.domain.SalesOrder;
import com.mycloud.order.domain.SalesOrderItem;
import com.mycloud.order.domain.enumeration.SalesOrderStatus;
import com.mycloud.order.repository.SalesOrderRepository;
import io.github.jhipster.config.DefaultProfileUtil;
import io.github.jhipster.config.JHipsterConstants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class OrderServiceApp implements CommandLineRunner {

    @Autowired
    SalesOrderRepository salesOrderRepository;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceApp.class);

    private final Environment env;

    public OrderServiceApp(Environment env) {
        this.env = env;
    }

    /**
     * Initializes orderService.
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="https://www.jhipster.tech/profiles/">https://www.jhipster.tech/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " +
                "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrderServiceApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}{}\n\t" +
                "External: \t{}://{}:{}{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles());

        String configServerStatus = env.getProperty("configserver.status");
        if (configServerStatus == null) {
            configServerStatus = "Not found or not setup for this application";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Config Server: \t{}\n----------------------------------------------------------", configServerStatus);
    }

    @Override
    public void run(String... args) throws Exception {
//        SalesOrderItem salesOrderItem = new SalesOrderItem();
//
//        salesOrderItem.setBarcode("12345678");
//        salesOrderItem.setSkuCode("sku-fanfree-1");
//        salesOrderItem.setName("fanfree-item-1");
//        BigDecimal price = new BigDecimal(999);
//        salesOrderItem.setPrice(price);
//
//        SalesOrderItem salesOrderItem1 = new SalesOrderItem();
//
//        salesOrderItem1.setBarcode("12345678");
//        salesOrderItem1.setSkuCode("sku-fanfree-2");
//        salesOrderItem1.setName("fanfree-item-2");
//
//        salesOrderItem1.setPrice(price);
//
//        SalesOrder salesOrder = new SalesOrder();
//
//        salesOrder.setOrderNo(8888L);
//        salesOrder.setDealerCode("fanfree");
//        salesOrder.setDeliveryAddress("安宁庄");
//        BigDecimal amount = new BigDecimal(999);
//        salesOrder.setAmount(amount);
//        salesOrder.setStatus(SalesOrderStatus.CANCELLED);
//        BigDecimal total = new BigDecimal(999);
//        salesOrder.setTotal(total);
//
//        salesOrder.addOrderItems(salesOrderItem);
//        salesOrder.addOrderItems(salesOrderItem1);
//
//        //salesOrderItem.setSalesOrder(salesOrder);
//        //salesOrderItem1.setSalesOrder(salesOrder);//不用设置维护关系，addOrderItems方法中已经维护过了；
//
//        salesOrderRepository.save(salesOrder);

        List<SalesOrder> salesOrderList = salesOrderRepository.findAll();
        System.out.println("----------:"+salesOrderList.size());
//        Optional<SalesOrder> so= salesOrderRepository.findById(40L);
//        so.ifPresent((so1)->{
//            so1.getOrderItems().forEach(item-> System.out.println("==========="+item.getSkuCode()));
//        });
        salesOrderList.forEach((fOrder) -> {
            System.out.println("======= Order : "+fOrder.getOrderNo());
            Set<SalesOrderItem> items=fOrder.getOrderItems();
            items.forEach(item-> System.out.println("--------"+item.getSkuCode()));
        });

    }
}
