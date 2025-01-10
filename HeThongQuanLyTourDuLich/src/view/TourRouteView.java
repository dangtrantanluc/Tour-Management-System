package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.TourRoute;
import model.TourRouteManager;

import javax.swing.table.DefaultTableModel;

public class TourRouteView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnEdit, btnDelete, btnFind;
    private JTextField tfRouteId, tfRouteName, tfDes, tfTrans;
    private TourRouteManager manager;

    public TourRouteView() {
        setLayout(new BorderLayout());  // Use BorderLayout for better positioning

        manager = new TourRouteManager();

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Text Fields
        tfRouteId = new JTextField(15);
        tfRouteName = new JTextField(15);
        tfDes = new JTextField(15);
        tfTrans = new JTextField(15);

        // Adding Labels and Text Fields to the GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("ID Tuyến Đường:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(tfRouteId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Tên Tuyến Đường:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(tfRouteName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Điểm Đến:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(tfDes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Phương Tiện:"), gbc);

        gbc.gridx = 1;
        inputPanel.add(tfTrans, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Thêm tuyến đường");
        btnEdit = new JButton("Sửa tuyến đường");
        btnDelete = new JButton("Xóa tuyến đường");
        btnFind = new JButton("Tìm kiếm tuyến đường");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnFind);

        // Add button panel to the grid
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // Span across both columns
        inputPanel.add(buttonPanel, gbc);

        // Add the input panel to the top of the BorderLayout
        add(inputPanel, BorderLayout.NORTH);

        // Table for displaying the list of tour routes
        String[] columnNames = {"ID", "Tên Tuyến Đường", "Điểm Đến", "Phương Tiện"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the table scroll pane to the center of the BorderLayout
        add(scrollPane, BorderLayout.CENTER);
    }

    public void displayTourRoutes(List<TourRoute> tourRoutes) {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Add new rows
        for (TourRoute route : tourRoutes) {
            tableModel.addRow(new Object[]{
                    route.getId(),
                    route.getName(),
                    route.getDestinations(),
                    route.getTransportations()
            });
        }
    }

    public JTable getTable() {
        return table;
    }

    public String getRouteId() {
        return tfRouteId.getText();
    }

    public String getRouteName() {
        return tfRouteName.getText();
    }

    public String getDestinations() {
        return tfDes.getText();
    }

    public String getTransportation() {
        return tfTrans.getText();
    }

    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void setEditButtonListener(java.awt.event.ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void setDeleteButtonListener(java.awt.event.ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void setFindButtonListener(java.awt.event.ActionListener listener) {
        btnFind.addActionListener(listener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Tuyến Đường");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        TourRouteView tourRouteView = new TourRouteView();
        frame.add(tourRouteView);
        frame.setVisible(true);
    }
}
