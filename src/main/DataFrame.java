package main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.Type;

import exceptions.ColonneNonCalculableException;
import exceptions.ColonneNonTrouveeException;

public class DataFrame {
	
	private String name;
	private ArrayList<Colonne> colonnes;

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
		ParserCsv parsing = new ParserCsv(this, csv);
		this.name = parsing.getName();
		this.colonnes = parsing.getAllColumn();
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
		return getColonnes().get(0).getCellules().size();
	}
	
	/***
	 * Affichage des labels des colonnes de la DataFrame
	 */
	public void afficherLabels()
	{
		System.out.println("DataFrame : "+this.getName());
		System.out.println();
		System.out.print("Ligne\t\t");
		for( Colonne c : this.getColonnes() )
		{
			afficher( c.getLabel() + "", 5 );
			afficherTab();
		}
		System.out.print( "\n" );
		System.out.print( "-----------------------------------------------------------------------------\n" );
	}
	
	/***
	 * Affichage des labels des colonnes de la DataFrame
	 */
	public void afficherLabels(int start, int end )
	{
		System.out.println("DataFrame : "+this.getName());
		System.out.println();
		System.out.print("Ligne\t\t");
		for( int i = start; i < end; i++  )
		{
			afficher( this.getColonnes().get( i ).getLabel() + "", 5 );
			afficherTab();
		}
		System.out.print( "\n" );
		System.out.print( "----------------------------------------------------------------------\n" );
	}
	
    /**
     * Affiche tout le dataframe
     *
     */
	public void afficherTout(){
        afficherLabels();
        // Affichage des données
        for (int numLigne = 0; numLigne < this.getNbLignes(); numLigne++){
            // Affichage des lignes
            System.out.print(numLigne+"\t\t");
            for( Colonne c : this.getColonnes() )
            {
                afficher( c.getCellules().get( numLigne ).getValue() + "", 5 );
                afficherTab();
            }
            System.out.println();
        }
    }

	/***
	 * Fonction pour calculer le nombre des lignes du DataFrame
	 * @return
	 */
	public int getSize()
	{
		int size = -1;
		
		for( Colonne c : this.getColonnes() )
		{
			if( c.getCellules().size() > size )
				size = c.getCellules().size();
		}
		
		return size;
	}
	
	/***
	 * Fonction pour calculer le nombre de colonnes du DataFrale
	 * @return
	 */
	public int getSizeColonnes()
	{	
		return this.getColonnes().size();
	}
	
	
	/***
	 * Fonction pour séléctionner des lignes dans le DataFrame
	 * @param start
	 * @param end
	 */
	public void selectLignes( int start, int end )
	{
		// On vérifie que les indices existent dans le DataFrame
		if( start < 0 || end > this.getSize() )
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			afficherLabels();
			for( int i = start; i <= end; i++ )
			{
				System.out.print( i + "\t\t");
				for( Colonne c : this.getColonnes() )
				{
					afficher( c.getCellules().get( i ).getValue() + "", 5 );
					afficherTab();
				}
				System.out.print("\n");
			}
		}
	}
	
	/***
	 * Fonction pour sélectionner les colonnes
	 * @param start
	 * @param end
	 */
	public void selectColonnes( int start, int end )
	{
		if( start < 0 || end > this.getSizeColonnes() )
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			afficherLabels( start, end );
			for( int i = 0; i < this.getSize(); i++ )
			{
				System.out.print( i + "\t\t" );
				//System.out.print( this.getColonnes().get( i ).getLabel() );
				for( int j = start; j < end; j++ )
				{
					afficher( this.getColonnes().get(j).getCellules().get(i).getValue() + "", 5);
					afficherTab();
				}
				System.out.print("\n");
			}
		}
	}
	
	
	/***
	 * Afficher Tabulations
	 */
	public void afficherTab()
	{
		System.out.print( "\t\t" );
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
     * @return Colonne au label donné en paramètre
     */
	public Colonne getColonne(String label) throws Exception{
		for (int i = 0; i < this.getColonnes().size(); i++){
			if (this.getColonnes().get(i).getLabel().equals(label)){
				return this.getColonnes().get(i);
			}
		}
		throw new ColonneNonTrouveeException(label);
	}
	
    /**
     * Fonction qui calcule la moyenne des valeurs d'une colonne
     *
     * @param colonneLabel Nom de la colonne à calculer
     * @throws ColonneNonTrouveeException Si le label donné en paramètre ne correspond
     * à aucune colonne
     * @throws ColonneNonCalculableException Si la colonne n'est pas calculable,
     * c'est-à-dire si elle n'est ni de type int, ni de type Float
     * @return moyenne des valeurs de la colonne au label passé en paramètre
     */
	public float calculerMoyenne(String colonneLabel) throws Exception {
		Colonne colonne = this.getColonne(colonneLabel);
		
		Type type = colonne.getType();
		if (type != Type.FLOAT && type != Type.INT){
			throw new ColonneNonCalculableException(colonneLabel, type);
		}

		Float somme = 0f;
		int nbElements = 0;
		
		for (int i = 0; i < colonne.getCellules().size(); i++){
			if (colonne.getCellules().get(i).getValue() != null){
				somme += Float.valueOf(colonne.getCellules().get(i).getValue().toString());
				nbElements++;
			}
		}
		return somme/nbElements;
	}
	
    /**
     * Fonction qui calcule le minimum des valeurs d'une colonne
     *
     * @param colonneLabel Nom de la colonne à calculer
     * @throws ColonneNonTrouveeException Si le label donné en paramètre ne correspond
     * à aucune colonne
     * @throws ColonneNonCalculableExceptinullon Si la colonne n'est pas calculable,
     * c'est-à-dire si elle n'est ni de type int, ni de type Float
     * @return Minimum des valeurs de la colonne au label passé en paramètre
     */
	public float calculerMinimum(String colonneLabel) throws Exception {
		Colonne colonne = this.getColonne(colonneLabel);
		
		Type type = colonne.getType();
		if (type != Type.FLOAT && type != Type.INT){
			throw new ColonneNonCalculableException(colonneLabel, type);
		}

		Float min = Float.MAX_VALUE;
		
		for (int i = 0; i < colonne.getCellules().size(); i++){
			if (colonne.getCellules().get(i).getValue() != null && min > Float.valueOf(colonne.getCellules().get(i).getValue().toString())){
				min = Float.valueOf(colonne.getCellules().get(i).getValue().toString());
			}
		}
		return min;
	}
	
    /**
     * Fonction qui calcule le maximum des valeurs d'une colonne
     *
     * @param colonneLabel Nom de la colonne à calculer
     * @throws ColonneNonTrouveeException Si le label donné en paramètre ne correspond
     * à aucune colonne
     * @throws ColonneNonCalculableException Si la colonne n'est pas calculable,
     * c'est-à-dire si elle n'est ni de type Int, ni de type Float
     * @return Maximum des valeurs de la colonne au label passé en paramètre
     */
	public float calculerMaximum(String colonneLabel) throws Exception {
		Colonne colonne = this.getColonne(colonneLabel);
		
		Type type = colonne.getType();
		if (type != Type.FLOAT && type != Type.INT){
			throw new ColonneNonCalculableException(colonneLabel, type);
		}

		Float max = 0f;
		
		for (int i = 0; i < colonne.getCellules().size(); i++){
			if (colonne.getCellules().get(i).getValue() != null && max < Float.valueOf(colonne.getCellules().get(i).getValue().toString())){
				max = Float.valueOf(colonne.getCellules().get(i).getValue().toString());
			}
		}
		return max;
	}
	
}
