package controller;

import model.Tour;
import model.TourManager;
import model.User;
import model.Booking;
import view.TourView;
import view.UserView;
import view.BookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TourController {
    private TourView view;
    private List<Tour> tourList;
    private TourManager tourManager;

    public TourController(TourView view) {
        this.view = view;
        this.tourManager = new TourManager();
        this.tourList = new ArrayList<>();
        initController();

        // Thêm dữ liệu tour mẫu
        addSampleTours1();


        updateTourTable(); // Hiển thị dữ liệu ban đầu trên bảng
    }

    private void initController() {
        view.setAddButtonListener(e -> addTourListener());
        view.setUpdateButtonListener(e -> updateTourListener());
        view.setDeleteButtonListener(e -> deleteTourListener());
        view.setFindBookingButtonListener(e -> findingBookingTourListener());
    }

    private void addSampleTours1() {
        tourList.add(new Tour("T1", "Tour Phú Quốc", "Phú Quốc", 4700000));
        tourList.add(new Tour("T2", "Tour Nha Trang", "Nha Trang", 2500000));
        tourList.add(new Tour("T3", "Tour Đà Nẵng", "Đà Nẵng", 3000000));
    }

    // Lớp xử lý khi nhấn nút "Thêm Tour"
    private void addTourListener() {
        try {
            String tourId = view.getTourId();
            String tourName = view.getTourName();
            String destination = view.getDestination();
            double price = view.getPrice();

            if (tourId == null || tourId.isEmpty() || tourName == null || tourName.isEmpty() || destination == null
                    || destination.isEmpty() || price < 0) {
                JOptionPane.showMessageDialog(view, "Please enter the full information!.");
                return;
            }
            try {
                if (price < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid price entered! Please enter a valid number.!");
                return;
            }

            if (tourManager.findTour(tourId) != null) {
                JOptionPane.showMessageDialog(view, "Tour already exists!");
                return;
            }

            // Tạo Tour mới và thêm vào danh sách
            Tour newTour = new Tour(tourId, tourName, destination, price);
            tourList.add(newTour);
            JOptionPane.showMessageDialog(view, "Thêm Tour thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật bảng tour
            updateTourTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý khi nhấn nút "Cập nhật Tour"
    private void updateTourListener() {
        try {
            String tourId = view.getTourId();
            Tour tour = tourManager.findTour(tourId);

            if (tour == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật thông tin tour
            tour.setName(view.getTourName());
            tour.setDestination(view.getDestination());
            tour.setPrice(view.getPrice());

            JOptionPane.showMessageDialog(view, "Cập nhật Tour thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật bảng tour
            updateTourTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý khi nhấn nút "Xóa Tour"
    private void deleteTourListener() {
        try {
            int selectedRow = view.getTourTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Please select a row to delete");
                return;
            }
            String tourId = (String) view.getTourTable().getValueAt(selectedRow, 0);

            Tour tour = tourManager.findTour(tourId);
            if (tour != null) {
                tourList.remove(tour);
                JOptionPane.showMessageDialog(view, "Remove Tour Success!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                updateTourTable();
            } else {
                JOptionPane.showMessageDialog(view, "No tour search!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findingBookingTourListener() {

        try {
            String tourId = view.getTourId();
            Tour tour = tourManager.findTour(tourId);
            if (tour == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy Tour du lịch", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String message = String.format("Thông tin về Tour:\nTour ID: %s\nName : %s\nDestination: %s\nPrice: %s",
                    tour.getId(), tour.getName(), tour.getDestination(), tour.getPrice());
            JOptionPane.showMessageDialog(view, message, "Thông tin Tour du lịch", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi tìm kiếm Tour du lịch", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    // Hàm tìm tour theo ID
    private Tour findTourById(String tourId) {
        for (Tour tour : tourList) {
            if (tour.getId().equals(tourId)) {
                return tour;
            }
        }
        return null;
    }

    // Cập nhật bảng Tour
    private void updateTourTable() {
        String[] columnNames = {"Tour ID", "Tên Tour", "Điểm đến", "Giá"};
        Object[][] data = new Object[tourList.size()][4];

        for (int i = 0; i < tourList.size(); i++) {
            Tour tour = tourList.get(i);
            data[i][0] = tour.getId();
            data[i][1] = tour.getName();
            data[i][2] = tour.getDestination();
            data[i][3] = tour.getPrice();
        }

        view.getTourTable().setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}