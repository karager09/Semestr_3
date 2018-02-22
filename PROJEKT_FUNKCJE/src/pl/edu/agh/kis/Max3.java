package pl.edu.agh.kis;

public class Max3 implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		return Math.max(Math.max(argumenty[0], argumenty[1]), argumenty[2]);
	}

	@Override
	public byte iloscArgumentow() {
		return 3;
	}

	@Override
	public String nazwaFunkcji() {
		return "maxt";
	}

}
