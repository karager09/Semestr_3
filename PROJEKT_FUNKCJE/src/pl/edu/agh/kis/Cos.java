package pl.edu.agh.kis;

public class Cos implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		return Math.cos(argumenty[0]);
	}

	@Override
	public byte iloscArgumentow() {
		return 1;
	}

	@Override
	public String nazwaFunkcji() {
		return "cos";
	}

}
