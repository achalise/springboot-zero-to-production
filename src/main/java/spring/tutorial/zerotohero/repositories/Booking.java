package spring.tutorial.zerotohero.repositories;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import spring.tutorial.zerotohero.booking.BookingRecord;
import spring.tutorial.zerotohero.booking.BookingStatus;

@Entity
public class Booking {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private String status;

    public BookingRecord toBookingRecord() {
        return new BookingRecord(id, userId, propertyId, BookingStatus.valueOf(status));
    }

    public Booking id(String id) {
        this.id = id;
        return this;
    }

    public Booking userId(String userId) {
        this.userId = userId;
        return this;
    }

    public Booking propertyId(String propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public Booking status(String status) {
        this.status = status;
        return this;
    }
}
