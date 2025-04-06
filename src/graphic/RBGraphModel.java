package graphic;

import structure.RBTree;

public class RBGraphModel {

    private RBTree<Integer> tree;

    public RBGraphModel() {
        this.tree = new RBTree<>();
    }

    public void insert(int n) {
        this.tree.insert(n);
    }

    public RBTree<Integer> getTree(){
        return tree;
    }
}
