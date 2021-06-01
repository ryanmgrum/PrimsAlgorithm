/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

import java.util.TreeMap;
import java.util.TreeSet;

/** A DirectedWeightedGraph stores WeightedEdge objects with edge costs coming from the from Vertex.
 * 
 * @param <K> The variable type stored in this Graph's Vertices.
 * @param <V> The type variable stored in this Graph's WeightedEdges.
 */
public class DirectedWeightedGraph<K extends Comparable<? super K>, V extends Comparable<? super V>> extends Graph<K> {
    /** Constructor that creates a new empty DirectedWeightedGraph.
     */
    public DirectedWeightedGraph() {
        graph = new TreeMap<>();
    }
    
    /** addEdge adds a new edge to the from Vertex with the given cost.
     *  If either Vertex does not exist in the graph, it is added.
     * 
     * @param from The Vertex that this WeightedEdge comes from.
     * @param to The destination Vertex of the from WeightedEdge.
     * @param cost The cost to associate with this new WeightedEdge.
     * @throws IllegalArgumentException If either from or to is null.
     */
    public void addEdge(Vertex<K> from, Vertex<K> to, V cost) throws IllegalArgumentException {
        // First check that neither from nor to are null.
        if (from == null)
            throw new IllegalArgumentException("Error while executing addEdge(Vertex<K>, Vertex<K>) in DirectedUnweightedGraph: The from parameter is null!");
        else if (to == null)
            throw new IllegalArgumentException("Error while executing addEdge(Vertex<K>, Vertex<K>) in DirectedUnweightedGraph: The to parameter is null!");
        
        
        // Add WeightedEdge to the from Vertex object that points to the other Vertex.
        if (!graph.containsKey(from)) {
            addVertex(from);
            TreeSet<Edge> fromList = new TreeSet<>();
            fromList.add(new WeightedEdge<>(to, cost));
            graph.put(from, fromList);
        } else
            graph.get(from).add(new WeightedEdge<>(to, cost));
        
        if (!graph.containsKey(to))
            addVertex(to);
    }
    
    /** toString outputs the Vertex and associated WeightedEdges in a readable format.
     * 
     * @return This Graph's list of Vertex and WeightedEdge objects.
     */
    @Override
    public String toString() {
        String result = "{" + System.lineSeparator();
        
        for (Vertex v : getVertices()) {
            result += "\t" + v + ": [";
            for(WeightedEdge<V> e : (TreeSet<WeightedEdge<V>>) getEdges(v))
                if (e.equals(graph.get(v).last()))
                    result += e;
                else
                    result += e + ", ";
            
            
            if (v.equals(graph.lastKey()))
                result += "]" + System.lineSeparator();
            else
                result += "]," + System.lineSeparator();
        }
        
        result += "}";
        
        return result;
    }
}