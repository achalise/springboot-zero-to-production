package spring.tutorial.zerotohero.booking.service;

import spring.tutorial.zerotohero.booking.Booking;

import java.util.List;

public interface BookingService {
    Booking getBooking(String id);
    Booking createBooking(Booking booking);
    List<Booking> getBookings();
}
