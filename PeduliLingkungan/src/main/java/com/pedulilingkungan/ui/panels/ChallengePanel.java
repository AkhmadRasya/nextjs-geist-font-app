package com.pedulilingkungan.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Panel untuk tantangan lingkungan
 */
public class ChallengePanel extends JPanel {
    private JList<Challenge> challengeList;
    private DefaultListModel<Challenge> challengeModel;
    private JTextArea challengeDetailArea;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private JLabel pointsLabel;
    private List<Challenge> activeChallenges;
    private int totalPoints = 0;
    
    public ChallengePanel() {
        activeChallenges = new ArrayList<>();
        initializeChallenges();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        updateProgress();
    }
    
    private void initializeChallenges() {
        // Create sample challenges
        Calendar cal = Calendar.getInstance();
        
        // Weekly challenges
        activeChallenges.add(new Challenge(
            "Hemat Listrik Seminggu",
            "Kurangi penggunaan listrik 20% selama seminggu",
            "Matikan lampu yang tidak perlu, cabut charger, dan gunakan AC secukupnya",
            ChallengeType.WEEKLY,
            100,
            getDateAfterDays(7),
            ChallengeStatus.ACTIVE
        ));
        
        activeChallenges.add(new Challenge(
            "Zero Waste Day",
            "Tidak menghasilkan sampah plastik selama 3 hari",
            "Gunakan tas belanja sendiri, hindari kemasan plastik, bawa botol minum",
            ChallengeType.DAILY,
            75,
            getDateAfterDays(3),
            ChallengeStatus.ACTIVE
        ));
        
        activeChallenges.add(new Challenge(
            "Transportasi Hijau",
            "Gunakan transportasi ramah lingkungan selama 5 hari",
            "Naik sepeda, jalan kaki, atau transportasi umum",
            ChallengeType.WEEKLY,
            120,
            getDateAfterDays(5),
            ChallengeStatus.ACTIVE
        ));
        
        // Monthly challenges
        activeChallenges.add(new Challenge(
            "Tanam 10 Pohon",
            "Tanam atau adopsi 10 pohon dalam sebulan",
            "Tanam di halaman, ikut program penghijauan, atau adopsi pohon online",
            ChallengeType.MONTHLY,
            300,
            getDateAfterDays(30),
            ChallengeStatus.ACTIVE
        ));
        
        // Completed challenges
        activeChallenges.add(new Challenge(
            "Hemat Air",
            "Kurangi penggunaan air 30% selama seminggu",
            "Mandi lebih singkat, perbaiki kebocoran, gunakan air secukupnya",
            ChallengeType.WEEKLY,
            80,
            getDateAfterDays(-2),
            ChallengeStatus.COMPLETED
        ));
        
        activeChallenges.add(new Challenge(
            "Daur Ulang Sampah",
            "Daur ulang 5kg sampah dalam seminggu",
            "Pisahkan sampah, buat kompos, daur ulang kertas dan plastik",
            ChallengeType.WEEKLY,
            90,
            getDateAfterDays(-5),
            ChallengeStatus.COMPLETED
        ));
        
        // Calculate total points from completed challenges
        totalPoints = activeChallenges.stream()
                                   .filter(c -> c.getStatus() == ChallengeStatus.COMPLETED)
                                   .mapToInt(Challenge::getPoints)
                                   .sum();
    }
    
    private Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(248, 250, 252));
        
        // Challenge list
        challengeModel = new DefaultListModel<>();
        for (Challenge challenge : activeChallenges) {
            challengeModel.addElement(challenge);
        }
        
        challengeList = new JList<>(challengeModel);
        challengeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        challengeList.setCellRenderer(new ChallengeListCellRenderer());
        challengeList.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Challenge detail area
        challengeDetailArea = new JTextArea();
        challengeDetailArea.setFont(new Font("Arial", Font.PLAIN, 13));
        challengeDetailArea.setEditable(false);
        challengeDetailArea.setLineWrap(true);
        challengeDetailArea.setWrapStyleWord(true);
        challengeDetailArea.setBackground(new Color(249, 250, 251));
        challengeDetailArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Progress components
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));
        progressBar.setForeground(new Color(34, 197, 94));
        
        progressLabel = new JLabel("Pilih tantangan untuk melihat detail");
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        pointsLabel = new JLabel("Total Poin: " + totalPoints);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        pointsLabel.setForeground(new Color(245, 158, 11));
    }
    
    private void setupLayout() {
        // Left panel - Challenge list
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Daftar Tantangan"));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(350, 0));
        
        JScrollPane listScrollPane = new JScrollPane(challengeList);
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        
        // Filter buttons
        JPanel filterPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        filterPanel.setBackground(Color.WHITE);
        
        JButton allButton = new JButton("Semua");
        JButton activeButton = new JButton("Aktif");
        JButton completedButton = new JButton("Selesai");
        JButton newButton = new JButton("Tambah");
        
        Font buttonFont = new Font("Arial", Font.BOLD, 10);
        allButton.setFont(buttonFont);
        activeButton.setFont(buttonFont);
        completedButton.setFont(buttonFont);
        newButton.setFont(buttonFont);
        
        allButton.setBackground(new Color(107, 114, 128));
        activeButton.setBackground(new Color(34, 197, 94));
        completedButton.setBackground(new Color(59, 130, 246));
        newButton.setBackground(new Color(245, 158, 11));
        
        allButton.setForeground(Color.WHITE);
        activeButton.setForeground(Color.WHITE);
        completedButton.setForeground(Color.WHITE);
        newButton.setForeground(Color.WHITE);
        
        allButton.setFocusPainted(false);
        activeButton.setFocusPainted(false);
        completedButton.setFocusPainted(false);
        newButton.setFocusPainted(false);
        
        filterPanel.add(allButton);
        filterPanel.add(activeButton);
        filterPanel.add(completedButton);
        filterPanel.add(newButton);
        
        leftPanel.add(filterPanel, BorderLayout.SOUTH);
        
        // Right panel - Challenge details
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setBackground(Color.WHITE);
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        headerPanel.add(pointsLabel, BorderLayout.WEST);
        
        // Detail panel
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Detail Tantangan"));
        detailPanel.setBackground(Color.WHITE);
        
        JScrollPane detailScrollPane = new JScrollPane(challengeDetailArea);
        detailScrollPane.setPreferredSize(new Dimension(0, 200));
        detailPanel.add(detailScrollPane, BorderLayout.CENTER);
        
        // Progress panel
        JPanel progressPanel = new JPanel(new BorderLayout(5, 5));
        progressPanel.setBorder(BorderFactory.createTitledBorder("Progress"));
        progressPanel.setBackground(Color.WHITE);
        progressPanel.add(progressLabel, BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        
        // Action panel
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(Color.WHITE);
        
        JButton joinButton = new JButton("Ikuti Tantangan");
        JButton completeButton = new JButton("Selesaikan");
        JButton shareButton = new JButton("Bagikan");
        
        joinButton.setFont(new Font("Arial", Font.BOLD, 12));
        completeButton.setFont(new Font("Arial", Font.BOLD, 12));
        shareButton.setFont(new Font("Arial", Font.BOLD, 12));
        
        joinButton.setBackground(new Color(34, 197, 94));
        completeButton.setBackground(new Color(59, 130, 246));
        shareButton.setBackground(new Color(168, 85, 247));
        
        joinButton.setForeground(Color.WHITE);
        completeButton.setForeground(Color.WHITE);
        shareButton.setForeground(Color.WHITE);
        
        joinButton.setFocusPainted(false);
        completeButton.setFocusPainted(false);
        shareButton.setFocusPainted(false);
        
        actionPanel.add(joinButton);
        actionPanel.add(completeButton);
        actionPanel.add(shareButton);
        
        rightPanel.add(headerPanel, BorderLayout.NORTH);
        rightPanel.add(detailPanel, BorderLayout.CENTER);
        rightPanel.add(progressPanel, BorderLayout.SOUTH);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(actionPanel, BorderLayout.CENTER);
        rightPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Main layout
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        
        // Event handlers
        allButton.addActionListener(e -> filterChallenges(null));
        activeButton.addActionListener(e -> filterChallenges(ChallengeStatus.ACTIVE));
        completedButton.addActionListener(e -> filterChallenges(ChallengeStatus.COMPLETED));
        newButton.addActionListener(e -> showNewChallengeDialog());
        
        joinButton.addActionListener(e -> joinChallenge());
        completeButton.addActionListener(e -> completeChallenge());
        shareButton.addActionListener(e -> shareChallenge());
    }
    
    private void setupEventHandlers() {
        challengeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayChallengeDetails();
            }
        });
        
        // Select first challenge by default
        if (challengeModel.getSize() > 0) {
            challengeList.setSelectedIndex(0);
        }
    }
    
    private void displayChallengeDetails() {
        Challenge selected = challengeList.getSelectedValue();
        if (selected != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            StringBuilder details = new StringBuilder();
            details.append("🎯 ").append(selected.getTitle()).append("\n\n");
            details.append("📝 DESKRIPSI:\n");
            details.append(selected.getDescription()).append("\n\n");
            details.append("💡 CARA MENYELESAIKAN:\n");
            details.append(selected.getInstructions()).append("\n\n");
            details.append("📅 BATAS WAKTU: ").append(sdf.format(selected.getDeadline())).append("\n");
            details.append("🏆 POIN REWARD: ").append(selected.getPoints()).append(" poin\n");
            details.append("📊 STATUS: ").append(getStatusText(selected.getStatus())).append("\n");
            details.append("🏷️ KATEGORI: ").append(selected.getType().getDisplayName());
            
            challengeDetailArea.setText(details.toString());
            
            // Update progress
            updateChallengeProgress(selected);
        }
    }
    
    private void updateChallengeProgress(Challenge challenge) {
        if (challenge.getStatus() == ChallengeStatus.COMPLETED) {
            progressBar.setValue(100);
            progressBar.setString("Tantangan Selesai! ✅");
            progressLabel.setText("Selamat! Anda telah menyelesaikan tantangan ini.");
        } else {
            // Calculate progress based on time remaining
            long now = System.currentTimeMillis();
            long deadline = challenge.getDeadline().getTime();
            long timeRemaining = deadline - now;
            
            if (timeRemaining > 0) {
                // Simulate progress (in real app, this would be based on actual user actions)
                int simulatedProgress = (int) (Math.random() * 60) + 10;
                progressBar.setValue(simulatedProgress);
                progressBar.setString(simulatedProgress + "% Selesai");
                
                long daysRemaining = timeRemaining / (1000 * 60 * 60 * 24);
                progressLabel.setText("Sisa waktu: " + daysRemaining + " hari");
            } else {
                progressBar.setValue(0);
                progressBar.setString("Waktu Habis ⏰");
                progressLabel.setText("Tantangan telah berakhir.");
            }
        }
    }
    
    private void updateProgress() {
        pointsLabel.setText("Total Poin: " + totalPoints);
    }
    
    private String getStatusText(ChallengeStatus status) {
        switch (status) {
            case ACTIVE: return "🟢 Aktif";
            case COMPLETED: return "✅ Selesai";
            case EXPIRED: return "⏰ Berakhir";
            default: return "❓ Tidak Diketahui";
        }
    }
    
    private void filterChallenges(ChallengeStatus status) {
        challengeModel.clear();
        for (Challenge challenge : activeChallenges) {
            if (status == null || challenge.getStatus() == status) {
                challengeModel.addElement(challenge);
            }
        }
        
        if (challengeModel.getSize() > 0) {
            challengeList.setSelectedIndex(0);
        } else {
            challengeDetailArea.setText("Tidak ada tantangan untuk kategori ini.");
            progressBar.setValue(0);
            progressBar.setString("0%");
            progressLabel.setText("Pilih tantangan lain");
        }
    }
    
    private void joinChallenge() {
        Challenge selected = challengeList.getSelectedValue();
        if (selected != null && selected.getStatus() == ChallengeStatus.ACTIVE) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin mengikuti tantangan:\n" + selected.getTitle() + "?",
                "Konfirmasi Ikuti Tantangan",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Selamat! Anda telah bergabung dalam tantangan:\n" + selected.getTitle() + 
                    "\n\nMulai sekarang dan raih " + selected.getPoints() + " poin!",
                    "Berhasil Bergabung",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Tantangan ini tidak dapat diikuti saat ini.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void completeChallenge() {
        Challenge selected = challengeList.getSelectedValue();
        if (selected != null && selected.getStatus() == ChallengeStatus.ACTIVE) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin telah menyelesaikan tantangan:\n" + selected.getTitle() + "?",
                "Konfirmasi Selesaikan Tantangan",
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                selected.setStatus(ChallengeStatus.COMPLETED);
                totalPoints += selected.getPoints();
                updateProgress();
                displayChallengeDetails();
                
                JOptionPane.showMessageDialog(this,
                    "🎉 SELAMAT! 🎉\n\n" +
                    "Anda telah menyelesaikan tantangan:\n" + selected.getTitle() + 
                    "\n\nPoin yang diperoleh: +" + selected.getPoints() + 
                    "\nTotal poin Anda: " + totalPoints,
                    "Tantangan Selesai!",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Tantangan ini tidak dapat diselesaikan saat ini.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void shareChallenge() {
        Challenge selected = challengeList.getSelectedValue();
        if (selected != null) {
            String shareText = "🌱 TANTANGAN PEDULI LINGKUNGAN 🌱\n\n" +
                             "🎯 " + selected.getTitle() + "\n\n" +
                             "📝 " + selected.getDescription() + "\n\n" +
                             "🏆 Reward: " + selected.getPoints() + " poin\n\n" +
                             "Mari bersama menjaga lingkungan! 🌍\n" +
                             "#PeduliLingkungan #ChallengeHijau";
            
            // Copy to clipboard
            java.awt.datatransfer.StringSelection stringSelection = 
                new java.awt.datatransfer.StringSelection(shareText);
            java.awt.datatransfer.Clipboard clipboard = 
                java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            
            JOptionPane.showMessageDialog(this,
                "Tantangan telah disalin ke clipboard!\nBagikan ke teman-teman Anda!",
                "Berhasil Disalin",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void showNewChallengeDialog() {
        String message = "Fitur tambah tantangan baru akan segera tersedia!\n\n" +
                        "Saat ini Anda dapat:\n" +
                        "• Mengikuti tantangan yang tersedia\n" +
                        "• Menyelesaikan tantangan aktif\n" +
                        "• Berbagi tantangan dengan teman\n\n" +
                        "Terima kasih atas antusiasme Anda!";
        
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Custom cell renderer for challenge list
    private class ChallengeListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if (value instanceof Challenge) {
                Challenge challenge = (Challenge) value;
                String statusIcon = challenge.getStatus() == ChallengeStatus.COMPLETED ? "✅" : 
                                  challenge.getStatus() == ChallengeStatus.ACTIVE ? "🟢" : "⏰";
                setText(statusIcon + " " + challenge.getTitle() + " (" + challenge.getPoints() + " poin)");
                
                if (challenge.getStatus() == ChallengeStatus.COMPLETED) {
                    setForeground(isSelected ? Color.WHITE : new Color(34, 197, 94));
                } else if (challenge.getStatus() == ChallengeStatus.EXPIRED) {
                    setForeground(isSelected ? Color.WHITE : Color.GRAY);
                }
            }
            
            return this;
        }
    }
    
    // Challenge class
    private static class Challenge {
        private String title;
        private String description;
        private String instructions;
        private ChallengeType type;
        private int points;
        private Date deadline;
        private ChallengeStatus status;
        
        public Challenge(String title, String description, String instructions, 
                        ChallengeType type, int points, Date deadline, ChallengeStatus status) {
            this.title = title;
            this.description = description;
            this.instructions = instructions;
            this.type = type;
            this.points = points;
            this.deadline = deadline;
            this.status = status;
        }
        
        // Getters and setters
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getInstructions() { return instructions; }
        public ChallengeType getType() { return type; }
        public int getPoints() { return points; }
        public Date getDeadline() { return deadline; }
        public ChallengeStatus getStatus() { return status; }
        public void setStatus(ChallengeStatus status) { this.status = status; }
        
        @Override
        public String toString() {
            return title;
        }
    }
    
    // Enums
    private enum ChallengeType {
        DAILY("Harian"),
        WEEKLY("Mingguan"),
        MONTHLY("Bulanan");
        
        private String displayName;
        
        ChallengeType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() { return displayName; }
    }
    
    private enum ChallengeStatus {
        ACTIVE, COMPLETED, EXPIRED
    }
}
