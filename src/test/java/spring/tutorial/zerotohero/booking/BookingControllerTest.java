package spring.tutorial.zerotohero.booking;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetByNonExistentIdReturnsBookingNotFound() {
        ResponseEntity<BookingRecord> bookingResponse = restTemplate.getForEntity("/booking/{id}", BookingRecord.class, 1L);
        Assertions.assertTrue(bookingResponse.getStatusCode().is4xxClientError());
    }

    @Test
    public void testGetByExistingIdReturnsBookingFound() {
        BookingRecord booking = new BookingRecord("123", "test@user.com", "prop1", BookingStatus.PENDING);
        ResponseEntity<BookingRecord> bookingResponseEntity = restTemplate.postForEntity("/booking", booking, BookingRecord.class);

        ResponseEntity<BookingRecord> bookingResponse = restTemplate.getForEntity("/booking/{id}", BookingRecord.class, bookingResponseEntity.getBody().id());
        Assertions.assertTrue(bookingResponse.getStatusCode().is2xxSuccessful());
        ResponseEntity<List<BookingRecord>> bookings = restTemplate.exchange("/booking", HttpMethod.GET, null, new ParameterizedTypeReference<List<BookingRecord>>() {});
        Assertions.assertEquals(1, bookings.getBody().size());
    }
}