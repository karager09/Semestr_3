package pl.edu.agh.kis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LiczZPlikowWielePunktow {
	
	
	/**
	 * G��wna funkcja licz�ca, przyjmuj�ca funkcje do obliczenia z jednego pliku, a z drugiego punkty, w kt�rych ma zosta� obliczona
	 * Ka�da funkcja zostaje obliczona w ka�dym zdefiniowanym punkcie.
	 * @param funkcje plik z funkcjami do wyliczenia
	 * @param wartosci plik z warto�ciami punkt�w, w kt�rych maj� zosta� wyliczone funkcje
	 * @param wynik plik wynikowy z obliczonymi warto�ciami
	 * @param wf implementacje w�asnych funkcji
	 */
	public static void liczZPlikowWielePunktow(File funkcje,File wartosci,File wynik,WlasnaFunkcjaInterfejs ... wf){
		try(Scanner plikFunkcje=new Scanner(funkcje);
				RandomAccessFile plikWartosci=new RandomAccessFile(wartosci,"r");
				PrintWriter pw=new PrintWriter(wynik)){

		while(plikFunkcje.hasNextLine()){
			String fun=plikFunkcje.nextLine();
			if(fun.equals(""))continue;
			String wart="";
			plikWartosci.seek(0);
			try{
			while(((wart=plikWartosci.readLine())!=null)&&(!wart.equals(""))){
				pw.println(fun+",\t "+wart+",\t "+Oblicz.zamienIOblicz(fun, wart, wf));
			}
			}catch(NoSuchElementException nsee){
				continue;
			}catch(Exception k){
				continue;
			}

		}
			
			
		}catch(FileNotFoundException fnf){
			System.out.println("Nie znaleziono pliku!");
		}catch(IOException e){
			System.out.println("Nieoczekiwany b��d!! ");//+e.getMessage());
			//e.printStackTrace();
		}/*catch(Exception e){
			System.out.println("Nieoczekiwany b��d!! ");//+e.getMessage());
			//e.printStackTrace();
		}*/
	}
	
	public static void main(String [] args){
		WlasnaFunkcjaInterfejs [] wf={new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()};
		liczZPlikowWielePunktow(new File("funkcje2.txt"), new File("wartosci2.txt"), new File("wynik2.txt"),wf);
	}

}
