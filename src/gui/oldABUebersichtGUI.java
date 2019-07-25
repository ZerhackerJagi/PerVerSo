package gui;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import logik.Arbeitsbereichverwaltung;

public class oldABUebersichtGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nmb;
	
	public oldABUebersichtGUI() {
		
		
		getContentPane().setBackground(new Color(51, 204, 0));
		getContentPane().setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Arbeitsbereich auswählen");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(50, 25, 303, 54);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 0));
		panel.setBorder(new LineBorder(new Color(102, 255, 0)));
		panel.setBounds(50, 103, 303, 297);
		JScrollPane sp = new JScrollPane(panel);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.setLayout(null);
		sp.setLocation(50, 100);
		sp.setSize(300, 300);
		sp.setViewportView(panel);
		sp.setPreferredSize(new Dimension(300,300));
		
		// dynamisches Erzeugen der Label
		Arbeitsbereichverwaltung av = Arbeitsbereichverwaltung.getInstance();
		JLabel[] lable = new JLabel[Arbeitsbereichverwaltung.getBereiche().size()];
		int k = 5;
		for(int i=0;i<Arbeitsbereichverwaltung.getBereiche().size();i++) {
			lable[i] = new JLabel(Arbeitsbereichverwaltung.getBereiche().get(i).getName());
			lable[i].setForeground(new Color(255,255,255));
			lable[i].setBounds(6, k , 250, 20);
			k+=20;
			nmb = i;
			lable[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// VERKNÃœPFUNG ARBEITSBEREICH VERWALTEN
					setVisible(false);
					dispose();
					
				}
				public void mouseEntered(MouseEvent e) {
					lable[nmb].setForeground(new Color(0,0,0));
					
				}
				public void mouseExited(MouseEvent e) {
					lable[nmb].setForeground(new Color(255,255,255));
					
				}
			});
			panel.add(lable[i]);
		}
		
		
		getContentPane().add(sp);
		setSize(450,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
				
		new oldABUebersichtGUI();
	}
}

