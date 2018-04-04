package main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.Type;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Premier tests pour le 1er constructeur
        Cellule cellule01_01 = new Cellule<String>("Paris");
        Cellule cellule01_02 = new Cellule<Integer>(2241346);
        Cellule cellule02_01 = new Cellule<String>("Grenoble");
        Cellule cellule02_02 = new Cellule<Integer>(300);

        ArrayList<Cellule> cellules01 = new ArrayList<>();
        ArrayList<Cellule> cellules02 = new ArrayList<>();
        cellules01.add(cellule01_01);
        cellules02.add(cellule02_01);
        Colonne colonne01 = new Colonne("Ville", Type.getType("String"), cellules01);
        Colonne colonne02 = new Colonne("Population", Type.getType("Integer"), cellules02);
        
        ArrayList<Colonne> colonnes = new ArrayList<>();
        colonnes.add(colonne01);
        colonnes.add(colonne02);
        DataFrame dataFrame = new DataFrame("Population des villes de France", colonnes);
        
        dataFrame.afficherTout();
        dataFrame.getSize();
        dataFrame.selectLines( 0, 1 );
    }
}
