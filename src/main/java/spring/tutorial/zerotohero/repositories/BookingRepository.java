package spring.tutorial.zerotohero.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.tutorial.zerotohero.booking.BookingRecord;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, String> {
}
