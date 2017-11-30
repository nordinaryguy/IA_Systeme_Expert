package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

//Classe pour l'interface graphique utilisateur 
public class SEGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624870242645119750L;
	
	
	JFrame MainFrame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu pointInterrogation = new JMenu("?");
	

	private JMenuItem ouvrir = new JMenuItem("Ouvrir");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenuItem aPropos = new JMenuItem("A propos...");

	public SEGUI() {

		//Définit un titre pour notre fenêtre
		this.setTitle("Intelligence Artificielle - Système Expert");
		//Définit sa taille : 400 pixels de large et 100 pixels de haut
		this.setSize(1000, 700);
		//Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Impossible de redimensionner la fenêtre : 
		this.setResizable(false);
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//JMenuBar
		//On initialise nos menus      
		this.fichier.add(ouvrir);

		//Ajout d'un séparateur
		this.fichier.addSeparator();
		
		this.pointInterrogation.add(aPropos);
		
		//Fermeture du programme si on clique sur Fermer
		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}        
		});
		
		this.fichier.add(fermer);
		
		this.aPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Intelligence Artificielle - Système Expert - All Rights Reserved - Created by Nordinaryguy", "A propos", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		

		//L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
		//Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
		this.menuBar.add(fichier);
		this.menuBar.add(pointInterrogation);
		this.setJMenuBar(menuBar);

		//Et enfin, la rendre visible        
		this.setVisible(true);
	}
}
