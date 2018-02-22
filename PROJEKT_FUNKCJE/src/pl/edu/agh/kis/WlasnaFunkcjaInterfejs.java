package pl.edu.agh.kis;
/**
 * Interfejs do implementacji, dziêki niemu mo¿emy definiowaæ w³asne funkcje.
 * @author Piotr
 * 
 */
public interface WlasnaFunkcjaInterfejs {
	/**
	 * G³ówna czêœæ obliczeniowa, mo¿e przyjmowaæ dowoln¹ liczbê argumentów (co najmniej 1)
	 * Konkretna implementacja tej funkcji jest odpowiedzialna za jej dzia³anie.
	 * @param argumenty liczby dla których liczona jest funkcja
	 * @return wyliczona wartoœæ funkcji w punkcie
	 */
	double implementacjaFunkcji(double... argumenty);
	
	/**
	 * 
	 * @return iloœæ argumentów, które przyjmuje nasza funkcja
	 */
	byte iloscArgumentow();
	
	/**
	 * 
	 * @return nazwa, po kórej bêdziemy rozpoznawaæ nasz¹ funkcje w ci¹gu znaków.
	 * Powinny to byæ wszystkie ma³e albo wielkie litery.
	 */
	String nazwaFunkcji();
}
