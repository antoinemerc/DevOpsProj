package main;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.Type;

public class Colonne {
	
	private String label;
	private Type type;
	private ArrayList<Cellule> cellules;

	/***
	 * 
	 * @param label
	 * @param type
	 */
	public Colonne( String label, Type type ) 
	{
		this.setLabel( label );
		this.setType( type );
		this.setCellules( new ArrayList<Cellule>() );
	}
	
	/***
	 * 
	 * @param label
	 * @param type
	 * @param cellules
	 */
	public Colonne(String label, Type type, ArrayList<Cellule> cellules){
		this.label = label;
		this.type = type;
		this.cellules = cellules;
	}

	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public ArrayList<Cellule> getCellules() {
		return cellules;
	}

	public void setCellules(ArrayList<Cellule> cellules) {
		this.cellules = cellules;
	}
	
}
