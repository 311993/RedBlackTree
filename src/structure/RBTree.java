package structure;
import java.util.Iterator;

public class RBTree<T extends Comparable<T>> implements Iterable<T>{

    private RBNode root;

    //Internal?
    private class RBNode{
        T key;
        boolean red;
        RBNode parent, left, right;

        RBNode(T key, RBNode parent){
            this.key = key;
            this.red = true;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }

    public RBTree(){
    }

    public RBTree(T key){
        this.insert(key);
    }

    /**Inserts a new node with value {@code key} into the Red-Black Tree.*/
    public void insert(T key){
        
        //Find parent for node
        RBNode parent = locateParent(key);

        //Attach node to parent
        RBNode newNode = new RBNode(key, parent);

        if(parent == null){
            this.root = newNode;
            this.root.red = false;

        }else if(key.compareTo(parent.key) < 0){
            parent.left = newNode;

        }else{
            parent.right = newNode;
        }

        //Maintain Red-Black Tree properties
        correctInsert(newNode);
    }

    public void remove(T key){

    }

    public boolean search(T key){

        return false;
    }

    /**Returns whether or not node {@code n} is black. */
    private boolean isBlack(RBNode n){
        return n == null || !n.red;
    }

    /**Rotates left about node {@code n}, which must have a right child.*/
    private void rotateLeft(RBNode n){
        
        RBNode rep = n.right;
        boolean isLeft = n == n.parent.left;

        //Preserve left subtree of replacement node
        if(rep.left != null){
            n.right = rep.left;
            n.right.parent = n;
        }

        //Swap replacement node with original node
        rep.left = n;
        rep.parent = n.parent;
        n.parent = rep;

        if(isLeft){
            rep.parent.left = rep;
        }else{
            rep.parent.right = rep;
        }
    }

    /**Rotates left about node {@code n}, which must have a right child.*/
    private void rotateRight(RBNode n){
        
        RBNode rep = n.left;
        boolean isLeft = n == n.parent.left;

        //Preserve left subtree of replacement node
        if(rep.right != null){
            n.left = rep.right;
            n.left.parent = n;
        }

        //Swap replacement node with original node
        rep.right = n;
        rep.parent = n.parent;
        n.parent = rep;

        if(isLeft){
            rep.parent.left = rep;
        }else{
            rep.parent.right = rep;
        }
    }

    /**Finds parent node at which to insert a new node with a value {@code key}, or null for insetion at the root.*/
    private RBNode locateParent(T key){
        
        RBNode parent = null;
        RBNode current = this.root;
        
        while(current != null){
            parent = current;

            if(key.compareTo(parent.key) < 0){
                current = parent.left;
            }else{
                current = parent.right;
            }
        }

        return parent;
    }

    /**Returns sibling of {@code n} if it exists. */
    private RBNode sibling(RBNode n){
        if(n.parent == null){
            return null;
        
        }else if(n == n.parent.left){
            return n.parent.right;

        }else{
            return n.parent.left;
        }
    }

    /**Corrects tree to maintain Red-Black Tree properties after insertion. */
    private void correctInsert(RBNode n){
        
        //Case 1: Parent Black - No Correction Needed
        if(isBlack(n.parent)){
            return;
        }
        
        //Case 2: Parent Red and Root - Recolor parent
        if(n.parent == root){
            
            n.parent.red = false;
            return;
        }

        RBNode pSibling = sibling(n.parent);
        
        //Case 3: Parent Red and pSibling Red - Recolor Parent and pSibling upward
        if(!isBlack(n.parent) && !isBlack(pSibling)){

            while(n != root && !isBlack(n.parent)){
                if(isBlack(pSibling)){
                    return;
                }

                n.parent.red = false;
                pSibling.red = false;
                n = n.parent.parent;
                n.red = true;
                pSibling = sibling(n.parent);
            }

        }

        //Case 4: Parent Red and pSibling Black - Rotate to correct

        //Left Child
        if(n == n.parent.left){
            
            //Ensure n an 'outer child'
            if(n.parent == n.parent.parent.right){
                n = n.parent;
                rotateRight(n);
            }

            rotateLeft(n.parent.parent);
            n.parent.red = false;
            sibling(n).red = true;

        //Right Child
        }else if(n == n.parent.right){
            
            //Ensure n an 'outer child'
            if(n.parent == n.parent.parent.left){
                n = n.parent;
                rotateLeft(n);
            }

            rotateRight(n.parent.parent);
            n.parent.red = false;
            sibling(n).red = true;
        }
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public String toString(){
        // TODO stub
        return super.toString();
    }
}