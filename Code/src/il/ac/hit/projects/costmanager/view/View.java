package il.ac.hit.projects.costmanager.view;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.model.CostManagerException;
import il.ac.hit.projects.costmanager.viewModel.IViewModel;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class View implements IView {



    private ApplicationUI appUI;
    private IViewModel vm;



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
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }


    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null,msg);
    }

    @Override
    public void showItems(CostItem[] item) {
    }

    public class ApplicationUI{
        private JFrame frame;

        private JPanel panelDates; // TOP

        private JPanel panelPieChart; // CENTER
        private JPanel panelCategoriesKeys; // LEFT
        private JPanel panelSum; // RIGHT

        private JPanel panelBottom; // BOTTOM
        private JPanel panelAddCategory; // BOTTOM LEFT
        private JPanel panelAddCost; // BOTTOM RIGHT


        //dates panel components
        private JLabel lbDates;
        private JTextField tfStartDate;
        private JTextField tfEndDate;
        private JButton btnRefreshReport;
        private JButton btnAddCost;

        private JLabel lbChartKeys;
        private JScrollPane scrollPaneCategories;
        private JLabel lbCategory;
        private JLabel lbSubmitCost;
        private JLabel lbPieChart;
        private JLabel lbCostSum;
        private JTextArea taCategoriesList;
        private JTextField tfNewCatName;
        private JButton btnAddCategory;

        private JTextField tfNewCostDetails;
        private JTextField tfNewCostCurrency;
        private JTextField tfNewCostCategory;
        private JTextField tfNewCostSum;
        private JTextField tfNewCostDate;


        public ApplicationUI() {
            frame = new JFrame();

            panelDates = new JPanel();

            panelPieChart = new JPanel();
            panelCategoriesKeys = new JPanel();
            panelSum = new JPanel();

            panelBottom = new JPanel();
            panelAddCategory = new JPanel();
            panelAddCost = new JPanel();

            btnRefreshReport = new JButton("Show Report");
            btnAddCategory = new JButton("Add Category");
            btnAddCost = new JButton("Add Cost");


            tfStartDate = new JTextField("Start date:",10);
            tfEndDate = new JTextField("End date:",10);
            tfNewCatName = new JTextField("New Category Name");
            tfNewCostCategory = new JTextField("Category");
            tfNewCostCurrency = new JTextField("Currency");
            tfNewCostDate = new JTextField("YYYY-MM-DD");
            tfNewCostDetails = new JTextField("Details");
            tfNewCostSum = new JTextField("Sum");

            taCategoriesList = new JTextArea();
            scrollPaneCategories = new JScrollPane (taCategoriesList);
            taCategoriesList.setEditable(false);

            lbDates = new JLabel("Choose dates: (MM/DD/YYYY) ");
            lbChartKeys = new JLabel("Chart keys:");
            lbPieChart = new JLabel("Pie Chart:");
            lbCostSum = new JLabel("Cost Sum");
            lbCategory=new JLabel("category");
            lbSubmitCost = new JLabel("Submit cost:");


        }

        public void start(){
            frame.setSize(680,480);
            frame.setLocationRelativeTo(null);

            panelDates.add(lbDates);
            panelDates.add(tfStartDate);
            panelDates.add(tfEndDate);
            panelDates.add(btnRefreshReport);

            panelCategoriesKeys.setLayout(new BorderLayout());
            panelCategoriesKeys.add(lbChartKeys, BorderLayout.NORTH);
            panelCategoriesKeys.add(scrollPaneCategories,BorderLayout.CENTER);

            panelSum.add(lbCostSum);

            panelAddCategory.add(tfNewCatName);
            panelAddCategory.add(btnAddCategory);

            panelAddCost.setLayout(new GridLayout(3,2));
            panelAddCost.add(tfNewCostDetails);
            panelAddCost.add(tfNewCostCurrency);
            panelAddCost.add(tfNewCostCategory);
            panelAddCost.add(tfNewCostSum);
            panelAddCost.add(tfNewCostDate);
            panelAddCost.add(btnAddCost);


            panelBottom.setLayout(new BorderLayout());
            panelBottom.add(panelAddCategory,BorderLayout.WEST);
            panelBottom.add(panelAddCost,BorderLayout.CENTER);

            taCategoriesList.setText(getCategoriesKeys());

            DefaultPieDataset dataset = new DefaultPieDataset( );
            dataset.setValue( "Food" , 800 );
            dataset.setValue( "Online shopping" , 300 );
            dataset.setValue( "Fuel and Cars" , 100 );
            dataset.setValue( "Bills" , 650 );

            PieChart pieChart = new PieChart("Cost Summary");
            panelPieChart = new ChartPanel( pieChart.createChart(dataset));


            frame.setLayout(new BorderLayout());
            frame.add(panelDates, BorderLayout.NORTH);
            frame.add(panelPieChart,BorderLayout.CENTER);
            frame.add(panelSum, BorderLayout.EAST);
            frame.add(panelCategoriesKeys, BorderLayout.WEST);
            frame.add(panelBottom,BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Cost Manager");
            frame.setVisible(true);

            btnAddCost.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        vm.addCostItem(new CostItem(Double.parseDouble(tfNewCostSum.getText()),
                                tfNewCostDetails.getText(),
                                tfNewCostCategory.getText(),
                                tfNewCostCurrency.getText(),
                                tfNewCostDate.getText()));
                        // not working problem with "isValidDate"
                        showMessage("Cost add successfully");
                    } catch (CostManagerException ex) {
                        showMessage(ex.getMessage());
                    }
                    showMessage("add cost msg");
                }
            });

            btnAddCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String newCatName = tfNewCatName.getText();
                        vm.addNewCat(newCatName);

                    } catch (Exception exception) {
                        showMessage(exception.getMessage());
                    }
                }
            });

            btnRefreshReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    dataset.setValue( "Food" , 1200 );
                    dataset.setValue( "Online shopping" , 150 );
                    dataset.setValue( "Fuel and Cars" , 300 );
                    dataset.setValue( "Bills" , 1000 );
                }
            });
        }


    }



    public String getCategoriesKeys(){

        List<String> categories = vm.getCategoriesKeys();

        StringBuilder sb = new StringBuilder();
        for(String cat : categories) {
            sb.append(cat);
            sb.append("\n");
        }
        String text = sb.toString();
        return text;
    }


}