package test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.*;

import com.sun.org.apache.bcel.internal.generic.Type;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class ColonneTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ColonneTest( String testName )
    { 
    		super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ColonneTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testCreateColonne() {
		
    		int result = 0;
    		
		Colonne colonne = createStringColonne();
		
		if ( colonne.getCellules().get(0).getValue() == "Kuala-Lumpur" && colonne.getCellules().get(2).getValue().equals("Singapour")) {
			result = 1;
		}
		
		System.out.println(result);
		assertEquals(1, result);
		System.out.println(result);
    }
    
    
    // -------------------------------------------
    
    public static Colonne createStringColonne() {
		Cellule<String> cellule01 = new Cellule<String>("Kuala-Lumpur"); // Should be false
		Cellule<String> cellule02 = new Cellule<String>("Hanoi");
		Cellule<String> cellule03 = new Cellule<String>("Singapour");
	
		ArrayList<Cellule<?>> cellules = new ArrayList<>();
		cellules.add(cellule01);
		cellules.add(cellule02);
		cellules.add(cellule03);
		
		Colonne colonne01 = new Colonne("City", Type.getType("String"), cellules);
		
		return colonne01;
    }
    
    public static Colonne createIntColonne() {
		Cellule<Integer> cellule01 = new Cellule<Integer>(15);
		Cellule<Integer> cellule02 = new Cellule<Integer>(30);
		Cellule<Integer> cellule03 = new Cellule<Integer>(45);

		ArrayList<Cellule<?>> cellules = new ArrayList<>();
		cellules.add(cellule01);
		cellules.add(cellule02);
		cellules.add(cellule03);

		Colonne colonne01 = new Colonne("CountryCode", Type.getType("Integer"), cellules);
	
		return colonne01;
    }
    
    public static Colonne createEmptyColonne() {
		Cellule<Integer> cellule01 = new Cellule<Integer>(null);
		Cellule<Integer> cellule02 = new Cellule<Integer>(null);

		ArrayList<Cellule<?>> cellules = new ArrayList<>();
		cellules.add(cellule01);
		cellules.add(cellule02);

		Colonne colonne01 = new Colonne("CountryCode", Type.getType("Integer"), cellules);
	
		return colonne01;
    }
    
}
    