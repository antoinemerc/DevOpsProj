package main;

public class DataFrame {
	
	private Colonne[] colonnes; // changer en tableau associatif

    /**
     * Constructeur qui prend en entrée un tableau de colonnes
     *
     * @param Tableau de colonnes
     */
	public DataFrame(Colonne[] colonnes){
		this.colonnes = colonnes;
	}
	
    /**
     * Constructeur qui prend en paramêtre le contenu d'un fichier
     * CSV
     *
     * @param String - nom du fichier CSV
     */
	public DataFrame(String csv){
		// Utilisation du future Parser...
	}

	public Colonne[] getColonnes() {
		return colonnes;
	}

	public void setColonnes(Colonne[] colonnes) {
		this.colonnes = colonnes;
	}
	
	public void afficher(){
		for (int i = 0; i < colonnes.length; i++){
			Colonne colonneCourante = colonnes[i];
			for (int j = 0; j < colonneCourante.getCellules().length; j++){
				System.out.print(colonneCourante.getCellules()[j].getValue() + "\t");
			}
			System.out.println();
		}
	}
	
}
