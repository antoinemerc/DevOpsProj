package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import com.sun.org.apache.bcel.internal.generic.Type;

import exceptions.CsvMissingComaException;
import exceptions.CsvParsingException;

/**
 * Un parser CSV pour custom DataFrame
 * @author Antoine
 *
 */
public class ParserCsv {

	private String fileName = "";	
	
	private int nbrColumn = 0;
	private int nbrLine = 0;

	private ArrayList<String> headerLine;
	private ArrayList<ArrayList<String>> contentLine = new ArrayList<ArrayList<String>>(); //contentLine[i][1]

	/**
	 * Initialise le parsing d'un fichier CSV
	 * @param fileName
	 */
	public ParserCsv(String fileName) {
		this.fileName = fileName;
		System.out.println(fileName);
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));			
			this.convertCsvToArrayList(br);
		
		} catch (FileNotFoundException | CsvParsingException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Obtient le nom du fichier
	 * @return nomdufichier.csv
	 */
	public String getName(){
		String[] fullPath = fileName.split("/");
		String fileWithExtension = fullPath[fullPath.length-1];
				
		return fileWithExtension;
	}
	
	/**
	 * Convertis un arraylist de string vers un format compatible pour DataFrame
	 * @return Un ArrayList de colonne adapté a un DataFrame
	 */
	public ArrayList<Colonne> getAllColumn(){
		return 	this.convertArrayListToDataFrame();
	}	
	
	/**
	 * Convertis le flux de donnée en un ArrayList<String>
	 * @param br Flux de donnée à convertir
	 * @throws CsvParsingException
	 */
	public void convertCsvToArrayList(BufferedReader br) throws CsvParsingException{
		String line = ""; 
		try {
			this.headerLine = new ArrayList<String>(Arrays.asList(br.readLine().split(",")));						
			this.nbrColumn = headerLine.size();
			
			while ((line = br.readLine()) != null) {
				this.nbrLine++;
				ArrayList<String> lineStr =  new ArrayList<String>(Arrays.asList(line.split(",")));
			    this.contentLine.add(lineStr);
			}
			checkCsvValidity();
			
		} catch (IOException | CsvMissingComaException | CsvParsingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie si il n'y a pas d'incohérence dans le fichier CSV
	 * @throws CsvParsingException 
	 * @throws CsvMissingComaException
	 */
	public void checkCsvValidity() throws CsvParsingException, CsvMissingComaException {
		
		for(int i = 0; i < this.contentLine.size(); i++){
			
			int lineSize =  this.contentLine.get(i).size();
			
			if(lineSize > nbrColumn) {
				throw new CsvParsingException();
			}
		
			if(lineSize < nbrColumn) {
				throw new CsvMissingComaException();
			}				
		}
			
	}
	
	/**
	 * Convertit les tableaux de string précédamment établit en un format adapté aux DataFrame 
	 * @return Une liste de colonne adapté pour un DataFrame.addColonnes()
	 */
	public ArrayList<Colonne> convertArrayListToDataFrame(){
		
		ArrayList<String> firstLine = this.contentLine.get(1);
		ArrayList<Colonne> colonnes = new ArrayList<Colonne>();
		
		for(int i = 0; i <= firstLine.size()-1; i++ ){
			
			Type typeColumn = guessTheType(firstLine.get(i));
			
			//if a field is left blank look for the next one in the column to guess the type
			int j = 1;
			boolean remainUnknown = false;
			while(typeColumn == Type.UNKNOWN || remainUnknown == true){
				
				ArrayList<String> firstLineNext = this.contentLine.get(j++);
				typeColumn = guessTheType(firstLineNext.get(i));
				
				//if all field blank return unknown
				if(j+1 == this.contentLine.size()){
					remainUnknown = true;
				}
			}
			Colonne col = new Colonne(this.headerLine.get(i), typeColumn, null);
			colonnes.add(col);
		}
		
		
		//iterate for the number of column
		for(int i = 0; i <= colonnes.size()-1; i++ ){
			
			Type typeColumn = colonnes.get(i).getType();
			ArrayList<Cellule<?>> cellsList = new ArrayList<Cellule<?>>();

			//iterate for the number of line in the csv file (minus header)
			for(int j = 0; j < this.nbrLine; j++){
				boolean nullValue = false;

				//special case if the value of a column is not filled
				if(this.contentLine.get(j).get(i).trim().equals("") || this.contentLine.get(j).get(i).equals(null)) 
					nullValue = true;
				
				if(typeColumn == Type.INT) {
					
					if(nullValue) {
						cellsList.add(new Cellule<Integer>(null));
					}else {
						cellsList.add(new Cellule<Integer>(Integer.parseInt(this.contentLine.get(j).get(i))));
					}
					
				}else if(typeColumn == Type.DOUBLE) {
					
					if(nullValue) {
						cellsList.add(new Cellule<Integer>(null));
					}else {
						cellsList.add(new Cellule<Double>(Double.parseDouble(this.contentLine.get(j).get(i))));
					}
				
				}else if(typeColumn == Type.STRING) {
					
					if(nullValue) {
						cellsList.add(new Cellule<Integer>(null));
					}else {
						cellsList.add(new Cellule<String>(this.contentLine.get(j).get(i)));
					}
				}
				
				
			}			
			colonnes.get(i).setCellules(cellsList);
		}
		
		return colonnes;
	}
	
	/**
	 * Trouve et attribue un type a un string
	 * @param value La valeur dont on doit trouver le type
	 * @return le type du string
	 */
	public Type guessTheType(String value){
		
		Type ret = null;
		int it = 0;
		boolean done = false;
		
		if(value == null){
			done = true;
			ret = Type.UNKNOWN;
		}
		
		while (done == false){
			try{
				switch (it){
					case 0:
						it++;
						Integer.parseInt(value);
						ret = Type.INT;
						done = true;
						break;
					case 1:
						it++;
						Double.parseDouble(value);
						ret = Type.DOUBLE;
						done = true;
						break;
					default:
						it++;
						ret = Type.STRING;
						done = true;		
				}
			}catch(Exception e){
				
			}
		}
		
		return ret;
	}	
}