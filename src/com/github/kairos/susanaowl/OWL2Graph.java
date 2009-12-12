package com.github.kairos.susanaowl;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import owl2prefuse.Writer;
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
	public GraphPanel createGraphPanel(Graph graph)
	{	    
	    // Step 2 - Create a graph display, using the graph distance filter.
	    GraphDisplay graphDisp = new GraphDisplay(graph, true);
	    
	    // Step 3 - Create a panel for the graph display, showing the legend and the 
	    // widget to control the number of hops of the graph distance filter.
	    GraphPanel graphPanel = new GraphPanel(graphDisp, true, true);

	    return graphPanel;
	}
	
	/**
	 * This exports a Prefuse graph to GraphML.
	 * @param p_graph The graph to be written to GraphML.
	 * @param p_file The file to which the GraphML is written.
	 */
	public void writeGraphML(Graph p_graph, String p_file)
	{
		Writer.writeGraphML(p_graph, p_file);
	}

	private Graph getGraph(String file)
	{
		String	res;
		
		res = OWL2Graph.class.getResource(file).getFile();
		
	    // Step 1 - Create the directed Prefuse graph from an OWL file.
	    OWLGraphConverter graphConverter = new OWLGraphConverter(res, true);
	    Graph graph = graphConverter.getGraph();
	    
	    return graph;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		OWL2Graph	og;
		GraphPanel	gp;
		Graph		g;
		
		og = new OWL2Graph();
		g = og.getGraph("/onto/astro.owl");
		
		og.writeGraphML(g, "/tmp/prueba.graphml");
		gp = og.createGraphPanel(g);
		
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
