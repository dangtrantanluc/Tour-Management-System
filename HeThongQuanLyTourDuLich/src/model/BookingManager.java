package model;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings;

    public BookingManager() {
        this.bookings = new ArrayList<>();
    }


    // Thêm một booking mới
    public boolean addBooking(Booking booking) {
        for (Booking existingBooking : bookings) {
            if (existingBooking.getBookingId().equals(booking.getBookingId())) {
                System.out.println("Error: Booking ID already exists!");
                return false;
            }
        }
        bookings.add(booking);
        System.out.println("Booking added successfully!");
        return true;
    }

    // Tìm một booking theo ID
    public Booking findBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        System.out.println("Error: Booking ID not found!");
        return null;
    }

    // In ra danh sách tất cả các bookings (nếu cần)
    public List<Booking> getAllBookings() {
        return bookings;
    }
}