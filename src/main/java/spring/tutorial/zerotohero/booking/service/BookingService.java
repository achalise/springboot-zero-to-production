package spring.tutorial.zerotohero.booking.service;

import spring.tutorial.zerotohero.booking.BookingRecord;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<BookingRecord> getBooking(String id);
    BookingRecord createBooking(BookingRecord booking);
    List<BookingRecord> getBookings();
}
