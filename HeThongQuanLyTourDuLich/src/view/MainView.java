package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    private JTabbedPane tabbedPane;
    private TourView tourView;
    private UserView userView;
    private BookingView bookingView;
    private LoginView loginView;
    private TourRouteView tourRouteView;

    public MainView() {
        // Thiết lập giao diện chính
        setLayout(new BorderLayout());

        // Tạo các tab
        tabbedPane = new JTabbedPane();

        // Tạo và thêm các View vào tab
        loginView = new LoginView();
        tourView = new TourView();
        userView = new UserView();
        bookingView = new BookingView();
        tourRouteView = new TourRouteView();

        tabbedPane.addTab("Quản lý Tour", tourView);
        tabbedPane.addTab("Quản lý Người dùng", userView);
        tabbedPane.addTab("Quản lý Đặt Tour", bookingView);
        tabbedPane.addTab("Quản lý tuyến đường", tourRouteView);

        // Thêm tabbedPane vào JPanel
        add(tabbedPane, BorderLayout.CENTER);
    }

    public TourView getTourView() {
        return tourView;
    }

    public UserView getUserView() {
        return userView;
    }

    public BookingView getBookingView() {
        return bookingView;
    }

    public LoginView getLoginView() {
        return loginView;
    }
}
