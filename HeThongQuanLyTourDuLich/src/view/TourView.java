package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class TourView extends JPanel {
    private JTextField tourIdField, nameField, destinationField, priceField;
    private JButton addTourButton, updateTourButton, deleteTourButton, findBookingTourButton;
    private JTable tourTable;
    private DefaultTableModel tableModel;

    public TourView() {
        setLayout(new BorderLayout());

        // Panel nhập liệu cho Tour
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tourIdField = new JTextField(15);
        nameField = new JTextField(15);
        destinationField = new JTextField(15);
        priceField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Tour ID:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(tourIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Tên Tour:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Điểm đến:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(destinationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Giá:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(priceField, gbc);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel();
        addTourButton = new JButton("Thêm Tour");
        updateTourButton = new JButton("Cập nhật Tour");
        deleteTourButton = new JButton("Xóa Tour");
        findBookingTourButton = new JButton("Tìm kiếm Tour");

        buttonPanel.add(addTourButton);
        buttonPanel.add(updateTourButton);
        buttonPanel.add(deleteTourButton);
        buttonPanel.add(findBookingTourButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Bảng hiển thị thông tin Tour
        String[] columnNames = {"Tour ID", "Tên Tour", "Điểm đến", "Giá"};
        Object[][] data = {}; // Sẽ được cập nhật từ model
        tourTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(tourTable);
        add(tableScrollPane, BorderLayout.CENTER);

    }


    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addTourButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(java.awt.event.ActionListener listener) {
        updateTourButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(java.awt.event.ActionListener listener) {
        deleteTourButton.addActionListener(listener);
    }

    public void setFindBookingButtonListener(java.awt.event.ActionListener listener) {
        findBookingTourButton.addActionListener(listener);
    }

    public String getTourId() {
        return tourIdField.getText();
    }

    public String getTourName() {
        return nameField.getText();
    }

    public String getDestination() {
        return destinationField.getText();
    }

    public double getPrice() {
        try {
            return Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            return 0.0; // Giá mặc định nếu nhập sai định dạng
        }
    }

    public JButton getAddTourButton() {
        return addTourButton;
    }

    public JButton getUpdateTourButton() {
        return updateTourButton;
    }

    public JButton getDeleteTourButton() {
        return deleteTourButton;
    }

    public JButton getFindBookingTourButton() {
        return findBookingTourButton;
    }

    public JTable getTourTable() {
        return tourTable;
    }

    public static void main(String[] args) {
        new TourView();
    }

}
