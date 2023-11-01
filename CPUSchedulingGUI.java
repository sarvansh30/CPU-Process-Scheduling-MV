import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CPUSchedulingGUI extends JFrame {

    private JComboBox<String> algorithmComboBox;
    private JButton browseButton;
    private JTextField numProcessesTextField;
    private JButton startButton;
     JFrame frame;
     JPanel panel;
    String selectedFile;

    public CPUSchedulingGUI() {

        frame=new JFrame("CPU Proces Scheduling");
        frame.setSize(900, 500);
        
         // Set the default close operation and frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
      
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image from a file
                ImageIcon background = new ImageIcon("00000.jpg");
                Image image = background.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null); // Use null layout for precise component positioning

        // Add the custom backgroundPanel to the JFrame
        frame.setContentPane(panel);


        // Create the heading label
        JLabel headingLabel = new JLabel("CPU Process Scheduling");
        headingLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text
        headingLabel.setFont(new Font("Times", Font.BOLD, 40)); // Increase font size
        headingLabel.setBounds(0, 20, 900, 100);
        headingLabel.setForeground(Color.white);
        panel.add(headingLabel);

        // Create the "Select Algorithm" label
        JLabel selectAlgorithmLabel = new JLabel("Select Algorithm:");
        selectAlgorithmLabel.setAlignmentX(panel.getAlignmentX()); // Center the label
        selectAlgorithmLabel.setFont(new Font("Times", Font.BOLD, 20));
        selectAlgorithmLabel.setBounds(255,0, 600, 300); // Set the size
        selectAlgorithmLabel.setForeground(Color.white);
        panel.add(selectAlgorithmLabel);

        // Create the algorithm selection combo box
        String[] algorithms = {"Round Robin", "Shortest Job First", "First Come First Serve"};
        algorithmComboBox = new JComboBox<>(algorithms);
        algorithmComboBox.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the combo box
        algorithmComboBox.setFont(new Font("Times", Font.BOLD, 15));
        algorithmComboBox.setBounds(455,140, 190, 27); // Set the size
        panel.add(algorithmComboBox);

         // Create the "Select Algorithm" label
        JLabel FileSelect= new JLabel("Choose text file:");
        FileSelect.setAlignmentX(panel.getAlignmentX()); // Center the label
        FileSelect.setFont(new Font("Times", Font.BOLD, 20));
        FileSelect.setBounds(255,50, 600, 300); // Set the size
        FileSelect.setForeground(Color.white);
        panel.add(FileSelect);

        // Create the "Browse" button
        browseButton = new JButton("Browse File");
        browseButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        browseButton.setFont(new Font("Times", Font.BOLD, 15));
        browseButton.setBounds(490,190, 120, 27); 
        panel.add(browseButton);

        // Create the "Number of Processes" label
        JLabel numProcessesLabel = new JLabel("Number of Processes:");
        numProcessesLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
          numProcessesLabel.setFont(new Font("Times", Font.BOLD, 18));
         numProcessesLabel.setBounds(255,100, 600, 300); // Set the size
         numProcessesLabel.setForeground(Color.white);
        panel.add(numProcessesLabel);
        JLabel ProcessLimit = new JLabel("0<n<=10");
        ProcessLimit.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
          ProcessLimit.setFont(new Font("Times", Font.BOLD, 18));
         ProcessLimit.setBounds(655,100, 600, 300); // Set the size
         ProcessLimit.setForeground(Color.white);
        panel.add(ProcessLimit);

        // Create the text field for entering the number of processes
        numProcessesTextField = new JTextField(1);
        numProcessesTextField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text field
       numProcessesTextField.setFont(new Font("Times", Font.BOLD, 15));
        numProcessesTextField.setBounds(455,235, 190, 27); 
        panel.add(numProcessesTextField);

        // Create the "Start" button
        startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
         startButton.setFont(new Font("Times", Font.BOLD, 18));
        startButton.setBounds(370,290, 120, 27);
        panel.add(startButton);

        JLabel TeamInfo = new JLabel("<html>Group Members:<br>42 Vansh Ranawat<br>46 Sarvansh Pachori<br>51 Anuj Deshmukh</html>");
        TeamInfo.setAlignmentX(panel.getAlignmentX()); // Center the label
        TeamInfo.setFont(new Font("Times", Font.PLAIN, 14));
        TeamInfo.setBounds(10, 270, 600, 300); // Set the size
        TeamInfo.setForeground(Color.white);
        panel.add(TeamInfo);
        

    //  frame.add(algorithmComboBox);
    //  frame.add(numProcessesLabel);
    //  frame.add(numProcessesTextField);
    //  frame.add(startButton);
    //  frame.add(browseButton);
    //  frame.setVisible(true);
        
        // Add action listeners for buttons
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    // Do something with the selected file
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                String numProcessesInput = numProcessesTextField.getText().trim(); // Trim to remove leading/trailing spaces
                String fileName = selectedFile;
        
                // Check if the number of processes is empty, less than or equal to 0, or greater than 10
                int numProcesses;
                try {
                    numProcesses = Integer.parseInt(numProcessesInput);
                    if (numProcesses <= 0 || numProcesses > 10) {
                        JOptionPane.showMessageDialog(null, "Number of processes must be between 1 and 10.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the action listener
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of processes.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the action listener
                }
        
                // Check if a file is selected and if it's a valid TXT file
                if (fileName == null || !fileName.toLowerCase().endsWith(".txt")) {
                    JOptionPane.showMessageDialog(null, "Please select a valid TXT file.", "File Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the action listener
                }
        
                // Handle the "Start" button click event based on the selected algorithm
                switch (selectedAlgorithm) {
                    case "Round Robin":
                        frame.setVisible(false);
                        RoundRobin round = new RoundRobin();
                        round.start(selectedFile, numProcesses);
                        // Code for Round Robin algorithm
                        break;
                    case "Shortest Job First":
                    frame.setVisible(false);
                        SJF obj2 = new SJF();
                        obj2.start(selectedFile, numProcesses);
                        // Code for Shortest Job First algorithm
                        break;
                    case "First Come First Serve":
                        frame.setVisible(false);
                        FCFS obj = new FCFS();
                        obj.start(selectedFile, numProcesses);
                        break;
                    default:
                        // Handle other cases or show an error message
                        break;
                }
            }
        });
        

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CPUSchedulingGUI gui = new CPUSchedulingGUI();
            // gui.setVisible(true);
        });
    }
}
