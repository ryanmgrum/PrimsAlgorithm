/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

/** This PriorityQueue uses a BinaryMinHeap to organize its entries.
 *
 * @param <K> The type of item that will be stored in this PriorityQueue.
 * @param <V> The type of value that will be used to order the items in this PriorityQueue.
 */
public class PriorityQueue<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    /** A binary heap is used to store this queue's contents.
     */
    BinaryMinHeap<K, V> queue;
    
    /** Constructor that takes a size to instantiate its PriorityQueue.
     * 
     * @param newSize The size to use to create the new PriorityQueue.
     * @throws IllegalArgumentException If the newSize parameter is
     *  less than 0.
     */
    PriorityQueue(int newSize) throws IllegalArgumentException {
        // First check that the newSize parameter is greater than or equal to 0.
        if (newSize < 0)
            throw new IllegalArgumentException("Error while creating a new PriorityQueue: The newSize parameter is less than 0!");
        
        queue = new BinaryMinHeap<>(newSize);
    }
    
    /** Insert adds a new item to the Priority Queue with the given priority value.
     * 
     * @param item The item to add to the Priority Queue.
     * @param value The priority value to set for the item.
     * @throws IllegalArgumentException If the item or value parameter is null.
     */
    public void Insert(K item, V value) throws IllegalArgumentException {
        // First check that the item and value parameters are not null.
        if (item == null)
            throw new IllegalArgumentException("Error while executing Insert(K, V) in PriorityQueue: The item parameter is null!");
        else if (value == null)
            throw new IllegalArgumentException("Error while executing Insert(K, V) in PriorityQueue for item \"" + item + "\": The value parameter is null!");

        queue.Insert(item, value);
    }
    
    /** ChangeKey changes the priority value of the given item, and then
     *  readjusts its position in the BinaryMinHeap as appropriate.
     * 
     * @param item The item whose priority value we wish to modify.
     * @param newValue The item's new priority value.
     * @throws IllegalArgumentException If the item or newValue parameter is null.
     * @throws IllegalStateException If the queue is empty.
     * @throws NullPointerException If the item does not exist in the Priority Queue.
     */
    public void ChangeKey(K item, V newValue) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        if (item == null)
            throw new IllegalArgumentException("Error while executing ChangeKey(K, V) in PriorityQueue: The item parameter is null!");
        else if (newValue == null)
            throw new IllegalArgumentException("Error while executing ChangeKey(K, V) in PriorityQueue for item \"" + item + "\": The newValue parameter is null!");
        else if (!queue.isEmpty())
            queue.ChangeKey(item, newValue);
        else
            throw new IllegalStateException("Error while executing ChangeKey(K, V) in PriorityQueue for item \"" + item + "\", newValue \"" + newValue + "\": The queue is empty!");
    }
    
    /** FindMin returns the item with the minimum value, then natural ordering,
     *  in this PriorityQueue, but does not remove it from the queue.
     * 
     * @return The lowest-priority value, then smallest item for items with
     *  equal priority, stored in this queue, or null if the queue is empty.
     */
    public K FindMin() {
        if (queue.isEmpty())
            return null;
        else
            return queue.FindMin();
    }
    
    /** GetValue returns the priority value associated with the passed-in key.
     *  
     * @param key The key whose priority value we want to return.
     * @return The priority value assigned to the passed-in key.
     * @throws IllegalArgumentException If the passed-in key parameter is null.
     */
    public V GetValue(K key) throws IllegalArgumentException {
        // First check that the key parameter is not null.
        if (key == null)
            throw new IllegalArgumentException("Error while executing GetValue(K) in BinaryMinHeap: The key parameter is null!");
        
        // Return the priority value associated with the key.
        return queue.GetValue(key);
    }
    
    /** ExtractMin removes the first entry with the smallest priority value,
     *  then smallest entry based on natural ordering, from the PriorityQueue.
     * 
     * @return The first entry in the PriorityQueue, or null if the PriorityQueue is empty.
     */
    public K ExtractMin() {
        if (queue.isEmpty())
            return null;
        else
            return queue.ExtractMin();
    }
    
    /** Delete removes the given item from the PriorityQueue.
     * 
     * @param item The item to remove from the queue.
     * @throws IllegalArgumentException If the item parameter is null.
     * @throws IllegalStateException If the PriorityQueue is empty.
     */
    public void Delete(K item) throws IllegalArgumentException, IllegalStateException {
        // First check that the item parameter is not null.
        if (item == null)
            throw new IllegalArgumentException("Error while executing Delete(K) in PriorityQueue: The item parameter is null!");
        else if (queue.isEmpty())
            throw new IllegalStateException("Error while executing Delete(K) in PriorityQueue for item \"" + item + "\": The PriorityQueue is empty!");
        
        queue.Delete(item);
    }
    
    /** isEmpty checks whether this PriorityQueue is empty.
     * 
     * @return True if this PriorityQueue is empty.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    /** isFull checks whether this PriorityQueue is full.
     * 
     * @return True if this PriorityQueue is full.
     */
    public boolean isFull() {
        return queue.isFull();
    }
}