package com.psd.eventplanner;

import com.psd.eventplanner.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = {com.psd.eventplanner.EventPlannerBackendApplication.class})
class JPATest {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private CustomerRepository customerRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(customerRepository).isNotNull();
    }

    @Configuration
    static class Config {

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setName("empty-sql-scripts-without-tx-mgr-test-db")//
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }
    }
}
