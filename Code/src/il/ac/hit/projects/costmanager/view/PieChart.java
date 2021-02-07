package il.ac.hit.projects.costmanager.view;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChart extends ApplicationFrame {

    public PieChart( String title ) {
        super( title );
    }

    //create pie chart with specific dataset of costs items and return jchart
    public static JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Cost Summery",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

}