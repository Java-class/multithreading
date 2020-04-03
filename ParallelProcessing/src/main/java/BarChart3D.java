
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class BarChart3D  extends ApplicationFrame {
    public static TimingResult _100K_duration;
    public static TimingResult _1M_duration;
    public static TimingResult _100M_duration;
    public static TimingResult _500M_duration;


    public BarChart3D(String title, TimingResult _100K_duration, TimingResult _1M_duration, TimingResult _100M_duration, TimingResult _500M_duration) {
        super(title);
        this._100K_duration = _100K_duration;
        this._1M_duration = _1M_duration;
        this._100M_duration = _100M_duration;
        this._500M_duration = _500M_duration;

        setContentPane(createDemoPanel());
    }
    public static JPanel createDemoPanel( ) {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    private static JFreeChart createChart() {
        final String linear = "LINEAR";
        final String _2Thread = "2Thread";
        final String _4Thread = "4Thread";
        final String _8Thread = "8Thread";

        
        final String _100k = "100K";
        final String _1M = "1M";
        final String _100M = "100M";
        final String _500M = "500M";

        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        dataset.addValue( _100K_duration.getLinearTime(), linear , _100k);
        dataset.addValue( _100K_duration.get_2ThreadsTime() , _2Thread , _100k );
        dataset.addValue( _100K_duration.get_4ThreadsTime() , _4Thread , _100k );
        dataset.addValue( _100K_duration.get_8ThreadsTime() , _8Thread , _100k );

        dataset.addValue( _1M_duration.getLinearTime(), linear , _1M );
        dataset.addValue( _1M_duration.get_2ThreadsTime() , _2Thread , _1M );
        dataset.addValue( _1M_duration.get_4ThreadsTime() , _4Thread , _1M );
        dataset.addValue( _1M_duration.get_8ThreadsTime() , _8Thread , _1M );

        dataset.addValue( _100M_duration.getLinearTime() , linear , _100M );
        dataset.addValue( _100M_duration.get_2ThreadsTime(), _2Thread , _100M );
        dataset.addValue( _100M_duration.get_4ThreadsTime() , _4Thread , _100M );
        dataset.addValue( _100M_duration.get_8ThreadsTime() , _8Thread , _100M );

        dataset.addValue( _500M_duration.getLinearTime(), linear , _500M );
        dataset.addValue( _500M_duration.get_2ThreadsTime(), _2Thread , _500M );
        dataset.addValue( _500M_duration.get_4ThreadsTime(), _4Thread , _500M );
        dataset.addValue( _500M_duration.get_8ThreadsTime(), _8Thread , _500M );


        JFreeChart barChart = ChartFactory.createBarChart(
                "Search Pattern  Result",
                "Category",
                "Time in milliseconds",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        return barChart;

    }

    public static void main(String[ ] args )throws Exception {
        BarChart3D demo = new BarChart3D( "Parallel Search" ,new TimingResult(5,5,5,5),new TimingResult(10,10,10,10),new TimingResult(20,20,20,20),new TimingResult(30,30,30,30));
        demo.setSize( 1000 , 800 );
        demo.setResizable(false);
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible( true );
    }
}