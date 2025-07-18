package com.pedulilingkungan.ui.panels;

import com.pedulilingkungan.model.WasteEntry;
import com.pedulilingkungan.model.WasteType;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Panel untuk melacak sampah yang didaur ulang
 */
public class WasteTrackerPanel extends JPanel {
    private JComboBox<WasteType> wasteTypeCombo;
    private JTextField weightField;
    private JTextArea descriptionArea;
    private JTable wasteTable;
    private DefaultTableModel tableModel;
    private List<WasteEntry> wasteEntries;
    private JLabel totalWeightLabel;
    private JLabel totalEntriesLabel;
    
    public WasteTrackerPanel() {
        wasteEntries = new ArrayList<>();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        updateStatistics();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(248, 250, 252));
        
        // Input components
        wasteTypeCombo = new JComboBox<>(WasteType.values());
        wasteTypeCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        
        weightField = new JTextField(10);
        weightField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        
        // Table
        String[] columnNames = {"Tanggal", "Jenis Sampah", "Berat (kg)", "Deskripsi", "Poin"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        wasteTable = new JTable(tableModel);
        wasteTable.setFont(new Font("Arial", Font.PLAIN, 12));
        wasteTable.setRowHeight(25);
        wasteTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        wasteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Statistics labels
        totalWeightLabel = new JLabel("Total Berat: 0.0 kg");
        totalWeightLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalWeightLabel.setForeground(new Color(34, 197, 94));
        
        totalEntriesLabel = new JLabel("Total Entri: 0");
        totalEntriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalEntriesLabel.setForeground(new Color(59, 130, 246));
    }
    
    private void setupLayout() {
        // Input panel
        JPanel inputPanel = createInputPanel();
        
        // Table panel
        JPanel tablePanel = createTablePanel();
        
        // Statistics panel
        JPanel statsPanel = createStatisticsPanel();
        
        // Main layout
        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tambah Data Sampah"));
        panel.setBackground(Color.WHITE);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Jenis Sampah
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Jenis Sampah:"), gbc);
        gbc.gridx = 1;
        formPanel.add(wasteTypeCombo, gbc);
        
        // Berat
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("Berat (kg):"), gbc);
        gbc.gridx = 3;
        formPanel.add(weightField, gbc);
        
        // Deskripsi
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Deskripsi:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        descScrollPane.setPreferredSize(new Dimension(400, 80));
        formPanel.add(descScrollPane, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton addButton = new JButton("Tambah Data");
        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        addButton.setBackground(new Color(34, 197, 94));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        
        JButton deleteButton = new JButton("Hapus Data");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setBackground(new Color(239, 68, 68));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        
        JButton clearButton = new JButton("Bersihkan Form");
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setBackground(new Color(107, 114, 128));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Event handlers
        addButton.addActionListener(e -> addWasteEntry());
        deleteButton.addActionListener(e -> deleteSelectedEntry());
        clearButton.addActionListener(e -> clearForm());
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Riwayat Sampah"));
        panel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(wasteTable);
        scrollPane.setPreferredSize(new Dimension(0, 300));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Statistik"));
        panel.setBackground(Color.WHITE);
        
        panel.add(totalWeightLabel);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(totalEntriesLabel);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        // Enter key in weight field
        weightField.addActionListener(e -> addWasteEntry());
        
        // Table selection
        wasteTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = wasteTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Could populate form with selected data for editing
                }
            }
        });
    }
    
    private void addWasteEntry() {
        try {
            WasteType type = (WasteType) wasteTypeCombo.getSelectedItem();
            String weightText = weightField.getText().trim();
            String description = descriptionArea.getText().trim();
            
            if (weightText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mohon masukkan berat sampah!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double weight = Double.parseDouble(weightText);
            if (weight <= 0) {
                JOptionPane.showMessageDialog(this, "Berat harus lebih dari 0!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            WasteEntry entry = new WasteEntry(type, weight, description);
            wasteEntries.add(entry);
            
            // Add to table
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Object[] rowData = {
                sdf.format(entry.getDate()),
                entry.getType().getDisplayName(),
                String.format("%.2f", entry.getWeight()),
                entry.getDescription(),
                entry.getPoints()
            };
            tableModel.addRow(rowData);
            
            clearForm();
            updateStatistics();
            
            JOptionPane.showMessageDialog(this, 
                "Data berhasil ditambahkan!\nAnda mendapat " + entry.getPoints() + " poin!", 
                "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format berat tidak valid!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteSelectedEntry() {
        int selectedRow = wasteTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus data ini?", 
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                wasteEntries.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                updateStatistics();
                
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", 
                                            "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", 
                                        "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearForm() {
        wasteTypeCombo.setSelectedIndex(0);
        weightField.setText("");
        descriptionArea.setText("");
        weightField.requestFocus();
    }
    
    private void updateStatistics() {
        double totalWeight = wasteEntries.stream()
                                       .mapToDouble(WasteEntry::getWeight)
                                       .sum();
        int totalEntries = wasteEntries.size();
        
        totalWeightLabel.setText(String.format("Total Berat: %.2f kg", totalWeight));
        totalEntriesLabel.setText("Total Entri: " + totalEntries);
    }
}
