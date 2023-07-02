package deque;
import java.util.Comparator;
import java.util.Iterator;
public class MaxArrayDeque<T> implements Deque<T>{
    private ArrayDeque<T> items;
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        items = new ArrayDeque<>();
        comparator = c;
    }
    public MaxArrayDeque(){
        items = new ArrayDeque<>();
        comparator = new Comparator<T>(){
            @Override
            public int compare(T o1, T o2){
                return ((Comparable<T>)o1).compareTo(o2);
            }
        };
    }
    public T max() {
        if (items.isEmpty()) {
            return null;
        }
        T returnMax = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (comparator.compare(items.get(i), returnMax) > 0) {
                returnMax = items.get(i);
            }
        }
        return returnMax;
    }
    public T max(Comparator<T> c) {
        if (items.isEmpty()) {
            return null;
        }
        T returnMax = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            if (c.compare(items.get(i), returnMax) > 0) {
                returnMax = items.get(i);
            }
        }
        return returnMax;
    }
    @Override
    public void addFirst(T item) {
        items.addFirst(item);
    }
    @Override
    public void addLast(T item) {
        items.addLast(item);
    }
    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
    @Override
    public int size() {
        return items.size();
    }
    @Override
    public void printDeque() {
        items.printDeque();
    }
    @Override
    public T removeFirst() {
        return items.removeFirst();
    }
    @Override
    public T removeLast() {
        return items.removeLast();
    }
    @Override
    public T get(int index) {
        return items.get(index);
    }
    public T getRecursive(int index) {
        return items.getRecursive(index);
    }
    public Iterator<T> iterator() {
        return items.iterator();
    }
    public boolean equals(Object o) {
        return items.equals(o);
    }
    public static void main(String[] args){
        MaxArrayDeque<Integer> a = new MaxArrayDeque<>();
        for(int i = 0; i < 17; i++){
            a.addLast(i);
        }
        a.addFirst(100);
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.printDeque();
        System.out.println(a.max());
        System.out.println(a.max(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        }));
    }


}
