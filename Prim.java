/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/** The Prim class implements Prim's Algorithm for finding the Minimum Spanning Tree
 *  of a graph from a given graph language file or already-parsed weighted graph.
 * 
 * @param <K> The type stored in the Vertexes of the graph.
 * @param <V> The WeightedEdge value type stored in the edges.
 */
public class Prim<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    /** The Minimum Spanning Tree found by running Prim's Algorithm on the passed-in graph.
     */
    private final Graph<K> mst;
    
    /** Constructor that creates a new instance of Prim using the passed-in GLParser.
     * 
     * @param parser The GLParser used to find the underlying graph's Minimum Spanning Tree.
     * @throws IllegalArgumentException If parser or its underlying graph is null.
     */
    public Prim(GLParser parser) throws IllegalArgumentException {
        // First check that parser is not null.
        if (parser == null)
            throw new IllegalArgumentException("Error while constructing a new Prim(GLParser): The parser parameter is null!");
        // Then make sure that its underlying graph is not null.
        else if (parser.getGraph() == null)
            throw new IllegalArgumentException("Error while constructing a new Prim(GLParser): The parser's underlying Graph<K> is null!");
        // Finally, make sure its underlying graph is weighted.
        else if (!parser.isWeighted())
            throw new IllegalArgumentException("Error while constructing a new Prim(GLParser): Prim's Algorithm will not work with a non-weighted graph!");
        
        
        /* We assume that the graph is undirected, so initialize mst appropriately
         * (we know it is weighted), and then find the GLParser's underlying graph's
         * Minimum Spanning Tree.
         */
        mst = new UndirectedWeightedGraph<K, V>();
        ExecutePrim((UndirectedWeightedGraph<K, V>) parser.getGraph());
    }
    
    /** Constructor that creates a new instance of Prim using the passed-in UndirectedWeightedGraph.
     * 
     * @param uwgraph The UndirectedWeightedGraph to find its Minimum Spanning Tree.
     * @throws IllegalArgumentException If the uwgraph parameter is null.
     */
    public Prim(UndirectedWeightedGraph<K, V> uwgraph) throws IllegalArgumentException {
        // First check that the uwgraph parameter is not null.
        if (uwgraph == null)
            throw new IllegalArgumentException("Error while constructing a new Prim(UndirectedWeightedGraph<K, V>): The uwgraph parameter is null!");
        
        // Initialize, and then find, the mst.
        mst = new UndirectedWeightedGraph<K, V>();
        ExecutePrim(uwgraph);
    }
    
    /** ExecutePrim finds the Minimum Spanning Tree of the passed-in weighted graph
     *  using Prim's Algorithm and stores the result in Prim's mst attribute.
     * 
     * @param graph The graph for which to find the Minimum Spanning Tree.
     */
    private void ExecutePrim(UndirectedWeightedGraph<K, V> graph) {
        // Create a new PriorityQueue as part of executing Prim's Algorithm.
        PriorityQueue<Vertex<K>, Float> queue = new PriorityQueue<>(graph.getVertices().size());
        
        /* Go through the Vertexes of the graph and set their
         * attachment costs as appropriate (0 for designated "r"
         * root node, Float.MAX_VALUE for the rest), and then add
         * it to the PriorityQueue.
         */
        for (Vertex<K> vertex : graph.getVertices()) {
            vertex.setAttachCost(vertex.equals((K) "r") ? 0.0f : Float.MAX_VALUE);
            queue.Insert(vertex, vertex.getAttachCost());
        }
        
        /* The mst graph is always empty at this point
         * because this function is only executed
         * in the constructors for Prim.
         */
        
        /* Now go through the PriorityQueue, extract each Vertex, add to
         * mst, and then for each adjacent Vertex, update its attachment cost
         * and parent Vertex to the currently extracted Vertex if the attachment
         * cost from this Vertex to the destination Vertex is less than the
         * destination Vertex's current attachment cost.
         */
        while (!queue.isEmpty()) {
            float subtotal = queue.GetValue(queue.FindMin());
            Vertex vertex = queue.ExtractMin();
            
            mst.addVertex(vertex); // Add Vertex with its lowest-cost edge weight to MST.
            if (vertex.getParent() != null) // Skip edge if root (no parent).
                ((UndirectedWeightedGraph<K, Float>) mst).addEdge(vertex.getParent(), vertex, vertex.getAttachCost());
            
            // Go through each adjacent Vertex and see whether it is affected by the extraction.
            for (WeightedEdge<Float> edge : (TreeSet<WeightedEdge<Float>>) graph.getEdges(vertex)) {
                if (!mst.getVertices().contains(edge.getTo())) { // Skip vertices that have already been extracted.
                    float totalAttachmentCost = subtotal + edge.getWeight();
                    if (Float.compare(totalAttachmentCost, queue.GetValue(edge.getTo())) < 0) {
                        queue.ChangeKey(edge.getTo(), totalAttachmentCost);
                        edge.getTo().setAttachCost(edge.getWeight());
                        edge.getTo().setParent(vertex);
                    }
                }
            }
        }
    }
    
    
    /** getMST returns the Minimum Spanning Tree stored in this Prim instance.
     * 
     * @return The mst attribute.
     */
    public Graph<K> getMST() {
        return mst;
    }
    
    /** glFormat outputs the MST stored in this Prim into the format found in .gl files.
     * 
     * @return The .gl format of the MST stored in this MST based on its vertex information.
     */
    private String glFormat() {
        String heading = String.format("%s %s%s", "undirected", "weighted", System.lineSeparator());
        String result[] = new String[mst.getVertices().size()-1]; // -1 due to skipping root's Vertex due to having null parent.
        
        int i = 0; // i walks through the indexes of the result array.
        for (Vertex<K> vertex : mst.getVertices()) {
            if (vertex.getParent() != null) // Skip root because it has no parent.
                result[i] = String.format( // Output floating point in either the exact needed size, or four decimal places if large.
                    Float.toString(vertex.getAttachCost()).replace(".", "").length() <= 4 ?
                        "%s=%s=%." + Float.toString(vertex.getAttachCost()).replace(".", "").length() + "g%s" :
                        "%s=%s=%.4g%s",
                    vertex.getParent(), vertex.get(), vertex.getAttachCost(), System.lineSeparator());
            i++;
        }
        
        // Prettify the output. First sort it alphabetically root-first.
        Comparator<String> rFirstComparator = new Comparator<>(){
            @Override
            public int compare(String o1, String o2) {
                if (o1.startsWith("r") && !o2.startsWith("r"))
                    return -1;
                else if (!o1.startsWith("r") && o2.startsWith("r"))
                    return 1;
                else
                    return o1.compareTo(o2);
            }
        };
        Arrays.sort(result, rFirstComparator);
        // Then append entries.
        for (String line : result)
            heading += line;

        return heading;
    }
    
    /** toString outputs the stored mst graph in a readable format.
     * 
     * @return A String containing the output of the Minimum Spanning Tree stored in this Prim.
     */
    @Override
    public String toString() {
        return "UndirectedWeightedGraph " + ((UndirectedWeightedGraph<K, V>) mst).toString();
    }
    
    /** save outputs the contents of the mst stored in this Prim instance to
     *  the format used in our graph language (.gl) file. Specifically, it saves
     *  the contents to the provided complete file path.
     * 
     * @param filePath The file to write the contents of the mst.
     * @throws IllegalArgumentException If the filePath parameter is null.
     * @throws IOException If there is an issue writing the file.
     */
    public void save(String filePath) throws IllegalArgumentException, IOException {
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write(glFormat());
        }
    }
}