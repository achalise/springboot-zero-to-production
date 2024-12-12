package spring.tutorial.zerotohero.booking.service;

import spring.tutorial.zerotohero.booking.BookingRecord;
import spring.tutorial.zerotohero.booking.BookingStatus;
import spring.tutorial.zerotohero.repositories.Booking;
import spring.tutorial.zerotohero.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DefaultBookingService implements BookingService {
    private final List<BookingRecord> bookings = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private final BookingRepository bookingRepository;
    public DefaultBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Optional<BookingRecord> getBooking(String id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.map(Booking::toBookingRecord);
    }

    @Override
    public BookingRecord createBooking(BookingRecord booking) {
        Booking booking1 = booking.toBooking();
        Booking saved = bookingRepository.save(booking1);
        return saved.toBookingRecord();
    }

    @Override
    public List<BookingRecord> getBookings() {
        return StreamSupport.stream(bookingRepository.findAll().spliterator(), false).map(Booking::toBookingRecord).collect(Collectors.toList());
    }
}
