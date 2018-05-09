package fr.requete.model;

public class TestTab {
	private Object[][] donnee = {
			{"Cysboy", "28 ans", "1.80 m","bla","blou","blu"},
			{"BZHHydde", "28 ans", "1.80 m","bla","blou","blu"},
			{"IamBow", "24 ans", "1.90 m","bla","blou","blu"},
			{"FunMan", "32 ans", "1.85 m","bla","blou","blu"}
	};
	 private String  titre[] = {"Pseudo", "Age", "Taille","bla","blou","blu"};
	
	public TestTab() {
		
	}

	public Object[][] getDonnee() {return donnee;}
	public void setDonnee(Object[][] donnee) {this.donnee = donnee;}

	public String[] getTitre() {return titre;}
	public void setTitre(String[] titre) {this.titre = titre;}
	
	
	
	
}
