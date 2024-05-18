package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class analytics extends ApplicationFrame {

    public analytics(String title) {
        super(title);
    }

    public void displayCharts() {
        DefaultCategoryDataset expTypeDataset = createExpTypeDataset();
        DefaultCategoryDataset lineDataset = createLineDataset();
        DefaultXYDataset scatterDataset = createScatterDataset();
        DefaultCategoryDataset correlationDataset = createCorrelationMatrix();

        JFreeChart expTypeChart = createBarChart(expTypeDataset, "Expense Type Distribution", "Exp Type", "Count");
        ChartPanel expTypeChartPanel = new ChartPanel(expTypeChart);
        expTypeChartPanel.setPreferredSize(new Dimension(800, 600));

        JFreeChart lineChart = createLineChart(lineDataset, "Numerical Data Over Time", "Time", "Value");
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        lineChartPanel.setPreferredSize(new Dimension(800, 600));

        JFreeChart pieChart = createPieChart(expTypeDataset, "Expense Type Distribution");
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(800, 600));

        JFreeChart scatterChart = createScatterPlot(scatterDataset, "Relationship Between Columns", "Column 1", "Column 2");
        ChartPanel scatterChartPanel = new ChartPanel(scatterChart);
        scatterChartPanel.setPreferredSize(new Dimension(800, 600));

        JFreeChart correlationChart = createBarChart(correlationDataset, "Correlation Matrix", "Variable", "Correlation");
        ChartPanel correlationChartPanel = new ChartPanel(correlationChart);
        correlationChartPanel.setPreferredSize(new Dimension(800, 600));

        JFrame frame = new JFrame("All Charts");
        frame.setLayout(new GridLayout(3, 2));
        frame.add(expTypeChartPanel);
        frame.add(lineChartPanel);
        frame.add(pieChartPanel);
        frame.add(scatterChartPanel);
        frame.add(correlationChartPanel);

        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    private DefaultCategoryDataset createExpTypeDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> expTypeCounts = new HashMap<>();

        String[] data = {
            "Bengaluru, India", "Bengaluru, India", "Greater Mumbai, India", "Greater Mumbai, India", "Bengaluru, India",
            "Greater Mumbai, India", "Ahmedabad, India", "Greater Mumbai, India", "Greater Mumbai, India", "Ahmedabad, India",
            "Bengaluru, India", "Delhi, India", "Greater Mumbai, India", "Greater Mumbai, India", "Bengaluru, India",
            "Greater Mumbai, India", "Delhi, India", "Greater Mumbai, India", "Delhi, India", "Ahmedabad, India"
        };

        // Count occurrences of each city
        for (String city : data) {
            expTypeCounts.put(city, expTypeCounts.getOrDefault(city, 0) + 1);
        }

        // Add data to the dataset
        for (Map.Entry<String, Integer> entry : expTypeCounts.entrySet()) {
            dataset.addValue(entry.getValue(), "Count", entry.getKey());
        }

        return dataset;
    }

    private DefaultCategoryDataset createLineDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Dummy data for demonstration
        for (int i = 0; i < 10; i++) {
            dataset.addValue(Math.random() * 100, "Value", "Date " + i);
        }

        return dataset;
    }

    private DefaultXYDataset createScatterDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();

        // Dummy data for demonstration
        double[][] data = new double[2][20];
        for (int i = 0; i < 20; i++) {
            data[0][i] = Math.random() * 100;
            data[1][i] = Math.random() * 100;
        }

        dataset.addSeries("Scatter Data", data);

        return dataset;
    }

    private DefaultCategoryDataset createCorrelationMatrix() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Sample data for correlation matrix computation
        double[] amounts = {
            283274, 25524, 141963, 226297, 165694, 1966, 228080, 107490, 154713, 144488,
            84568, 255257, 220351, 214808, 175504, 149784, 195264, 183737, 22272, 9221
        };

        // Dummy data for other variables (replace with actual data as needed)
        double[] dummyData1 = new double[amounts.length];
        double[] dummyData2 = new double[amounts.length];
        for (int i = 0; i < amounts.length; i++) {
            dummyData1[i] = Math.random() * 1000;
            dummyData2[i] = Math.random() * 1000;
        }

        // Compute correlations
        double correlationAmountWithDummy1 = computeCorrelation(amounts, dummyData1);
        double correlationAmountWithDummy2 = computeCorrelation(amounts, dummyData2);

        dataset.addValue(correlationAmountWithDummy1, "Amount vs Dummy1", "Correlation");
        dataset.addValue(correlationAmountWithDummy2, "Amount vs Dummy2", "Correlation");

        return dataset;
    }

    private double computeCorrelation(double[] data1, double[] data2) {
        int n = data1.length;
        double sum1 = 0.0, sum2 = 0.0, sum1Sq = 0.0, sum2Sq = 0.0, pSum = 0.0;

        for (int i = 0; i < n; i++) {
            sum1 += data1[i];
            sum2 += data2[i];
            sum1Sq += data1[i] * data1[i];
            sum2Sq += data2[i] * data2[i];
            pSum += data1[i] * data2[i];
        }

        double num = pSum - (sum1 * sum2 / n);
        double den = Math.sqrt((sum1Sq - sum1 * sum1 / n) * (sum2Sq - sum2 * sum2 / n));

        return (den == 0) ? 0.0 : num / den;
    }

    private JFreeChart createLineChart(DefaultCategoryDataset dataset, String title, String categoryAxisLabel, String valueAxisLabel) {
        return ChartFactory.createLineChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    private JFreeChart createPieChart(DefaultCategoryDataset dataset, String title) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        for (int i = 0; i < dataset.getColumnCount(); i++) {
            pieDataset.setValue(dataset.getColumnKey(i).toString(), dataset.getValue(0, i));
        }

        return ChartFactory.createPieChart(
                title,
                pieDataset,
                true, true, false);
    }

    private JFreeChart createScatterPlot(DefaultXYDataset dataset, String title, String xAxisLabel, String yAxisLabel) {
        return ChartFactory.createScatterPlot(
            title,
            xAxisLabel,
            yAxisLabel,
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);
    }

    private JFreeChart createBarChart(DefaultCategoryDataset dataset, String title, String categoryAxisLabel, String valueAxisLabel) {
        return ChartFactory.createBarChart(
            title,
            categoryAxisLabel,
            valueAxisLabel,
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);
    }

    public static void main(String[] args) {
        analytics chart = new analytics("Expense Analysis");
        chart.displayCharts();
    }
}
