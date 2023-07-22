package hashmap;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;
        Node(K k, V v) {
            key = k;
            value = v;
        }
    }
    private static final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private final double loadFactor;
    private int capacity;
    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.loadFactor = maxLoad;
        this.capacity = initialSize;
        this.buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        this.buckets = createTable(this.capacity);
        this.size = 0;
    }
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % this.capacity;
    }
    @Override
    public void put(K key, V value){
        if(size > capacity * loadFactor){
            resize(capacity * 2);
        }
        int index = hash(key);
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
        }
        buckets[index].add(createNode(key, value));
        size++;
//        if(size > capacity * loadFactor){
//            resize(capacity * 2);
//        }
    }
    private void resize(int newCapacity){
        Collection<Node>[] newBuckets = createTable(newCapacity);
        for(Collection<Node> bucket : buckets){
            for(Node node : bucket){
                int index = hash(node.key);
                newBuckets[index].add(node);
            }
        }
        this.buckets = newBuckets;
        this.capacity = newCapacity;
    }
    @Override
    public boolean containsKey(K key){
        int index = hash(key);
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                return true;
            }
        }
        return false;
    }
    @Override
    public V get(K key){
        int index = hash(key);
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }
    @Override
    public int size(){
        return this.size;
    }
    @Override
    public V remove(K key){
        int index = hash(key);
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                buckets[index].remove(node);
                size--;
                return node.value;
            }
        }
        return null;
    }
    @Override
    public V remove(K key, V value){
        int index = hash(key);
        for(Node node : buckets[index]){
            if(node.key.equals(key) && node.value.equals(value)){
                buckets[index].remove(node);
                size--;
                return node.value;
            }
        }
        return null;
    }
    @Override
    public Iterator<K> iterator(){
        return new MyHashMapIterator();
    }
    private class MyHashMapIterator implements Iterator<K>{
        private int index;
        private Iterator<Node> bucketIterator;
        public MyHashMapIterator(){
            this.index = 0;
            this.bucketIterator = buckets[index].iterator();
        }
        @Override
        public boolean hasNext(){
            if(bucketIterator.hasNext()){
                return true;
            }
            for(int i = index + 1; i < capacity; i++){
                if(!buckets[i].isEmpty()){
                    return true;
                }
            }
            return false;
        }
        @Override
        public K next(){
            if(bucketIterator.hasNext()){
                return bucketIterator.next().key;
            }
            for(int i = index + 1; i < capacity; i++){
                if(!buckets[i].isEmpty()){
                    index = i;
                    bucketIterator = buckets[index].iterator();
                    return bucketIterator.next().key;
                }
            }
            return null;
        }
    }
    @Override
    public Set<K> keySet(){
        Set<K> keySet = new java.util.HashSet<>();
        for(Collection<Node> bucket : buckets){
            for(Node node : bucket){
                keySet.add(node.key);
            }
        }
        return keySet;
    }
    public static void main(String[] args){
        MyHashMap<String, Integer> b = new MyHashMap<>();
        for (int i = 0; i < 51; i++) {
            b.put("hi" + i, 1);
        }
        b.put("hi51", 1);
        System.out.println(b.get("hi50"));
        System.out.println(b.containsKey("hi50"));
    }

}
