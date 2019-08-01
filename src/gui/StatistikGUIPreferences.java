package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class StatistikGUIPreferences extends JFrame{
	public StatistikGUIPreferences() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 430, 373);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 30, 450, 5);
		panel_1.setBackground(new Color(100, 150, 200));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEinstellungenFrDie = new JLabel("Einstellungen f\u00FCr die Statistik");
		lblEinstellungenFrDie.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEinstellungenFrDie.setBounds(113, 0, 214, 25);
		panel.add(lblEinstellungenFrDie);
		
		JLabel lblStandardwerteFestlegen = new JLabel("Standardwerte festlegen ( SOLL WERTE - bspw. 0.5 = 50%)");
		lblStandardwerteFestlegen.setBounds(10, 59, 388, 28);
		panel.add(lblStandardwerteFestlegen);
		
		JLabel lblSoll = new JLabel("%-Anteil m\u00E4nnliche MA");
		lblSoll.setBounds(10, 95, 145, 28);
		panel.add(lblSoll);
		
		JLabel lblanteilWeiblicheMa = new JLabel("%-Anteil weibliche MA");
		lblanteilWeiblicheMa.setBounds(10, 131, 145, 28);
		panel.add(lblanteilWeiblicheMa);
		
		JLabel lblanteilDiverseMa = new JLabel("%-Anteil diverse MA");
		lblanteilDiverseMa.setBounds(10, 170, 145, 28);
		panel.add(lblanteilDiverseMa);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(100, 150, 200));
		panel_2.setBounds(0, 293, 450, 5);
		panel.add(panel_2);
		
		JButton btnOkSpeichern = new JButton("Ok - Speichern");
		btnOkSpeichern.setBounds(61, 320, 122, 23);
		panel.add(btnOkSpeichern);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(245, 320, 122, 23);
		panel.add(btnAbbrechen);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
