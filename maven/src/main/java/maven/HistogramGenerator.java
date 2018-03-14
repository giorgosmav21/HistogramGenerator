package maven;

import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/** Histogram Generator contains a method which reads the grades from a text and
 * a method which generates an histogram based on these grades. 
 * It also contains main method
 */
public class HistogramGenerator {
	
	public static void main(String[] args) {
		HistogramGenerator h = new HistogramGenerator();
		h.generateChart(h.readFile(args[0]));
	}
	
	/** ReadFiles reads the lines of a text file, parses the grades, 
	 * adds them to an array called frequencies and returns the array
	 * 
	 * @author giorgosmav21
	 * @version 1.0
	 * @since 2018-3-13
	 */
	public int[] readFile(String path) {
		String line;
		int frequencies[] = new int[11];
		
	    try {
	    	
	        BufferedReader bufferreader = new BufferedReader(new FileReader(path));
	        line = bufferreader.readLine();

	        while (line != null) { //loop for reading all the text file 
	        	int l = Integer.parseInt(line);
	        	for (int j = 0; j <=10; j++) { //filling the array
	        		if (l == j) {
	        		frequencies[j]++;
	        		}
	        	}
	            line = bufferreader.readLine();
	        }
	        
	        bufferreader.close();
	        
	        
	    } catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	   
	    return frequencies;
		
	}
	/**GenerateChart receive an array of grades' frequencies and creates the histogram
	 */
	public void generateChart(int[] dataValues) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		XYSeries data = new XYSeries("random values");

		
		for (int i = 0; i < dataValues.length; i++) { 
			data.add(i, dataValues[i]);
		}

		dataset.addSeries(data);

		boolean legend = false; 
		boolean tooltips = false; 
		boolean urls = false; 

		JFreeChart chart = ChartFactory.createXYLineChart("Grades and Frequency", "Grades", "Frequency", dataset,
				PlotOrientation.VERTICAL, legend, tooltips, urls);

		
		ChartFrame frame = new ChartFrame("Grades", chart);
		frame.pack();
		
		frame.setVisible(true);
	}
}