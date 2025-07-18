package com.pedulilingkungan.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Panel Dashboard - Tampilan utama aplikasi
 */
public class DashboardPanel extends JPanel {
    private JLabel welcomeLabel;
    private JLabel dateLabel;
    private JPanel statsPanel;
    private JPanel quickActionsPanel;
    
    public DashboardPanel() {
        initializeComponents();
        setupLayout();
        updateDateTime();
        
        // Update time every minute
        Timer timer = new Timer(60000, e -> updateDateTime());
        timer.start();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(248, 250, 252));
        
        // Welcome section
        welcomeLabel = new JLabel("Selamat Datang di Aplikasi Peduli Lingkungan!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(34, 197, 94));
        
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setForeground(Color.GRAY);
        
        // Stats panel
        statsPanel = createStatsPanel();
        
        // Quick actions panel
        quickActionsPanel = createQuickActionsPanel();
    }
    
    private void setupLayout() {
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(welcomeLabel, BorderLayout.NORTH);
        headerPanel.add(dateLabel, BorderLayout.SOUTH);
        
        // Main content
        JPanel mainContent = new JPanel(new GridLayout(2, 1, 10, 10));
        mainContent.setOpaque(false);
        mainContent.add(statsPanel);
        mainContent.add(quickActionsPanel);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
        
        // Environmental quote
        JPanel quotePanel = createQuotePanel();
        add(quotePanel, BorderLayout.SOUTH);
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Statistik Anda"));
        panel.setBackground(Color.WHITE);
        
        // Stat cards
        panel.add(createStatCard("Sampah Didaur Ulang", "25 kg", "📦", new Color(59, 130, 246)));
        panel.add(createStatCard("CO2 Dikurangi", "150 kg", "🌱", new Color(34, 197, 94)));
        panel.add(createStatCard("Tantangan Selesai", "8", "🏆", new Color(245, 158, 11)));
        panel.add(createStatCard("Poin Lingkungan", "1,250", "⭐", new Color(168, 85, 247)));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, String emoji, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setBackground(Color.WHITE);
        
        JLabel emojiLabel = new JLabel(emoji, SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(valueLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(titleLabel);
        
        return card;
    }
    
    private JPanel createQuickActionsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 15, 15));
        panel.setBorder(BorderFactory.createTitledBorder("Aksi Cepat"));
        panel.setBackground(Color.WHITE);
        
        // Quick action buttons
        panel.add(createActionButton("Catat Sampah", "♻️", new Color(34, 197, 94)));
        panel.add(createActionButton("Hitung Karbon", "🌱", new Color(59, 130, 246)));
        panel.add(createActionButton("Tips Hari Ini", "💡", new Color(245, 158, 11)));
        panel.add(createActionButton("Tantangan Baru", "🎯", new Color(168, 85, 247)));
        panel.add(createActionButton("Komunitas", "👥", new Color(236, 72, 153)));
        panel.add(createActionButton("Laporan", "📊", new Color(20, 184, 166)));
        
        return panel;
    }
    
    private JButton createActionButton(String text, String emoji, Color color) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        button.setFocusPainted(false);
        
        JLabel emojiLabel = new JLabel(emoji, SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        
        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 12));
        textLabel.setForeground(color);
        
        button.add(emojiLabel, BorderLayout.CENTER);
        button.add(textLabel, BorderLayout.SOUTH);
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });
        
        // Action listener
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Fitur '" + text + "' akan segera tersedia!\nTerima kasih atas kesabaran Anda.", 
                "Info", JOptionPane.INFORMATION_MESSAGE);
        });
        
        return button;
    }
    
    private JPanel createQuotePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panel.setOpaque(false);
        
        String[] quotes = {
            "\"Bumi tidak kita warisi dari nenek moyang, tetapi kita pinjam dari anak cucu kita.\"",
            "\"Setiap tindakan kecil untuk lingkungan adalah langkah besar untuk masa depan.\"",
            "\"Jadilah perubahan yang ingin kamu lihat di dunia.\"",
            "\"Alam tidak terburu-buru, namun semuanya tercapai.\"",
            "\"Kita tidak mewarisi bumi dari leluhur kita, kita meminjamnya dari anak-anak kita.\""
        };
        
        String randomQuote = quotes[(int) (Math.random() * quotes.length)];
        
        JLabel quoteLabel = new JLabel("<html><div style='text-align: center; font-style: italic;'>" + 
                                     randomQuote + "</div></html>");
        quoteLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        quoteLabel.setForeground(new Color(75, 85, 99));
        quoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(quoteLabel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy - HH:mm");
        dateLabel.setText(sdf.format(new Date()));
    }
}
