package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import _programmstart.Programmstart;
import extern.Datum;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.Urlaubseintrag;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
/*@author:		Soeren Hebestreit
 *@date: 		25.07.2019
 *@description: GUI zur Ansicht des eigenen AZK
 *				1. Soll + Ueberminuten mit Warnung falls nahe/ueber Grenze
 *				2. Urlaub + genommen und die Urlaubseintraege des aktuellen Jahres
 */

public class ShowAzkGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private double grenze = 0.2;
		
//******************** KONSTRUKTOR ********************
	
	public ShowAzkGUI(int PID) {
			
		setIconImages(Programmstart.iconlist);
		
		setSize(360, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		setResizable(false);
		
		Personalverwaltung pv = Personalverwaltung.getInstance();
		Mitarbeiter ma = ((Mitarbeiter) pv.suchen(PID));
		
		JLabel lblAZK = new JLabel("Arbeitszeitkonto");
		lblAZK.setFont(new Font("Dialog", Font.BOLD, 21));
		lblAZK.setForeground(new Color(255, 255, 255));
		lblAZK.setBounds(24, 8, 360, 36);
		getContentPane().add(lblAZK);
		
		JLabel lblName = new JLabel(ma.getVorname()+" "+ma.getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 36, 360, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 360, 64);
		getContentPane().add(rahmenOben);
		
		int y = 40;
		
		JLabel lblSoll = new JLabel("Sollstunden:");
		lblSoll.setBounds(24, y+40, 100, 24);
		getContentPane().add(lblSoll);
		
		JLabel lblSollData = new JLabel(""+ma.getAzk().getSollstunden());
		lblSollData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSollData.setBounds(150, y+40, 100, 24);
		getContentPane().add(lblSollData);
		
		JLabel lblUeber = new JLabel("Überminuten:");
		lblUeber.setBounds(24, y+70, 100, 24);
		getContentPane().add(lblUeber);
		
		JLabel lblUeberData = new JLabel(""+ma.getAzk().getUeberminuten());
		lblUeberData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUeberData.setBounds(150, y+70, 100, 24);
		getContentPane().add(lblUeberData);
		
		JLabel lblWarnung = new JLabel("");
		if (ma.getAzk().getUeberminutenmax()*grenze < ma.getAzk().getUeberminuten()) {
			lblWarnung.setText("Obergrenze Überminuten beachten! ("+ma.getAzk().getUeberminutenmax()+")");		
		} else if (ma.getAzk().getUeberminutenmin()*grenze > ma.getAzk().getUeberminuten()) {
			lblWarnung.setText("Untergrenze Überminuten beachten! ("+ma.getAzk().getUeberminutenmin()+")");
		}
		lblWarnung.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblWarnung.setForeground(new Color(250, 50, 50));
		lblWarnung.setBounds(24, y+100, 300, 24);
		getContentPane().add(lblWarnung);
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, y+136, 360, 4);
		getContentPane().add(rahmenUnten);
		
		JLabel lblResturlaub = new JLabel("Resturlaub:");
		lblResturlaub.setBounds(24, y+156, 100, 24);
		getContentPane().add(lblResturlaub);
		
		int resturlaub = ma.getAzk().getUrlaubbasis();
		ArrayList<Urlaubseintrag> urlaubsliste = new ArrayList<Urlaubseintrag>();
		for (int i = 0; i < ma.getAzk().getListe().size(); i++) {
			if (ma.getAzk().getListe().get(i) instanceof Urlaubseintrag) {
				if (ma.getAzk().getListe().get(i).getStart().getJahr() == (new Datum()).getJahr()) {
					urlaubsliste.add((Urlaubseintrag) ma.getAzk().getListe().get(i));
					resturlaub = resturlaub - ma.getAzk().getListe().get(i).getArbeitstage();
				}
			}
		}
		
		JLabel lblResturlaubData = new JLabel(resturlaub+" Tage");
		lblResturlaubData.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblResturlaubData.setBounds(150, y+156, 100, 24);
		getContentPane().add(lblResturlaubData);	
		
		JLabel lblUrlaub = new JLabel("Urlaub");
		lblUrlaub.setBounds(24, y+186, 100, 24);
		getContentPane().add(lblUrlaub);

		JLabel[][] lable = new JLabel[urlaubsliste.size()][3];
		int k = y+216;
		for(int i=0 ; i < urlaubsliste.size(); i++) {
			lable[i][0] = new JLabel(""+urlaubsliste.get(i).getStart());
			lable[i][0].setFont(new Font("Dialog", Font.PLAIN, 12));
			lable[i][0].setBounds(50, k , 100, 24);
			lable[i][1] = new JLabel("bis");
			lable[i][1].setFont(new Font("Dialog", Font.PLAIN, 12));
			lable[i][1].setBounds(150, k , 40, 24);
			lable[i][2] = new JLabel(""+urlaubsliste.get(i).getEnde());
			lable[i][2].setFont(new Font("Dialog", Font.PLAIN, 12));
			lable[i][2].setBounds(200, k , 100, 24);
			k+=24;
			getContentPane().add(lable[i][0]);
			getContentPane().add(lable[i][1]);
			getContentPane().add(lable[i][2]);
		}		
		
		setVisible(true);
	}
}
