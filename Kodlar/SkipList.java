import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


/**
 * In this class I implement The Skip List method which I needed.
 * Also I create the iterator and comparable class.
 */
@SuppressWarnings("unchecked")
public class SkipList<E extends Comparable<E>> implements Iterable<E>{
    private static final double LOG2 = Math.log(2.0);
    private int maxLevel = 4;
    private int maxCap = (int) (Math.pow(2, maxLevel) - 1);
    private int size = 0;
    private SLNode<E> head;

    public SkipList() {
        head = new SLNode<>(maxLevel, null);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    public Iterator<E> iterator() {
        return new SkipListIter();
    }

    private static class SLNode<E>{
        SLNode<E>[] links;
        E data;
        public SLNode(int m, E data) {
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }
    }

    /**
     * I generate random number for express numbersin Skip-List.
     */
    private int logRandom(){
        Random rand = new Random();
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }
    /**
     * I search the number that I needed thanks to express line and I search easily because of express line.
     * @param target
     * @return pred
     */
    private SLNode<E>[] search (E target) {
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel]; SLNode<E> current = head;
        for (int i = current.links.length - 1; i >= 0; i--) {
            while (current.links[i] != null
                    && current.links[i].data.compareTo(target) < 0) {
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }

    public E find(E target) {
        SLNode<E>[] pred = search(target);
        if (pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0) {
            return pred[0].links[0].data;
        } else {
            return null;
        }
    }
    /**
     * I removed target which determined in skip list.
     * @param target
     */
    public E remove(E target){
        SLNode<E>[] pred = search(target);
        if(pred[0].links[0] != null && pred[0].links[0].data.compareTo(target) == 0){
            E returnVal = pred[0].links[0].data;
            int level = pred[0].links[0].links.length;
            for(int i = 0; i < level; i++){
                pred[i].links[i] = pred[i].links[i].links[i];
            }
            size--;
            return returnVal;
        }
        else{
            return null;
        }
    }
    /**
     * I adding the element to skip list.
     * @param item
     */
    public void add(E item){
        int level = logRandom();
        SLNode<E>[] pred = search(item);
        SLNode<E> newNode = new SLNode<>(level, item);
        for(int i = 0; i < level; i++){
            newNode.links[i] = pred[i].links[i];
            pred[i].links[i] = newNode;
        }
        size++;
        if (size > maxCap) {
            maxLevel++;
            maxCap = (int) (Math.pow(2, maxLevel) - 1);
            head.links = Arrays.copyOf(head.links, maxLevel);
            //pred = Arrays.copyOf(update, maxLevel);
            //pred[maxLevel ‐ 1] = head;
        }
    }

    public int size(){
        return size;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<E> iterator = new SkipListIter();
        int count = 0;
        if(size == 0){
            return "[]";
        }
        s.append("[");
        while (iterator.hasNext()){
            if (count == size - 1) {
                s.append(iterator.next()).append("]");
            }
            else {
                s.append(iterator.next()).append(", ");
            }
            count++;
        }
        return s.toString();
    }

    /**
     * I create this class for iterator in skip list.
     */
    private class SkipListIter implements Iterator<E>{
        private SLNode<E> current = head;
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        

         @Override
        public boolean hasNext() {
            return current.links[0] != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return (current = current.links[0]).data;
        }
        

    }

   


}
