import java.util.Comparator;

/**
 * This is an implementation of a sorted three, in which each node's left child is less than the node itself and
 * each node's right child is bigger than the node itself
 */
class SortedTree<V> {

    //The tree is always nonempty
    private final Node root = new Node(Integer.MIN_VALUE);
    //This implementation supports comparators for changing the ordering of its nodes
    private Comparator = Comparator.comparingInt(Node::getValue);

    private class Node {
        V value;
        Node leftChild;
        Node rightChild;

        Node(int value) { this.value = value; }

        V getValue() { return value; }

        void add(int value) {
            if (value < this.value) {
                if (this.leftChild == null) this.leftChild = new Node(value);
                else this.leftChild.add(value);
                }
            else {
                if (this.rightChild == null) this.rightChild = new Node(value);
                else this.rightChild.add(value);
            }   }

    void addNode(int value) { root.add(value); }
}
