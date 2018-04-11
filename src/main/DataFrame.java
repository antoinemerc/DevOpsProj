package main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.Type;

import exceptions.ColonneNonCalculableException;
import exceptions.ColonneNonTrouveeException;

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
	
    /**
     * Fonction qui retourne la colonne du label donné en paramètre
     *
     * @param label Nom de la colonne à retourner
     * @throws ColonneNonTrouveeException Si aucune colonne correspond au label donné
     */
	public Colonne getColonne(String label) throws Exception{
		for (int i = 0; i < this.getColonnes().size(); i++){
			if (this.getColonnes().get(i).getLabel().equals(label)){
				return this.getColonnes().get(i);
			}
		}
		throw new ColonneNonTrouveeException("Colonne non trouvé");
	}
	
    /**
     * Fonction qui calcule la moyenne des valeurs d'une colonne
     *
     * @param msg Message à afficher
     * @param limit Nombre de caractères maximale à afficher
     * @throws ColonneNonTrouveeException Si le label donné en paramètre ne correspond
     * à aucune colonne
     * @throws ColonneNonCalculableException Si la colonne n'est pas calculable,
     * c'est-à-dire si elle n'est ni de type Int, ni de type Float
     */
	public float calculerMoyenne(String colonneLabel) throws Exception {
		Colonne colonne = this.getColonne(colonneLabel);
		
		Type type = colonne.getType();
		if (type != Type.FLOAT && type != Type.INT){
			throw new ColonneNonCalculableException(colonneLabel, type);
		}

		Float somme = 0f;
		int nbElements = 0;
		
		for (int i = 0; i < colonne.getCellules().size(); i++, nbElements++){
			somme += Float.valueOf(colonne.getCellules().get(i).getValue().toString());
		}
		return somme/nbElements;
	}
	
}
