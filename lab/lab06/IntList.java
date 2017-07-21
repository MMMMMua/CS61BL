/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 * Encapsulated version.
 */
public class IntList {

    /**
     * The head of the list is the first node in the list. 
     * If the list is empty, head is null 
     */
    private IntListNode head;
    private int size;

    /**
     * IntListNode is a nested class. It can be instantiated
     * when associated with an instance of IntList.
     */
    public class IntListNode {
        int item;
        IntListNode next;

        @Override
        public String toString() {
            return "IntListNode{" +
                   "item=" + item +
                   ", next=" + next +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IntListNode)) return false;

            IntListNode that = (IntListNode) o;

            if (item != that.item) return false;
            return next != null ? next.equals(that.next) : that.next == null;
        }

        @Override
        public int hashCode() {
            int result = item;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        public IntListNode(int item, IntListNode next) {
            this.item = item;

            this.next = next;
        }
    }

    public int getSize() {
        return size;
    }

    public IntList() {}

    public IntList(int[] initial) {
        for (int i = initial.length - 1; i >= 0; i--) {
            head = new IntListNode(initial[i], head);
        }
        size = initial.length;
    }

    /**
     * Get the value at position pos. If the position does not exist, throw an
     * IndexOutOfBoundsException.
     * @param position to get from
     * @return the int at the position in the list.
     */
    public int get(int position) {
        if (position >= size) throw new IndexOutOfBoundsException("Position larger than size of list.");
        IntListNode curr = head;
        while (position > 0) {
            curr = curr.next;
            position--;
        }
        return curr.item;
    }

    /* Fill in below! */

    /**
     * Insert a new node into the IntList.
     * @param x value to insert
     * @param position position to insert into. If position exceeds the size of the list, insert into
     *            the end of the list.
     */
    public void insert(int x, int position) {
        IntListNode iter = this.head, before = null;
        IntListNode newnode = new IntListNode(x, null);

        if (size == 0) {
            this.head = newnode;
            size += 1;
            return;
        }
        while (iter.next != null && position > 0) {
            before = iter;
            iter = iter.next;
            position--;
        }

        if (position > 0) {
            newnode.next = null;
            iter.next = newnode;
        }
        else {
            if (before != null)
                before.next = newnode;
            else
                this.head = newnode;
            newnode.next = iter;
        }
        size += 1;
    }

    /**
     * Merge two sorted IntLists a and b into one sorted IntList containing all of their elements.
     * @return a new IntList without modifying either parameter
     */
    public static IntList merge(IntList a, IntList b) {
        IntListNode head1 = a.head, head2 = b.head;
        IntList merged = new IntList();
        while (head1 != null && head2 != null) {
            if (head1.item > head2.item) {
                merged.insert(head2.item, 10000000);
                head2 = head2.next;
            }
            else {
                merged.insert(head1.item, 10000000);
                head1 = head1.next;
            }
        }

        if (head2 == null)
            while (head1 != null) {
                merged.insert(head1.item, 10000000);
                head1 = head1.next;
            }

        if (head1 == null)
            while (head2 != null) {
                merged.insert(head2.item, 10000000);
                head2 = head2.next;
            }
        return merged;
    }

    /**
     * Reverse the current list recursively, using a helper method.
     */
    public void reverse() {
        this.head = reverseHelper(this.head, null);
    }

    public static IntListNode reverseHelper(IntListNode l, IntListNode soFar) {
        if (l.next == null) {
            l.next = soFar;
            return l;
        }
        IntListNode tmp = l.next;
        l.next = soFar;
        return reverseHelper(tmp, l);
    }

    /* Optional! */

    /**
     * Remove the node at position from this list.
     * @param position int representing the index of the node to remove. If greater than the size
     *                 of this list, throw an IndexOutOfBoundsException.
     */
    public void remove(int position) {
        if (position >= size) throw new IndexOutOfBoundsException();
        IntListNode iter = this.head, before = null;
        while (position > 0) {
            before = iter;
            iter = iter.next;
            position--;
        }
        if (before != null)
            before.next = iter.next;
        else
            this.head = iter.next;
        this.size -= 1;
    }
}
