package deque;
import java.util.Comparator;
import java.util.Iterator;
public class MaxArrayDeque<T> implements Deque<T>{
    private ArrayDeque<T> items;
    private Comparator<T> comparator;

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
