import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class LabelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create a label with custom font and color
        JLabel label = new JLabel("Styled Label");
        label.setFont(new Font("Arial", Font.BOLD, 18)); // Set the font
        label.setForeground(Color.BLUE); // Set the text color
        panel.add(label);

        // Create a label with custom style (e.g., underline)
        JLabel styledLabel = new JLabel("Styled Text");
        Map<TextAttribute, Integer> fontAttributes = new HashMap<>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        styledLabel.setFont(new Font("Arial", Font.PLAIN, 16).deriveFont(fontAttributes));
        styledLabel.setForeground(Color.RED); // Set the text color
        panel.add(styledLabel);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
