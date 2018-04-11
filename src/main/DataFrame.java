package main;

import java.util.ArrayList;

public class DataFrame {
	
	private String name;
	private ArrayList<Colonne> colonnes;
	private int nbLignes;

    /**
     * Constructeur qui prend en entrée un tableau de colonnes
     *
     * @param Tableau de colonnes
     */
	public DataFrame(String name, ArrayList<Colonne> colonnes){
		this.name = name;
		this.colonnes = colonnes;
		this.nbLignes = colonnes.size();
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
	
    public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}
	
    /**
     * Affiche tout le dataframe
     *
     */
	public void afficherTout(){
		System.out.println("DataFrame : "+this.getName());
		// Affichage des labels
		System.out.print("Ligne \t\t");
		for (int numColonne = 0; numColonne < this.getColonnes().size(); numColonne++){
			afficher(colonnes.get(numColonne).getLabel(), 5);
			System.out.print("\t\t");
		}
		System.out.println();
		// Affichage des données
		for (int numLigne = 0; numLigne <= this.getNbLignes(); numLigne++){
			// Affichage des lignes
			System.out.print(numLigne+"\t\t");
			for( Colonne c : this.getColonnes() )
			{
				afficher( c.getCellules().get( numLigne ).getValue().toString(),5);
				System.out.print("\t\t");
			}
			System.out.println();
		}
	}
	
    /**
     * Fonction qui limite la taille du mot affiché
     *
     * @param msg Message à afficher
     * @param limit Nombre de caractères maximale à afficher
     */
	public void afficher(String msg, int limit){
		System.out.print(msg.substring(0, Math.min(limit, msg.length())));
		if (msg.length() > limit){
			System.out.print("..");
		}
	}
	
}
