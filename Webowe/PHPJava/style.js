function pokazStyle() {
	var list = ""; 
	for (var i = 0; (styl = document.getElementsByTagName("link")[i]); i++) { 				if (styl.getAttribute("title")) { 
			nazwa_stylu = styl.getAttribute("title"); 
			list = list+"<a href=\"#\" onclick=\"ustawStyl(\'" + nazwa_stylu + "\'); return false;\">Zmień styl na " + nazwa_stylu + ".</a><br/>"; 		}
	}
	document.getElementById("listaStyli").innerHTML = list; 
}

function ustawStyl(nazwa) {
	var styl;
	for (var i = 0; (styl = document.getElementsByTagName("link")[i]); i++) 
	{ 
		if (styl.getAttribute("title")) 
		{ 	
		styl.disabled = true; 
		if (styl.getAttribute("title") == nazwa) styl.disabled = false;
		}
	}
}


function obecnyStyl() {
	var styl;
	for (var i = 0; (styl = document.getElementsByTagName("link")[i]); i++) 
	{ 		
	if ((styl.getAttribute("title")) && (!styl.disabled)) return styl.getAttribute("title"); 
	}
	return null;
}


function zrobCookie(styl, dni) {
	if (dni) 
	{ 
	var date = new Date();
	date.setTime(date.getTime() + (dni*24*60*60*1000)); 
	var wygasa = "; expires=" + date.toGMTString();
  	}
	else wygasa = "";
	document.cookie = "style=" + styl + wygasa + ";path=/"; 
	    //nazwa=wartosc;expires=data;domain=domena;path=sciezka 
	// style=main; expires=Thu, 2 Aug 2001 20:47:11 UTC; path=/

}


function odczytCookie() {

	var podzial = document.cookie.split(';'); 
	var nazwa = "style=";
	for(var i = 0; i < podzial.length; i++) {
		var c = podzial[i];
		c=c.trim(); 
		if (c.indexOf(nazwa) == 0) return c.substring(nazwa.length, c.length); 

	}
	return null;
}


window.onload = function(e) {
	var zapisany_styl = odczytCookie(); 
	var styl = zapisany_styl ? zapisany_styl : obecnyStyl();
	ustawStyl(styl); 
}

window.onunload = function(e) {
	var styl = obecnyStyl(); 
	zrobCookie( styl, 14); 
}

var zapisany_styl = odczytCookie();
var styl = zapisany_styl ? zapisany_styl : obecnyStyl();
ustawStyl(styl);
