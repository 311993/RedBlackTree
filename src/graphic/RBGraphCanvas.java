package graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import structure.RBTree;

public class RBGraphCanvas extends Canvas {

    private int width, height;
    private BufferedImage display;

    public RBGraphCanvas(int w, int h) {
        this.width = w;
        this.height = h;

        this.setSize(this.width, this.height);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics window) {
        window.clearRect(0, 0, this.width, this.height);
        window.drawImage(this.display, 0, 0, null);
    }

    public void drawTree(Graphics window, RBTree<Integer> t) {

        this.display = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);

        RBTree<Integer>.RBNode root = t.getRoot();

        final int nodeSize = 40;

        this.drawNode(this.display.getGraphics(), this.width / 2 - nodeSize / 2, nodeSize, nodeSize, this.width, root);

        this.paint(window);
    }

    private void drawNode(Graphics g, int x, int y, int r, int px, RBTree<Integer>.RBNode n) {

        if (n.left != null) {
            g.setColor(Color.DARK_GRAY);

            int x2 = x - Math.abs(px - x) / 2;
            int y2 = y + 2 * r;

            g.drawLine(x + r / 2, y + r / 2, x2 + r / 2, y2 + r / 2);
            this.drawNode(g, x2, y2, r, x, n.left);
        }

        if (n.right != null) {
            g.setColor(Color.DARK_GRAY);

            int x2 = x + Math.abs(px - x) / 2;
            int y2 = y + 2 * r;

            g.drawLine(x + r / 2, y + r / 2, x2 + r / 2, y2 + r / 2);
            this.drawNode(g, x2, y2, r, x, n.right);
        }

        if (n.red) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillOval(x, y, r, r);

        g.setColor(Color.WHITE);
        g.drawString("" + n.key, x + r / 2, y + r / 2);
    }
}
