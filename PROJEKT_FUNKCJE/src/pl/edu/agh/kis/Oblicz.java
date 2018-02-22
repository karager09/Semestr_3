package pl.edu.agh.kis;

import java.util.HashMap;
import java.util.regex.*;


/**
 * Klasa abstrakcyjna (nie ma potrzeby tworzyæ jej instancji). 
 * Dziêki niej mo¿emy dokonywaæ obliczeñ na wejœciowym ci¹gu znaków.
 * Dzia³a podobnie jak Math.
 * Funkcj¹, dziêki której dokonujemy obliczeñ to zamienIOblicz(), 
 * która przyjmuje ró¿ne argumenty, w zale¿noœci od tego, co chcemy obliczyæ.
 * Mo¿emy np. podaæ tylko String, aby funkcja dokona³a obliczeñ, ale mo¿emy te¿ podaæ zmienne, które chcielibyœmy w nim wykorzystaæ.
 * Mo¿emy równie¿ przekazaæ dodatkowe funkcje, które sami zaimplementowaliœmy (klasy implementuj¹ce WlasaFunkcjaInterfejs).
 * 
 * @author Piotr
 *
 */
abstract public class Oblicz {

	
	/**
	 * G³ówna funkcja obliczaj¹ca wartoœci funkcji w podanych punktach
	 * @param doObliczenia funkcja, której waroœæ chcemy wyliczyæ w konkretnym punkcie
	 * @param wlasneFunkcje tablica klas implementuj¹cych WlasnaFunkcjaInterfejs, które implementuj¹ funkcje wykorzystywane przy obliczaniu
	 * @return zwraca obliczon¹ wartoœæ w postali ci¹gu znaków, jeœli mo¿liwe by³o obliczenie
	 * @throws Exception 
	 */
	private static String obl(String doObliczenia,HashMap<String,Double> zmienne,WlasnaFunkcjaInterfejs ... wlasneFunkcje) throws Exception{
		String zapamietana=doObliczenia;
		if(Pattern.matches("-?\\d+\\.?\\d*", doObliczenia)) return doObliczenia;//sprawdzam czy to ju¿ jest zwyk³a liczba, jak tak to ja zwracam
		if(Pattern.matches("\\+\\d+\\.?\\d*", doObliczenia)) return doObliczenia.substring(1);
		if(zmienne!=null){
			if(zmienne.containsKey(doObliczenia)) return Double.toString(zmienne.get(doObliczenia));
			if(doObliczenia.startsWith("-")&&(doObliczenia.length()>1)&&(zmienne.containsKey(doObliczenia.substring(1))))return Double.toString(-zmienne.get(doObliczenia.substring(1)));
		}

		
		//System.out.println(doObliczenia);
		doObliczenia=doObliczenia.replaceAll("(--)+", "+");
		doObliczenia=doObliczenia.replaceAll("-(--)+", "-");
		//doObliczenia=doObliczenia.replaceAll("\\+-", "-");
		
		if(wlasneFunkcje!=null)//obliczamy funkcje, które zdefiniowaliœmy wczeœniej, jeœli taki wystêpuj¹
		for(WlasnaFunkcjaInterfejs wfi:wlasneFunkcje){
			doObliczenia=obliczWlasnaFunkcje(doObliczenia,zmienne,wfi,wlasneFunkcje);
			for(WlasnaFunkcjaInterfejs wfii:wlasneFunkcje){
				doObliczenia=obliczWlasnaFunkcje(doObliczenia,zmienne,wfii,wlasneFunkcje);
			}
		}

		/*if(wlasneFunkcje!=null)
		for(WlasnaFunkcja wf:wlasneFunkcje){
				try {
					doObliczenia=wf.obliczWLasnaFunkcje(doObliczenia);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
		//System.out.println(doObliczenia);
		//doObliczenia=doObliczenia.replaceAll(",", ".");
		
			try{
			Matcher nawiasy=Pattern.compile("(.*)\\(([^()]+)\\)(.*)").matcher(doObliczenia);//najpierw licze nawiasy
			while(nawiasy.find()){
				if(!nawiasy.group(2).contains(",")){
					//zapamietana=doObliczenia;
					return obl(nawiasy.group(1)+obl(nawiasy.group(2),zmienne,wlasneFunkcje)+nawiasy.group(3),zmienne,wlasneFunkcje);
				}
				}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(Exception e){
				//if(!e.getMessage().isEmpty())throw e;
				throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
			}
			
			String a="",b="";
			try{
			Matcher dodawanie=Pattern.compile("([^+]*[^/\\*\\^])[+](.+)").matcher(doObliczenia);
			if(dodawanie.find())
			{
			double d=Double.parseDouble(a=obl(dodawanie.group(1),zmienne,wlasneFunkcje))+Double.parseDouble(b=obl(dodawanie.group(2),zmienne,wlasneFunkcje));
			return Double.toString(d);
			}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(Exception e){
				//if(!e.getMessage().isEmpty()) throw e;
				if(!Pattern.matches("-?\\d+\\.?\\d*", a)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
				else if(!Pattern.matches("-?\\d+\\.?\\d*", b)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
				else throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+"Wyst¹pi³ b³¹d przy dodawaniu");
			}
			
			try{
			//Matcher odejmowanie=Pattern.compile("([^-]*[^*-/])-(.+)").matcher(doObliczenia);
			//Matcher odejmowanie=Pattern.compile("(.*[^*-/])-([^-]+)").matcher(doObliczenia);
				Matcher odejmowanie=Pattern.compile("(.*[^*-/\\^])-(.+)").matcher(doObliczenia);
			if(odejmowanie.find())
			{
				//System.out.println(odejmowanie.group(1)+",  "+odejmowanie.group(2));
			double d=Double.parseDouble(a=obl(odejmowanie.group(1),zmienne,wlasneFunkcje))-Double.parseDouble(b=obl(odejmowanie.group(2),zmienne,wlasneFunkcje));
			return Double.toString(d);
			}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(Exception e){
				//if(!e.getMessage().isEmpty()) throw e;
					if(!Pattern.matches("-?\\d+\\.?\\d*", a)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else if(!Pattern.matches("-?\\d+\\.?\\d*", b)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else throw new MojWyjatek("Wyst¹pi³ b³¹d przy odejmowaniu");
			}
			
			try{
			Matcher mnozenie=Pattern.compile("([^*]+)\\*(.+)").matcher(doObliczenia);
			if(mnozenie.find())
			{
			double d=Double.parseDouble(a=obl(mnozenie.group(1),zmienne,wlasneFunkcje))*Double.parseDouble(b=obl(mnozenie.group(2),zmienne,wlasneFunkcje));
			return Double.toString(d);
			}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(Exception e){
					//if(!e.getMessage().isEmpty()) throw e;
					if(!Pattern.matches("-?\\d+\\.?\\d*", a)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else if(!Pattern.matches("-?\\d+\\.?\\d*", b)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else throw new Exception("Wyst¹pi³ b³¹d przy mno¿eniu");
			}
			
			try{
			Matcher dzielenie=Pattern.compile("(.+)/([^/]+)").matcher(doObliczenia);
			if(dzielenie.find())
			{
			double dzielna=Double.parseDouble(b=obl(dzielenie.group(2),zmienne,wlasneFunkcje));
			
			if(dzielna==0) throw new MojWyjatek("Próbujesz podzieliæ przez 0!");
			double d=Double.parseDouble(a=obl(dzielenie.group(1),zmienne,wlasneFunkcje))/dzielna;
			return Double.toString(d);
			}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(Exception e){
					//if(!e.getMessage().isEmpty()) throw e;
					if(!Pattern.matches("-?\\d+\\.?\\d*", b)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());	
					else if(!Pattern.matches("-?\\d+\\.?\\d*", a)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else throw new MojWyjatek("Wyst¹pi³ b³¹d przy dzieleniu");
			}
			
			try{
			//Matcher potega=Pattern.compile("(-?\\d+\\.?\\d*)\\^(-?\\d+\\.?\\d*)").matcher(doObliczenia);
				Matcher potega=Pattern.compile("(.+)\\^(.+)").matcher(doObliczenia);
			if(potega.find())
			{
				double podstawa=Double.parseDouble(a=obl(potega.group(1),zmienne,wlasneFunkcje));
				double wykladnik=Double.parseDouble(b=obl(potega.group(2),zmienne,wlasneFunkcje));
				if(podstawa<0&&wykladnik%1!=0) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+"Nie mo¿na obliczyc pierwiastka z ujemnej liczby.");
			double d=Math.pow(podstawa,wykladnik);
			return Double.toString(d);
			}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(Exception e){
				//if(!e.getMessage().isEmpty()) throw e;
					if(!Pattern.matches("-?\\d+\\.?\\d*", a)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else if(!Pattern.matches("-?\\d+\\.?\\d*", b)) throw new MojWyjatek("Wyst¹pi³ b³¹d przy obliczaniu "+zapamietana+". "+e.getMessage());
					else throw new MojWyjatek("Wyst¹pi³ b³¹d przy potêgowaniu");
			}
	
			if(!Pattern.matches("-?\\d+\\.?\\d*", doObliczenia))throw new Exception("Niepoprawne wyra¿enie: "+doObliczenia);
			return doObliczenia;
	}
	
	/**
	 * Funkcja sprawdzaj¹ca czy w danym ci¹gu znajduje siê wywo³anie funkcji przez nas zaimplementowanej, zajmuje siê jej obliczeniem
	 * @param doObliczenia ci¹g znaków reprezentuj¹cy funkcje, której wartoœæ w danym punkcie chcemy poznaæ
	 * @param wlasnaFunkcja funkcja przez nas zaimplementowana, której szukamy w ci¹gu
	 * @param inneFunkcje wszystkie funkcje, które mog¹ wyst¹piæ w danym ci¹gu
	 * @return ci¹g z wyliczonymi w³asnymi funkcjami, a reszta bez zmian
	 * @throws Exception 
	 */
	private static String obliczWlasnaFunkcje(String doObliczenia,HashMap<String,Double> zmienne,WlasnaFunkcjaInterfejs wlasnaFunkcja,WlasnaFunkcjaInterfejs []inneFunkcje) throws Exception{
		
		try{
			if(wlasnaFunkcja.iloscArgumentow()<2){
				Matcher znalezionaFunkcja=Pattern.compile("(.*)"+wlasnaFunkcja.nazwaFunkcji()+"(-?\\d+\\.?\\d*)(.*)").matcher(doObliczenia);//najpierw licze nawiasy
				while(znalezionaFunkcja.find()){
				doObliczenia=znalezionaFunkcja.group(1)+wlasnaFunkcja.implementacjaFunkcji(Double.parseDouble(znalezionaFunkcja.group(2)))+znalezionaFunkcja.group(3);
				znalezionaFunkcja=Pattern.compile("(.*)"+wlasnaFunkcja.nazwaFunkcji()+"(-?\\d+\\.?\\d*)(.*)").matcher(doObliczenia);
			}
			}
			else{
			Matcher znalezionaWieloargumentowa=Pattern.compile("(.*)"+wlasnaFunkcja.nazwaFunkcji()+"\\(([^)(]*)\\)(.*)").matcher(doObliczenia);
			while(znalezionaWieloargumentowa.find()){
				String [] podzielone=znalezionaWieloargumentowa.group(2).split(",");
				//System.out.println(podzielone[0]);
				if(podzielone.length!=wlasnaFunkcja.iloscArgumentow()) throw new MojWyjatek("Wyst¹pi³ b³¹d, z³a liczba argumentów dla "+wlasnaFunkcja.nazwaFunkcji());
				double [] d= new double[wlasnaFunkcja.iloscArgumentow()];
				for(int i=0;i<wlasnaFunkcja.iloscArgumentow();i++){
					try{
					d[i]=Double.parseDouble(obl(podzielone[i],zmienne,inneFunkcje));
					}catch (NumberFormatException e){
						throw new MojWyjatek("B³¹d przy obliczaniu funkcji "+wlasnaFunkcja.nazwaFunkcji()+ ", a konkretnie ci¹gu: "+podzielone[i]);
					}

				}
				doObliczenia= znalezionaWieloargumentowa.group(1)+Double.toString(wlasnaFunkcja.implementacjaFunkcji(d))+znalezionaWieloargumentowa.group(3);
				znalezionaWieloargumentowa=Pattern.compile("(.*)"+wlasnaFunkcja.nazwaFunkcji()+"\\(([^)(]*)\\)(.*)").matcher(doObliczenia);
				}
			
				
			}
			}catch(MojWyjatek mw){
				throw mw;
			}catch(IndexOutOfBoundsException e){
				throw new MojWyjatek("Wyst¹pi³ b³¹d, z³a liczba argumentów"+wlasnaFunkcja.nazwaFunkcji());
			}
			catch(Exception e){//e.printStackTrace();
				//if(!e.getMessage().isEmpty()) throw e;
				throw new MojWyjatek("Nie uda³o siê dokonaæ obliczeñ dla funkcji "+wlasnaFunkcja.nazwaFunkcji());
				
				//throw new Exception("B³¹d przy obliczaniu "+wlasnaFunkcja.nazwaFunkcji());}
			}
		
			
			return doObliczenia;
	}
	
	
	/**
	 * Funkcja zajmuj¹ca siê zamian¹ zmiennych na ich wartoœci.
	 * Dodatkowo wstêpnie przetwarza ci¹g znaków: usuwa odstêpy, zmienia litery na ma³e itd.
	 * @param doObliczenia ci¹g znaków który ma zostaæ póziej obliczony
	 * @param argumenty argumenty i ich wartoœci, powinny byæ w postaci argument=wartoœæ, oddzielone przecinkami
	 * @param wlasneFunkcje tablica klas, które reprezentuj¹ funkcje potrzebne do obliczeñ
	 * @return zwraca wstêpnie przetworzony ci¹g
	 * @throws Exception 
	 */
	private static String zamianaZmiennych(String doObliczenia,String argumenty,WlasnaFunkcjaInterfejs ... wlasneFunkcje) throws Exception{
		
		//doObliczenia=doObliczenia.toLowerCase();
		//System.out.println(doObliczenia);
		if(wlasneFunkcje!=null)
		for(WlasnaFunkcjaInterfejs wlasna:wlasneFunkcje){
			for(WlasnaFunkcjaInterfejs doSprawdzenia:wlasneFunkcje){
				if(doSprawdzenia.nazwaFunkcji().equals(wlasna.nazwaFunkcji())&&(doSprawdzenia!=wlasna))throw new MojWyjatek("Dwie funkcje o takich samych nazwach: "+doSprawdzenia.nazwaFunkcji());
			}
			
			//doObliczenia=doObliczenia.replaceAll(wlasna.nazwaFunkcji().toLowerCase(), wlasna.nazwaFunkcji().toUpperCase());
			if(!Pattern.matches("[a-z]+",wlasna.nazwaFunkcji())) throw new MojWyjatek("Niepoprawna nazwa funkcji "+ wlasna.nazwaFunkcji());
			if(wlasna.iloscArgumentow()<1) throw new MojWyjatek("Funkcja"+wlasna.nazwaFunkcji()+" nie mo¿e przyjmowaæ mniej ni¿ 1 argument");
		}
		
		/*if((argumenty!=null)&&(!Pattern.matches("\\s*", argumenty))){
		String [] args=argumenty.toLowerCase().replaceAll("\\s*","").split(",");
		for(int i=0;i<args.length;++i){
			try{
			if(args[i].equals(""))continue;
			String [] podzielone=args[i].split("=");
			//System.out.println(podzielone[0]+podzielone[1]);
			if(!Pattern.matches("[a-zA-Z]+", podzielone[0])) throw new MojWyjatek();
			//if(!Pattern.matches("-?\\d+\\.?\\d*", podzielone[1])) continue;
			doObliczenia=doObliczenia.replaceAll(podzielone[0],obl(podzielone[1],wlasneFunkcje));}//nawet jak punkt nie jest liczb¹, to proboje go obliczyc i wtedy dopiero wstawic
			catch(Exception e){throw new MojWyjatek("B³¹d przy deklaracji zmiennych. Konkretnie przy: "+args[i]);}
		}
		}*/
		/*if(wlasneFunkcje!=null)
		for(WlasnaFunkcjaInterfejs wlasna:wlasneFunkcje){
			doObliczenia=doObliczenia.replaceAll(wlasna.nazwaFunkcji().toUpperCase(), wlasna.nazwaFunkcji().toLowerCase());
		}*/
		HashMap<String,Double> zmienne=new HashMap<String,Double>();
		if((argumenty!=null)&&(!Pattern.matches("\\s*", argumenty))){
			//String [] args=argumenty.toLowerCase().replaceAll("\\s*","").split(",");
			String [] args=argumenty.replaceAll("\\s*","").split(",");
			for(int i=0;i<args.length;++i){
				try{
				if(args[i].equals(""))continue;
				String [] podzielone=args[i].split("=");
				//System.out.println(podzielone[0]+podzielone[1]);
				if(!Pattern.matches("[a-zA-Z]+", podzielone[0])) throw new Exception();
				if(!zmienne.containsKey(podzielone[0]))  zmienne.put(podzielone[0], Double.parseDouble(obl(podzielone[1],zmienne,wlasneFunkcje)));
				else throw new MojWyjatek("B³¹d przy deklaracji zmiennych. Zmienna\t"+ podzielone[0]+"\t ju¿ istnieje.");
				//if(!Pattern.matches("-?\\d+\\.?\\d*", podzielone[1])) continue;
				//doObliczenia=doObliczenia.replaceAll(podzielone[0],obl(podzielone[1],wlasneFunkcje));}//nawet jak punkt nie jest liczb¹, to proboje go obliczyc i wtedy dopiero wstawic
				}catch(MojWyjatek mw){
					throw new MojWyjatek("B³¹d w deklaracjach zmiennych: "+args[i]+". "+mw.getMessage());
				}
				catch(Exception e){throw new MojWyjatek("B³¹d przy deklaracji zmiennych. Konkretnie przy: "+args[i]);}
			}
			}
		
		int iloœæ_nawiasów_otw = doObliczenia.length() - doObliczenia.replace("(", "").length();
		
		int iloœæ_nawiasów_zam = doObliczenia.length() - doObliczenia.replace(")", "").length();
		if(iloœæ_nawiasów_otw!=iloœæ_nawiasów_zam)throw new MojWyjatek("Niepoprawna iloœæ nawiasów. Nawiasów otwieraj¹cych "+iloœæ_nawiasów_otw+", nawiasów zamykaj¹cych "+iloœæ_nawiasów_zam);
		//System.out.println(iloœæ_nawiasów_otw+", "+iloœæ_nawiasów_zam);
		doObliczenia= doObliczenia.replaceAll("\\s*", "");
		return doObliczenia=obl(doObliczenia,zmienne,wlasneFunkcje);
	}
	
	/**
	 * Funkcja zajmuj¹ca siê obliczaniem wartoœci funkcji w punkcie
	 * @param doObliczenia ci¹g, który ma zostaæ obliczony
	 * @param argumenty punkty, w których chcemy dokonaæ obliczeñ, powinny byæ w postaci argument=wartoœæ, oddzielone przecinkami
	 * @param wlasneFunkcje tablica klas reprezentuj¹cych funkcje, które bêd¹ wykorzystywane przy obliczaniu
	 * klasy te implementuj¹ WlasnaFunkcjaInterfejs
	 * @return obliczona wartoœæ funkcji w punkcie. 
	 * @throws Exception gdy funkcja jest niepoprawna, rzucany jest wyj¹tek. Jego komunikat mo¿na sprawdziæ za pomoc¹ e.getMessage();
	 */
	 public static String zamienIOblicz(String doObliczenia,String argumenty,WlasnaFunkcjaInterfejs ... wlasneFunkcje) throws Exception{
		 	try{
		 	doObliczenia=zamianaZmiennych(doObliczenia,argumenty,wlasneFunkcje);
			//doObliczenia=obl(doObliczenia,zmienne,wlasneFunkcje);
			if(Pattern.matches("-?\\d+\\.?\\d*", doObliczenia)||Pattern.matches("-?\\d+\\.?\\d*[Ee]\\d+", doObliczenia))
			return doObliczenia;
			return "?"+doObliczenia;
		 	}catch(Exception e){
		 		//e.printStackTrace();
		 		throw e;
		 	}
	}
	 
	 /**
	 * Funkcja zajmuj¹ca siê obliczaniem wartoœci funkcji w punkcie
	 *	@param doObliczenia ci¹g, który ma zostaæ obliczony
	 * 	@return obliczona wartoœæ funkcji w punkcie. 
	 * @throws Exception gdy funkcja jest niepoprawna rzucany jest wyj¹tek. Jego komunikat mo¿na sprawdziæ za pomoc¹ e.getMessage();
	 */
	 public static String zamienIOblicz(String doObliczenia) throws Exception{
		try{	
		 doObliczenia=obl(doObliczenia,null);
			//return doObliczenia;
			if(Pattern.matches("-?\\d+\\.?\\d*", doObliczenia)||Pattern.matches("-?\\d+\\.?\\d*[Ee]\\d+", doObliczenia))
				return doObliczenia;
				return "?"+doObliczenia;
		}catch(Exception e){
	 		throw e;
	 	}
	}
	 
	 
	 /**
	  * Funkcja zajmuj¹ca siê obliczaniem wartoœci funkcji w punkcie
	  * @param doObliczenia ci¹g, który ma zostaæ obliczony
	  * @param wlasneFunkcje tablica klas reprezentuj¹cych funkcje, które bêd¹ wykorzystywane przy obliczaniu,
	  * 	klasy te implementuj¹ WlasnaFunkcjaInterfejs
	  * @return obliczona wartoœæ funkcji w punkcie.
	 * @throws Exception gdy funkcja jest niepoprawna rzucany jest wyj¹tek. Jego komunikat mo¿na sprawdziæ za pomoc¹ e.getMessage();
	  */
	 public static String zamienIOblicz(String doObliczenia,WlasnaFunkcjaInterfejs ... wlasneFunkcje) throws Exception{
		 	try{
		 	doObliczenia=zamianaZmiennych(doObliczenia,null,wlasneFunkcje);
		 	//doObliczenia=obl(doObliczenia,wlasneFunkcje);
		 	//return doObliczenia;
		 	if(Pattern.matches("-?\\d+\\.?\\d*", doObliczenia)||Pattern.matches("-?\\d+\\.?\\d*[Ee]\\d+", doObliczenia))
				return doObliczenia;
				return "?"+doObliczenia;
		 	}catch(Exception e){
		 		throw e;
		 	}
	}
	 
	
	public static void main(String [] args){
		String s="maxt(1,2,max(3,4))/(3-1)+x*y/(x*y)+sin(x)^2+cos(x)^2";
		s="maxt(max(1,2),ln(x),exp-1)+max(max((X^2)*3,2.55555),-100)";
		s="sin(1)+2/3+((3*4)+4";
		s="x-1";
		//WlasnaFunkcjaInterfejs [] wf={new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()};

		try {
			System.out.println(zamienIOblicz(s,"X=sin1^2+cos1^2,y=7,,x=1*1",new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//System.out.println(zamienIOblicz("1+2+3/6-sin(3)",new Sinus(),new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()));
		//System.out.println(zamienIOblicz("1+2+3/6"));
	}
}
