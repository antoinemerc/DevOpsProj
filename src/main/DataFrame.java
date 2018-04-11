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
			System.out.print( c.getLabel() + "\t\t" );
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
			System.out.print( this.getColonnes().get( i ).getLabel() + "\t\t" );
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
        for (int numLigne = 0; numLigne <= this.getNbLignes(); numLigne++){
            // Affichage des lignes
            System.out.print(numLigne+"\t\t");
            for( Colonne c : this.getColonnes() )
            {
                System.out.print( c.getCellules().get( numLigne ).getValue() + "\t\t" );
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
						System.out.print( c.getCellules().get( i ).getValue() + "\t\t" );
					
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
					System.out.print( this.getColonnes().get(j).getCellules().get(i).getValue() + "\t\t");
				}
				System.out.print("\n");
			}
		}
	}
	
}
