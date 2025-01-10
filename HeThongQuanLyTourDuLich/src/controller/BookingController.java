package controller;

import model.*;
import view.BookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private BookingView view;
    private BookingManager bookingManager;
    private List<Booking> bookingList;
    private List<User> userList;
    private TourManager tourManager;
    private List<Tour> tourList;
    private UserManager userManager;

    public BookingController(BookingView view) {
        this.view = view;

        userList = createSampleUsers();
        tourList = createSampleTours();
        bookingList = createSampleBookings();
        bookingManager = new BookingManager();
        userManager = new UserManager();
        this.tourManager = new TourManager();
        initController();

        updateBookingTable();
    }

    private void initController() {
        view.setAddButtonListener(e -> addBookingListener());
        view.setUpdateButtonListener(e -> updateBookingListener());
        view.setDeleteButtonListener(e -> deleteBookingListener());
        view.setFindBookingTourButtonListener(e -> findBookingListener());
    }


    // Sample User Data
    private List<User> createSampleUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("ngan", "Lê Thị Thanh Ngân", "ngan179@gmail.com"));
        users.add(new User("luc", "Đặng Trần Tấn Lực", "tanluc@gmail.com"));
        users.add(new User("tuanh", "Phạm Trần Tuấn Anh", "trtuanh@gmail.com"));
        users.add(new User("nhut", "Nguyễn Minh Nhựt", "minhnhut@gmail.com"));
        return users;
    }

    // Sample Tour Data
    private List<Tour> createSampleTours() {
        List<Tour> tours = new ArrayList<>();
        tours.add(new Tour("T1", "Phú Quốc", "Phú Quốc", 150));
        tours.add(new Tour("T2", "Nha Trang", "Nha Trang", 100));
        tours.add(new Tour("T3", "Đà Nẵng", "Đà Nẵng", 100));
        tours.add(new Tour("T4", "Hà Nội", "Hà Nội", 100));
        return tours;
    }

    // Sample Booking Data
    private List<Booking> createSampleBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("BO1", "ngan", "T1", "2024-01-19"));
        bookings.add(new Booking("BO2", "luc", "T2", "2023-09-02"));
        bookings.add(new Booking("BO3", "tuanh", "T3", "2021-07-09"));
        bookings.add(new Booking("BO4", "nhut", "T4", "2022-11-12"));
        return bookings;
    }

    // Lớp xử lý khi nhấn nút "Thêm Đặt Tour"
    private void addBookingListener() {
        try {
            String bookingId = view.getBookingId();
            String userId = view.getUserId();
            String tourId = view.getTourId();
            String bookingDate = view.getBookingDate();

            if (bookingId == null || bookingId.isEmpty() || userId == null || userId.isEmpty() || tourId == null
                    || tourId.isEmpty() || bookingDate == null || bookingDate.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra thông tin người dùng và tour hợp lệ
            User user = userManager.findUser(userId);
            Tour tour = tourManager.findTour(tourId);
            if (bookingManager.findBooking(bookingId) != null) {
                JOptionPane.showMessageDialog(view, "Error: Booking ID already exists");
                return;
            }

            if (user == null) {
                JOptionPane.showMessageDialog(view, "Người dùng không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tour == null) {
                JOptionPane.showMessageDialog(view, "Tour không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo Booking mới và thêm vào danh sách
            Booking newBooking = new Booking(bookingId, userId, tourId, bookingDate);
            bookingList.add(newBooking);
            JOptionPane.showMessageDialog(view, "Thêm Đặt Tour thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật bảng đặt tour
            updateBookingTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm Đặt Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý khi nhấn nút "Cập nhật Đặt Tour"
    private void updateBookingListener() {
        try {
            String bookingId = view.getBookingId();
            Booking booking = bookingManager.findBooking(bookingId);

            if (booking == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy Đặt Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật thông tin đặt tour
            booking.setUserId(view.getUserId());
            booking.setTourId(view.getTourId());
            booking.setBookingDate(view.getBookingDate());

            JOptionPane.showMessageDialog(view, "Cập nhật Đặt Tour thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật bảng đặt tour
            updateBookingTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật Đặt Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý khi nhấn nút "Xóa Đặt Tour"
    private void deleteBookingListener() {
        try {
            int selectedRow = view.getBookingTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Please select a row to delete");
                return;
            }
            String bookingId = (String) view.getBookingTable().getValueAt(selectedRow, 0);
            Booking booking = bookingManager.findBooking(bookingId);
            if (booking != null) {
                bookingList.remove(booking);
                JOptionPane.showMessageDialog(view, "Remove Booking Success!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                updateBookingTable();
            } else {
                JOptionPane.showMessageDialog(view, "No booking search!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error while deleting booking!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // actioner for findingtour button
    private void findBookingListener() {

        try {
            String bookingId = view.getBookingId();
            Booking id = bookingManager.findBooking(bookingId);
            if (id == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy Đặt Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String message = String.format(
                    "Thông tin Đặt Tour:\nBooking ID: %s\nUser ID: %s\nTour ID: %s\nNgày Đặt Tour: %s",
                    id.getBookingId(), id.getUserId(), id.getTourId(), id.getBookingDate());
            JOptionPane.showMessageDialog(view, message, "Thông tin Đặt Tour", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi tìm kiếm Đặt Tour", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    // Cập nhật bảng Đặt Tour
    private void updateBookingTable() {
        String[] columnNames = {"Booking ID", "User ID", "Tour ID", "Ngày Đặt Tour"};
        Object[][] data = new Object[bookingList.size()][4];

        for (int i = 0; i < bookingList.size(); i++) {
            Booking booking = bookingList.get(i);
            data[i][0] = booking.getBookingId();
            data[i][1] = booking.getUserId();
            data[i][2] = booking.getTourId();
            data[i][3] = booking.getBookingDate();
        }

        view.getBookingTable().setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}