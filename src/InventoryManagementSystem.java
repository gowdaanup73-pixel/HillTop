import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class InventoryManagementSystem extends JFrame {

    // Theme Colors
    private static final Color CORPORATE_BLUE = new Color(0, 86, 179);
    private static final Color BACKGROUND_WHITE = Color.WHITE;
    private static final Color PANEL_BACKGROUND = new Color(244, 246, 249);
    private static final Color TEXT_DARK = new Color(33, 37, 41);
    
    // Fonts
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 22);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font TABLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    // Components
    private JTable productTable;
    private DefaultTableModel tableModel;

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(PANEL_BACKGROUND);

        // Top Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(CORPORATE_BLUE);
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("INVENTORY MANAGEMENT SYSTEM");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Center Panel for Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(PANEL_BACKGROUND);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 10));

        // Setup Table
        String[] columns = {"Product ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing directly in table
            }
        };
        productTable = new JTable(tableModel);
        productTable.setFont(TABLE_FONT);
        productTable.setRowHeight(30);
        productTable.setGridColor(new Color(220, 220, 220));
        productTable.setSelectionBackground(new Color(200, 225, 255));
        productTable.setSelectionForeground(TEXT_DARK);

        // Style Table Header
        JTableHeader tableHeader = productTable.getTableHeader();
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setBackground(Color.WHITE);
        tableHeader.setForeground(TEXT_DARK);
        
        // Center text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < productTable.getColumnModel().getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scrollPane.getViewport().setBackground(Color.WHITE);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Right Panel for Action Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setBackground(PANEL_BACKGROUND);
        actionPanel.setBorder(new EmptyBorder(20, 10, 20, 20));

        JButton btnAdd = createStyledButton("Add New Product");
        JButton btnUpdate = createStyledButton("Update Selected");
        JButton btnDelete = createStyledButton("Delete Selected");
        JButton btnSearch = createStyledButton("Search Product");
        JButton btnSave = createStyledButton("Save Data");
        JButton btnLoad = createStyledButton("Load Data");

        actionPanel.add(Box.createVerticalStrut(20));
        actionPanel.add(btnAdd);
        actionPanel.add(Box.createVerticalStrut(15));
        actionPanel.add(btnUpdate);
        actionPanel.add(Box.createVerticalStrut(15));
        actionPanel.add(btnDelete);
        actionPanel.add(Box.createVerticalStrut(15));
        actionPanel.add(btnSearch);
        actionPanel.add(Box.createVerticalGlue()); // Pushes the rest to the bottom
        actionPanel.add(btnSave);
        actionPanel.add(Box.createVerticalStrut(15));
        actionPanel.add(btnLoad);

        // Add dummy data for visual testing
        tableModel.addRow(new Object[]{"P001", "Wireless Mouse", "50", "$25.00"});
        tableModel.addRow(new Object[]{"P002", "Mechanical Keyboard", "30", "$75.00"});
        tableModel.addRow(new Object[]{"P003", "27-inch Monitor", "15", "$250.00"});

        // Assemble Main Frame
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.EAST);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(CORPORATE_BLUE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add minimal hover effect mimicking modern web (shadows/glow in pure swing are tricky, we'll shift color lightly)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 105, 217)); // Slightly lighter blue
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(CORPORATE_BLUE);
            }
        });
        
        return button;
    }

    public static void main(String[] args) {
        // Set Look and Feel to System to allow better foundation before our custom styling
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            InventoryManagementSystem ims = new InventoryManagementSystem();
            ims.setVisible(true);
        });
    }
}
