package com.psd.eventplanner;

import com.psd.eventplanner.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Customer> json;

    @Test
    void customerSerializationTest() throws IOException {
        Customer customer = new Customer(1L, "Karl", "Dall", "karl@dall.com");

        assertThat(json.write(customer)).isStrictlyEqualToJson("""
                {
                    "id":1,
                    "firstName": "Karl",
                    "lastName": "Dall",
                    "email": "karl@dall.com"
                }
                """);
        assertThat(json.write(customer)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(customer)).extractingJsonPathValue("@.firstName")
                .isEqualTo("Karl");
    }

    @Test
    void customerDeserializationTest() throws IOException {
        String expected = """
                {
                    "id":1,
                    "firstName": "Karl",
                    "lastName": "Dall",
                    "email": "karl@dall.com"
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(
                new Customer(1L, "Karl", "Dall", "karl@dall.com"));
        assertThat(json.parseObject(expected).getFirstName()).isEqualTo("Karl");
    }
}

