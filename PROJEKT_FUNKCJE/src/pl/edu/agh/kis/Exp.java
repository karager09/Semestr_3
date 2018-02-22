package pl.edu.agh.kis;

public class Exp implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double ...argumenty) {
		
		return Math.exp(argumenty[0]);
	}

	@Override
	public byte iloscArgumentow() {
		return 1;
	}

	@Override
	public String nazwaFunkcji() {
		return "exp";
	}

}
