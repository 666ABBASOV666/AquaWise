import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FishInformationViewer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowUI("fish.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void createAndShowUI(String filePath) throws IOException {
        JFrame frame = new JFrame("Fish Information Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the JLabels
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3, 100, 40)); // GridLayout with variable rows, 1 column and 10 pixels of horizontal and vertical gap

        // Read the content from the "fish.txt" file
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            String currentLine;

            int labelCount = 0; // To keep track of the label index

            while ((currentLine = reader.readLine()) != null) {
                // Create a JLabel for each line
                JLabel fishLabel = new JLabel(currentLine);

                // Set the preferred size (height) to be 5 times of the current height
                fishLabel.setPreferredSize(new Dimension(fishLabel.getPreferredSize().width / 2, fishLabel.getPreferredSize().height * 5));

                // Set the background color based on the label index
                fishLabel.setBackground(getLabelColor(labelCount));

                // Set other properties for background color to take effect
                fishLabel.setOpaque(true);

                // Add a black border around the JLabel
                fishLabel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(40, 40, 40, 40)));

                // Increment label count
                labelCount++;

                // Add the JLabel to the panel
                panel.add(fishLabel);
            }
        }

        // Add the panel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);

        // Set up scrolling
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set JFrame properties and make it visible
        frame.getContentPane().add(scrollPane);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to get label background color based on the specified pattern
    private static Color getLabelColor(int labelIndex) {
        int r = 0;
        int g = 0;
        int b = 0;

        // Change color pattern based on label index
        if (labelIndex % 3 == 0) {
            r = 204;
            g = 255;
            b = 255;
        } else if (labelIndex % 3 == 1) {
            r = 204;
            g = 204;
            b = 204;
        } else {
            r = 255;
            g = 153;
            b = 153;
        }

        return new Color(r, g, b);
    }
}
