package spring.tutorial.zerotohero.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetByNonExistentIdReturnsBookingNotFound() {
        ResponseEntity<Booking> bookingResponse = restTemplate.getForEntity("/booking/{id}", Booking.class, 1L);
        Assertions.assertTrue(bookingResponse.getStatusCode().is4xxClientError());
    }

    @Test
    public void testGetByExistingIdReturnsBookingFound() {
        Booking booking = new Booking("123", "test@user.com", "prop1", null);
        ResponseEntity<Booking> bookingResponseEntity = restTemplate.postForEntity("/booking", booking, Booking.class);

        ResponseEntity<Booking> bookingResponse = restTemplate.getForEntity("/booking/{id}", Booking.class, bookingResponseEntity.getBody().id());
        Assertions.assertTrue(bookingResponse.getStatusCode().is2xxSuccessful());
        ResponseEntity<List<Booking>> bookings = restTemplate.exchange("/booking", HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {});
        Assertions.assertEquals(1, bookings.getBody().size());
    }
}