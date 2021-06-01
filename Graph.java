/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

import java.util.TreeMap;
import java.util.TreeSet;

/** Graph encapsulates the information and functionality necessary to represent
 *  a graph.
 * 
 * @param <K> The type stored in this Graph's vertices.
 */
public class Graph<K extends Comparable<? super K>> {
    /** graph holds the mappings between this Graph's Vertices and Edges.
     */
    TreeMap<Vertex<K>, TreeSet<Edge>> graph;
    
    /** addVertex adds a new Vertex to this graph with an empty Edge list.
     * 
     * @param vertex The new Vertex to add to this graph.
     * @throws IllegalArgumentException If vertex parameter is null.
     */
    public void addVertex(Vertex<K> vertex) throws IllegalArgumentException {
        // First check that the vertex parameter is not null.
        if (vertex == null)
            throw new IllegalArgumentException("Error while executing addVertex(Vertex<K>) in UndirectedUnweightedGraph: The vertex parameter is null!");
        
        // Add the new Vertex if it does not already exist.
        if (!graph.containsKey(vertex))
            graph.put(vertex, new TreeSet<>());
    }
    
    /** getEdges returns a LinkedList of Edge objects associated with the passed-in Vertex.
     * 
     * @param vertex The Vertex whose Edge objects to return in which is a from Vertex.
     * @return A LinkedList of Edge objects associated with this Vertex.
     * @throws IllegalArgumentException If the vertex parameter is null.
     * @throws NullPointerException If the Vertex does not exist in this graph.
     */
    public TreeSet<Edge> getEdges(Vertex<K> vertex) throws IllegalArgumentException, NullPointerException {
        // First check that the vertex parameter is not null and that the Vertex exists in the graph.
        if (vertex == null)
            throw new IllegalArgumentException("Error while executing getEdges(Vertex<K>) in UndirectedUnweightedGraph: The vertex parameter is null!");
        else if (!graph.containsKey(vertex))
            throw new NullPointerException("Error while executing getEdges(Vertex<K>) in UndirectedUnweightedGraph: The vertex does not exist in the graph!");
        else
            return graph.get(vertex); // Return the list of Edges associated with this Vertex.
    }
    
    /** getVertices returns a LinkedList containing the Vertex objects stored in this graph.
     * 
     * @return A TreeSet containing this Graph's Vertexes.
     */
    public TreeSet<Vertex<K>> getVertices() {
        return new TreeSet<>(graph.keySet());
    }
}