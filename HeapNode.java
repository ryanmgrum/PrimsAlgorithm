/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

/** The HeapNode class encapsulates the data inside a Binary Heap node.
 * 
 * @param <K> The key type stored in the HeapNode.
 * @param <V> The associated value type stored in the HeapNode.
 */
public class HeapNode<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    /** The key stored in this HeapNode.
     */
    private final K key;
    /** The priority value stored in this HeapNode.
     */
    private V value;
    
    /** Constructor that creates a new HeapNode using the passed-in newKey and newValue.
     * 
     * @param newKey The new key to set in this node.
     * @param newValue The associated value to set for the key in this node.
     * @throws IllegalArgumentException If either newKey or newValue are null.
     */
    HeapNode(K newKey, V newValue) throws IllegalArgumentException {
        if (newKey == null)
            throw new IllegalArgumentException("Error while creating HeapNode: newKey parameter is null!");
        else if (newValue == null)
            throw new IllegalArgumentException("Error while creating HeapNode: newValue parameter is null!");
        
        key = newKey;
        value = newValue;
    }
    
    /** getKey returns the key stored in this HeapNode.
     * 
     * @return The key stored in this node.
     */
    public K getKey() {
        return key;
    }
    
    /** getValue returns the value stored in this HeapNode.
     * 
     * @return The value stored in this node.
     */
    public V getValue() {
        return value;
    }
    
    /** setValue sets the value of this HeapNode to newValue.
     * 
     * @param newValue The new value to set in this HeapNode.
     * @throws IllegalArgumentException If the newValue parameter is null.
     */
    public void setValue(V newValue) throws IllegalArgumentException {
        if (newValue == null)
            throw new IllegalArgumentException("Error while executing setValue(V) in HeapNode: newValue parameter is null!");
        
        value = newValue;
    }
    
    /** compareTo compares this HeapNode with the passed-in node and returns
     *  the result of comparing their values then keys.
     * 
     * @param node The node to compare to this node.
     * @return < 0 if this node is less than the passed-in node, 0 if equal, and > 0 if greater.
     * @throws IllegalArgumentException If the node parameter is null.
     */
    public int compareTo(HeapNode<K, V> node) throws IllegalArgumentException {
        // First check that the node parameter is not null.
        if (node == null)
            throw new IllegalArgumentException("Error while executing compareTo(HeapNode<K, V>) in HeapNode: node parameter is null!");
        
        // First compare their priority values, and then their keys.
        if (node.getValue().equals(value))
            return getKey().compareTo(node.getKey());
        else
            return getValue().compareTo(node.getValue());
    }
    
    /** equals compares this HeapNode's key and value with the passed-in node for equality.
     * 
     * @param node The node to compare with this HeapNode.
     * @return True if this heapNode and the other node are the same.
     * @throws IllegalArgumentException If the node parameter is null.
     */
    public boolean equals(HeapNode<K, V> node) throws IllegalArgumentException {
        // First check that the node parameter is not null.
        if (node == null)
            throw new IllegalArgumentException("Error while executing equals(HeapNode<K, V>) in HeapNode: The node parameter is null!");
        
        return node.getKey().equals(key) && node.getValue().equals(value);
    }
}