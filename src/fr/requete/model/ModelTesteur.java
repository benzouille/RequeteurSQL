package fr.requete.model;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

//CTRL + SHIFT + O pour générer les imports
public class ModelTesteur {

	private Long temps;
	private int lignes;
	private String requete = "SELECT * FROM professeur ORDER BY 1 ASC";
	private String[] titre;
	private Object[][] donnee;
	private boolean erreur = false;
	
	/**
	 * Insertion de la requete sql 
	 * @param requete
	 */
	public ModelTesteur() {
		long startTime = System.currentTimeMillis();
		
		try {
			//Création d'un objet Statement
			Statement state = Connecter.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			//Recuperation des datas
			ResultSet result = state.executeQuery(requete) ;

			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

			//recuperation du nombre de ligne
			result.last();
			lignes = result.getRow();

			//retour au debut pour lire les donnees et les meta
			result.beforeFirst();

			//On recupere le nom des colonnes
			titre = new String[resultMeta.getColumnCount()];
			for(int i = 0; i < titre.length; i++) {
				titre[i] = resultMeta.getColumnName(i+1);
				System.out.print(titre[i].toString()); //DEBUG------------------------------------------------
				//grosse difficulté à comprendre que la boucle for commence à 1 
				//alors que dans la boucle for le tableau titre commence à -1	
			}
			System.out.println("\n"); //DEBUG------------------------------------------------

			result.beforeFirst();
			
			//On recupere les donnee du tableau
			int i = 0;
			donnee = new Object[lignes+1][titre.length+1];
			result.next();
			while(i < lignes){
				donnee[i] = new Object[titre.length];
				for(int j = 0 ; j < titre.length  ; j++) {					
					donnee[i][j] = result.getObject(j+1);
					System.out.print(result.getObject(j+1).toString()); //DEBUG------------------------------------------------
				}
				i++;
				result.next();
			}

			result.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("block catch"); //DEBUG------------------------------------------------
			JOptionPane.showMessageDialog(null, "Erreur ! \n Requête SQL non valide.", "ERREUR", JOptionPane.ERROR_MESSAGE);
			erreur = true;	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			System.out.println("block finally"); //DEBUG------------------------------------------------
			long tempsTotal = System.currentTimeMillis() - startTime;
			temps = tempsTotal;
			System.out.println("temps en ms : "+temps+" et affiche " +lignes+" ligne(s)"); //DEBUG------------------------------------------------
		}
	}

	//SETTER GUETTER
	public long getTemps() {return temps;}
	public void setTemps(long temps) {this.temps = temps;}

	public long getLignes() {return lignes;}
	public void setLignes(int lignes) {this.lignes = lignes;}

	public String[] getTitre() {return titre;}
	public void setTitre(String[] titre) {this.titre = titre;}

	public Object[][] getDonnee() {return donnee;}
	public void setDonnee(Object[][] donnee) {this.donnee = donnee;}

	public boolean isErreur() {return erreur;}
	public void setErreur(boolean erreur) {this.erreur = erreur;}

	public String getRequete() {return requete;}
	public void setRequete(String requete) {this.requete = requete;}

	//public static void main(String[] args) {ModelTesteur mt = new ModelTesteur("SELECT * FROM professeur ORDER BY 1 ASC");}
}
