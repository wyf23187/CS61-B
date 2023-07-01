package deque;
import java.util.Iterator;
public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int head;
    private int tail;
    private int size;
    public ArrayDeque(){
        size = 8;
        items = (T[])new Object[8];
        head = 0;
        tail = 0;
    }
    private void resizeLarger(){
        T[] newArray = (T[])new Object[size * 2];
        System.arraycopy(items, 0, newArray, 0, tail);
        System.arraycopy(items, tail, newArray, size + tail, size - tail);
        items = newArray;
        head = size + tail;
        size *= 2;
    }
    @Override
    public void addLast(T item){
        if(head == tail && items[head] != null){
            resizeLarger();
        }
        items[tail] = item;
        tail++;
        tail %= size;
    }
    @Override
    public void addFirst(T item){
        if(head == tail && items[head] != null){
            resizeLarger();
        }
        head--;
        head = (head + size) % size;
        items[head] = item;
    }
    @Override
    public boolean isEmpty(){
        return head == tail && items[head] == null;
    }
    private void resizeShorter(){
        if(size <= 16){
            return;
        }
        T[] newArray = (T[])new Object[size / 2];
        if(head > tail){
            System.arraycopy(items, 0, newArray, 0, tail);
            System.arraycopy(items, head, newArray, tail, size - head);
            items = newArray;
            head = tail;
            size /= 2;
        }
        else if(head < tail){
            System.arraycopy(items, head, newArray, 0, tail - head);
            items = newArray;
            head = 0;
            size /= 2;
            tail = size;
        }
    }
    @Override
    public T removeFirst(){
        if(Math.abs(head - tail) == size / 2){
            resizeShorter();
        }
        T returnValue = items[head];
        items[head] = null;
        head++;
        head %= size;
        return returnValue;
    }
    @Override
    public T removeLast(){
        if(Math.abs(head - tail) == size / 2){
            resizeShorter();
        }
        T returnValue = items[(size + tail - 1) % size];
        items[(size + tail - 1) % size] = null;
        tail--;
        tail = (size + tail) % size;
        return returnValue;
    }
    @Override
    public void printDeque(){
        int tmphead = head;
        if(tmphead >= tail){
            while(tmphead < size){
                System.out.print(items[tmphead] + " ");
                tmphead++;
            }
            tmphead %= size;
        }
        while(tmphead < tail){
            System.out.print(items[tmphead] + " ");
            tmphead++;
        }
        System.out.println();
    }
    @Override
    public T get(int index){
        int cnt = 0;
        if(head + index >= size){
            index = (head + index) % size;
            return items[index];
        }
        return items[head + index];
    }
    @Override
    public int size(){
        return size;
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int index;
        public ArrayDequeIterator(){
            index = 0;
        }
        @Override
        public boolean hasNext(){
            return index < size;
        }
        @Override
        public T next(){
            T returnValue = items[index];
            index++;
            return returnValue;
        }
    }
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>)o;
        if(this.size() != other.size()){
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = other.iterator();
        while(thisIterator.hasNext()){
            if(!thisIterator.next().equals(otherIterator.next())){
                return false;
            }
        }
        return true;
    }
    public T getRecursive(int index){
        if(index >= size){
            return null;
        }
        if(head + index >= size){
            index = (head + index) % size;
            return items[index];
        }
        return items[head + index];
    }
    public static void main(String[] args){
        java.util.Iterator<Integer> a = new ArrayDeque<Integer>().iterator();
        System.out.println(a.hasNext());

    }
}