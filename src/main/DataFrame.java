package main;

import java.util.ArrayList;

public class DataFrame {
	
	private String name;

	private ArrayList<Colonne> colonnes; // changer en tableau associatif

    /**
     * Constructeur qui prend en entrée un tableau de colonnes
     *
     * @param Tableau de colonnes
     */
	public DataFrame(String name, ArrayList<Colonne> colonnes){
		this.name = name;
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

	public ArrayList<Colonne> getColonnes() {
		return colonnes;
	}

	public void setColonnes(ArrayList<Colonne> colonnes) {
		this.colonnes = colonnes;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Affiche tout le dataframe
     *
     */
	public void afficherTout(){
//		System.out.println("DataFrame : "+this.getName());
//		for (int numLigne = 0; numLigne < colonnes.length; i++){
//			Colonne colonneCourante = colonnes[i];
//			System.out.print();
//			System.out.println();
//		}
	}
	
}
