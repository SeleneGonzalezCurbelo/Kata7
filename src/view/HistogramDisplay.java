package view;

import com.google.gson.Gson;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JPanel;

import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Histogram;

public class HistogramDisplay extends ApplicationFrame {
    
    private String jsonString;
    private static final int CHART_WIDTH = 500;
    private static final int CHART_HEIGHT = 400;
    
    public HistogramDisplay(String jsonString){
        super("HISTOGRAMA");
        if (jsonString == null) {
            throw new IllegalArgumentException("Histogram cannot be null");
        }
        this.jsonString = jsonString;
        setContentPane(createPanel());
        pack();
    }

    public void execute() {
        setVisible(true);
    }

    private JFreeChart createChart(DefaultCategoryDataset dataSet){
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(jsonString, Map.class);
        String dimension = (String) data.get("dimension");
        JFreeChart chart = ChartFactory.createBarChart("Flights", dimension, "Nº of flights", dataSet, PlotOrientation.VERTICAL, false, false, rootPaneCheckingEnabled);
        return chart;
    }
    
    private DefaultCategoryDataset createDataSet(String jsonString) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        Gson gson = new Gson();
        Map<String, Object> data = gson.fromJson(jsonString, Map.class);
        Map<String, Integer> histogram = (Map<String, Integer>) data.get("histogram");
        histogram.keySet().forEach((key) -> {
            dataSet.addValue(histogram.get(key), "", key);
        });
        return dataSet;
    }
    
    private JPanel createPanel(){
         DefaultCategoryDataset dataSet = createDataSet(jsonString);
         JFreeChart chart = createChart(dataSet);
         ChartPanel chartPanel = new ChartPanel(chart);
         chartPanel.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT)); 
         return chartPanel;
     }
}