package pl.edu.agh.kis;

public class Ln implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		return Math.log(argumenty[0]);
	}

	@Override
	public byte iloscArgumentow() {
		return 1;
	}

	@Override
	public String nazwaFunkcji() {
		return "ln";
	}

}
