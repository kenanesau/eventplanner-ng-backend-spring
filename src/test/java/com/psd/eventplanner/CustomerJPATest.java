package com.psd.eventplanner;

import com.psd.eventplanner.entity.Customer;
import com.psd.eventplanner.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = {com.psd.eventplanner.EventPlannerBackendApplication.class})
class CustomerJPATest {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private CustomerRepository customerRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(customerRepository).isNotNull();
    }

    @Test
    public void findCustomerByIdTest() {
        Customer cust = customerRepository.findCustomerById(1L);
        assertThat(cust).isNotNull();
        assertThat(cust.getId()).isEqualTo(1L);
    }

    @Test
    void findAllCutomers() {
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(3);
    }

    @Test
    @DirtiesContext
    void saveCustomer() {
        Customer cust = new Customer(0L, "Jack", "Ripper", "jacktheripper@buckinghampalast.co.uk");
        cust = customerRepository.save(cust);
        assertThat(cust.getId()).isNotEqualTo(0L);
        assertThat(cust.getFirstName()).isEqualTo("Jack");
        assertThat(cust.getLastName()).isEqualTo("Ripper");
        assertThat(cust.getEmail()).isEqualTo("jacktheripper@buckinghampalast.co.uk");

        String sql = "SELECT id, first_name, last_name, email FROM customer WHERE id = ?";
        Customer cust2 = jdbcTemplate.queryForObject(sql,
                new Object[]{cust.getId()}, new int[]{java.sql.Types.BIGINT}, new BeanPropertyRowMapper<>(Customer.class));

        assertThat(cust2).isEqualTo(cust);
    }

    @Configuration
    static class Config {

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setName("empty-sql-scripts-without-tx-mgr-test-db")
                    .addScripts("/import.sql")
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }
    }
}
