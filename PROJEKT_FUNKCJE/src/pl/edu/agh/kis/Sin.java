package pl.edu.agh.kis;

public class Sin implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		return Math.sin(argumenty[0]);
		
	}

	@Override
	public byte iloscArgumentow() {
		return 1;
	}

	@Override
	public String nazwaFunkcji() {

		return "sin";
	}

}
