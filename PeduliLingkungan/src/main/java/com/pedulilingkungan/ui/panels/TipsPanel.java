package com.pedulilingkungan.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Panel untuk menampilkan tips lingkungan
 */
public class TipsPanel extends JPanel {
    private JList<String> categoryList;
    private JTextArea tipsArea;
    private JLabel dailyTipLabel;
    private List<EnvironmentalTip> allTips;
    private DefaultListModel<String> categoryModel;
    
    public TipsPanel() {
        initializeTips();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        showDailyTip();
    }
    
    private void initializeTips() {
        allTips = new ArrayList<>();
        
        // Tips Hemat Energi
        allTips.add(new EnvironmentalTip("Hemat Energi", "Gunakan Lampu LED", 
            "Ganti lampu pijar dengan lampu LED. LED menggunakan 75% lebih sedikit energi dan tahan 25 kali lebih lama."));
        allTips.add(new EnvironmentalTip("Hemat Energi", "Cabut Peralatan Elektronik", 
            "Cabut charger dan peralatan elektronik saat tidak digunakan. Peralatan dalam mode standby tetap mengonsumsi listrik."));
        allTips.add(new EnvironmentalTip("Hemat Energi", "Atur Suhu AC", 
            "Set AC pada suhu 25-26°C. Setiap derajat lebih rendah meningkatkan konsumsi energi hingga 10%."));
        allTips.add(new EnvironmentalTip("Hemat Energi", "Manfaatkan Cahaya Alami", 
            "Buka tirai dan jendela di siang hari untuk memanfaatkan cahaya matahari alami."));
        
        // Tips Hemat Air
        allTips.add(new EnvironmentalTip("Hemat Air", "Perbaiki Kebocoran", 
            "Perbaiki keran yang bocor segera. Satu tetes per detik dapat membuang 19 liter air per hari."));
        allTips.add(new EnvironmentalTip("Hemat Air", "Mandi Lebih Singkat", 
            "Kurangi waktu mandi menjadi 5 menit. Ini dapat menghemat hingga 95 liter air per hari."));
        allTips.add(new EnvironmentalTip("Hemat Air", "Gunakan Ember untuk Cuci Mobil", 
            "Gunakan ember daripada selang saat mencuci mobil. Ini menghemat hingga 300 liter air."));
        allTips.add(new EnvironmentalTip("Hemat Air", "Kumpulkan Air Hujan", 
            "Pasang penampung air hujan untuk menyiram tanaman dan keperluan non-konsumsi lainnya."));
        
        // Tips Pengelolaan Sampah
        allTips.add(new EnvironmentalTip("Pengelolaan Sampah", "Pisahkan Sampah", 
            "Pisahkan sampah organik, anorganik, dan B3. Ini memudahkan proses daur ulang dan pengolahan."));
        allTips.add(new EnvironmentalTip("Pengelolaan Sampah", "Kompos dari Sampah Organik", 
            "Buat kompos dari sisa makanan dan daun kering untuk pupuk tanaman alami."));
        allTips.add(new EnvironmentalTip("Pengelolaan Sampah", "Kurangi Plastik Sekali Pakai", 
            "Bawa tas belanja sendiri dan hindari sedotan plastik. Gunakan alternatif yang dapat digunakan berulang."));
        allTips.add(new EnvironmentalTip("Pengelolaan Sampah", "Daur Ulang Kertas", 
            "Kumpulkan kertas bekas untuk didaur ulang. Satu ton kertas daur ulang menyelamatkan 17 pohon."));
        
        // Tips Transportasi
        allTips.add(new EnvironmentalTip("Transportasi", "Gunakan Transportasi Umum", 
            "Naik bus atau kereta untuk mengurangi emisi karbon. Satu bus dapat menggantikan 40 mobil pribadi."));
        allTips.add(new EnvironmentalTip("Transportasi", "Bersepeda atau Jalan Kaki", 
            "Untuk jarak dekat, gunakan sepeda atau jalan kaki. Ini baik untuk kesehatan dan lingkungan."));
        allTips.add(new EnvironmentalTip("Transportasi", "Carpooling", 
            "Berbagi kendaraan dengan teman atau keluarga untuk mengurangi jumlah kendaraan di jalan."));
        allTips.add(new EnvironmentalTip("Transportasi", "Perawatan Kendaraan", 
            "Lakukan servis rutin kendaraan untuk menjaga efisiensi bahan bakar dan mengurangi emisi."));
        
        // Tips Konsumsi
        allTips.add(new EnvironmentalTip("Konsumsi", "Beli Produk Lokal", 
            "Pilih produk lokal untuk mengurangi jejak karbon dari transportasi barang."));
        allTips.add(new EnvironmentalTip("Konsumsi", "Kurangi Konsumsi Daging", 
            "Kurangi konsumsi daging 1-2 hari per minggu. Produksi daging menghasilkan emisi gas rumah kaca tinggi."));
        allTips.add(new EnvironmentalTip("Konsumsi", "Beli Sesuai Kebutuhan", 
            "Rencanakan belanja dan beli hanya yang dibutuhkan untuk mengurangi pemborosan."));
        allTips.add(new EnvironmentalTip("Konsumsi", "Pilih Kemasan Ramah Lingkungan", 
            "Pilih produk dengan kemasan minimal atau kemasan yang dapat didaur ulang."));
        
        // Tips Rumah Hijau
        allTips.add(new EnvironmentalTip("Rumah Hijau", "Tanam Pohon", 
            "Tanam pohon di halaman rumah. Satu pohon dapat menyerap 22 kg CO2 per tahun."));
        allTips.add(new EnvironmentalTip("Rumah Hijau", "Buat Taman Vertikal", 
            "Manfaatkan dinding untuk membuat taman vertikal jika lahan terbatas."));
        allTips.add(new EnvironmentalTip("Rumah Hijau", "Gunakan Cat Ramah Lingkungan", 
            "Pilih cat dengan kandungan VOC rendah untuk kualitas udara dalam ruangan yang lebih baik."));
        allTips.add(new EnvironmentalTip("Rumah Hijau", "Isolasi Rumah", 
            "Pasang isolasi yang baik untuk mengurangi penggunaan AC dan pemanas."));
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(248, 250, 252));
        
        // Category list
        categoryModel = new DefaultListModel<>();
        categoryModel.addElement("Semua Tips");
        categoryModel.addElement("Hemat Energi");
        categoryModel.addElement("Hemat Air");
        categoryModel.addElement("Pengelolaan Sampah");
        categoryModel.addElement("Transportasi");
        categoryModel.addElement("Konsumsi");
        categoryModel.addElement("Rumah Hijau");
        
        categoryList = new JList<>(categoryModel);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setSelectedIndex(0);
        categoryList.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryList.setBackground(Color.WHITE);
        
        // Tips area
        tipsArea = new JTextArea();
        tipsArea.setFont(new Font("Arial", Font.PLAIN, 13));
        tipsArea.setEditable(false);
        tipsArea.setLineWrap(true);
        tipsArea.setWrapStyleWord(true);
        tipsArea.setBackground(new Color(249, 250, 251));
        tipsArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Daily tip label
        dailyTipLabel = new JLabel();
        dailyTipLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dailyTipLabel.setForeground(new Color(34, 197, 94));
        dailyTipLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }
    
    private void setupLayout() {
        // Left panel - Categories
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Kategori Tips"));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(200, 0));
        
        JScrollPane categoryScrollPane = new JScrollPane(categoryList);
        categoryScrollPane.setPreferredSize(new Dimension(180, 0));
        leftPanel.add(categoryScrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton randomTipButton = new JButton("Tip Acak");
        randomTipButton.setFont(new Font("Arial", Font.BOLD, 11));
        randomTipButton.setBackground(new Color(59, 130, 246));
        randomTipButton.setForeground(Color.WHITE);
        randomTipButton.setFocusPainted(false);
        
        JButton shareButton = new JButton("Bagikan");
        shareButton.setFont(new Font("Arial", Font.BOLD, 11));
        shareButton.setBackground(new Color(34, 197, 94));
        shareButton.setForeground(Color.WHITE);
        shareButton.setFocusPainted(false);
        
        JButton printButton = new JButton("Cetak");
        printButton.setFont(new Font("Arial", Font.BOLD, 11));
        printButton.setBackground(new Color(107, 114, 128));
        printButton.setForeground(Color.WHITE);
        printButton.setFocusPainted(false);
        
        buttonPanel.add(randomTipButton);
        buttonPanel.add(shareButton);
        buttonPanel.add(printButton);
        
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Right panel - Tips content
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        
        // Daily tip panel
        JPanel dailyTipPanel = new JPanel(new BorderLayout());
        dailyTipPanel.setBorder(BorderFactory.createTitledBorder("💡 Tips Hari Ini"));
        dailyTipPanel.setBackground(new Color(240, 253, 244));
        dailyTipPanel.add(dailyTipLabel, BorderLayout.CENTER);
        
        // Tips content panel
        JPanel tipsContentPanel = new JPanel(new BorderLayout());
        tipsContentPanel.setBorder(BorderFactory.createTitledBorder("Daftar Tips"));
        tipsContentPanel.setBackground(Color.WHITE);
        
        JScrollPane tipsScrollPane = new JScrollPane(tipsArea);
        tipsScrollPane.setPreferredSize(new Dimension(0, 300));
        tipsContentPanel.add(tipsScrollPane, BorderLayout.CENTER);
        
        rightPanel.add(dailyTipPanel, BorderLayout.NORTH);
        rightPanel.add(tipsContentPanel, BorderLayout.CENTER);
        
        // Main layout
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        // Event handlers
        randomTipButton.addActionListener(e -> showRandomTip());
        shareButton.addActionListener(e -> shareTips());
        printButton.addActionListener(e -> printTips());
    }
    
    private void setupEventHandlers() {
        categoryList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayTipsForCategory();
            }
        });
        
        // Initial display
        displayTipsForCategory();
    }
    
    private void displayTipsForCategory() {
        String selectedCategory = categoryList.getSelectedValue();
        StringBuilder content = new StringBuilder();
        
        if ("Semua Tips".equals(selectedCategory)) {
            // Group tips by category
            String currentCategory = "";
            for (EnvironmentalTip tip : allTips) {
                if (!tip.getCategory().equals(currentCategory)) {
                    currentCategory = tip.getCategory();
                    content.append("\n=== ").append(currentCategory.toUpperCase()).append(" ===\n\n");
                }
                content.append("• ").append(tip.getTitle()).append("\n");
                content.append("  ").append(tip.getDescription()).append("\n\n");
            }
        } else {
            // Show tips for specific category
            content.append("=== ").append(selectedCategory.toUpperCase()).append(" ===\n\n");
            for (EnvironmentalTip tip : allTips) {
                if (tip.getCategory().equals(selectedCategory)) {
                    content.append("• ").append(tip.getTitle()).append("\n");
                    content.append("  ").append(tip.getDescription()).append("\n\n");
                }
            }
        }
        
        tipsArea.setText(content.toString());
        tipsArea.setCaretPosition(0);
    }
    
    private void showDailyTip() {
        Random random = new Random();
        EnvironmentalTip dailyTip = allTips.get(random.nextInt(allTips.size()));
        dailyTipLabel.setText("<html><div style='text-align: center;'>" + 
                             dailyTip.getTitle() + "<br><small>" + 
                             dailyTip.getDescription() + "</small></div></html>");
    }
    
    private void showRandomTip() {
        Random random = new Random();
        EnvironmentalTip randomTip = allTips.get(random.nextInt(allTips.size()));
        
        String message = "🌱 TIP ACAK 🌱\n\n" +
                        "Kategori: " + randomTip.getCategory() + "\n" +
                        "Judul: " + randomTip.getTitle() + "\n\n" +
                        "Deskripsi:\n" + randomTip.getDescription();
        
        JOptionPane.showMessageDialog(this, message, "Tip Lingkungan Acak", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void shareTips() {
        String selectedCategory = categoryList.getSelectedValue();
        StringBuilder shareContent = new StringBuilder();
        shareContent.append("🌱 TIPS PEDULI LINGKUNGAN 🌱\n");
        shareContent.append("Kategori: ").append(selectedCategory).append("\n\n");
        
        if ("Semua Tips".equals(selectedCategory)) {
            // Share a few random tips
            Random random = new Random();
            shareContent.append("Beberapa tips pilihan:\n\n");
            for (int i = 0; i < Math.min(5, allTips.size()); i++) {
                EnvironmentalTip tip = allTips.get(random.nextInt(allTips.size()));
                shareContent.append("• ").append(tip.getTitle()).append("\n");
                shareContent.append("  ").append(tip.getDescription()).append("\n\n");
            }
        } else {
            for (EnvironmentalTip tip : allTips) {
                if (tip.getCategory().equals(selectedCategory)) {
                    shareContent.append("• ").append(tip.getTitle()).append("\n");
                    shareContent.append("  ").append(tip.getDescription()).append("\n\n");
                }
            }
        }
        
        shareContent.append("Mari bersama menjaga lingkungan! 🌍");
        
        // Copy to clipboard
        java.awt.datatransfer.StringSelection stringSelection = 
            new java.awt.datatransfer.StringSelection(shareContent.toString());
        java.awt.datatransfer.Clipboard clipboard = 
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        
        JOptionPane.showMessageDialog(this, 
            "Tips telah disalin ke clipboard!\nAnda dapat membagikannya di media sosial atau aplikasi chat.", 
            "Berhasil Disalin", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void printTips() {
        try {
            boolean complete = tipsArea.print();
            if (complete) {
                JOptionPane.showMessageDialog(this, "Tips berhasil dicetak!", 
                                            "Cetak Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Pencetakan dibatalkan oleh pengguna.", 
                                            "Cetak Dibatalkan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak: " + e.getMessage(), 
                                        "Error Cetak", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Inner class for Environmental Tip
    private static class EnvironmentalTip {
        private String category;
        private String title;
        private String description;
        
        public EnvironmentalTip(String category, String title, String description) {
            this.category = category;
            this.title = title;
            this.description = description;
        }
        
        public String getCategory() { return category; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
    }
}
