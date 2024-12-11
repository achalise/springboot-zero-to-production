package spring.tutorial.zerotohero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.tutorial.zerotohero.booking.service.BookingService;
import spring.tutorial.zerotohero.booking.service.DefaultBookingService;

@SpringBootApplication
public class ZerotoheroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerotoheroApplication.class, args);
	}

}

@Configuration
class Config {

	@Bean
	public BookingService bookingService() {
		return new DefaultBookingService();
	}
}
