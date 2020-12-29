package View;

import Model.CostItem;
import ViewModel.IViewModel;

import javax.swing.*;
import java.awt.*;

public class View implements IView {



    private ApplicationUI appUI;


    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                appUI = new ApplicationUI();
                appUI.start();
            }
        });
    }

    @Override
    public void setViewModel(IViewModel viewModel) {
    }

    @Override
    public void showMassege(String msg) {
    }

    @Override
    public void showItems(CostItem[] item) {
    }

    public class ApplicationUI{
        private JFrame frame;


        private JPanel mainPanel;
        private JPanel panelDates;
        private JPanel panelReport;
        private JPanel panelBottom;
        private JPanel panelCategory;
        private JPanel panelCost;

        //dates panel components
        private JLabel lbDates;
        private JTextField tfStartDate;
        private JTextField tfEndDate;
        private JButton bRefreshReport;

        private JLabel lbChartKeys;
        private JScrollPane sp;
        private JLabel lbCategory;
        private JLabel lbSubmitCost;

        public ApplicationUI() {
            frame = new JFrame();

            panelDates = new JPanel();
            lbDates = new JLabel("Choose dates: (MM/DD/YYYY) ");
            tfStartDate = new JTextField("Start date:",10);
            tfEndDate = new JTextField("End date:",10);
            bRefreshReport = new JButton("Show Report");

            panelReport =new JPanel();
            lbChartKeys = new JLabel("Chart keys:");


            panelBottom = new JPanel();
            panelCategory = new JPanel();
            panelCategory.add(lbCategory=new JLabel("category"));
            panelCost = new JPanel();
            panelCost.add(lbSubmitCost = new JLabel("Submit cost:"));

            panelBottom.add(panelCategory,BorderLayout.EAST);
            panelBottom.add(panelCost,BorderLayout.WEST);




        }

        public void start(){
            frame.setSize(680,480);
            frame.setLocationRelativeTo(null);

            panelDates.add(lbDates);
            panelDates.add(tfStartDate);
            panelDates.add(tfEndDate);
            panelDates.add(bRefreshReport);

            panelReport.setLayout(new BorderLayout(5,5));
            panelReport.add(lbChartKeys);


            frame.add(panelDates, BorderLayout.NORTH);
            frame.add(panelReport,BorderLayout.CENTER);
            frame.add(panelBottom,BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Cost Manager");
            frame.setVisible(true);
        }
    }


}
