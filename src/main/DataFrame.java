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
			afficher( c.getLabel() + "", 5 );
			afficherTab();
		}
		System.out.print( "\n" );
		System.out.print( "-----------------------------------------------------------------------------\n" );
	}
	
	/***
	 * Affichage des labels des colonnes de la DataFrame
	 * Affichage uniquement des labels compris dans les indices start et end
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
	
	/***
	 * Affichage des labels des colonnes de la DataFrame
	 * Affichage uniquement des colonnes passées en paramètre
	 */
	public void afficherLabels( String... arg )
	{
		System.out.println("DataFrame : "+this.getName());
		System.out.println();
		System.out.print("Ligne\t\t");
		for( String c : arg )
		{
			Colonne col = this.getColonneByName(c);
			if( col != null )
			{
				afficher( col.getLabel() + "", 5 );
				afficherTab();
			}
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
	 * Fonction pour séléctionner et afficher des lignes dans le DataFrame
	 * Affichage à partir des indices des lignes
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
		else if( start > end )
		{
			System.out.println( "Error SelectLignes: L'indice de début ne peut pas dépasser l'indice de fin" );
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
	 * Fonction pour sélectionner et afficher les colonnes
	 * Affichage des colonnes à partir des indices des colonnes
	 * @param start
	 * @param end
	 */
	public void selectColonnes( int start, int end )
	{
		if( start < 0 || end > this.getSizeColonnes() )
		{
			throw new IndexOutOfBoundsException();
		}
		else if( start > end )
		{
			System.out.println( "Error SelectLignes: L'indice de début ne peut pas dépasser l'indice de fin" );
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
	 * Fonction pour sélectioner et afficher les colonnes
	 * Affichage des colonnes à partir du nom de chaque colonne
	 * @param args
	 */
	public void selectColonnes( String... args )
	{
		Colonne colonne;
		afficherLabels( args );
		for( int i = 0; i < this.getSize(); i++ )
		{
			System.out.print( i + "\t\t" );
			for( String s : args )
			{
				colonne = this.getColonneByName( s );
				if( colonne != null )
				{
					afficher( colonne.getCellules().get( i ).getValue() + "", 5 );
					afficherTab();
				}
			}
			System.out.println();
		}
	}
	
	/***
	 * Fonction pour récupérer une colonne à partir de son nom
	 * @param sString le nom de la colonne
	 * @return Null si la colonne n'existe pas ou l'objet de type Colonne
	 */
	public Colonne getColonneByName( String s )
	{
		for( Colonne c : this.getColonnes() )
		{
			if( c.getLabel().toLowerCase().equals( s.toLowerCase() ) )
				return c;
		}
		return null;
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
	
}
