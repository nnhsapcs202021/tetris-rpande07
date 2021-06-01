import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * Write a description of class JBrainTetris here.
 *
 * @author rpande
 * @version 31 May 2021
 */
public class JBrainTetris extends JTetris
{
    // instance variables - replace the example below with your own
    private Brain brain;

    // Controls
    // JComboBox
    private JComboBox brainComboBox;

    /**
     * Constructor for objects of class JBrainTetris
     * @param width - width of panel
     * @param length - length of panel
     * 
     */
    public JBrainTetris(int width, int height)
    {
        super(width, height);
    }

    /**
     * Creates panel
     * @return - returns container
     */
    public Container createControlPanel()
    {
        Container panel = super.createControlPanel();

        ArrayList<Brain> brains = BrainFactory.createBrains();

        this.brainComboBox = new JComboBox();

        for (Brain b : brains)
        {
            this.brainComboBox.addItem(b);
        }
        brain = (Brain) this.brainComboBox.getSelectedItem();

        this.brainComboBox.addActionListener(new BrainActionListener());
        panel.add(this.brainComboBox);

        return panel;
    }

    private class BrainActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            selectBrain();
        }
    }
    /**
     * Select the brain.
     */
    public void selectBrain()
    {
        brain = (Brain) this.brainComboBox.getSelectedItem();
    }
}
