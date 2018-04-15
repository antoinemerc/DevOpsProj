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
public class DataFrameTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataFrameTest( String testName )
    { 
    		super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataFrameTest.class );
    }

    // --- DataFrame Creation Test ---
    
    public void testCreateDataframe() {
    	
		int result = 0;
	
		DataFrame dataframe = createDataframe();
		
	
		if ( dataframe.getColonnes().get(0).getCellules().get(0).getValue().equals("Kuala-Lumpur") && dataframe.getColonnes().get(1).getCellules().get(2).getValue().equals(45) ) {
			result = 1;
		}
		
		assertEquals(1, result);
    }
    
    
    // --- Method Tests ---
    
    public void testGetColonnes() {
    	
		int result = 0;
	
		DataFrame dataframe = createDataframe();

			if ( dataframe.getColonnes().get(1).getCellules().get(0).getValue().equals(15) && dataframe.getColonnes().get(1).getCellules().get(2).getValue().equals(45) ) {
				result = 1;
			}
			assertEquals(1, result);
    }
    
    public void testGetColonne() {
    	
		DataFrame dataframe = createDataframe();
		
			try {
				if ( !(dataframe.getColonne("colonne2").equals(dataframe.getColonnes().get(1)))) {
					assert(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
    
    
    public void testCalculerMoyenne() {
    	
		int result = 0;
		float mean = 30;
	
		DataFrame dataframe = createDataframe();

			try {
				if ( dataframe.calculerMoyenne("colonne2") == mean ) {
					result = 1;
				}
			} catch (Exception e) {
				assertEquals(1, result);
				e.printStackTrace();
			}
			assertEquals(1, result);
    }
    
    
    public void testCalculerMinimum() {
    	
		int result = 0;
		float min = 15;
	
		DataFrame dataframe = createDataframe();

			try {
				if ( dataframe.calculerMinimum("colonne2") == min ) {
					result = 1;
				}
			} catch (Exception e) {
				assertEquals(1, result);
				e.printStackTrace();
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMaximum() {
    	
		int result = 0;
		float max = 45;
	
		DataFrame dataframe = createDataframe();

			try {
				if ( dataframe.calculerMaximum("colonne2") == max ) {
					result = 1;
				}
			} catch (Exception e) {
				assertEquals(1, result);
				e.printStackTrace();
			}
			assertEquals(1, result);
    }
    
    public void testGetSize() {
    		DataFrame dataframe = createDataframe();
    		
    		if (dataframe.getSize() != 3) {
    			assert(false);
    		}
    }
    
    public void testSelectLignes() {
		DataFrame dataframe = createDataframe();
		
		if (dataframe.getSizeColonnes() != 2) {
			assert(false);
		}
    }
    
    
    // --- Method Tests For Particular Cases ---
    
    public void testCalculerMoyenneWrongType() {
    	
		int result = 1;
		float mean = 30;
	
		DataFrame dataframe = createDataframe();

			try {
				if ( dataframe.calculerMoyenne("colonne1") == mean ) {
					result = 0;
				}
			} catch (Exception e) {
				assertEquals(1, result);
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMinimumWrongType() {
    	
		int result = 1;
		float min = 15;
	
		DataFrame dataframe = createDataframe();

			try {
				if ( dataframe.calculerMinimum("colonne1") == min ) {
					result = 0;
				}
			} catch (Exception e) {
				assertEquals(1, result);
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMaximumWrongType() {
    	
		int result = 1;
		float max = 45;
	
		DataFrame dataframe = createDataframe();

			try {
				if ( dataframe.calculerMaximum("colonne1") == max ) {
					result = 0;
				}
			} catch (Exception e) {
				assertEquals(1, result);
			}
			assertEquals(1, result);
    }
    
    
    public void testCalculerMoyenneEmptyCol() {
    	
		int result = 1;
		float mean = 30;
	
		DataFrame dataframe = createEmptyDataframe();

			try {
				if ( dataframe.calculerMoyenne("colonne1") == mean ) {
					result = 0;
				}
			} catch (Exception e) {
				assertEquals(1, result);
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMinimumEmptyCol() {
    	
		int result = 1;
		float min = 15;
	
		DataFrame dataframe = createEmptyDataframe();

			try {
				if ( dataframe.calculerMinimum("colonne1") == min ) {
					result = 0;
				}
			} catch (Exception e) {
				assertEquals(1, result);
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMaximumEmptyCol() {

    	
		int result = 1;
		float max = 45;
	
		DataFrame dataframe = createEmptyDataframe();

			try {
				if ( dataframe.calculerMaximum("colonne1") == max ) {
					result = 0;
				}
			} catch (Exception e) {
				assertEquals(1, result);
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMoyenneNonExistingCol() {
    	
		int result;
	
		DataFrame dataframe = createEmptyDataframe();

			try {
				dataframe.calculerMoyenne("wrongColonne");
				result = 0;
				
			} catch (Exception e) {
				result = 0;
			}
			assertEquals(1, result);
    }
    
    public void testCalculerMinimumNonExistingCol() {
    	
		int result;
	
		DataFrame dataframe = createEmptyDataframe();

			try {
				dataframe.calculerMinimum("wrongColonne");
				result = 0;
				
			} catch (Exception e) {
				result = 0;
			}
			assertEquals(1, result);
    }

    public void testCalculerMaximumNonExistingCol() {
    	
		int result;
	
		DataFrame dataframe = createEmptyDataframe();

			try {
				dataframe.calculerMaximum("wrongColonne");
				result = 0;
				
			} catch (Exception e) {
				result = 0;
			}
			assertEquals(1, result);
    }
    
    public void testGetSizeEmpty() {
		DataFrame dataframe = createEmptyDataframe();
		
		if (dataframe.getSize() != 2) {
			assert(false);
		}
	}
	
	public void testGetSizeColonnesEmpty() {
		DataFrame dataframe = createEmptyDataframe();
		
		if (dataframe.getSizeColonnes() != 2) {
			assert(false);
		}
	}
    
    public void testGetWrongColonne() {
    	
		DataFrame dataframe = createDataframe();
		
		int result;
		
			try {
				dataframe.getColonne("colonne3");
				result = 0;
			} catch (Exception e) {
				result = 1;
				e.printStackTrace();
			}
			
			assertEquals(1, result);
    }

    
    // --- Functions Needed For Tests ---
    
    public DataFrame createDataframe() {
 	   
		Colonne colonne1 = ColonneTest.createStringColonne();
		Colonne colonne2 = ColonneTest.createIntColonne();
	
		colonne1.setLabel("colonne1");
		colonne2.setLabel("colonne2");
		
		ArrayList<Colonne> colonnes = new ArrayList<>();
		colonnes.add(colonne1);
		colonnes.add(colonne2);

		DataFrame dataframe = new DataFrame( "Asian Cities", colonnes);
	
		return dataframe;
    }
    
    public DataFrame createEmptyDataframe() {
  	   
    		Colonne colonne1 = ColonneTest.createEmptyColonne();
		Colonne colonne2 = ColonneTest.createEmptyColonne();
	
		colonne1.setLabel("colonne1");
		colonne2.setLabel("colonne2");
		
		ArrayList<Colonne> colonnes = new ArrayList<>();
		colonnes.add(colonne1);
		colonnes.add(colonne2);

		DataFrame dataframe = new DataFrame( "Asian Cities", colonnes);
	
		return dataframe;
    }
}
  