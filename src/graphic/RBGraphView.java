package graphic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import structure.RBTree;

public class RBGraphView extends JFrame implements ActionListener {

    private int width, height;

    private RBGraphCanvas canvas;
    private JButton insertButton, removeButton, searchButton;
    private JTextField enterField, errField;

    private RBGraphController controller;

    public RBGraphView() {

        // Set style
        super("Red-Black Tree GUI");

        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize()
                .getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize()
                .getHeight();

        this.setSize(this.width, this.height);
        this.setLayout(new GridBagLayout());

        // Add canvas
        this.canvas = new RBGraphCanvas(this.width, (this.height * 4) / 5);

        GridBagConstraints canvasConstraints = new GridBagConstraints();
        canvasConstraints.gridx = 0;
        canvasConstraints.gridy = 0;
        canvasConstraints.weighty = 0.8;

        this.add(this.canvas, canvasConstraints);

        // Add bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout());

        GridBagConstraints bottomConstraints = new GridBagConstraints();
        bottomConstraints.gridx = 0;
        bottomConstraints.gridy = 1;
        bottomConstraints.weighty = 0.2;
        bottomConstraints.fill = GridBagConstraints.BOTH;

        this.add(bottomPanel, bottomConstraints);

        // Add buttons and text fields to bottom panel
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        bottomPanel.add(this.insertButton);

        this.removeButton = new JButton("Remove");
        this.removeButton.addActionListener(this);
        bottomPanel.add(this.removeButton);

        this.searchButton = new JButton("Search");
        this.searchButton.addActionListener(this);
        bottomPanel.add(this.searchButton);

        this.enterField = new JTextField("0");
        this.enterField.addActionListener(this);
        this.enterField.setName("Key: ");
        this.enterField.setToolTipText("Key");
        bottomPanel.add(this.enterField);

        bottomPanel.add(new JPanel());

        this.errField = new JTextField();
        this.errField.setEditable(false);
        bottomPanel.add(this.errField);

        // Finalize JFrame
        this.setFocusable(true);
        this.requestFocus();
        this.setUndecorated(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void registerObserver(RBGraphController controller) {
        this.controller = controller;
    }

    public void updateTree(RBTree<Integer> t) {
        this.canvas.drawTree(this.canvas.getGraphics(), t);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == this.insertButton) {
            this.errField.setText(this.controller.insertNode(this.enterField.getText()));
        }
    }

}
