import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class SJF extends JFrame {

    public static JFrame frame;
    public static JPanel panel;
    public static JLabel CPUlabel, CPUtxt, AvgWT, AvgTT, AWTtxt, ATTtxt, Pro, BurLable, WaitLable, QueueLabel;
    public static JTextField queuetxt;
    public static JProgressBar[] pbar;
    public static JLabel[] proc, waitTime, burstTime;

    public SJF() {
        frame = new JFrame("Shortest Job First");

        frame.setSize(900, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(900, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // JPanel panel = new JPanel() {
        // @Override
        // protected void paintComponent(Graphics g) {
        // super.paintComponent(g);
        // // Load the image from a file
        // ImageIcon background = new ImageIcon("00000.jpg");
        // Image image = background.getImage();
        // g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        // }
        // };

        // panel.setLayout(null); // Use null layout for precise component positioning

        // // Add the custom backgroundPanel to the JFrame
        // frame.setContentPane(panel);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(900, 500);
        frame.add(panel);
        panel.setBackground(Color.DARK_GRAY);
        // Create the heading label
        CPUlabel = new JLabel("CPU allocated to:");
        CPUlabel.setFont(new Font("Times", Font.BOLD, 17));
        CPUlabel.setBounds(10, 10, 200, 30);
        CPUlabel.setForeground(Color.white);
        panel.add(CPUlabel);

        CPUtxt = new JLabel("....");
        CPUtxt.setFont(new Font("Times", Font.BOLD, 17));
        CPUtxt.setBounds(220, 10, 200, 30);
        CPUtxt.setForeground(Color.white);
        panel.add(CPUtxt);

        AvgWT = new JLabel("Avg Waiting Time:");
        AvgWT.setFont(new Font("Times", Font.BOLD, 17));
        AvgWT.setBounds(10, 50, 200, 30);
        AvgWT.setForeground(Color.white);
        panel.add(AvgWT);

        AWTtxt = new JLabel("....");
        AWTtxt.setFont(new Font("Times", Font.BOLD, 17));
        AWTtxt.setBounds(220, 50, 200, 30);
        AWTtxt.setForeground(Color.white);
        panel.add(AWTtxt);

        AvgTT = new JLabel("Avg Turnaround Time:");
        AvgTT.setFont(new Font("Times", Font.BOLD, 17));
        AvgTT.setBounds(10, 90, 200, 30);
        AvgTT.setForeground(Color.white);
        panel.add(AvgTT);

        ATTtxt = new JLabel("....");
        ATTtxt.setFont(new Font("Times", Font.BOLD, 17));
        ATTtxt.setBounds(220, 90, 200, 30);
        ATTtxt.setForeground(Color.white);
        panel.add(ATTtxt);

        Pro = new JLabel("Processes");
        Pro.setFont(new Font("Times", Font.BOLD, 17));
        Pro.setBounds(325, 10, 200, 20);
        Pro.setForeground(Color.white);
        panel.add(Pro);

        // processes print
        proc = new JLabel[11];
        for (int i = 1; i <= 10; i++) {
            proc[i] = new JLabel("P" + i);
            proc[i].setFont(new Font("Times", Font.BOLD, 17));
            proc[i].setBounds(350, 40 * i, 200, 20);
            proc[i].setForeground(Color.white);
            panel.add(proc[i]);

        }
        pbar = new JProgressBar[11];
        for (int i = 1; i <= 10; i++) {
            pbar[i] = new JProgressBar();
            pbar[i].setValue(0);
            pbar[i].setStringPainted(true);
            pbar[i].setBounds(390, 40 * i, 225, 23);
            panel.add(pbar[i]);

        }

        JLabel BurLable = new JLabel("Wait Time");
        BurLable.setFont(new Font("Times", Font.BOLD, 12));
        BurLable.setBounds(665, 10, 200, 20);
        BurLable.setForeground(Color.white);
        panel.add(BurLable);
        burstTime = new JLabel[11];
        for (int i = 1; i <= 10; i++) {
            burstTime[i] = new JLabel("0s");
            burstTime[i].setFont(new Font("Times", Font.BOLD, 17));
            burstTime[i].setBounds(700, 40 * i, 200, 20);
            burstTime[i].setForeground(Color.white);
            panel.add(burstTime[i]);

        }

        WaitLable = new JLabel("TurnaroundTime");
        WaitLable.setFont(new Font("Times", Font.BOLD, 11));
        WaitLable.setBounds(755, 10, 200, 20);
        WaitLable.setForeground(Color.white);
        panel.add(WaitLable);
        waitTime = new JLabel[11];
        for (int i = 1; i <= 10; i++) {
            waitTime[i] = new JLabel("0s");
            waitTime[i].setFont(new Font("Times", Font.BOLD, 17));
            waitTime[i].setBounds(800, 40 * i, 200, 20);
            waitTime[i].setForeground(Color.white);
            panel.add(waitTime[i]);

        }

        QueueLabel = new JLabel("Ready Queue:");
        QueueLabel.setFont(new Font("Times", Font.BOLD, 17));
        QueueLabel.setBounds(10, 350, 200, 30);
        QueueLabel.setForeground(Color.white);
        panel.add(QueueLabel);
        queuetxt = new JTextField(1);
        queuetxt.setFont(new Font("Times", Font.BOLD, 14));
        queuetxt.setBounds(10, 390, 300, 30);
        queuetxt.setBackground(Color.white);
        panel.add(queuetxt);

    }

    public static void start(String file, int num) {
        List<String> processIds = new ArrayList<>();
        List<Integer> arrivalTimes = new ArrayList<>();
        List<Integer> burstTimes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int linesRead = 0;
            while ((line = br.readLine()) != null && linesRead < num) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    processIds.add(parts[0]);
                    arrivalTimes.add(Integer.parseInt(parts[1]));
                    burstTimes.add(Integer.parseInt(parts[2]));
                    linesRead++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a ready queue array by sorting the lists based on arrival times
        int size = processIds.size();
        String[] readyQueueProcessIds = new String[size];
        int[] readyQueueArrivalTimes = new int[size];
        int[] readyQueueBurstTimes = new int[size];

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indexes.add(i);
        }

        indexes.sort(Comparator.comparing(burstTimes::get));

        for (int i = 0; i < size; i++) {
            int originalIndex = indexes.get(i);
            readyQueueProcessIds[i] = processIds.get(originalIndex);
            readyQueueArrivalTimes[i] = arrivalTimes.get(originalIndex);
            readyQueueBurstTimes[i] = burstTimes.get(originalIndex);
        }
        String arr = Arrays.toString(readyQueueProcessIds);
        queuetxt.setText(arr);

        // Now you have the ready queue arrays sorted by arrival times
        System.out.println("Ready Queue (sorted by arrival times):");
        for (int i = 0; i < size; i++) {
            System.out.println("Process ID: " + readyQueueProcessIds[i] + " Arrival Time: " + readyQueueArrivalTimes[i]
                    + " Burst Time: " + readyQueueBurstTimes[i]);
        }

        // pbar[indexes.get(0) + 1].setValue(100);

        new Thread(() -> {
            int turnTime = 0;
            int total = 0;
            int tol=0;
            for (int i = 0; i < num; i++) {
                // queuetxt.setText(Arrays.toString(arr.substring(i+1)));
                queuetxt.setText(arr.substring((i+1)*4));
                CPUtxt.setText(readyQueueProcessIds[i]);
                CPUtxt.setForeground(Color.RED);
                pbar[indexes.get(i) + 1].setMinimum(0);
                pbar[indexes.get(i) + 1].setMaximum((readyQueueBurstTimes[i]+1)*100);
                burstTime[indexes.get(i) + 1].setText(total + "s");
                turnTime=total+readyQueueBurstTimes[i];
                tol=tol+turnTime;
                waitTime[indexes.get(i) + 1].setText( turnTime+ "s");
                // while (readyQueueBurstTimes[i] != 0) {
                    int cnt=0;
                    while (cnt <= (readyQueueBurstTimes[i]+1)*100) {
                        cnt=cnt+10;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        pbar[indexes.get(i) + 1].setValue(cnt);
                        

                    }
                    total = total + readyQueueBurstTimes[i];
                    readyQueueBurstTimes[i] = 0;
                    
                // }
            }
            AWTtxt.setText((float)total/num +"s");
            ATTtxt.setText((float)tol/num +"s");
            CPUtxt.setText("Idle");

        }).start();
        
    }

}
