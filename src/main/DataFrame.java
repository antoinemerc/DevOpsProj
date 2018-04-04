package main;

public class DataFrame {
	
	private Colonne[] colonnes; // changer en tableau associatif

    /**
     * Constructeur
     *
     * @param 
     */
	public DataFrame(Colonne[] colonnes){
		this.colonnes = colonnes;
	}
	
	public DataFrame(String csv){
		// Utilisation du future Parser...
	}

	public Colonne[] getColonnes() {
		return colonnes;
	}

	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}
	
}
