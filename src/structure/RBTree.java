package structure;

import java.util.Iterator;

public class RBTree<T extends Comparable<T>> implements Iterable<T> {

    private RBNode root;
    private int size;

    // Internal?
    public class RBNode {
        public T key;
        public boolean red;
        public RBNode parent, left, right;

        public RBNode(T key, RBNode parent) {
            this.key = key;
            this.red = true;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            String result = "(" + this.key + ", " + this.red;
            result += ", " + (this.parent == null ? null : this.parent.key);
            result += ", " + (this.left == null ? null : this.left.key);
            result += ", " + (this.right == null ? null : this.right.key);
            return result + ")";
        }
    }

    public RBTree() {
        this.size = 0;
    }

    public RBTree(T key) {
        this();
        this.insert(key);
    }

    /** Inserts a new node with value {@code key} into the Red-Black Tree. */
    public void insert(T key) {

        this.size++;

        // Find parent for node
        RBNode parent = this.locateParent(key);

        // Attach node to parent
        RBNode newNode = new RBNode(key, parent);

        if (parent == null) {
            this.root = newNode;

        } else if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;

        } else {
            parent.right = newNode;
        }

        // Maintain Red-Black Tree properties
        this.correctInsert(newNode);
    }

    public void remove(T key) {

    }

    public boolean search(T key) {

        return false;
    }

    /** Returns whether or not node {@code n} is black. */
    private boolean isBlack(RBNode n) {
        return n == null || !n.red;
    }

    /** Rotates left about node {@code n}, which must have a right child. */
    private void rotateLeft(RBNode n) {

        int dir = 0;

        if (n != this.root) {
            dir = n == n.parent.left ? -1 : 1;
        }

        RBNode rep = n.right;

        // Preserve left subtree of replacement node
        n.right = rep.left;

        if (n.right != null) {
            n.right.parent = n;
        }

        // Swap replacement node with original node
        rep.left = n;
        rep.parent = n.parent;
        n.parent = rep;

        if (dir < 0) {
            rep.parent.left = rep;
        } else if (dir > 0) {
            rep.parent.right = rep;
        } else {
            this.root = rep;
        }
    }

    /** Rotates left about node {@code n}, which must have a right child. */
    private void rotateRight(RBNode n) {

        int dir = 0;

        if (n != this.root) {
            dir = n == n.parent.left ? -1 : 1;
        }

        RBNode rep = n.left;

        // Preserve left subtree of replacement node
        n.left = rep.right;

        if (n.left != null) {
            n.left.parent = n;
        }

        // Swap replacement node with original node
        rep.right = n;
        rep.parent = n.parent;
        n.parent = rep;

        if (dir < 0) {
            rep.parent.left = rep;
        } else if (dir > 0) {
            rep.parent.right = rep;
        } else {
            this.root = rep;
        }
    }

    /**
     * Finds parent node at which to insert a new node with a value {@code key}, or
     * null for insetion at the root.
     */
    private RBNode locateParent(T key) {

        RBNode parent = null;
        RBNode current = this.root;

        while (current != null) {
            parent = current;

            if (key.compareTo(parent.key) < 0) {
                current = parent.left;
            } else {
                current = parent.right;
            }
        }

        return parent;
    }

    /** Returns sibling of {@code n} if it exists. */
    private RBNode sibling(RBNode n) {
        if (n.parent == null) {
            return null;

        } else if (n == n.parent.left) {
            return n.parent.right;

        } else {
            return n.parent.left;
        }
    }

    /** Corrects tree to maintain Red-Black Tree properties after insertion. */
    private void correctInsert(RBNode n) {

        // Case 1: Parent Black - No Correction Needed
        if (this.isBlack(n.parent)) {
            return;
        }

        // Case 2: Parent Red and Root - Recolor parent
        if (n.parent == this.root) {

            n.parent.red = false;
            return;
        }

        RBNode pSibling = this.sibling(n.parent);

        // TODO: fix case 3, does not propagate upwards
        // Case 3: Parent Red and pSibling Red - Recolor Parent and pSibling upward
        if (!this.isBlack(n.parent) && !this.isBlack(pSibling)) {

            pSibling = this.sibling(n.parent);

            while (n != this.root && !this.isBlack(n.parent)) {
                if (this.isBlack(pSibling)) {
                    return;
                }

                n.parent.red = false;
                pSibling.red = false;
                n = n.parent.parent;
                n.red = true;
            }

            return;
        }

        // Case 4: Parent Red and pSibling Black - Rotate to correct

        // Left Child
        if (n == n.parent.left) {

            // Ensure n an 'outer child'
            if (n.parent == n.parent.parent.right) {
                n = n.parent;
                this.rotateLeft(n);
            }

            this.rotateRight(n.parent.parent);
            n.parent.red = false;
            this.sibling(n).red = true;

            // Right Child
        } else if (n == n.parent.right) {

            // Ensure n an 'outer child'
            if (n.parent == n.parent.parent.left) {
                n = n.parent;
                this.rotateRight(n);
            }

            this.rotateLeft(n.parent.parent);
            n.parent.red = false;
            this.sibling(n).red = true;
        }
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    public RBNode getRoot() {
        return this.root;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String result = "{";
        // TODO: better
        result += this.nodeString(this.root);

        return result + "}";
    }

    private String nodeString(RBNode n) {
        String result = n.toString();

        if (n.left != null && n.left != n.parent) {
            result += ", " + this.nodeString(n.left);
        }

        if (n.right != null && n.right != n.parent) {
            result += ", " + this.nodeString(n.right);
        }

        return result;
    }
}