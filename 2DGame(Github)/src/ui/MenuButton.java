package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuButton extends JFrame implements ActionListener{

	private JButton start, credit, quit;
	private JPanel panel, creditPanel;
	
	public MenuButton(){
	   
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   this.setLayout(null);
	   this.setSize(640,320);
	   this.setVisible(true);
	   this.add(start);
	   this.add(credit);
	   this.add(quit);
	   

	   // Create icons
	   Icon startIcon = new ImageIcon("res/start.png");
	   Icon hoverstart = new ImageIcon("res/hoverstart.png");
	   Icon creditIcon = new ImageIcon("res/credit.png");
	   Icon hovercredits = new ImageIcon("res/hovercredit.png");
	   Icon quitIcon = new ImageIcon("res/quit.png");
	   Icon hoverquit = new ImageIcon("res/hoverquit.png");
   
		// Create buttons
		start = new JButton(startIcon);
		start.setRolloverIcon(hoverstart);
		credit = new JButton(creditIcon);
		credit.setRolloverIcon(hovercredits);
		quit = new JButton(quitIcon);
		quit.setRolloverIcon(hoverquit);
		
		// create title
//		JLabel title = new JLabel("THIS IS A GAME");
//		title.setFont(new Font("Serif", Font.BOLD, 90));
		
//		//create panel and add + configure components
//		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 20));
//		panel.add(title);
//		start.setPreferredSize(new Dimension(220, 90));
//		panel.add(start);
//		credit.setPreferredSize(new Dimension(220, 90));
//		panel.add(credit);
//		quit.setPreferredSize(new Dimension(220, 90));
//		panel.add(quit);
//   
   
   }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
