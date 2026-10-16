package com.ragelite.ui;

import com.ragelite.model.User;
import com.ragelite.util.JobCounter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {

    private final JProgressBar progressBar;
    private final JLabel jobStatusLabel;
    private final JButton runJobButton;

    public DashboardFrame(User user) {
        setTitle(user.getDashboardTitle());
        setSize(440, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel welcome = new JLabel("Welcome, " + user.getName() + " (" + user.getRole() + ")", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(welcome, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String permission : user.getPermissions()) {
            listModel.addElement(permission);
        }
        JList<String> permissionList = new JList<>(listModel);
        add(new JScrollPane(permissionList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        bottomPanel.add(progressBar);

        jobStatusLabel = new JLabel("No training job running.", SwingConstants.CENTER);
        bottomPanel.add(jobStatusLabel);

        runJobButton = new JButton("Run Training Job (Background Thread)");
        runJobButton.addActionListener(e -> runTrainingJob());
        bottomPanel.add(runJobButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });
        bottomPanel.add(logoutButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void runTrainingJob() {
        runJobButton.setEnabled(false);
        JobCounter.incrementJobs();
        jobStatusLabel.setText("Training job started... Active jobs: " + JobCounter.getActiveJobs());

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int progress = 0; progress <= 100; progress += 10) {
                    Thread.sleep(300);
                    publish(progress);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latest = chunks.get(chunks.size() - 1);
                progressBar.setValue(latest);
            }

            @Override
            protected void done() {
                JobCounter.decrementJobs();
                jobStatusLabel.setText("Training job completed! Active jobs: " + JobCounter.getActiveJobs());
                runJobButton.setEnabled(true);
            }
        };

        worker.execute();
    }
}