package pl.edu.agh.kis;

import java.io.*;
import java.util.Scanner;

/**
 * Klasa pozwalaj�ca wylicza� wiele funkcji w danych punktach, korzystaj�c z metod klasy Oblicz.
 * @author Piotr
 *
 */
public class LiczZPlikow {
	
	/**
	 * G��wna funkcja licz�ca, przyjmuj�ca funkcje do obliczenia z jednego pliku, a z drugiego punkty, w kt�rych ma zosta� obliczona
	 * Dzia�a tak: 1 linijka z funkcji jako punkt pobiera pierwsz� linijk� z warto�c.
	 * 2 linijka odpowiada 2 linijce itd.
	 * @param funkcje plik z funkcjami do wyliczenia
	 * @param wartosci plik z warto�ciami punkt�w, w kt�rych maj� zosta� wyliczone funkcje
	 * @param wynik plik wynikowy z obliczonymi warto�ciami
	 * @param wf implementacje w�asnych funkcji
	 */
	public static void liczZPlikow(File funkcje,File wartosci,File wynik,WlasnaFunkcjaInterfejs ... wf){
		try(Scanner plikFunkcje=new Scanner(funkcje);
				Scanner plikWartosci=new Scanner(wartosci);
				PrintWriter pw=new PrintWriter(wynik)){

		while(plikFunkcje.hasNextLine()){
			String wart="";
			if(plikWartosci.hasNextLine())
				wart=plikWartosci.nextLine();
			String fun=plikFunkcje.nextLine();
			if(fun.equals(""))continue;

				try{
				pw.println(fun+",\t "+wart+",\t "+Oblicz.zamienIOblicz(fun, wart, wf));
				}catch(Exception e){
					continue;
				}
		}
			
			
		}catch(FileNotFoundException fnf){
			System.out.println("Nie znaleziono pliku!");
		}catch(Exception e){
			System.out.println("Nieoczekiwany b��d!!");
		}

	}
	
	
	
	public static void main(String [] args){
		WlasnaFunkcjaInterfejs [] wf={new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()};
		liczZPlikow(new File("funkcje.txt"), new File("wartosci.txt"), new File("wynik.txt"),wf);
	}

}
