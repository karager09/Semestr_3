package pl.edu.agh.kis;

public class Sinus implements WlasnaFunkcjaInterfejs {

	@Override
	public double implementacjaFunkcji(double... argumenty) {
		
		return argumenty[0];
	}

	@Override
	public byte iloscArgumentow() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String nazwaFunkcji() {
		// TODO Auto-generated method stub
		return "si3";
	}

}
