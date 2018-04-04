package main;

import com.sun.org.apache.bcel.internal.generic.Type;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        // Premier tests pour le 1er constructeur
        Cellule cellule01_01 = new Cellule<String>("Paris");
        Cellule cellule01_02 = new Cellule<String>("1500");
        Cellule cellule02_01 = new Cellule<String>("Grenoble");
        Cellule cellule02_02 = new Cellule<String>("300");

        Cellule[] cellules01 = {cellule01_01, cellule02_01};
        Cellule[] cellules02 = {cellule01_02, cellule02_02};
        Colonne colonne01 = new Colonne("Ville", Type.getType("String"), cellules01);
        Colonne colonne02 = new Colonne("Ville", Type.getType("String"), cellules02);
        
        Colonne[] colonnes = {colonne01, colonne02};
        DataFrame dataFrame = new DataFrame(colonnes);
        
        dataFrame.afficher();

    }
}
