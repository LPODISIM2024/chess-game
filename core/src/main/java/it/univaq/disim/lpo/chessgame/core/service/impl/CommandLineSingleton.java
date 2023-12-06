package it.univaq.disim.lpo.chessgame.core.service.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.Maps;

import it.univaq.disim.lpo.chessgame.core.datamodel.Pezzo;

public class CommandLineSingleton implements Closeable{
	private Scanner scanner = new Scanner(System.in);
	private static CommandLineSingleton cls = new CommandLineSingleton();

	private CommandLineSingleton() {}

	public static CommandLineSingleton getInstance() {
		return cls;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void disposeScanner() {
		scanner.close();
	}

	@Override
	public void close() throws IOException {
		scanner.close();	
	}
	
	Integer readInteger(){
		return scanner.nextInt();
	}
	public <T> T readIntegerUntilPossibleValue(List<T> lista){
		Map<Integer, T> mappaPezzi = Maps.newHashMap();
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(String.format("%d - %s", i, lista.get(i).toString()));
			mappaPezzi.put(i, lista.get(i));
		}
		Integer[] values = new Integer[mappaPezzi.keySet().size()];
		Integer selezione = readIntegerUntilPossibleValue(mappaPezzi.keySet().toArray(values));
		return lista.get(selezione);
	}
	public Integer readIntegerUntilPossibleValue(Integer[] possibleValues){
		while (true) {
			try {
				Integer scelta = CommandLineSingleton.getInstance().readInteger();
				if (Arrays.stream(possibleValues).anyMatch(x -> x == scelta)){
					return scelta;
				}
			} catch (InputMismatchException e) {
				System.out.println("Scelta inammissibile. Devi inserire un valore idoneo.");
				scanner.nextLine();
			} 
		}
		
	}
	String readString(){
		return scanner.next();
	}
	
}
