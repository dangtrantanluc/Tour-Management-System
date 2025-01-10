package controller;

import model.TourRoute;
import model.TourRouteManager;
import view.TourRouteView;

import javax.swing.*;

public class TourRouteController {
    private TourRouteView view;
    private TourRouteManager manager;

    public TourRouteController(TourRouteView view) {
        this.view = view;
        this.manager = new TourRouteManager();
        initController();
        loadSampleData();
    }

    private void initController() {
        view.setAddButtonListener(e -> addTourRoute());
        view.setEditButtonListener(e -> editTourRoute());
        view.setDeleteButtonListener(e -> deleteTourRoute());
        view.setFindButtonListener(e -> findTourRoute());
    }

    private void addTourRoute() {
        try {
            String routeId = view.getRouteId();
            String routeName = view.getRouteName();
            String destination = view.getDestinations();
            String trans = view.getTransportation();

            if (routeId == null || routeId.isEmpty() || routeName == null || routeName.isEmpty() || destination == null || destination.isEmpty() || trans == null || trans.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter the full information!.");
                return;
            }
            if (manager.findById(routeId) != null) {
                JOptionPane.showMessageDialog(view, "This route already exists!");
                return;
            }

            manager.addTourRoute(new TourRoute(routeId, routeName, destination, trans));
            JOptionPane.showMessageDialog(view, "Thêm Tour thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            // Hiển thị lại bảng
            view.displayTourRoutes(manager.getAllTourRoutes());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editTourRoute() {
        try {
            String routeId = view.getRouteId().trim();
            String routeName = view.getRouteName().trim();

            if (routeId.isEmpty() || routeName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter the full information!");
                return;
            }
            if (manager.findById(routeId) == null) {
                JOptionPane.showMessageDialog(view, "Route not found!");
                return;
            }

            manager.updateTourRoute(routeId, routeName);
            JOptionPane.showMessageDialog(view, "Tour updated successfully!");
            view.displayTourRoutes(manager.getAllTourRoutes());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error updating tour!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void deleteTourRoute() {
        try {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row from the table");
                return;
            }
            String routeId = (String) view.getTable().getValueAt(selectedRow, 0);
            TourRoute tourRoute = manager.findById(routeId);
            if (tourRoute != null) {
                manager.deleteTourRoute(routeId);
                JOptionPane.showMessageDialog(view, "Remove Tour Success!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                view.displayTourRoutes(manager.getAllTourRoutes());
            } else {
                JOptionPane.showMessageDialog(view, "No tour search!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa Tour", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void findTourRoute() {
        try {
            String routeId = view.getRouteId();
            TourRoute tourRoute = manager.findById(routeId);
            if (tourRoute == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy Tour Route du lịch", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String message = String.format("Thông tin về Tour:\nTourRoute ID: %s\nName : %s\nDestinations: %s\nTransportations: %s",
                    tourRoute.getId(), tourRoute.getName(), tourRoute.getDestinations(), tourRoute.getTransportations());
            JOptionPane.showMessageDialog(view, message, "Thông tin Tour du lịch", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi tìm kiếm Tour du lịch", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Thêm phương thức load dữ liệu mẫu
    private void loadSampleData() {
        manager.addTourRoute(new TourRoute("TR001", "Khám phá Hà Nội", "Hồ Gươm, Văn Miếu, Chùa Một Cột", "Xe du lịch"));
        manager.addTourRoute(new TourRoute("TR002", "Du lịch Đà Nẵng - Hội An", "Bà Nà Hills, Cầu Rồng, Phố cổ Hội An", "Máy bay, Xe du lịch"));
        manager.addTourRoute(new TourRoute("TR003", "Hành trình Nha Trang", "Vinpearl Land, Vịnh Nha Phu, Bãi Dài", "Tàu, Xe máy"));

         //Sau khi thêm xong thì hiển thị lên bảng
        view.displayTourRoutes(manager.getAllTourRoutes());
    }


}
