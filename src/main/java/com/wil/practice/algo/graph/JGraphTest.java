package com.wil.practice.algo.graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxStylesheet;
import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JGraphTest {

    public static void main(String[] args) {
        Graph<String, DefaultWeightedEdge> graph =  new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        // Add vertices (nodes)
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Add edges (with weights)
        graph.setEdgeWeight(graph.addEdge("A", "B"), 3);
        graph.setEdgeWeight(graph.addEdge("A", "C"), 1);
        graph.setEdgeWeight(graph.addEdge("B", "C"), 4);
        graph.setEdgeWeight(graph.addEdge("B", "D"), 2);
        graph.setEdgeWeight(graph.addEdge("C", "D"), 5);


        KruskalMinimumSpanningTree<String, DefaultWeightedEdge> mst = new KruskalMinimumSpanningTree<>(graph);
        System.out.println("Minimum Spanning Tree weight: " + mst.getSpanningTree().getWeight());
        System.out.println("Edges in the MST: " + mst.getSpanningTree().getEdges());

        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<>(graph);
        mxStylesheet stylesheet = graphAdapter.getStylesheet();
        Map<String, Object> edgeStyle = new HashMap<>(stylesheet.getDefaultEdgeStyle());
        edgeStyle.put(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        edgeStyle.put(mxConstants.STYLE_SPACING, 20);
        edgeStyle.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ORTHOGONAL);
        edgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        stylesheet.setDefaultEdgeStyle(edgeStyle);

        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // Set edge labels (weights)
        HashMap<DefaultWeightedEdge, mxICell> edgeToCellMap = graphAdapter.getEdgeToCellMap();
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            mxCell cell = (mxCell) edgeToCellMap.get(edge);
            // Set the edge label to display the weight
            graphAdapter.getModel().setValue(edgeToCellMap.get(edge), graph.getEdgeWeight(edge));
            if (graph.getEdgeSource(edge).equals("A") && graph.getEdgeTarget(edge).equals("C")) {
                cell.getGeometry().setOffset(new mxPoint(0, 20));  // Move label for (A, C) slightly upward
            }
        }


        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        graphComponent.setPreferredSize(new Dimension(400, 400));
        JFrame frame = new JFrame();
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);
        try {
            BufferedImage image =  mxCellRenderer.createBufferedImage(graphAdapter, null, 1, Color.WHITE, true, null);
            File imgFile = new File("src/main/resources/graph.png");
            ImageIO.write(image, "PNG", imgFile);
        } catch (IOException i) {
            i.printStackTrace();
        }
        Timer timer = new Timer(2000, e -> {
            // Close the JFrame
            frame.dispose();
            System.exit(0);
        });
        // Only run once
        timer.setRepeats(false);
        timer.start();
    }
}
