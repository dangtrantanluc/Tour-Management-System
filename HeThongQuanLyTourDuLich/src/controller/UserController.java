package controller;

import model.User;
import model.UserManager;
import view.UserView;
import view.RegisterView;
import view.LoginView;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;

public class UserController {
    private UserView view;
    private List<User> userList;
    private LoginView loginView;
    private RegisterView registerView;
    private UserManager userManager;
    private MainView mainView;
    private JPanel cardPanel; // Thêm tham chiếu đến JPanel chứa CardLayout

    // Dữ liệu người dùng mẫu
    private static final List<User> sampleUsers = new ArrayList<>(
            List.of(new User("ngan", "Lê Thị Thanh Ngân", "ngan179@gmail.com", "1234"),
                    new User("luc", "Đặng Trần Tấn Lực", "tanluc@gmail.com", "abcd"),
                    new User("tuanh", "Phạm Trần Tuấn Anh", "trtuanh@gmail.com", "0000"),
                    new User("nhut", "Nguyễn Minh Nhựt", "minhnhut@gmail.com", "010804")));


    public UserController(UserView view, RegisterView registerView, LoginView loginView, UserManager userManager,
                          JPanel cardPanel) {
        this.view = view;
        this.userList = new ArrayList<>(); // Khởi tạo list user rỗng
        this.registerView = registerView;
        this.loginView = loginView;
        this.userManager = userManager;
        this.cardPanel = cardPanel;
        initController();

        // Thêm các user mẫu vào danh sách
        userList.addAll(sampleUsers);

        // Thêm user mẫu vào userManager
        for (User user : sampleUsers) {
            userManager.addUser(user);
        }

        // Cập nhật bảng người dùng khi khởi tạo
        updateUserTable();

        // Xử lý sự kiện đăng ký
        registerView.getRegisterButton().addActionListener(e -> handleRegister());

        // Xử lý sự kiện đăng nhập
        loginView.getLoginButton().addActionListener(e -> handleLogin());

        // Xử lý sự kiện chuyển sang RegisterView từ LoginView
        loginView.getRegisterButton().addActionListener(e -> {
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Register");
        });

        // Xử lý sự kiện chuyển về LoginView từ RegisterView
        registerView.getBackToLoginButton().addActionListener(e -> {
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Login");
        });



    }

    public void initController() {
        this.view.setAddButtonListener(e -> addUserListener());
        this.view.setUpdateButtonListener(e -> updateUserListener());
        this.view.setDeleteButtonListener(e -> deleteUserListener());
        this.view.setFindUserButtonListener(e -> findingUserListener());
    }

    // Kiểm tra các trường nhập liệu
    private boolean validateFields(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Xử lý sự kiện đăng ký
    private void handleRegister() {
        String userId = registerView.getUserId();
        String name = registerView.getUserName();
        String email = registerView.getUserEmail();
        String password = registerView.getPassword();
        String rePassword = registerView.getRePassword();

        if (!validateFields(userId, name, email, password)) {
            JOptionPane.showMessageDialog(registerView, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(rePassword)) {
            JOptionPane.showMessageDialog(registerView, "Mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User(userId, name, email, password);
        if (userManager.addUser(newUser)) {
            JOptionPane.showMessageDialog(registerView, "Đăng ký thành công!");
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Login");
        } else {
            JOptionPane.showMessageDialog(registerView, "User ID đã tồn tại.");
        }
    }

    // Xử lý sự kiện đăng nhập
    private void handleLogin() {
        String userId = loginView.getUserId();
        String password = loginView.getPassword();

        if (userManager.login(userId, password)) {
            JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công!");
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Main");
        } else {
            JOptionPane.showMessageDialog(loginView, "Đăng nhập thất bại, vui lòng thử lại.");
        }
    }

    // Lớp xử lý khi nhấn nút "Thêm Người dùng"
    private void addUserListener() {
        try {
            String userId = view.getUserId();
            String userName = view.getUserName();
            String userEmail = view.getUserEmail();

            if (userManager.findUser(userId) != null) {
                JOptionPane.showMessageDialog(view, "User đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateFields(userId, userName, userEmail)) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo User mới và thêm vào danh sách
            User newUser = new User(userId, userName, userEmail);
            userList.add(newUser);
            JOptionPane.showMessageDialog(view, "Thêm Người dùng thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật bảng người dùng
            updateUserTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm Người dùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý khi nhấn nút "Cập nhật Người dùng"
    private void updateUserListener() {
        try {
            String userId = view.getUserId();
            User user = userManager.findUser(userId);

            if (user == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy Người dùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật thông tin người dùng
            user.setName(view.getUserName());
            user.setEmail(view.getUserEmail());

            JOptionPane.showMessageDialog(view, "Cập nhật Người dùng thành công", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            // Cập nhật bảng người dùng
            updateUserTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật Người dùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý khi nhấn nút "Xóa Người dùng"
    private void deleteUserListener() {
        try {
            int selectedRow = view.getUserTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một hàng để xóa!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String userId = (String) view.getUserTable().getValueAt(selectedRow, 0);
            User user = userManager.findUser(userId);

            if (user != null) {
                userList.remove(user);
                JOptionPane.showMessageDialog(view, "Xóa người dùng thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                updateUserTable();
            } else {
                JOptionPane.showMessageDialog(view, "Không tìm thấy người dùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa Người dùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lớp xử lý tìm kiếm người dùng theo User ID
    private void findingUserListener() {
        String userId = view.getUserId();
        User user = userManager.findUser(userId);
        try {
            if (user == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy người dùng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String message = String.format("Thông tin người dùng:\nUser ID: %s\nTên: %s\nEmail: %s",
                    user.getUserId(), user.getName(), user.getEmail());
            JOptionPane.showMessageDialog(view, message, "Thông tin người dùng", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi tìm kiếm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

//    // Hàm tìm người dùng theo ID
//    private User findUserById(String userId) {
//        for (User user : userList) {
//            if (user.getUserId().equals(userId)) {
//                return user;
//            }
//        }
//        return null;
//    }

    // Cập nhật bảng Người dùng
    private void updateUserTable() {
        SwingUtilities.invokeLater(() -> {
            String[] columnNames = {"User ID", "Tên Người dùng", "Email"};
            Object[][] data = new Object[userList.size()][3];

            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                data[i][0] = user.getUserId();
                data[i][1] = user.getName();
                data[i][2] = user.getEmail();
            }

            view.getUserTable().setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        });
    }

    // Cập nhật phương thức setMainView để nhận thêm đối số JPanel
    public void setMainView(MainView mainView, JPanel cardPanel) {
        this.mainView = mainView;
        // Chuyển sang MainView khi đăng nhập thành công
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Main");
    }
}