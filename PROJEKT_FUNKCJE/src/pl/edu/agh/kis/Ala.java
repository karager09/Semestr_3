package pl.edu.agh.kis;

public class Ala implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		// TODO Auto-generated method stub
		return 2*argumenty[0];
	}

	@Override
	public byte iloscArgumentow() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String nazwaFunkcji() {
		// TODO Auto-generated method stub
		return "ala";
	}

}
