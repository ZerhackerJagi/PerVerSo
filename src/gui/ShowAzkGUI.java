package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import extern.Datum;
import logik.Mitarbeiter;
import logik.Personalverwaltung;
import logik.Urlaubseintrag;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class ShowAzkGUI extends JFrame{
	
//******************** PARAMETER ********************

	private static final long serialVersionUID = 1L;
	private double grenze = 0.2;
		
//******************** KONSTRUKTOR ********************
	
	public ShowAzkGUI(int PID) {
		/*@author:		Soeren Hebestreit
		 *@date: 		20.07.2019
		 *@description: GUI zur Ansicht des eigenen AZK
		 *				1. Soll + Ueberminuten mit Warnung falls nahe/ueber Grenze
		 *				2. Urlaub + genommen und die Urlaubseintraege des aktuellen Jahres
		 */
		
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
		lblAZK.setBounds(24, 20, 360, 24);
		getContentPane().add(lblAZK);
		
		JLabel lblName = new JLabel(ma.getVorname()+" "+ma.getName());
		lblName.setFont(new Font("Dialog", Font.BOLD, 21));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(24, 44, 360, 24);
		getContentPane().add(lblName);
		
		JPanel rahmenOben = new JPanel();
		rahmenOben.setBackground(new Color(100, 150, 200));
		rahmenOben.setBounds(0, 0, 360, 88);
		getContentPane().add(rahmenOben);
		
		JLabel lblSoll = new JLabel("Sollstunden:");
		lblSoll.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSoll.setBounds(24, 100, 100, 24);
		getContentPane().add(lblSoll);
		
		JLabel lblSollData = new JLabel(""+ma.getAzk().getSollstunden());
		lblSollData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSollData.setBounds(160, 100, 100, 24);
		getContentPane().add(lblSollData);
		
		JLabel lblUeber = new JLabel("Überminuten:");
		lblUeber.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUeber.setBounds(24, 124, 100, 24);
		getContentPane().add(lblUeber);
		
		JLabel lblUeberData = new JLabel(""+ma.getAzk().getUeberminuten());
		lblUeberData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUeberData.setBounds(160, 124, 100, 24);
		getContentPane().add(lblUeberData);
		
		if (ma.getAzk().getUeberminutenmax()*grenze < ma.getAzk().getUeberminuten()) {
			JLabel lblWarnung = new JLabel("Obergrenze Überminuten beachten!");
			lblWarnung.setFont(new Font("Dialog", Font.BOLD, 14));
			lblWarnung.setForeground(new Color(250, 50, 50));
			lblWarnung.setBounds(24, 160, 300, 24);
			getContentPane().add(lblWarnung);
		} else if (ma.getAzk().getUeberminutenmin()*grenze > ma.getAzk().getUeberminuten()) {
			JLabel lblWarnung = new JLabel("Untergrenze Überminuten beachten!");
			lblWarnung.setFont(new Font("Dialog", Font.BOLD, 14));
			lblWarnung.setForeground(new Color(250, 50, 50));
			lblWarnung.setBounds(24, 160, 300, 24);
			getContentPane().add(lblWarnung);
		}
		
		JPanel rahmenUnten = new JPanel();
		rahmenUnten.setBackground(new Color(100, 150, 200));
		rahmenUnten.setBounds(0, 200, 360, 4);
		getContentPane().add(rahmenUnten);
		
		ArrayList<Urlaubseintrag> urlaubsliste = new ArrayList<Urlaubseintrag>();
		for (int i = 0; i < ma.getAzk().getListe().size(); i++) {
			if (ma.getAzk().getListe().get(i) instanceof Urlaubseintrag) {
				if (ma.getAzk().getListe().get(i).getStart().getJahr() == (new Datum()).getJahr()) {
					urlaubsliste.add((Urlaubseintrag) ma.getAzk().getListe().get(i));
				}
			}
		}	
		
		JLabel lblUrlaub = new JLabel("Urlaub:");
		lblUrlaub.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUrlaub.setBounds(24, 220, 100, 24);
		getContentPane().add(lblUrlaub);

		JLabel[][] lable = new JLabel[urlaubsliste.size()][3];
		int k = 264;
		for(int i=0 ; i < urlaubsliste.size(); i++) {
			lable[i][0] = new JLabel(""+urlaubsliste.get(i).getStart());
			lable[i][0].setBounds(50, k , 100, 24);
			lable[i][1] = new JLabel("bis");
			lable[i][1].setBounds(150, k , 40, 24);
			lable[i][2] = new JLabel(""+urlaubsliste.get(i).getEnde());
			lable[i][2].setBounds(200, k , 100, 24);
			k+=24;
			getContentPane().add(lable[i][0]);
			getContentPane().add(lable[i][1]);
			getContentPane().add(lable[i][2]);
		}		

		JLabel lblResturlaub = new JLabel("Resturlaub:");
		lblResturlaub.setFont(new Font("Dialog", Font.BOLD, 14));
		lblResturlaub.setBounds(24, k+20, 100, 24);
		getContentPane().add(lblResturlaub);
		
		JLabel lblResturlaubData = new JLabel((ma.getAzk().getUrlaubskontingent()-ma.getAzk().getUrlaubgenommen())+" Tage");
		lblResturlaubData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblResturlaubData.setBounds(160, k+20, 100, 24);
		getContentPane().add(lblResturlaubData);
		
		setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
				
		new ShowAzkGUI(1);
	}
}
