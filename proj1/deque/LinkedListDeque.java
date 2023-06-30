package deque;

public class LinkedListDeque<T> {
    private class StuffNode{
        public T item;
        public StuffNode pre;
        public StuffNode next;
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
    public void addFirst(T item){
        size++;
        StuffNode newNode = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.pre = newNode;
        sentinel.next = newNode;
    }
    public void addLast(T item){
        size++;
        StuffNode newNode = new StuffNode(item, sentinel.pre, sentinel);
        sentinel.pre.next = newNode;
        sentinel.pre = newNode;
    }
    public boolean isEmpty(){
        return (sentinel.next == sentinel) && (sentinel.pre == sentinel);
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        StuffNode it = sentinel;
        while(it.next != sentinel){
            System.out.print(it.next.item + " ");
            it = it.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        T returnValue = sentinel.next.item;
        if(returnValue != null){
            size--;
        }
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        return returnValue;
    }
    public T removeLast(){
        T returnValue = sentinel.pre.item;
        if(returnValue != null){
            size--;
        }
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        return returnValue;
    }
    public T get(int index){
        StuffNode it = sentinel;
        int cnt = 0;
        while(cnt <= index){
            cnt++;
            it = it.next;
        }
        return it.item;
    }
    public static void main(String[] args){
        LinkedListDeque<String> s = new LinkedListDeque<String>();
        s.addFirst("dad");
        s.addFirst("mother");
        s.addFirst("son");
        s.addLast("grandmother");
        s.addLast("grandfather");
        s.printDeque();
        System.out.println(s.size());
        System.out.println(s.removeFirst());
        System.out.println(s.removeLast());
        System.out.println(s.size());
        System.out.println(s.get(4));
        s.printDeque();

    }
}
