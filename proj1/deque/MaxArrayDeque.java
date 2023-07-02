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
    @Override
    public T getRecursive(int index) {
        return items.getRecursive(index);
    }
    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }


}
