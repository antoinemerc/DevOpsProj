package main;

public class Cellule<E> {
	
	private E value;

	public Cellule(E value){
		this.value = value;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}
	
	public boolean equals(Object obj){
		if (obj == null || getClass() != obj.getClass()){
			return false;
		}
		Cellule cellule2 = (Cellule)obj;
		if (this.getValue().equals(cellule2.getValue())){
			return true;
		}
		return false;
	}
	
}
