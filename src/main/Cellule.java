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
	
}
