package model;

import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private List<Tour> tours;

    public TourManager() {
        this.tours = new ArrayList<>();
    }

    // Thêm một tour mới
    public boolean addTour(Tour tour) {
        for (Tour existingTour : tours) {
            if (existingTour.getId().equals(tour.getId())) {
                System.out.println("Error: Tour ID already exists!");
                return false;
            }
        }
        tours.add(tour);
        System.out.println("Tour added successfully!");
        return true;
    }

    // Tìm tour theo ID
    public Tour findTour(String tourId) {
        for (Tour tour : tours) {
            if (tour.getId().equals(tourId)) {
                return tour;
            }
        }
        System.out.println("Error: Tour ID not found!");
        return null;
    }

    // Lấy danh sách tất cả các tour
    public List<Tour> getAllTours() {
        return tours;
    }

    // In thông tin tất cả các tour
    public void printAllTours() {
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }
}