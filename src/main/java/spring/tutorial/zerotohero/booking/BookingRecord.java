package spring.tutorial.zerotohero.booking;

import spring.tutorial.zerotohero.repositories.Booking;

import java.util.UUID;

public record BookingRecord(String id, String userId, String propertyId, BookingStatus status) {
    public Booking toBooking() {
        Booking b = new Booking();
        b.propertyId(propertyId).userId(userId).status(status.name()).id(UUID.randomUUID().toString());
        return b;
    }
}
