package model;

import java.util.LinkedList;


public class Booking {
    private String bookingId;
    private String userId;
    private String tourId;
    private String bookingDate;
    
    // Danh sách các tour đã đặt (nếu cần dùng cho logic khác)
    protected LinkedList<Tour> bookings;
    
    // Constructor đầy đủ với các tham số
    public Booking(String bookingId, String userId, String tourId, String bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tourId = tourId;
        this.bookingDate = bookingDate;
    }

    // Getter và Setter cho các thuộc tính
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

  

    public Booking() {
        bookings = new LinkedList<>();
    }

    public void addBooking(Tour tour) {
        bookings.add(tour);
    }

    public void cancelBooking(Tour tour) {
        bookings.remove(tour);
    }

    public LinkedList<Tour> getAllBookings() {
        return bookings;
    }
}
