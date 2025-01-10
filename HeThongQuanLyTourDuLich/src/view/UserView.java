package view;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {
    private JTextField userIdField, nameField, emailField;
    private JButton addUserButton, updateUserButton, deleteUserButton, findUserButton;
    private JTable userTable;

    public UserView() {
        setLayout(new BorderLayout());

        // Panel nhập liệu cho Người dùng
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        userIdField = new JTextField(15);
        nameField = new JTextField(15);
        emailField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("User ID:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Tên Người dùng:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel();
        addUserButton = new JButton("Thêm Người dùng");
        updateUserButton = new JButton("Cập nhật Người dùng");
        deleteUserButton = new JButton("Xóa Người dùng");
        findUserButton = new JButton("Tìm người dùng");
        buttonPanel.add(addUserButton);
        buttonPanel.add(updateUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(findUserButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Bảng hiển thị thông tin Người dùng
        String[] columnNames = {"User ID", "Tên Người dùng", "Email"};
        Object[][] data = {};  // Sẽ được cập nhật từ model
        userTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(userTable);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addUserButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(java.awt.event.ActionListener listener) {
        updateUserButton.addActionListener(listener);

    }

    public void setDeleteButtonListener(java.awt.event.ActionListener listener) {
        deleteUserButton.addActionListener(listener);
    }

    public void setFindUserButtonListener(java.awt.event.ActionListener listener) {
        findUserButton.addActionListener(listener);
    }

    public String getUserId() {
        return userIdField.getText();
    }

    public String getUserName() {
        return nameField.getText();
    }

    public String getUserEmail() {
        return emailField.getText();
    }

    public JButton getAddUserButton() {
        return addUserButton;
    }

    public JButton getUpdateUserButton() {
        return updateUserButton;
    }

    public JButton getDeleteUserButton() {
        return deleteUserButton;
    }

    public JButton getFindUserButton() {
        return findUserButton;
    }

    public JTable getUserTable() {
        return userTable;
    }
}
