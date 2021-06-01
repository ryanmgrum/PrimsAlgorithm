/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

/** Vertex implements the functionality for vertices in a graph.
 * 
 * @param <K> The type of item stored in this Vertex.
 */
public class Vertex<K extends Comparable<? super K>> implements Comparable<Vertex<K>> {
    /** The item stored in this Vertex.
     */
    private K item;
    /** The cost of attaching this vertex to a tree.
     */
    private float attachCost;
    /** The parent vertex from the tree that added this Vertex to it.
     */
    private Vertex<K> parent;
    
    /** Constructor that takes a new item and stores it in this Vertex.
     * 
     * @param newItem The item to store in this Vertex.
     * @throws IllegalArgumentException If the newItem parameter is null.
     */
    public Vertex(K newItem) throws IllegalArgumentException {
        // Check that the newItem parameter is not null.
        if (newItem == null)
            throw new IllegalArgumentException("Error while creating new Vertex(K): The newItem parameter is null!");
        
        item = newItem;
        attachCost = 0;
        parent = null;
    }
    
    /** Constructor that takes a new item and attachment cost and stores them in this Vertex.
     * 
     * @param newItem The item to store in this Vertex.
     * @param newAttachCost The new attachment cost to store in this Vertex.
     * @throws IllegalArgumentException If the newItem parameter is null.
     */
    public Vertex(K newItem, float newAttachCost) throws IllegalArgumentException {
        // Check that the newItem parameter is not null.
        if (newItem == null)
            throw new IllegalArgumentException("Error while creating new Vertex(K, float): The newItem parameter is null!");
        
        item = newItem;
        attachCost = newAttachCost;
        parent = null;
    }
    
    /** Constructor that takes a new item, attachment cost, and parent Vertex reference, and stores them in this Vertex.
     * 
     * @param newItem The item to store in this Vertex.
     * @param newAttachCost The new attachment cost to store in this Vertex.
     * @param newParent The new parent Vertex to associate from this Vertex.
     * @throws IllegalArgumentException If the newItem parameter is null.
     */
    public Vertex(K newItem, float newAttachCost, Vertex<K> newParent) throws IllegalArgumentException {
        // Check that the newItem parameter is not null.
        if (newItem == null)
            throw new IllegalArgumentException("Error while creating new Vertex(K, float): The newItem parameter is null!");
        
        item = newItem;
        attachCost = newAttachCost;
        parent = newParent;
    }
    
    /** get returns the item stored in this Vertex.
     * 
     * @return The item attribute.
     */
    public K get() {
        return item;
    }
    
    /** set sets a new item into this Vertex and returns its previous item.
     * 
     * @param newItem The new item to store in this Vertex.
     * @return The previous item stored in this Vertex.
     * @throws IllegalArgumentException If the newItem parameter is null.
     */
    public K set(K newItem) throws IllegalArgumentException {
        // First check that newItem is not null.
        if (newItem == null)
            throw new IllegalArgumentException("Error while executing set(K) in Vertex: The newItem parameter is null!");
        
        K result = item;
        item  = newItem;
        return result;
    }
    
    /** getAttachCost returns the attachment cost set in this Vertex.
     * 
     * @return The attachCost attribute.
     */
    public float getAttachCost() {
        return attachCost;
    }
    
    /** setAttachCost sets the attachment cost stored in this Vertex.
     * 
     * @param newAttachCost The new attachment cost to assign to this Vertex.
     */
    public void setAttachCost(float newAttachCost) {
        attachCost = newAttachCost;
    }
    
    /** getParent returns the parent Vertex reference stored in this Vertex.
     * 
     * @return The parent attribute.
     */
    public Vertex<K> getParent() {
        return parent;
    }
    
    /** setParent sets the parent Vertex that added this Vertex to its tree.
     * 
     * @param newParent The new parent Vertex that added this Vertex.
     */
    public void setParent(Vertex<K> newParent) {
        parent = newParent;
    }
    
    /** equals checks whether this Vertex is equal to the passed-in vertex.
     * 
     * @param vertex The vertex to check for equality against this one.
     * @return True if this vertex is equal to the passed-in vertex.
     * @throws IllegalArgumentException If the passed-in vertex is null.
     */
    public boolean equals(Vertex<K> vertex) throws IllegalArgumentException {
        // First check that the passed-in vertex is not null.
        if (vertex == null)
            throw new IllegalArgumentException("Error while executing equals(Vertex<K>) in Vertex: The vertex parameter is null!");
        
        return item.equals(vertex.get());
    }
    
    /** equals checks whether this Vertex's item is equal to the passed-in item.
     * 
     * @param otherItem The other item to compare to this Vertex's item.
     * @return True if this Vertex's item equals otherItem.
     * @throws IllegalArgumentException If otherItem is null.
     */
    public boolean equals(K otherItem) throws IllegalArgumentException {
        // First check that otherItem is not null.
        if (otherItem == null)
            throw new IllegalArgumentException("Error while executing equals(K) in Vertex: The otherItem parameter is null!");
        
        return item.equals(otherItem);
    }

    /** compareTo compares this Vertex with the passed-in Vertex; in particular,
     *  it compares their stored items.
     * 
     * @param vertex The other Vertex to compare to this Vertex.
     * @return {@literal <} 0 if this Vertex is less than the passed-in Vertex,
     * 0 if equal, and {@literal >} 0 if this Vertex is greater than the
     * passed-in Vertex.
     * @throws IllegalArgumentException If the passed-in vertex is null.
     */
    @Override
    public int compareTo(Vertex<K> vertex) throws IllegalArgumentException {
        if (this == vertex)
            return 0;
        else if (vertex == null)
            throw new IllegalArgumentException("Error while executing compareTo(Object) in Vertex: The o parameter is null!");
        else
            return item.compareTo(vertex.get());
    }
    
    /** toString outputs this Vertex's item attribute.
     * 
     * @return The item attribute's toString() method.
     */
    @Override
    public String toString() {
        return item.toString();
    }
}