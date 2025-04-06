package graphic;

public class RBGraphMain {

    /** Empty constructor to prevent instantiation. */
    private RBGraphMain() {
    }

    /** Start up Red-Black Tree GUI */
    public static void main(String... args) {

        System.setProperty("sun.java2d.uiScale", "1.0");

        RBGraphModel model = new RBGraphModel();
        RBGraphView view = new RBGraphView();
        RBGraphController controller = new RBGraphController(model, view);

        view.registerObserver(controller);

    }
}
