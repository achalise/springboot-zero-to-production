package spring.tutorial.zerotohero.booking;

public record Booking(String id, String userId, String propertyId, BookingStatus status) {
}
