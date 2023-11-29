package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.LoadSave;

/**
 * 
 * Class to allow you to select multiple characters 
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class CharacterSelection extends JFrame implements ActionListener {

    private JButton finn, fionna;
    private JPanel panel;
    private GameWindow window;

    public CharacterSelection(GameWindow window) {
    	this.window = window;
    	
        this.setTitle("2D JAVA GAME");
        this.setSize(1294, 675);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Initialize the buttons and set up the character selection
        selectYourCharacter();
        // Add the panel to the frame
        this.add(panel);
        this.setVisible(true);
    }

    
    /**
     * Display the characters than can be selected from 
     */
    public void selectYourCharacter() {
        finn = new JButton(new ImageIcon("res/finnCharacterSelection.png"));
        finn.setRolloverIcon(new ImageIcon("res/finnCharacterSelectionRollover.png"));
        finn.setPreferredSize(new Dimension(300, 300));
        finn.addActionListener(this);

        fionna = new JButton(new ImageIcon("res/fionnaCharacterSelection.png"));
        fionna.setRolloverIcon(new ImageIcon("res/fionnaCharacterSelectionRollover.png"));
        fionna.setPreferredSize(new Dimension(300, 300));
        fionna.addActionListener(this);

        panel = new JPanel(null); // Use null layout
        panel.setOpaque(false);

        finn.setBounds(200, 200, 304, 304); // Set absolute position for finn button
        finn.setFocusable(false);
        finn.setOpaque(false);
        finn.setContentAreaFilled(false);
        finn.setBorderPainted(false);
        
        fionna.setBounds(750, 200, 304, 304); // Set absolute position for fionna button
        fionna.setFocusable(false);
        fionna.setOpaque(false);
        fionna.setContentAreaFilled(false);
        fionna.setBorderPainted(false);
        
        finn.addActionListener(this);
        fionna.addActionListener(this);

        panel.add(finn);
        panel.add(fionna);
    }

    
    /**
     * Update the game to load the proper character sprites and animations 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == finn) {
            LoadSave.setSelectedCharacter("finn");
            window.hideMenuPanel();
            new Game(window).getPlayer().reloadAnimation();
        } else if (e.getSource() == fionna) {
            LoadSave.setSelectedCharacter("fionna");
            window.hideMenuPanel();
            new Game(window).getPlayer().reloadAnimation();
        }
    	dispose(); // close character selection window 
    }
    
}
    
    

