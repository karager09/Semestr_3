package pl.edu.agh.kis;
/**
 * Interfejs do implementacji, dzi�ki niemu mo�emy definiowa� w�asne funkcje.
 * @author Piotr
 * 
 */
public interface WlasnaFunkcjaInterfejs {
	/**
	 * G��wna cz�� obliczeniowa, mo�e przyjmowa� dowoln� liczb� argument�w (co najmniej 1)
	 * Konkretna implementacja tej funkcji jest odpowiedzialna za jej dzia�anie.
	 * @param argumenty liczby dla kt�rych liczona jest funkcja
	 * @return wyliczona warto�� funkcji w punkcie
	 */
	double implementacjaFunkcji(double... argumenty);
	
	/**
	 * 
	 * @return ilo�� argument�w, kt�re przyjmuje nasza funkcja
	 */
	byte iloscArgumentow();
	
	/**
	 * 
	 * @return nazwa, po k�rej b�dziemy rozpoznawa� nasz� funkcje w ci�gu znak�w.
	 * Powinny to by� wszystkie ma�e albo wielkie litery.
	 */
	String nazwaFunkcji();
}
