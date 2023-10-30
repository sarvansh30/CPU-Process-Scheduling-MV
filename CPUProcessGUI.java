import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CPUProcessGUI extends JFrame {
    private JProgressBar[] progressBars;
    private JLabel[] labels;
    private JTextField[] textFields;

    private List<Process> processes;

    public CPUProcessGUI() {
        // Set the title of the frame
        setTitle("CPU Process Metrics");

        // Create a JPanel to hold the components
        JPanel panel = new JPanel(new GridLayout(20, 10, 1, 3)); // Rows, columns, horizontal gap, vertical gap

        // Create an array to hold the progress bars
        progressBars = new JProgressBar[10];

        // Create arrays to hold labels and text fields
        labels = new JLabel[4];
        textFields = new JTextField[4];

        // Create labels and text fields for metrics
        String[] metricLabels = {"Ready Queue:", "Waiting Time:", "Turnaround Time:", "CPU Average Wait Time"};

        for (int i = 0; i < 4; i++) {
            labels[i] = new JLabel(metricLabels[i]);
            textFields[i] = new JTextField(10);
            textFields[i].setEditable(false); // Make text fields read-only

            // Add labels and text fields to the panel
            panel.add(labels[i]);
            panel.add(textFields[i]);
        }

        // Create and add progress bars and labels for each process
        processes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setValue(0);
            progressBars[i].setStringPainted(true);
            panel.add(new JLabel("Process " + (i + 1)));
            panel.add(progressBars[i]);
            panel.add(new JLabel("")); // Empty label as a placeholder

            // Generate random arrival and burst times
            int arrivalTime = new Random().nextInt(10);
            int burstTime = new Random().nextInt(10);
            Process process = new Process(i + 1, arrivalTime, burstTime);
            processes.add(process);
        }

        // Create a button to start the progress simulation
        JButton startButton = new JButton("Start Simulation");
        panel.add(startButton);

        // Add an action listener to the Start Simulation button
        startButton.addActionListener(e -> {
            startFCFSSimulation();
        });

        // Add the panel to the frame
        add(panel);

        // Set the default close operation and frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to simulate the FCFS algorithm
    private void startFCFSSimulation() {
        int currentTime = 0;

        for (Process process : processes) {
            if (currentTime < process.getArrivalTime()) {
                // If the process has not yet arrived, wait until its arrival time
                currentTime = process.getArrivalTime();
            }

            // Set progress bar value
            progressBars[process.getId() - 1].setValue(100);

            // Calculate and display waiting time
            int waitingTime = currentTime - process.getArrivalTime();
            textFields[1].setText(Integer.toString(waitingTime));

            // Update current time
            currentTime += process.getBurstTime();

            // Calculate and display turnaround time
            int turnaroundTime = currentTime - process.getArrivalTime();
            textFields[2].setText(Integer.toString(turnaroundTime));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CPUProcessGUI gui = new CPUProcessGUI();
            gui.setVisible(true);
        });
    }
}

class Process {
    private int id;
    private int arrivalTime;
    private int burstTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
}
