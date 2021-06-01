/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 3 (Implement Prim's Algorithm)
 */

import java.util.TreeMap;

/** The BinaryMinHeap class is based on a Binary Heap, which is a binary tree with an ordering placed on it.
 * 
 * @param <K> The item type for this BinaryMinHeap.
 * @param <V> The value type to order the keys.
 */
public class BinaryMinHeap<K extends Comparable<? super K>, V extends Comparable<? super V>> {
    /** heap contains the HeapNodes that make up the binary heap.
     */
    private HeapNode<K, V>[] heap;
    /** Position's values holds the index of the given item K in the heap.
     */
    private TreeMap<K, Integer> Position;
    /** size tracks the current number of nodes in the heap.
     */
    private int size;
    
    /** Constructor that creates a new BinaryMinHeap with a maximum size of passed-in newSize parameter.
     * 
     * @param newSize The maximum size of this PriorityQueue
     * @throws IllegalArgumentException If the newSize parameter is less than zero.
     */
    BinaryMinHeap(int newSize) throws IllegalArgumentException {
        if (newSize < 0)
            throw new IllegalArgumentException("Error while creating BinaryMinHeap: newSize parameter (" + newSize + ") is less than 0!");
        
        StartHeap(newSize);
        size = 0;
    }
    
    /** Heapify_Up recursively shifts the given node at the passed-in index
     *  parameter up the Binary Heap until it is in its proper place.
     * 
     * @param index The node to shift up.
     */
    private void Heapify_Up(int index) {
        if (index > 1) {
            int j = Math.floorDiv(index, 2); // j is index's parent.
            if (heap[index].compareTo(heap[j]) < 0) { // If index's priority value, then item if priority is equal, is < j's priority value and item.
                // Swap index and j, and then call Heapify_Up for j.
                HeapNode<K, V> temp = heap[index];
                heap[index] = heap[j];
                heap[j] = temp;
                Position.put(heap[index].getKey(), index);
                Position.put(heap[j].getKey(), j);
                Heapify_Up(j);
            }
        } 
    }
    
    /** Heapify_Down recursively shifts the given node at the passed-in index
     *  parameter down the Binary Heap until it is in its proper place.
     * 
     * @param index The index of the node to shift down.
     */
    private void Heapify_Down(int index) {
        if (2*index <= size) { // Skip if 2*index > size.
            int j = 0; // The eventual child node to swap with the parent node.
            if (2*index < size) {
                int left = 2*index, right = 2*index + 1;
                // If the left child node's priority value, then item, is < the right child node's priority value and item.
                if (heap[left] == null)
                    j = left;
                else if (heap[right] == null)
                    j = right;
                else if (heap[left].compareTo(heap[right]) < 0) // Bring up the smaller of the two.
                    j = left;
                else
                    j = right;
            } else if (2*index == size) // If the index is the last index in the heap.
                j = 2*index;
            if (heap[j].compareTo(heap[index]) < 0) { // j's item's priority value, then item, is less than index's priority value or item.
                HeapNode<K, V> temp = heap[index];
                heap[index] = heap[j];
                heap[j] = temp;
                Position.put(heap[index].getKey(), index);
                Position.put(heap[j].getKey(), j);
                Heapify_Down(j);
            }
        }
    }
    
    /** StartHeap initializes a new Binary Heap of the passed-in size N
     * (+1 to allow element zero to be used as temporary storage).
     * 
     * @param N The size of the heap.
     * @throws IllegalArgumentException If N is negative.
     */
    private void StartHeap(int N) throws IllegalArgumentException {
        if (N < 0)
            throw new IllegalArgumentException("Error while executing StartHeap(int) in BinaryMinHeap: The passed-in value (" + N + ") is negative!");
        
        heap = new HeapNode[N+1]; // +1 to allow position 0 to be a temporary working element.
        Position = new TreeMap<>();
    }
    
    /** Insert adds a new item to the BinaryMinHeap with the given priority value.
     * 
     * @param item The item to add to the BinaryMinHeap.
     * @param value The priority value to set for the item.
     * @throws IllegalArgumentException If the item or value parameter is null.
     * @throws OutOfMemoryError If the BinaryMinHeap is full.
     */
    public void Insert(K item, V value) throws IllegalArgumentException, OutOfMemoryError {
        // First check that the item and value parameters are not null.
        if (item == null)
            throw new IllegalArgumentException("Error while executing Insert(K, V) in BinaryMinHeap: The item parameter is null!");
        else if (value == null)
            throw new IllegalArgumentException("Error while executing Insert(K, V) in BinaryMinHeap for item \"" + item + "\": The value parameter is null!");
        
        if (Position.containsKey(item)) // If the item already exists in the heap, update its value.
            ChangeKey(item, value); 
        else if (isFull())
            throw new OutOfMemoryError("Error while executing Insert(K, V) in BinaryMinHeap for item \"" + item + "\", value \"" + value + "\": BinaryMinHeap is full!");
        else {
            size++;
            heap[size] = new HeapNode<>(item, value);
            Position.put(item, size);
            Heapify_Up(size);
        }
    }
    
    /** FindMin returns the item with the minimum value, then natural ordering,
     *  in this BinaryMinHeap, but does not remove it from the heap.
     * 
     * @return The lowest-priority value, then item, stored in this heap,
     *  or null if the heap is empty.
     */
    public K FindMin() {
        if (isEmpty())
            return null;
        else
            return heap[1].getKey();
    }
    
    /** GetValue returns the priority value associated with the passed-in key.
     *  
     * @param key The key whose priority value we want to return.
     * @return The priority value assigned to the passed-in key.
     * @throws IllegalArgumentException If the passed-in key parameter is null.
     * @throws NullPointerException If the key does not exist in the heap.
     */
    public V GetValue(K key) throws IllegalArgumentException, NullPointerException {
        // First check that the key parameter is not null.
        if (key == null)
            throw new IllegalArgumentException("Error while executing GetValue(K) in BinaryMinHeap: The key parameter is null!");
        
        // Next check that the key exists.
        Integer pos = Position.get(key);
        if (pos == null)
            throw new NullPointerException("Error while executing GetValue(K) in BinaryMinHeap: The key does not exist!");
        else
            return heap[pos].getValue();
    }
    
    /** Delete removes the entry at the given passed-in index from the heap.
     * 
     * @param index The index of the node to remove.
     * @throws IndexOutOfBoundsException If the index is greater than or equal
     *  to the size of the heap or less than 1.
     * @throws IllegalStateException If the BinaryMinHeap is empty.
     */
    private void Delete(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Error while executing Delete(int) in BinaryMinHeap for index \"" + index + "\": The BinaryMinHeap is empty!");
        
        if (index < size && index > 0) {
            Position.remove(heap[index].getKey());
            heap[index] = heap[size];
            heap[size] = null;
            size--;
            Position.put(heap[index].getKey(), index);
            Heapify_Down(index);
        } else if (index == size) {
            Position.remove(heap[size].getKey());
            heap[size] = null;
            size--;
        } else
            throw new IndexOutOfBoundsException("Error while executing Delete(int) in BinaryMinHeap for index \"" + index + "\": index out of bounds (current size: " + size + ")!");
    }
    
    /** Delete removes the given item from the BinaryMinHeap.
     * 
     * @param item The item to remove from the heap.
     * @throws IllegalArgumentException If the item parameter is null.
     * @throws IllegalStateException If the BinaryMinHeap is empty.
     */
    public void Delete(K item) throws IllegalArgumentException, IllegalStateException {
        // First check that the item parameter is not null.
        if (item == null)
            throw new IllegalArgumentException("Error while executing Delete(K) in BinaryMinHeap: The item parameter is null!");
        else if (isEmpty())
            throw new IllegalStateException("Error while executing Delete(K) in BinaryMinHeap for item \"" + item + "\": The BinaryMinHeap is empty!");
        
        Delete(Position.get(item));
    }
    
    /** ExtractMin removes the first entry with the smallest priority value,
     *  then smallest entry based on natural ordering, from the BinaryMinHeap.
     * 
     * @return The first entry in the BinaryMinHeap, or null if the BinaryMinHeap is empty.
     */
    public K ExtractMin() {
        if (isEmpty())
            return null;
        
        K result = FindMin();
        Delete(1);
        return result;
    }
    
    /** ChangeKey changes the priority value of the given item, and then
     *  readjusts its position in the BinaryMinHeap as appropriate.
     * 
     * @param item The item whose priority value we wish to modify.
     * @param newValue The item's new priority value.
     * @throws IllegalArgumentException If the item or newValue parameter is null.
     * @throws IllegalStateException If the heap is empty.
     * @throws NullPointerException If the item does not exist in the BinaryMinHeap.
     */
    public void ChangeKey(K item, V newValue) throws IllegalArgumentException, IllegalStateException, NullPointerException {
        if (item == null)
            throw new IllegalArgumentException("Error while executing ChangeKey(K, V) in BinaryMinHeap: The item parameter is null!");
        else if (newValue == null)
            throw new IllegalArgumentException("Error while executing ChangeKey(K, V) in BinaryMinHeap for item \"" + item + "\": The newValue parameter is null!");
        else if (!isEmpty()) {
            Integer pos = Position.get(item); // Fetch the item's position within the heap.
            if (pos == null)
                throw new NullPointerException("Error while executing ChangeKey(K, V) in BinaryMinHeap for item \"" + item + "\", newValue \"" + newValue + "\": The item does not exist!");

            V oldValue = heap[pos].getValue();
            heap[pos].setValue(newValue);

            if (newValue.compareTo(oldValue) < 0)
                Heapify_Up(pos);
            else if (newValue.compareTo(oldValue) > 0)
                Heapify_Down(pos);
        } else
            throw new IllegalStateException("Error while executing ChangeKey(K, V) in BinaryMinHeap for item \"" + item + "\", newValue \"" + newValue + "\": The heap is empty!");
    }
    
    /** isEmpty checks whether this BinaryMinHeap is empty.
     * 
     * @return True if this BinaryMinHeap is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** isFull checks whether this BinaryMinHeap is full.
     * 
     * @return True if this BinaryMinHeap is full.
     */
    public boolean isFull() {
        return size == heap.length - 1;
    }
}