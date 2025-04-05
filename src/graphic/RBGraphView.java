package graphic;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class RBGraphView extends JFrame{

    private int width, height;

    public RBGraphView() {
        super("Red-Black Tree GUI");

        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize()
                .getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize()
                .getHeight();

        this.setSize(this.width, this.height);

        this.setFocusable(true);
        this.requestFocus();
        this.setUndecorated(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
