package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BookingView extends JPanel {
    private JTextField bookingIdField, userIdField, tourIdField, bookingDateField;
    private JButton addBookingButton, updateBookingButton, deleteBookingButton, findBookingTourButton;
    private JTable bookingTable;
    private Image background;

    public BookingView() {
        setLayout(new BorderLayout());

        // Panel nhập liệu cho Đặt Tour
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        bookingIdField = new JTextField(15);
        userIdField = new JTextField(15);
        tourIdField = new JTextField(15);
        bookingDateField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Booking ID:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(bookingIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("User ID:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Tour ID:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(tourIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Ngày Đặt Tour:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(bookingDateField, gbc);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel();
        addBookingButton = new JButton("Thêm Đặt Tour");
        updateBookingButton = new JButton("Cập nhập Đặt Tour");
        deleteBookingButton = new JButton("Xóa Đặt Tour");
        findBookingTourButton = new JButton("Tìm kiếm Tour");

        buttonPanel.add(addBookingButton);
        buttonPanel.add(updateBookingButton);
        buttonPanel.add(deleteBookingButton);
        buttonPanel.add(findBookingTourButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Bảng hiển thị thông tin Đặt Tour
        String[] columnNames = {"Booking ID", "User ID", "Tour ID", "Ngày Đặt Tour"};
        Object[][] data = {};  // Sẽ được cập nhật từ model
        bookingTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(bookingTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Load background image
        try {
            background = ImageIO.read(new File("img/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Fallback background color if image not found
            background = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background image if available
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Fallback background color if image is not loaded
            g.setColor(Color.LIGHT_GRAY);  // Set a default color
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addBookingButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(java.awt.event.ActionListener listener) {
        updateBookingButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(java.awt.event.ActionListener listener) {
        deleteBookingButton.addActionListener(listener);
    }

    public void setFindBookingTourButtonListener(java.awt.event.ActionListener listener) {
        findBookingTourButton.addActionListener(listener);
    }

    public String getBookingId() {
        return bookingIdField.getText();
    }

    public String getUserId() {
        return userIdField.getText();
    }

    public String getTourId() {
        return tourIdField.getText();
    }

    public String getBookingDate() {
        return bookingDateField.getText();
    }

    public JButton getAddBookingButton() {
        return addBookingButton;
    }

    public JButton getUpdateBookingButton() {
        return updateBookingButton;
    }

    public JButton getDeleteBookingButton() {
        return deleteBookingButton;
    }

    public JButton getFindBookingTourButton() {
        return findBookingTourButton;
    }

    public JTable getBookingTable() {
        return bookingTable;
    }

}
