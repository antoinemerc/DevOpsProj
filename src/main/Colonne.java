package main;

import com.sun.org.apache.bcel.internal.generic.Type;

public class Colonne {
	
	private String label;
	private Type type;
	private Cellule[] cellules;

	public Colonne(String label, Type type, Cellule[] cellules){
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

	public Cellule[] getCellules() {
		return cellules;
	}

	public void setCellules(Cellule[] cellules) {
		this.cellules = cellules;
	}
	
}
