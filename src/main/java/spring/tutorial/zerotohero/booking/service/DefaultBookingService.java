package spring.tutorial.zerotohero.booking.service;

import spring.tutorial.zerotohero.booking.Booking;
import spring.tutorial.zerotohero.booking.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultBookingService implements BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    @Override
    public Booking getBooking(String id) {
        return bookings.stream().filter(booking1 -> booking1.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Booking createBooking(Booking booking) {
        Booking b = new Booking(Integer.toString(counter.getAndIncrement()), booking.userId(), booking.propertyId(), BookingStatus.PENDING);
        bookings.add(b);
        return b;
    }

    @Override
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
}
