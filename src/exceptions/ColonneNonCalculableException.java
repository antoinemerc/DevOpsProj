package exceptions;

import com.sun.org.apache.bcel.internal.generic.Type;

public class ColonneNonCalculableException extends Exception {
	public ColonneNonCalculableException(String label, Type type){
		System.err.println("La colonne \""+label+"\" n'est pas calculable puisqu'elle contient des données de type "+type+".");
		System.err.println("Les calculs de statistiques s'effectuent uniquement sur des colonnes contenant des Float ou des int.");
	}
}
