package pl.edu.agh.kis;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class ObliczTest {

	@Test
	public void testProsteOperacje() {
		try{
		Assert.assertEquals("Zwyk³a liczba", 1, Double.parseDouble(Oblicz.zamienIOblicz("1")), 0);
		Assert.assertEquals("Zwyk³a liczba", 1234, Double.parseDouble(Oblicz.zamienIOblicz("1234")), 0);
		Assert.assertEquals("Blad przy dodawaniu", 5, Double.parseDouble(Oblicz.zamienIOblicz("1+2+2")),0);
		Assert.assertEquals("Blad przy dodawaniu/odejmowaniu", 5, Double.parseDouble(Oblicz.zamienIOblicz("1+2-123+123+2")),0);
		Assert.assertEquals("Blad przy dodawaniu/odejmowaniu", 5, Double.parseDouble(Oblicz.zamienIOblicz("1+-2+6")),0);
		Assert.assertEquals("Blad przy dodawaniu/odejmowaniu", 5, Double.parseDouble(Oblicz.zamienIOblicz("1--2+2")),0);
		Assert.assertEquals("Blad przy dodawaniu/odejmowaniu", 1, Double.parseDouble(Oblicz.zamienIOblicz("1---2+2")),0);
		Assert.assertEquals("Blad przy mno¿eniu", 6, Double.parseDouble(Oblicz.zamienIOblicz("1*2*3")),0);		
		Assert.assertEquals("Blad przy mno¿eniu", 3, Double.parseDouble(Oblicz.zamienIOblicz("1*2*3-3")),0);
		Assert.assertEquals("Blad przy mno¿eniu", 9, Double.parseDouble(Oblicz.zamienIOblicz("1*2*3+3")),0);
		Assert.assertEquals("Blad przy mno¿eniu", 30, Double.parseDouble(Oblicz.zamienIOblicz("5*6")),0);
		Assert.assertEquals("Blad przy kolejnoœci dzia³añ", 6, Double.parseDouble(Oblicz.zamienIOblicz("2*2+2")),0);
		Assert.assertEquals("Blad przy kolejnoœci dzia³añ", 6, Double.parseDouble(Oblicz.zamienIOblicz("2+2*2")),0);
		Assert.assertEquals("Blad przy kolejnoœci dzia³añ", 2, Double.parseDouble(Oblicz.zamienIOblicz("2+2*2-2*2")),0);
		Assert.assertEquals("Blad przy kolejnoœci dzia³añ", 4, Double.parseDouble(Oblicz.zamienIOblicz("2-2+2*2*2-2*2")),0);
		
		//sprawdzanie dzielenia
		Assert.assertEquals("Blad przy dzieleniu", 0.5, Double.parseDouble(Oblicz.zamienIOblicz("1/2")),0);
		Assert.assertEquals("Blad przy dzieleniu", 0.25, Double.parseDouble(Oblicz.zamienIOblicz("1/2/2")),0);		
		Assert.assertEquals("Blad przy dzieleniu", 0.125, Double.parseDouble(Oblicz.zamienIOblicz("1/2/2/2")),0);	
		Assert.assertEquals("Blad przy dzieleniu", 1.5, Double.parseDouble(Oblicz.zamienIOblicz("1/2+1/2+1/2/2+1/2/2")),0);	
		//Assert.assertEquals("?Infinity", Oblicz.zamienIOblicz("1/0"));
		//System.out.println(Oblicz.zamienIOblicz("1/0"));
		Assert.assertEquals( 1, Double.parseDouble(Oblicz.zamienIOblicz("1+1/2-1/2")),0);	
		
		//sprawdzanie potêgowanie
		Assert.assertEquals( 1, Double.parseDouble(Oblicz.zamienIOblicz("1^2")),0);
		Assert.assertEquals( 4, Double.parseDouble(Oblicz.zamienIOblicz("2^2")),0);
		Assert.assertEquals( 0.25, Double.parseDouble(Oblicz.zamienIOblicz("0.5^2")),0);
		Assert.assertEquals( 2, Double.parseDouble(Oblicz.zamienIOblicz("4^1/2")),0);
		Assert.assertEquals( 2, Double.parseDouble(Oblicz.zamienIOblicz("16^0.25")),0);
		Assert.assertEquals( 0, Double.parseDouble(Oblicz.zamienIOblicz("0^3")),0);
		Assert.assertEquals( 0.5, Double.parseDouble(Oblicz.zamienIOblicz("2^-1")),0);
		Assert.assertEquals( 0.5, Double.parseDouble(Oblicz.zamienIOblicz("4^-0.5")),0);
		Assert.assertEquals( 6, Double.parseDouble(Oblicz.zamienIOblicz("10-2^2")),0);
		Assert.assertEquals( 4, Double.parseDouble(Oblicz.zamienIOblicz("-2^2")),0);
		}catch(Exception e){
			Assert.fail();
		}
	
	}
	
	@Test
	public void testNawiasowanie(){
		try{
		Assert.assertEquals(3, Double.parseDouble(Oblicz.zamienIOblicz("(1+2)")), 0);
		Assert.assertEquals(3, Double.parseDouble(Oblicz.zamienIOblicz("(1+2)+1-1")), 0);
		Assert.assertEquals(6, Double.parseDouble(Oblicz.zamienIOblicz("(1+2)*2")), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("(1+2)/3")), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("(1*2)/(2*1)")), 0);
		Assert.assertEquals(0, Double.parseDouble(Oblicz.zamienIOblicz("(1+2)*(1-1)/(2-1)")), 0);
		//Assert.assertEquals("?Infinity", Oblicz.zamienIOblicz("(2)/(1-1/1)"));
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("(1+2/(2-1))/(1.5*2)")), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("1*8/(2*2*2)")), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("((-1*2)+1)+(3*4/2-4)")), 0);
		Assert.assertEquals(-2, Double.parseDouble(Oblicz.zamienIOblicz("((-1*2)+1)*(3*4/2-4)")), 0);
		Assert.assertEquals(4, Double.parseDouble(Oblicz.zamienIOblicz("2^(4-2*1)")), 0);
		Assert.assertEquals(9, Double.parseDouble(Oblicz.zamienIOblicz("(1+1+1)^(2-1+1)")), 0);
		Assert.assertEquals(-0.5, Double.parseDouble(Oblicz.zamienIOblicz("(-1/2)^(1/2+1/2)")), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("-((1+2)^2)+(10*1/1)")), 0);
		}catch(Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void testProstaZmianaZmiennych(){
		//dla jednej zmiennej
		try{
		Assert.assertEquals(3, Double.parseDouble(Oblicz.zamienIOblicz("x","x=3")), 0);
		Assert.assertEquals(6, Double.parseDouble(Oblicz.zamienIOblicz("x+x","x=3")), 0);
		Assert.assertEquals(9, Double.parseDouble(Oblicz.zamienIOblicz("x*x","x=3")), 0);
		Assert.assertEquals(12, Double.parseDouble(Oblicz.zamienIOblicz("x+(x*x)","x=3")), 0);
		Assert.assertEquals(18, Double.parseDouble(Oblicz.zamienIOblicz("x*(x+x)","x=3")), 0);
		Assert.assertEquals(11, Double.parseDouble(Oblicz.zamienIOblicz("x+2^(x)","x=3")), 0);
		Assert.assertEquals(-6, Double.parseDouble(Oblicz.zamienIOblicz("-x-x","x=3")), 0);
		Assert.assertEquals(-1, Double.parseDouble(Oblicz.zamienIOblicz("-x/x+x/x-x/x","x=3")), 0);
		Assert.assertEquals(-1, Double.parseDouble(Oblicz.zamienIOblicz("1/-2-1/2","x=3")), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("-x/-x-x/-x-x/x","x=3")), 0);
		
		//przy dwóch zmiennych
		Assert.assertEquals(4, Double.parseDouble(Oblicz.zamienIOblicz("x+y","x=3 , y=1")), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("x-y+1*2","x=1 , y=1")), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("x^x+y^y*x","x=1 , y=1")), 0);
		Assert.assertEquals(1.5, Double.parseDouble(Oblicz.zamienIOblicz("x/y+y/-x","x=2 , y=1")), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("x-y-x/-x","x=-2 , y=-3")), 0);
		Assert.assertEquals(-31, Double.parseDouble(Oblicz.zamienIOblicz("x*y*x","x=-1 , y=-31")), 0);
		}catch(Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void testWlasneFunkcje(){
		//testuje eksponente
		try{
		WlasnaFunkcjaInterfejs [] wf={new Exp()};
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("exp(x)","x=0",wf)), 0);
		Assert.assertEquals(Math.exp(1), Double.parseDouble(Oblicz.zamienIOblicz("exp(exp(x))","x=0",wf)), 0.00001);
		Assert.assertEquals(-2, Double.parseDouble(Oblicz.zamienIOblicz("-exp(x)-exp(-x)","x=0",wf)), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("exp(x-y/x+exp(0)-1)","x=1,y=1",wf)), 0);
		Assert.assertEquals(-1, Double.parseDouble(Oblicz.zamienIOblicz("-exp(x)^exp(y)","x=0,y=0",wf)), 0.01);
		}catch(Exception e){
			Assert.fail();
		}
		}
	
	@Test
	public void testWlasneFunkcjeWieloargumentowe(){
		try{
		WlasnaFunkcjaInterfejs [] wf={new Max(),new Max3()};
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("max(x,x+1)","x=0",wf)), 0);
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("max(-1,1)","x=0",wf)), 0);
		Assert.assertEquals(0, Double.parseDouble(Oblicz.zamienIOblicz("max(-x,x)","x=0",wf)), 0);
		Assert.assertEquals(25, Double.parseDouble(Oblicz.zamienIOblicz("max(x*x,x+1)","x=5",wf)), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("max(max(x+2,x-1),x+1)","x=0",wf)), 0);
		Assert.assertEquals(15, Double.parseDouble(Oblicz.zamienIOblicz("max(max(max(15,4),6),7)","x=0",wf)), 0);
		Assert.assertEquals(-4, Double.parseDouble(Oblicz.zamienIOblicz("max(max(max(-100,-4),-6),-7)","x=0",wf)), 0);
		Assert.assertEquals(-4, Double.parseDouble(Oblicz.zamienIOblicz("max(max(max(-100+2*12/3,-4),-6),x-8)","x=0",wf)), 0);
		Assert.assertEquals(-2, Double.parseDouble(Oblicz.zamienIOblicz("max(max(max(-100,-4),-6),-7)-max(-4,-2)","x=0",wf)), 0);	
	
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("maxt(x,2,x)","x=0",wf)), 0);	
		Assert.assertEquals(6, Double.parseDouble(Oblicz.zamienIOblicz("maxt(maxt(4,5,6),1,2)","x=0",wf)), 0);	
		Assert.assertEquals(12, Double.parseDouble(Oblicz.zamienIOblicz("maxt(1-1,3*4,x*1*2*3+6)","x=0",wf)), 0);	
		}catch(Exception e){
			Assert.fail();
		}
	}

	
	@Test
	public void testPolaczenieRoznychFunkcji(){
		try{
		WlasnaFunkcjaInterfejs [] wf={new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()};
		Assert.assertEquals(1, Double.parseDouble(Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2","x=1.23",wf)), 0);
		Assert.assertEquals(1.23, Double.parseDouble(Oblicz.zamienIOblicz("maxt(x,-x,0)","x=1.23",wf)), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("maxt(max(1,2),ln(x),exp-1)","x=1",wf)), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("maxt(max(1,2),ln1,exp-1)","x=1",wf)), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("maxt(max(1,2),ln1,exp-1)","x=1",wf)), 0);
		Assert.assertEquals(2, Double.parseDouble(Oblicz.zamienIOblicz("maxt(max(1,2),ln1,exp-1)","x=1",wf)), 0);
		Assert.assertEquals(0, Double.parseDouble(Oblicz.zamienIOblicz("maxt(max(x,2),ln(x),exp(-x))-maxt(max(x,2),ln(x),exp(-x))","x=1",wf)), 0);
		Assert.assertEquals(0, Double.parseDouble(Oblicz.zamienIOblicz("ln1+ln1-ln1*1*-1","x=1",wf)), 0);
		Assert.assertEquals(0, Double.parseDouble(Oblicz.zamienIOblicz("ln(x+exp(x)-1+1-x)+1*x","x=0",wf)), 0);
		}catch(Exception e){
			Assert.fail();
		}
		}
	
	
	@Test
	public void testZPlikami(){
		WlasnaFunkcjaInterfejs [] wf={new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()};
		LiczZPlikow.liczZPlikow(new File("funkcje.txt"), new File("wartosci.txt"), new File("wynik.txt"), wf);
		LiczZPlikow.liczZPlikow(new File("funk.txt"), new File("wartosci.txt"), new File("wynik.txt"), wf);
		LiczZPlikow.liczZPlikow(new File("funkcje.txt"), new File("wari.txt"), new File("wynik.txt"), wf);
		LiczZPlikow.liczZPlikow(new File("funkcje.txt"), new File("wartosci.txt"), new File("wynik.txt"));
		LiczZPlikow.liczZPlikow(new File("funkcje.txt"), new File("wartosci.txt"), new File("wynik.txt"));
		LiczZPlikow.liczZPlikow(new File("funkcje.txt"), new File("wartosci.txt"), new File("wynik.txt"), wf);
		LiczZPlikow.liczZPlikow(new File("funkcje.txt"), new File("wartosci.txt"), new File("wynik.txt"),wf);
		
	}
	
	@Test
	public void testBledy(){
		WlasnaFunkcjaInterfejs [] wf={new Max(),new Exp(),new Sin(),new Cos(),new Ln(),new Max3()};
		
		
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("1++1","x=1");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("x2","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("x+x+x.x","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("234+cd-321","");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("54/0","x=1.23.");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("((432.43+431)","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("43/11+(-1)^0.5","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("34+X/7","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("23-43)","x=1.23");
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cosx^2","x=1.23",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("34/(56-56)+32","x=1.23",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("cos(x)-sinx","x=1.23",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1.23,Y=5,Y=6",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1.23,432=43",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1.23,45-21=s",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1.23,x=1",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1.23.",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1.23,,3",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-X","x=1/0",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+coss^2","x=3",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-x","x= ds",wf);
		Assert.fail();}catch(Exception e){}
		try{Oblicz.zamienIOblicz("sin(x)^2+cos(x)^2-x","x=1fsdaf",wf);
		Assert.fail();}catch(Exception e){}
		
		
	}
		
}