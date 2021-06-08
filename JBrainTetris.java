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
    private Brain selectedBrain;

    // Controls
    // JComboBox
    private JComboBox brainComboBox;
    private JButton enableBrainButton;
    private boolean isBrainEnabled;
    private Move goal; 

    /**
     * Constructor for objects of class JBrainTetris
     * @param width - width of panel
     * @param length - length of panel
     * 
     */
    public JBrainTetris(int width, int height)
    {
        super(width, height);
        isBrainEnabled = false;
    }

    /**
     * Creates panel
     * @return - returns container
     */
    public Container createControlPanel()
    {
        Container container = super.createControlPanel();

        ArrayList<Brain> brains = BrainFactory.createBrains();

        this.brainComboBox = new JComboBox();
        
        // to show ComboBox in a single line
        JPanel panelForComboBox = new JPanel();
        panelForComboBox.add(brainComboBox);

        for (Brain b : brains)
        {
            this.brainComboBox.addItem(b);
        }
        
        selectedBrain = (Brain) this.brainComboBox.getSelectedItem();

        this.brainComboBox.addActionListener(new BrainActionListener());
        container.add(panelForComboBox);
        
        enableBrainButton = new JButton("Enable Brain");
        this.enableBrainButton.addActionListener(new EnableBrainActionListener());
        container.add(this.enableBrainButton);
        
        return container;
    }
    /**
     * invoked when an item is selected by user in BrainComboBox
     */
    private class BrainActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            selectBrain();
        }
    }
    /**
     * invoked when the EnableBrainButton is clicked by user
     */
    private class EnableBrainActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (!isBrainEnabled)
            {
                enableBrainButton.setText("Disable Brain");
            }
            else
            {
                enableBrainButton.setText("Enable Brain");
            }
            
            isBrainEnabled = !isBrainEnabled;
        }
    }
    /**
     * Select the brain.
     */
    public void selectBrain()
    {
        selectedBrain = (Brain) this.brainComboBox.getSelectedItem();
    }
    /**
     * Selects the next piece to use using the random generator set in startGame().
     * 
     * @return - returns the piece after determining what is the best move
     */
    @Override
    public Piece pickNextPiece()
    {
        int pieceNum = (int) (this.pieces.length * this.random.nextDouble());
        
        if (isBrainEnabled)
        {
            int limitHeight = 16;
            this.goal = this.selectedBrain.bestMove(this.board, this.pieces[pieceNum], limitHeight);
        }
        
        return this.pieces[pieceNum];
    }
    /**
     * this overrides the tick method in the super class JTetris
     * adjusts the piece by rotating or moving it horizontally based on the goal move determined by the selectedBrain
     * 
     * @param verb - the action the piece takes
     */
    @Override
    public void tick(int verb)
    {
        System.out.println("Selected Brain: " + selectedBrain + " isBrainEnabled: " + isBrainEnabled);
        
        if (this.isBrainEnabled)
        {
            if(verb == DOWN && this.goal != null)
            {
                int forceMove = -1;
                
                // Keep rotating until we reach the goal move's rotation
                if (this.goal.getPiece().nextRotation() != this.currentPiece.nextRotation())
                {
                    forceMove = ROTATE;
                    tick(forceMove);
                }
                
                // Keep shifting left until we reach the goal move's X-coordinate
                if (this.goal.getX() < this.currentX)
                {
                    forceMove = LEFT;
                    tick(forceMove);
                }
                // Keep shifting right until we reach the goal move's X-coordinate
                else if (this.goal.getX() > this.currentX)
                {
                    forceMove = RIGHT;
                    tick(forceMove);
                }
            }
        }
        
        super.tick(verb);
    }
}
