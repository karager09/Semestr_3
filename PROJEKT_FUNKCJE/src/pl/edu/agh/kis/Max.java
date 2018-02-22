package pl.edu.agh.kis;

public class Max implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		
		return Math.max(argumenty[0], argumenty[1]);
	}

	@Override
	public byte iloscArgumentow() {
		return 2;
	}

	@Override
	public String nazwaFunkcji() {
		return "max";
	}

}
