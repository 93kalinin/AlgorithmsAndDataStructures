import java.util.ConcurrentModificationException;
import java.util.Optional;

/**
 * This is an implementation of a sorted three, in which each node's left child is less than the node itself and
 * each node's right child is bigger than the node itself
 */
class SortedTree<V extends Comparable<V>> {

    private BinaryNode root = null;

    private class BinaryNode {
        V value;
        BinaryNode parent;
        BinaryNode leftChild;
        BinaryNode rightChild;

        BinaryNode(V value) { this.value = value; }

        V getValue() { return value; }    //нужен ли?

        private BinaryNode find(V value) {
            if (this.value == value) return this;
            else if (this.value.compareTo(value) > 0)
                return (this.leftChild == null) ? null : this.leftChild.find(value);
            else
                return (this.rightChild == null) ? null : this.rightChild.find(value);
        }

        void add(V value) {    //разрешены ли одинаковые значения?
            if (this.value.compareTo(value) > 0) {
                if (this.leftChild == null) this.leftChild = new BinaryNode(value);
                else this.leftChild.add(value);
                }
            else {
                if (this.rightChild == null) this.rightChild = new BinaryNode(value);
                else this.rightChild.add(value);
            }   }

        V remove(V value) throws IllegalStateException {
            BinaryNode forDeletion = this.find(value);
            if (forDeletion == null) { return null; }
            BinaryNode parent = forDeletion.parent;

            boolean hasLeft = (forDeletion.leftChild != null);
            boolean hasRight = (forDeletion.rightChild != null);
            boolean isLeaf = (!hasLeft && !hasRight);
            boolean isRoot = (parent == null);
            boolean isLeft = !isRoot && (parent.leftChild.value == value);
            boolean isRight = !isRoot && (parent.rightChild.value == value);

            if ((isLeft && isRight) || (!isLeft && !isRight && !isRoot))
                { throw new IllegalStateException("The tree is corrupted and no longer should be used"); }

            if (isRoot && isLeaf) { root = null;  return this.value; }
            else if (!isRoot && isLeaf) {
                if (isLeft) { parent.leftChild = null; }
                else { parent.rightChild = null; }
                return this.value;
                }
            else if (hasLeft && !hasRight) {
                if (isRoot) { root = forDeletion.leftChild; }
                else if (isLeft) { parent.leftChild = forDeletion.leftChild; }
                else { parent.rightChild = forDeletion.leftChild; }
                return this.value;
                }
            else if (!hasLeft && hasRight) {
                if (isRoot) { root = forDeletion.rightChild; }
                else if (isLeft) { parent.leftChild = forDeletion.rightChild; }
                else { parent.rightChild = forDeletion.rightChild; }
                return this.value;
                }
            return this.value;
        }}

    void insert(V value) {
        if (value == null) throw new NullPointerException("This sorted tree cannot store null values");
        if (root == null) root = new BinaryNode(value);
        else this.root.add(value);
    }   }