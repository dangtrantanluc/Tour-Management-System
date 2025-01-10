package runmain;

import controller.TourRouteController;
import model.Tour;
import model.User;
import model.UserManager;
import model.Booking;
import view.*;
import controller.TourController;
import controller.UserController;
import controller.BookingController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        // Khởi tạo dữ liệu ban đầu
        List<Tour> tourList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Booking> bookingList = new ArrayList<>();

        UserManager userManager = new UserManager();

        // Tạo các đối tượng View
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        MainView mainView = new MainView();
        TourView tourView = new TourView();
        UserView userView = new UserView();
        TourRouteView tourRouteView = new TourRouteView();
        BookingView bookingView = new BookingView();

        // Tạo TabbedPane cho MainView
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quản lý Tour", tourView);
        tabbedPane.addTab("Quản lý Người dùng", userView);
        tabbedPane.addTab("Quản lý Đặt Tour", bookingView);
        tabbedPane.addTab("Quản lý tuyến đường", tourRouteView);

        // Gắn TabbedPane vào MainView
        mainView.setLayout(new BorderLayout());
        mainView.add(tabbedPane, BorderLayout.CENTER);

        // Tạo JPanel với CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(loginView, "Login");
        cardPanel.add(registerView, "Register");
        cardPanel.add(mainView, "Main");

        // Tạo các đối tượng Controller
        TourController tourController = new TourController(tourView);
        UserController userController = new UserController(userView, registerView, loginView, userManager, cardPanel);
        BookingController bookingController = new BookingController(bookingView);
        TourRouteController tourRouteController = new TourRouteController(tourRouteView);

        // Thiết lập JFrame
        JFrame frame = new JFrame("Tour Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(cardPanel);

        // Bắt đầu với LoginView
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Login");
        frame.setVisible(true);
    }
}
