package com.pedulilingkungan.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Panel untuk menghitung jejak karbon
 */
public class CarbonCalculatorPanel extends JPanel {
    private JTextField electricityField;
    private JTextField gasField;
    private JTextField carDistanceField;
    private JTextField motorcycleDistanceField;
    private JTextField publicTransportField;
    private JTextField flightDistanceField;
    private JTextArea resultArea;
    private JProgressBar carbonBar;
    private JLabel carbonLevelLabel;
    
    // Carbon emission factors (kg CO2 per unit)
    private static final double ELECTRICITY_FACTOR = 0.85; // per kWh
    private static final double GAS_FACTOR = 2.3; // per liter
    private static final double CAR_FACTOR = 0.21; // per km
    private static final double MOTORCYCLE_FACTOR = 0.11; // per km
    private static final double PUBLIC_TRANSPORT_FACTOR = 0.05; // per km
    private static final double FLIGHT_FACTOR = 0.25; // per km
    
    public CarbonCalculatorPanel() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(248, 250, 252));
        
        // Input fields
        electricityField = new JTextField(10);
        gasField = new JTextField(10);
        carDistanceField = new JTextField(10);
        motorcycleDistanceField = new JTextField(10);
        publicTransportField = new JTextField(10);
        flightDistanceField = new JTextField(10);
        
        // Set font for all fields
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        electricityField.setFont(fieldFont);
        gasField.setFont(fieldFont);
        carDistanceField.setFont(fieldFont);
        motorcycleDistanceField.setFont(fieldFont);
        publicTransportField.setFont(fieldFont);
        flightDistanceField.setFont(fieldFont);
        
        // Result area
        resultArea = new JTextArea(8, 40);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(249, 250, 251));
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Carbon level indicator
        carbonBar = new JProgressBar(0, 100);
        carbonBar.setStringPainted(true);
        carbonBar.setFont(new Font("Arial", Font.BOLD, 12));
        
        carbonLevelLabel = new JLabel("Tingkat Emisi: Belum Dihitung");
        carbonLevelLabel.setFont(new Font("Arial", Font.BOLD, 14));
    }
    
    private void setupLayout() {
        // Input panel
        JPanel inputPanel = createInputPanel();
        
        // Result panel
        JPanel resultPanel = createResultPanel();
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        
        // Main layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Input Data Konsumsi (Per Bulan)"));
        panel.setBackground(Color.WHITE);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Electricity
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("🔌 Listrik (kWh):"), gbc);
        gbc.gridx = 1;
        formPanel.add(electricityField, gbc);
        
        // Gas
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("🔥 Gas (Liter):"), gbc);
        gbc.gridx = 3;
        formPanel.add(gasField, gbc);
        
        // Car distance
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("🚗 Jarak Mobil (km):"), gbc);
        gbc.gridx = 1;
        formPanel.add(carDistanceField, gbc);
        
        // Motorcycle distance
        gbc.gridx = 2; gbc.gridy = 1;
        formPanel.add(new JLabel("🏍️ Jarak Motor (km):"), gbc);
        gbc.gridx = 3;
        formPanel.add(motorcycleDistanceField, gbc);
        
        // Public transport
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("🚌 Transportasi Umum (km):"), gbc);
        gbc.gridx = 1;
        formPanel.add(publicTransportField, gbc);
        
        // Flight distance
        gbc.gridx = 2; gbc.gridy = 2;
        formPanel.add(new JLabel("✈️ Penerbangan (km):"), gbc);
        gbc.gridx = 3;
        formPanel.add(flightDistanceField, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.WHITE);
        
        JButton calculateButton = new JButton("Hitung Jejak Karbon");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(34, 197, 94));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setPreferredSize(new Dimension(200, 40));
        
        JButton clearButton = new JButton("Bersihkan");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(107, 114, 128));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(120, 40));
        
        JButton tipsButton = new JButton("Tips Mengurangi Emisi");
        tipsButton.setFont(new Font("Arial", Font.BOLD, 14));
        tipsButton.setBackground(new Color(59, 130, 246));
        tipsButton.setForeground(Color.WHITE);
        tipsButton.setFocusPainted(false);
        tipsButton.setPreferredSize(new Dimension(200, 40));
        
        panel.add(calculateButton);
        panel.add(clearButton);
        panel.add(tipsButton);
        
        // Event handlers
        calculateButton.addActionListener(e -> calculateCarbonFootprint());
        clearButton.addActionListener(e -> clearAllFields());
        tipsButton.addActionListener(e -> showCarbonReductionTips());
        
        return panel;
    }
    
    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Hasil Perhitungan"));
        panel.setBackground(Color.WHITE);
        
        // Result text area
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        
        // Carbon level panel
        JPanel levelPanel = new JPanel(new BorderLayout(5, 5));
        levelPanel.setBackground(Color.WHITE);
        levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        levelPanel.add(carbonLevelLabel, BorderLayout.NORTH);
        levelPanel.add(carbonBar, BorderLayout.CENTER);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(levelPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void setupEventHandlers() {
        // Enter key listeners for all fields
        ActionListener calculateOnEnter = e -> calculateCarbonFootprint();
        
        electricityField.addActionListener(calculateOnEnter);
        gasField.addActionListener(calculateOnEnter);
        carDistanceField.addActionListener(calculateOnEnter);
        motorcycleDistanceField.addActionListener(calculateOnEnter);
        publicTransportField.addActionListener(calculateOnEnter);
        flightDistanceField.addActionListener(calculateOnEnter);
    }
    
    private void calculateCarbonFootprint() {
        try {
            // Get input values
            double electricity = getDoubleValue(electricityField);
            double gas = getDoubleValue(gasField);
            double carDistance = getDoubleValue(carDistanceField);
            double motorcycleDistance = getDoubleValue(motorcycleDistanceField);
            double publicTransport = getDoubleValue(publicTransportField);
            double flightDistance = getDoubleValue(flightDistanceField);
            
            // Calculate emissions
            double electricityEmission = electricity * ELECTRICITY_FACTOR;
            double gasEmission = gas * GAS_FACTOR;
            double carEmission = carDistance * CAR_FACTOR;
            double motorcycleEmission = motorcycleDistance * MOTORCYCLE_FACTOR;
            double publicTransportEmission = publicTransport * PUBLIC_TRANSPORT_FACTOR;
            double flightEmission = flightDistance * FLIGHT_FACTOR;
            
            double totalEmission = electricityEmission + gasEmission + carEmission + 
                                 motorcycleEmission + publicTransportEmission + flightEmission;
            
            // Display results
            DecimalFormat df = new DecimalFormat("#,##0.00");
            StringBuilder result = new StringBuilder();
            result.append("=== HASIL PERHITUNGAN JEJAK KARBON ===\n\n");
            result.append("Emisi per Kategori (kg CO2/bulan):\n");
            result.append("• Listrik          : ").append(df.format(electricityEmission)).append(" kg\n");
            result.append("• Gas              : ").append(df.format(gasEmission)).append(" kg\n");
            result.append("• Mobil            : ").append(df.format(carEmission)).append(" kg\n");
            result.append("• Motor            : ").append(df.format(motorcycleEmission)).append(" kg\n");
            result.append("• Transportasi Umum: ").append(df.format(publicTransportEmission)).append(" kg\n");
            result.append("• Penerbangan      : ").append(df.format(flightEmission)).append(" kg\n");
            result.append("\n" + "=".repeat(40) + "\n");
            result.append("TOTAL EMISI BULANAN: ").append(df.format(totalEmission)).append(" kg CO2\n");
            result.append("TOTAL EMISI TAHUNAN: ").append(df.format(totalEmission * 12)).append(" kg CO2\n\n");
            
            // Add comparison
            result.append("Perbandingan:\n");
            result.append("• Rata-rata global: ~4,000 kg CO2/tahun\n");
            result.append("• Target Paris Agreement: ~2,300 kg CO2/tahun\n");
            
            resultArea.setText(result.toString());
            
            // Update carbon level indicator
            updateCarbonLevelIndicator(totalEmission * 12);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Mohon masukkan angka yang valid untuk semua field!", 
                "Error Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private double getDoubleValue(JTextField field) {
        String text = field.getText().trim();
        return text.isEmpty() ? 0.0 : Double.parseDouble(text);
    }
    
    private void updateCarbonLevelIndicator(double annualEmission) {
        String level;
        Color color;
        int percentage;
        
        if (annualEmission <= 2300) {
            level = "SANGAT BAIK (Target Paris Agreement)";
            color = new Color(34, 197, 94); // Green
            percentage = (int) ((annualEmission / 2300) * 25);
        } else if (annualEmission <= 4000) {
            level = "BAIK (Di bawah rata-rata global)";
            color = new Color(245, 158, 11); // Yellow
            percentage = 25 + (int) (((annualEmission - 2300) / 1700) * 25);
        } else if (annualEmission <= 8000) {
            level = "SEDANG (Di atas rata-rata global)";
            color = new Color(249, 115, 22); // Orange
            percentage = 50 + (int) (((annualEmission - 4000) / 4000) * 25);
        } else {
            level = "TINGGI (Perlu pengurangan signifikan)";
            color = new Color(239, 68, 68); // Red
            percentage = 75 + Math.min(25, (int) (((annualEmission - 8000) / 8000) * 25));
        }
        
        carbonLevelLabel.setText("Tingkat Emisi: " + level);
        carbonLevelLabel.setForeground(color);
        carbonBar.setValue(percentage);
        carbonBar.setForeground(color);
        carbonBar.setString(percentage + "% dari batas maksimal");
    }
    
    private void clearAllFields() {
        electricityField.setText("");
        gasField.setText("");
        carDistanceField.setText("");
        motorcycleDistanceField.setText("");
        publicTransportField.setText("");
        flightDistanceField.setText("");
        resultArea.setText("");
        carbonBar.setValue(0);
        carbonBar.setString("0%");
        carbonLevelLabel.setText("Tingkat Emisi: Belum Dihitung");
        carbonLevelLabel.setForeground(Color.BLACK);
        electricityField.requestFocus();
    }
    
    private void showCarbonReductionTips() {
        String tips = "TIPS MENGURANGI JEJAK KARBON:\n\n" +
                     "🔌 LISTRIK:\n" +
                     "• Gunakan lampu LED\n" +
                     "• Cabut peralatan yang tidak digunakan\n" +
                     "• Gunakan AC secukupnya (25-26°C)\n" +
                     "• Pilih peralatan hemat energi\n\n" +
                     
                     "🚗 TRANSPORTASI:\n" +
                     "• Gunakan transportasi umum\n" +
                     "• Berjalan kaki atau bersepeda untuk jarak dekat\n" +
                     "• Carpooling dengan teman/keluarga\n" +
                     "• Perawatan kendaraan secara rutin\n\n" +
                     
                     "🏠 RUMAH TANGGA:\n" +
                     "• Kurangi penggunaan gas\n" +
                     "• Masak dengan efisien\n" +
                     "• Gunakan air secukupnya\n" +
                     "• Daur ulang sampah\n\n" +
                     
                     "✈️ PERJALANAN:\n" +
                     "• Kurangi penerbangan jarak jauh\n" +
                     "• Pilih penerbangan langsung\n" +
                     "• Kompensasi karbon untuk penerbangan\n" +
                     "• Liburan lokal";
        
        JTextArea tipsArea = new JTextArea(tips);
        tipsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        tipsArea.setEditable(false);
        tipsArea.setCaretPosition(0);
        
        JScrollPane scrollPane = new JScrollPane(tipsArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
                                    "Tips Mengurangi Jejak Karbon", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
}
