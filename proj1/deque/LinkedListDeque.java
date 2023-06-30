package deque;

public class LinkedListDeque<T> implements Deque<T>{
    private class StuffNode{
        private T item;
        private StuffNode pre;
        private StuffNode next;
        public StuffNode(T i , StuffNode p, StuffNode n){
            item = i;
            pre = p;
            next = n;
        }
    }
    private StuffNode sentinel;
    private int size;
    public LinkedListDeque(){
        size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    @Override
    public void addFirst(T item){
        size++;
        StuffNode newNode = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.pre = newNode;
        sentinel.next = newNode;
    }
    @Override
    public void addLast(T item){
        size++;
        StuffNode newNode = new StuffNode(item, sentinel.pre, sentinel);
        sentinel.pre.next = newNode;
        sentinel.pre = newNode;
    }
    @Override
    public boolean isEmpty(){
        return (sentinel.next == sentinel) && (sentinel.pre == sentinel);
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        StuffNode it = sentinel;
        while(it.next != sentinel){
            System.out.print(it.next.item + " ");
            it = it.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        T returnValue = sentinel.next.item;
        if(returnValue != null){
            size--;
        }
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        return returnValue;
    }
    @Override
    public T removeLast(){
        T returnValue = sentinel.pre.item;
        if(returnValue != null){
            size--;
        }
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        return returnValue;
    }
    @Override
    public T get(int index){
        StuffNode it = sentinel;
        int cnt = 0;
        while(cnt <= index){
            cnt++;
            it = it.next;
        }
        return it.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        // 迭代器实现...
    }
    public static void main(String[] args){
        Deque<Integer> a = new ArrayDeque<>();
        for(int i = 0; i < 17; i++){
            a.addLast(i);
        }
        a.addFirst(-100);
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.printDeque();

    }
}
