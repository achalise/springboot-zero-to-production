package spring.tutorial.zerotohero.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.tutorial.zerotohero.booking.service.BookingService;
import spring.tutorial.zerotohero.repositories.Booking;

import java.util.List;

@RestController
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingRecord> getBooking(@PathVariable String id) {
      BookingRecord b = bookingService.getBooking(id).orElse(null);
      if (b == null) {
          return ResponseEntity.notFound().build();
      } else {
          return ResponseEntity.ok(b);
      }
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingRecord> createBooking(@RequestBody BookingRecord booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @GetMapping("/booking")
    public ResponseEntity<List<BookingRecord>> getBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }
}
