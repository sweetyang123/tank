package frame.iterator;
import java.util.Iterator;


public class LinkList_ <E>{
    transient int size = 0;

    transient Node first;


    transient Node last;
    public static void main(String[] args) {
        LinkList_ linkList_ = new LinkList_();
        linkList_.add(new Node(1));
        linkList_.add(new Node(3));
        linkList_.add(new Node(4));
        Iterator iterator=linkList_.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    public Iterator iterator(){
        return new LinkListIterator();
    }
    public void add(Node node){
        if (first==null){first=node;last=first;}
        else {last.next=node;last=last.next;}
        size++;
    }
    private class LinkListIterator implements Iterator{
        Node current=first;

        @Override
        public boolean hasNext() {
            if (current.next==null) return false;
            return true;
        }

        @Override
        public Object next() {
            Node temp = current;
            current= current.next;
            if (temp==first){return first.value;}
            return temp.value;
        }
    }
    public static class Node{
        public Integer value;
        public Node next;
        public Node(Integer data) {
            this.value = data;
        }
    }
}


