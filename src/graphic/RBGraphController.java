package graphic;

public class RBGraphController {

    private RBGraphModel model;
    private RBGraphView view;

    public RBGraphController(RBGraphModel model, RBGraphView view) {
        this.model = model;
        this.view = view;
    }

    public String insertNode(String text) {
        try {

            int key = Integer.parseInt(text, 10);

            this.model.insert(key);

            this.view.updateTree(this.model.getTree());

            return "Added Node: " + key;

        } catch (NumberFormatException e) {

            return "Invalid key value";
        }

    }

}
