package com.pedulilingkungan.ui;

import com.pedulilingkungan.ui.panels.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Frame utama aplikasi Peduli Lingkungan
 */
public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private DashboardPanel dashboardPanel;
    private WasteTrackerPanel wasteTrackerPanel;
    private CarbonCalculatorPanel carbonCalculatorPanel;
    private TipsPanel tipsPanel;
    private ChallengePanel challengePanel;
    
    public MainFrame() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setTitle("Aplikasi Peduli Lingkungan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Set icon
        setIconImage(createEnvironmentIcon());
        
        // Initialize panels
        dashboardPanel = new DashboardPanel();
        wasteTrackerPanel = new WasteTrackerPanel();
        carbonCalculatorPanel = new CarbonCalculatorPanel();
        tipsPanel = new TipsPanel();
        challengePanel = new ChallengePanel();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        // Add tabs
        tabbedPane.addTab("🏠 Dashboard", dashboardPanel);
        tabbedPane.addTab("♻️ Pelacak Sampah", wasteTrackerPanel);
        tabbedPane.addTab("🌱 Kalkulator Karbon", carbonCalculatorPanel);
        tabbedPane.addTab("💡 Tips Lingkungan", tipsPanel);
        tabbedPane.addTab("🏆 Tantangan", challengePanel);
        
        // Create menu bar
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        
        // Add to frame
        add(tabbedPane, BorderLayout.CENTER);
        
        // Add status bar
        JPanel statusBar = createStatusBar();
        add(statusBar, BorderLayout.SOUTH);
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Keluar");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        
        // Help menu
        JMenu helpMenu = new JMenu("Bantuan");
        JMenuItem aboutItem = new JMenuItem("Tentang");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        return menuBar;
    }
    
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusBar.add(new JLabel("Siap - Mari bersama menjaga lingkungan!"));
        return statusBar;
    }
    
    private void setupEventHandlers() {
        // Tab change listener
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String tabName = tabbedPane.getTitleAt(selectedIndex);
            System.out.println("Tab berubah ke: " + tabName);
        });
    }
    
    private Image createEnvironmentIcon() {
        // Create a simple green circle as icon
        BufferedImage icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = icon.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(34, 197, 94)); // Green color
        g2d.fillOval(4, 4, 24, 24);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("🌱", 8, 22);
        g2d.dispose();
        return icon;
    }
    
    private void showAboutDialog() {
        String message = "Aplikasi Peduli Lingkungan\n" +
                        "Versi 1.0\n\n" +
                        "Aplikasi ini membantu Anda untuk:\n" +
                        "• Melacak aktivitas ramah lingkungan\n" +
                        "• Menghitung jejak karbon\n" +
                        "• Mendapatkan tips lingkungan\n" +
                        "• Mengikuti tantangan lingkungan\n\n" +
                        "Mari bersama menjaga bumi kita!";
        
        JOptionPane.showMessageDialog(this, message, "Tentang Aplikasi", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
}
