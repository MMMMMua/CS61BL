import java.util.TreeMap;

/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * @author Maurice Lee and Wan Fung Chui
 */

public class IntList {

    /** The integer stored by this node. */
    private int item;
    /** The next node in this IntList. */
    private IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item() = item;
        this.next() = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /** Returns an IntList consisting of the elements in ITEMS.
     * IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints (1 2 3) */
    public static IntList list(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next() = new IntList(items[i]);
            last = last.next();
        }
        return head;
    }

    /** Returns the integer stored by this IntList. */
    public int item() {
        return item;
    }

    /** Returns the next node stored by this IntList. */
    public IntList next() {
        return next;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        if (position < 0)
            throw new IllegalArgumentException("list index out of range");
        IntList curr = this;
        for (int i = 0; i < position; ++i)
            if (curr.next() != null)
                curr = curr.next();
            else
                throw new IllegalArgumentException("list index out of range");
        return curr.item();
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        int count = 1;
        IntList curr = this;
        while (curr.next() != null) {
            curr = curr.next();
            count++;
        }
        return count;
    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "( 1 2 3 )".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        IntList curr = this;
        String str = "( " + curr.item();
        while (curr.next() != null) {
            curr = curr.next();
            str += " " + curr.item();
        }
        str = str + " )";
        return str;
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        if (obj.getClass().getSimpleName() != "IntList")
            return false;
        IntList cur = this;
        IntList ano = (IntList)obj;
        while (cur.next() != null && ano.next() != null && cur.item() == ano.item()) {
            cur = cur.next();
            ano = ano.next();
        }
        if (cur.next() != null || ano.next() != null)
            return false;
        return true;
    }

    /**
     * Adds the given item at the end of the list.
     *
     * @param item, the int to be added.
     */
    public void add(int item) {
        IntList iter = this;
        while (iter.next() != null)
            iter = iter.next();
        IntList last = new IntList(item);
        iter.next() = last;
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        IntList iter = this;
        int ret = iter.item();
        while (iter.next() != null) {
            ret = iter.item() > ret ? ret : iter.item();
            iter = iter.next();
        }
        ret = iter.item() > ret ? ret : iter.item();
        return ret;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        IntList iter = this;
        int ret = 0;
        while (iter.next() != null) {
            ret += iter.item() * iter.item();
            iter = iter.next();
        }
        ret += iter.item() * iter.item();
        return ret;
    }

    /**
     * Returns a new IntList consisting of L1 followed by L2,
     * non-destructively.
     *
     * @param l1 list to be on the front of the new list.
     * @param l2 list to be on the back of the new list.
     * @return new list with L1 followed by L2.
     */
    public static IntList append(IntList l1, IntList l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;
        IntList iter1 = l1;
        IntList iter2 = l2;
        IntList iter3 = new IntList(iter1.item());
        IntList ret = iter3;
        while (iter1.next()() != null) {
            iter1 = iter1.next();
            iter3.add(iter1.item());
            iter3 = iter3.next();
        }
        while (iter2.next() != null) {
            iter3.add(iter2.item());
            iter2 = iter2.next();
            iter3 = iter3.next();
        }
        iter3.add(iter2.item());
        return ret;
    }
}