package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.Type;

import sun.corba.OutputStreamFactory;

public class ParserCsv {

	private String fileName = "";
	private BufferedReader br = null;
	private DataFrame df = null;
	
	private int nbrColumn = 0;
	private int nbrLine = 0;

	private String[] headerLine;

	private Colonne[] headers;
	private ArrayList<String[]> contentLine = new ArrayList<String[]>(); //contentLine[i][1]

	/**
	 * 
	 * @param fileName
	 */
	public ParserCsv(DataFrame df, String fileName) {
		this.fileName = fileName;
		
		try {
			this.br = new BufferedReader(new FileReader(fileName));
			this.df = df;
			this.convertCsvToStringArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String getName(){
		String[] fullPath = fileName.split("/");
		String fileWithExtension = fullPath[fullPath.length-1];
				
		return fileWithExtension;
	}
	
	
	/**
	 * 
	 */
	public void convertCsvToStringArray(){
		String line = ""; 
		try {
			this.headerLine = br.readLine().split(",");						
			this.nbrColumn = headerLine.length;
			
			while ((line = br.readLine()) != null) {
				this.nbrLine++;
			    String[] lineStr = line.split(",");
			    this.contentLine.add(lineStr);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Colonne> convertStringArrayToDataFrame(){
		
		String[] firstLine = this.contentLine.get(1);
		ArrayList<Colonne> colonnes = new ArrayList<Colonne>();
		
		for(int i = 0; i <= firstLine.length-1; i++ ){
			
			Type typeColumn = guessTheType(firstLine[i]);
			
			//if a field is left blank look for the next one in the column to guess the type
			int j = 1;
			boolean remainUnknown = false;
			while(typeColumn == Type.UNKNOWN || remainUnknown == true){
				
				String[] firstLineNext = this.contentLine.get(j++);
				typeColumn = guessTheType(firstLineNext[i]);
				
				//if all field blank return unknown
				if(j+1 == this.contentLine.size()){
					remainUnknown = true;
				}
			}
			Colonne col = new Colonne(this.headerLine[i], typeColumn, null);
			colonnes.add(col);
		}
		
		/*
		for(Colonne c : colonnes){
			System.out.println(c.getLabel()+","+c.getType().toString());
		}*/
		
		//iterate for the number of column
		for(int i = 0; i <= colonnes.size()-1; i++ ){
			
			Type typeColumn = colonnes.get(i).getType();
			ArrayList<Cellule> cellsList = new ArrayList<Cellule>();

			//iterate for the number of line in the csv file (minus header)
			for(int j = 0; j < this.nbrLine; j++){
				if(typeColumn == Type.INT)
					cellsList.add(new Cellule<Integer>(Integer.parseInt(this.contentLine.get(j)[i])));
				else if(typeColumn == Type.STRING)
					cellsList.add(new Cellule<String>(this.contentLine.get(j)[i]));
				
				
			}			
			colonnes.get(i).setCellules(cellsList);
		}
		
		return colonnes;
	}
	
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
						int testInt = Integer.parseInt(value);
						ret = Type.INT;
						done = true;
						break;
					case 1:
						it++;
						float testDouble = Float.parseFloat(value);
						ret = Type.FLOAT;
						done = true;
						break;
					default:
						it++;
						ret = Type.STRING;
						done = true;		
				}
			}catch(Exception e){}
		}
		
		return ret;
	}
	
	public ArrayList<Colonne> getAllColumn(){
		return 	this.convertStringArrayToDataFrame();

	}
	
}
