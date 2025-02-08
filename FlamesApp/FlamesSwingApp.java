import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlamesSwingApp extends JFrame {
    private JTextField name1Field;
    private JTextField name2Field;
    private JLabel resultLabel;
    private JLabel titleLabel;

    public FlamesSwingApp() {
        setTitle("FLAMES Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Gradient Background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(135, 206, 250); // Light Sky Blue
                Color color2 = new Color(255, 182, 193); // Light Pink
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());
        titleLabel = new JLabel("FLAMES Result:", JLabel.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18)); // Title font

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns
        gbc.insets = new Insets(20, 10, 5, 10); // Adjusted spacing
        backgroundPanel.add(titleLabel, gbc);
        // Custom Font
        Font customFont = new Font("Tahoma", Font.BOLD, 16); 

        // Label for Name 1
        JLabel name1Label = new JLabel("Enter your name:");
        name1Label.setFont(customFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // spacing
        backgroundPanel.add(name1Label, gbc);

        // Input field for Name 1
        
        name1Field = new JTextField(25); 
        name1Field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        backgroundPanel.add(name1Field, gbc);

        // Label for Name 2
        JLabel name2Label = new JLabel("Enter second name:");
        name2Label.setFont(customFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(name2Label, gbc);

        // Input field for Name 2
        name2Field = new JTextField(25); // Made text box broader
        name2Field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        backgroundPanel.add(name2Field, gbc);

        // Button to calculate FLAMES result
        JButton calcButton = new JButton("Calculate FLAMES");
        calcButton.setFont(customFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(calcButton, gbc);

        // Result label to display the outcome
        resultLabel = new JLabel("Result will be shown here", JLabel.CENTER);
        resultLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        backgroundPanel.add(resultLabel, gbc);

        // Action listener for the button
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name1 = name1Field.getText().toLowerCase().trim();
                String name2 = name2Field.getText().toLowerCase().trim();
                if (!name1.isEmpty() && !name2.isEmpty()) {
                    int totalLetters = Calc(name1, name2);
                    String flamesResult = Flames(totalLetters);
                    resultLabel.setText(flamesResult);
                    name1Field.setText(name1);
                    name2Field.setText(name2);

                    // Add icon based on result
                    addVisuals(flamesResult);
                } else {
                    resultLabel.setText("Please enter both names.");
                }
            }
        });
    }

    private void addVisuals(String flamesResult) {
        // Setting icon based on the FLAMES result
        ImageIcon icon = null;
        switch (flamesResult) {
            case "FRIENDS! xD":
                icon = new ImageIcon("Friend.png"); 
                break;
            case "LOVE!!!":
                icon = new ImageIcon("love.png"); 
                break;
            case "AFFECTION":
                icon = new ImageIcon("affection.png"); 
                break;
            case "MARRIAGE!! xD":
                icon = new ImageIcon("marriage.png"); 
                break;
            case "ENEMIES":
                icon = new ImageIcon("path_to_enemies_icon.png"); 
                break;
            case "SISTER":
                icon = new ImageIcon("sisten.png"); 
                break;
            default:
                resultLabel.setIcon(null);
                return;
        }

        if(icon != null){
            Image image = icon.getImage();
            Image scaledImage=image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);

            resultLabel.setIcon(icon);
        }
    }

    public static int Calc(String name1, String name2) {
        StringBuilder f = new StringBuilder();
        int c = 0;

        // Loop through name1 and name2 to find differing characters
        for (int i = 0; i < name1.length(); i++) {
            boolean found = false;
            for (int j = 0; j < name2.length(); j++) {
                if (name1.charAt(i) == name2.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                f.append(name1.charAt(i));
                c++;
            }
        }

        // Loop through name2 to find differing characters
        for (int i = 0; i < name2.length(); i++) {
            boolean found = false;
            for (int j = 0; j < name1.length(); j++) {
                if (name2.charAt(i) == name1.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                f.append(name2.charAt(i));
                c++;
            }
        }
        return f.length();
    }

    public static String Flames(int n) {
        StringBuilder flames = new StringBuilder("FLAMES");
        n = n - 1;

        int l = flames.length();
        while (l > 1) {
            int i = n;
            while (i >= l) {
                i = i - l;
            }
            flames.deleteCharAt(i);
            l = flames.length();
        }

        switch (flames.toString()) {
            case "F":
                return "FRIENDS! xD";
            case "L":
                return "LOVE!!!";
            case "A":
                return "AFFECTION";
            case "M":
                return "MARRIAGE!! xD";
            case "E":
                return "ENEMIES";
            case "S":
                return "SISTER";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlamesSwingApp().setVisible(true);
            }
        });
    }
}
