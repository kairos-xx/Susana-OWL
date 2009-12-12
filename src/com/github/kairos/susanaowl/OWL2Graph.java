package com.github.kairos.susanaowl;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import owl2prefuse.graph.GraphDisplay;
import owl2prefuse.graph.GraphPanel;
import owl2prefuse.graph.OWLGraphConverter;
import prefuse.data.Graph;

public class OWL2Graph
{
	/**
	 * This method creates a JPanel which depicts a Prefuse graph from an OWL file.
	 * @param p_OWLFile The file path to the OWL file.
	 * @return The JPanel containing the Prefuse graph.
	 */
	public GraphPanel createGraphPanel(String p_OWLFile)
	{
	    // Step 1 - Create the directed Prefuse graph from an OWL file.
	    OWLGraphConverter graphConverter = new OWLGraphConverter(p_OWLFile, true);
	    Graph graph = graphConverter.getGraph();
	    
	    // Step 2 - Create a graph display, using the graph distance filter.
	    GraphDisplay graphDisp = new GraphDisplay(graph, true);
	    
	    // Step 3 - Create a panel for the graph display, showing the legend and the 
	    // widget to control the number of hops of the graph distance filter.
	    GraphPanel graphPanel = new GraphPanel(graphDisp, true, true);

	    return graphPanel;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		OWL2Graph	og;
		GraphPanel	gp;
		
		og = new OWL2Graph();
		gp = og.createGraphPanel(OWL2Graph.class.getResource("/onto/astro.owl").getFile());
		
	    JFrame f = new JFrame("This is a test");
	    f.setSize(1000, 700);
	    Container content = f.getContentPane();
	    content.add(gp);
	    f.addWindowListener(new ExitListener());
	    f.setVisible(true);
	}

}

class ExitListener extends WindowAdapter {
	  public void windowClosing(WindowEvent event) {
	    System.exit(0);
	  }
	}
