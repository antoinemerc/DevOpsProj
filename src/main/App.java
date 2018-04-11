package main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.Type;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // Premier DataFrame de test avec le 1er constructeur
    	ArrayList<Cellule> colonne01 = new ArrayList<>();
    	ArrayList<Cellule> colonne02 = new ArrayList<>();
    	ArrayList<Cellule> colonne03 = new ArrayList<>();

        ArrayList<Colonne> colonnes = new ArrayList<>();
        colonnes.add(new Colonne("Pays", Type.getType("String"), colonne01));
        colonnes.add(new Colonne("Population", Type.getType("Integer"), colonne02));
        colonnes.add(new Colonne("Département", Type.getType("String"), colonne03));
        DataFrame dataFrame = new DataFrame("Villes de France", colonnes);
        
        // Données
        colonne01.add(new Cellule<String>("Paris"));
        colonne01.add(new Cellule<String>("Grenoble"));
        colonne01.add(new Cellule<String>("Lyon"));
        colonne01.add(new Cellule<String>("Saint-Etienne"));
        colonne01.add(new Cellule<String>("Meylan"));

        colonne02.add(new Cellule<Integer>(2241346));
        colonne02.add(new Cellule<Integer>(664832));
        colonne02.add(new Cellule<Integer>(2265375));
        colonne02.add(new Cellule<Integer>(172023));
        colonne02.add(new Cellule<Integer>(null));
        
        colonne03.add(new Cellule<String>("Paris(dept)"));
        colonne03.add(new Cellule<String>("Isère"));
        colonne03.add(new Cellule<String>("Rhône"));
        colonne03.add(new Cellule<String>("Loire"));
        colonne03.add(new Cellule<String>("Isère"));
        
        dataFrame.afficherTout();
        //System.out.println( dataFrame.getSizeColonnes() );
        System.out.println();
        System.out.println();
        //dataFrame.selectColonnes( "pays", "départem" );
        //dataFrame.selectColonnes( 0, 2 );
        dataFrame.afficherLignes(0, 2);
        System.out.println();
        DataFrame hey = dataFrame.selectLignes( 0, 2 );
        hey.afficherTout();
        //newData.afficherTout();
        //System.out.println("Moyenne des populations : " + dataFrame.calculerMoyenne("Population"));
        //System.out.println("Population minimale : " + dataFrame.calculerMinimum("Population"));
        //System.out.println("Population maximale : " + dataFrame.calculerMaximum("Population"));
    }
}
