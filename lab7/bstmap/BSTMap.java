package bstmap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private static class Node<K extends Comparable<K>, V>{
        K key;
        V value;
        Node<K, V> left, right;
        boolean isRed;
        Node(K k, V v){
            this.key = k;
            this.value = v;
            this.isRed = true;
        }
    }
    private Node<K, V> root;
    private int size;
    public BSTMap(){
        root = null;
        size = 0;
    }
    @Override
    public void put(K key, V value){
        root = putHelper(root, key, value);
        root.isRed = false;
        size++;
    }
    private Node<K, V> putHelper(Node<K, V> node, K key, V value){
        if(node == null){
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = putHelper(node.left, key, value);
        }else if(cmp > 0){
            node.right = putHelper(node.right, key, value);
        }else{
            node.value = value;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }
    // Helper method
    private boolean isRed(Node<K, V> n){
        if(n == null){
            return false;
        }
        return n.isRed;
    }
    private Node<K, V> rotateLeft(Node<K, V> h) {
        Node<K, V> x = h.right;
        h.right = x.left;
        x.left = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }

    private Node<K, V> rotateRight(Node<K, V> h) {
        Node<K, V> x = h.left;
        h.left = x.right;
        x.right = h;
        x.isRed = h.isRed;
        h.isRed = true;
        return x;
    }
    private void flipColors(Node<K, V> h) {
        h.isRed = true;
        h.left.isRed = false;
        h.right.isRed = false;
    }
    @Override
    public V get(K key){
        return getHelper(root, key);
    }
    private V getHelper(Node<K, V> node, K key){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return getHelper(node.left, key);
        }else if(cmp > 0){
            return getHelper(node.right, key);
        }else{
            return node.value;
        }
    }
    @Override
    public void clear(){
        size = 0;
        root = null;
    }
    @Override
    public boolean containsKey(K key){
        return containsKeyHelper(root, key);
    }
    private boolean containsKeyHelper(Node<K, V> node, K key){
        if(node == null){
            return false;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return containsKeyHelper(node.left, key);
        }else if(cmp > 0){
            return containsKeyHelper(node.right, key);
        }else{
            return true;
        }
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public java.util.Iterator<K> iterator(){
        return new BSTMapIterator();
    }
    private class BSTMapIterator implements Iterator<K>{
        private final Stack<Node<K, V>> stack;
        public BSTMapIterator(){
            stack = new Stack<>();
            pushLeft(root);
        }
        private void pushLeft(Node<K, V> node){
            if(node == null){
                return;
            }
            stack.push(node);
            pushLeft(node.left);
        }
        @Override
        public boolean hasNext(){
            return !stack.isEmpty();
        }
        @Override
        public K next(){
            Node<K, V> node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }
    public void printInOrder(){
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node<K, V> node){
        if(node == null){
            return;
        }
        printInOrderHelper(node.left);
        System.out.println(node.key + " " + node.value);
        printInOrderHelper(node.right);
    }
    public void printTreeGraphically(){
        printTreeGraphicallyHelper(root, 0);
    }
    private void printTreeGraphicallyHelper(Node<K, V> node, int space){
        if(node == null){
            return;
        }
        for(int i = 0; i < space; i++){
            System.out.print(" ");
        }
        System.out.println(node.key + " " + node.value);
        printTreeGraphicallyHelper(node.left, space + 2);
        printTreeGraphicallyHelper(node.right, space + 2);

    }
    public static void main(String[] args){
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("a", 1);
        bst.put("b", 2);
        bst.put("c", 3);
        bst.put("d", 4);
        bst.put("e", 5);
        bst.put("f", 6);
        bst.put("g", 7);
        for(String s : bst){
            System.out.println(s);
        }
    }

}
