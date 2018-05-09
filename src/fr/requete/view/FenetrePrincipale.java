package fr.requete.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import fr.requete.model.ModelTesteur;
import fr.requete.model.TestTab;

public class FenetrePrincipale extends JFrame {
	//Model
	private ModelTesteur mt = new ModelTesteur();
	//private TestTab tt = new TestTab();
	//JPanel
	private JPanel container = new JPanel();
	private JPanel sousContainer = new JPanel();
	private JTextArea textRequete = new JTextArea("Entrez votre requête SQL");
	private JLabel timeRequete = new JLabel("Durée de la requête");
	private JTable tabResult;
	//JToolBar
	private JToolBar toolBar = new JToolBar();
	private Run run = new Run();
	private JButton exe = new JButton(new ImageIcon("ressources/images/play.png"));
	
	//Boolean
	//private Boolean effaceText = true;

	public FenetrePrincipale() {
		this.setTitle("Testeur de rêquetes");
		this.setSize(1024, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		
		initFenetre();
		initTableau();
		initToolBar();
		this.setVisible(true);
	}

	public void initFenetre() {
		//textRequete
		textRequete.setPreferredSize(new Dimension(1004, 200));
		textRequete.addMouseListener( new  MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				//TODO ne fonctione pas pour garder le text une fois cliqué une premiere fois, peut être essayer de comparer le texte inscrit avec le texte initial  
				//if (effaceText = true) {
					//effaceText = false;
					//textRequete.setText(""); 	
				//}
			}

		});
		//textRequete
		sousContainer.add(textRequete, BorderLayout.NORTH);

		//timeRequete
		timeRequete.setOpaque(true);
		timeRequete.setPreferredSize(new Dimension(1004, 25));
		container.add(timeRequete, BorderLayout.SOUTH);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(sousContainer);

		this.setContentPane(container);
		
		System.out.println("c'est l'initFenetre"); //DEBUG------------------------------------------------
	}

	public void initTableau() {
		//tabResult

		tabResult = new JTable(mt.getDonnee(), mt.getTitre());
		tabResult.setPreferredSize(new Dimension(1004, 400));
		sousContainer.add(new JScrollPane(tabResult), BorderLayout.CENTER);
		System.out.println("c'est l'initTableau"); //DEBUG------------------------------------------------
	}

	public void initToolBar() {
		toolBar.setPreferredSize(new Dimension(1004,50));
		exe.setSize(new Dimension(50,50));
		exe.addActionListener(run);
		exe.setBackground(Color.GRAY);
		toolBar.add(exe);
		add(toolBar, BorderLayout.NORTH);
	}
/**
 * Rafrechit l'interface graphique
 */
	public void refresh() {
		if(mt.isErreur()) {reset();}
		else {
			this.
			removeAll();
			
			String textFieldValue = textRequete.getText();
			System.out.println(textFieldValue); //DEBUG------------------------------------------------
			mt.requete(textFieldValue);
			String time = ("La requête a été retournée en "+mt.getTemps()+ " ms et a retournée "+mt.getLignes()+" ligne(s)");
			timeRequete.setText(time);
			initFenetre();
			initTableau();
			System.out.println("c'est le refresh"); //DEBUG------------------------------------------------
			String []titreCol = mt.getTitre();
			System.out.println("titre des colones"+titreCol.toString()); //DEBUG------------------------------------------------
			
			revalidate();	
		}
	}

	public void reset() {
		removeAll();
		initFenetre();
		revalidate();
	}

	class Run implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			refresh();
		}
	}     

	public static void main(String[] args) {
		FenetrePrincipale fp = new FenetrePrincipale();
	}
}
