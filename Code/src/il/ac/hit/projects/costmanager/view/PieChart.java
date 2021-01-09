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
        setContentPane(createDemoPanel( ));
    }


    private static PieDataset createDataset( ) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "IPhone 5s" , 20.0 );
        dataset.setValue( "SamSung Grand" , 20.0 );
        dataset.setValue( "MotoGP" , 40.0 );
        dataset.setValue( "Nokia Lumia" , 10.0 );
        return dataset;
    }

    public static JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Mobile Sales",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public static JPanel createDemoPanel( ) {
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel( chart );
    }

//    public static void main( String[ ] args ) {
//        PieChart_AWT demo = new PieChart_AWT( "Mobile Sales" );
//        demo.setSize( 560 , 367 );
//        RefineryUtilities.centerFrameOnScreen( demo );
//        demo.setVisible( true );
//    }
}