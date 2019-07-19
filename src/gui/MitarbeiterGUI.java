package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MitarbeiterGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	
	
//******************** KONSTRUKTOR ********************
	
	public MitarbeiterGUI() {
		/*@author:		Jakob Kuechler, Soeren Hebestreit
		 *@date: 		xx.xx.2019, 19.07.2019
		 *@description: Mitarbeiter GUI
		 */
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(100, 150, 200));
		getContentPane().setLayout(null);
		//getContentPane().setBackground(new Color(51, 204, 0));
		setBackground(Color.GREEN);
		getContentPane().setForeground(Color.GREEN);
		
		JLabel label = new JLabel("Hauptmenue");
		label.setForeground(new Color(255, 245, 238));
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(83, 37, 238, 45);
		getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 0));
		panel.setForeground(new Color(51, 204, 0));
		panel.setBorder(new LineBorder(new Color(102, 255, 0)));
		panel.setBounds(85, 110, 230, 240);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblArbeitsplanAnsehen = new JLabel("Arbeitsplan ansehen");
		lblArbeitsplanAnsehen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				lblArbeitsplanAnsehen.setForeground(new Color(51, 204, 0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblArbeitsplanAnsehen.setForeground(new Color(0,0,0));
				
			}
		});
		lblArbeitsplanAnsehen.setBounds(6, 20, 200, 17);
		lblArbeitsplanAnsehen.setForeground(Color.WHITE);
		lblArbeitsplanAnsehen.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblArbeitsplanAnsehen);
		
		JLabel label_3 = new JLabel("Programm beenden");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				label_3.setForeground(new Color(51, 204, 0));
				
			}
			public void mouseExited(MouseEvent e) {
				label_3.setForeground(new Color(0,0,0));
			}
		});
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Dialog", Font.BOLD, 14));
		label_3.setBounds(6, 193, 140, 17);
		panel.add(label_3);
		
		JLabel lblArbeitszeitkontoAnsehen = new JLabel("Arbeitszeitkonto ansehen");
		lblArbeitszeitkontoAnsehen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				lblArbeitszeitkontoAnsehen.setForeground(new Color(51, 204, 0));
				
			}
			public void mouseExited(MouseEvent e) {
				lblArbeitszeitkontoAnsehen.setForeground(new Color(0,0,0));
				
			}
		});
		lblArbeitszeitkontoAnsehen.setForeground(Color.WHITE);
		lblArbeitszeitkontoAnsehen.setFont(new Font("Dialog", Font.BOLD, 14));
		lblArbeitszeitkontoAnsehen.setBounds(6, 49, 200, 17);
		panel.add(lblArbeitszeitkontoAnsehen);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("/Users/jakobkuchler/Downloads/HWR-Logo_black_small.png"));
		label_1.setBounds(6, 438, 150, 34);
		getContentPane().add(label_1);
		
		setSize(450, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
