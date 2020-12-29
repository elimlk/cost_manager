package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CostManagerView implements ActionListener {

    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    public CostManagerView () {

        frame = new JFrame();
        frame.setSize(680,480);
        frame.setLocationRelativeTo(null);

        JButton submitCostButton = new JButton("Submit Cost");
        JButton addCategoryButton = new JButton("Add Category");
        JButton refreshReportButton = new JButton("Refresh Report");

        //button.addActionListener(this);
        label = new JLabel ("Label Test");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(submitCostButton);
        panel.add(addCategoryButton);
        panel.add(refreshReportButton);

        //panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cost Manager");
        // frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
