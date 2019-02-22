/**
 * This is an implementation of a sorted three, in which each node's left child is less than the node itself and
 * each node's right child is bigger than the node itself
 */

class SortedTree<V extends Comparable<V>> {

    private BinaryNode root = null;

    private class BinaryNode {
        V value;
        BinaryNode leftChild;
        BinaryNode rightChild;

        BinaryNode(V value) { this.value = value; }

        V getValue() { return value; }

        void addNode(V value) {
            if (this.value.compareTo(value) > 0) {
                if (this.leftChild == null) this.leftChild = new BinaryNode(value);
                else this.leftChild.addNode(value);
                }
            else {
                if (this.rightChild == null) this.rightChild = new BinaryNode(value);
                else this.rightChild.addNode(value);
            }   }

        BinaryNode find(V value) {
            if (this.value == value) return this;
            else if (this.value.compareTo(value) > 0)
                return (this.leftChild == null) ? null : this.leftChild.find(value);
            else
                return (this.rightChild == null) ? null : this.rightChild.find(value);
        }   }

    void add(V value) {
        if (value == null) throw new NullPointerException("This sorted tree cannot store null values");
        if (root == null) root = new BinaryNode(value);
        else this.root.addNode(value);
    }   }