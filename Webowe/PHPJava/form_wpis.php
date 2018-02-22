<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Formularz do wpisów</title>
	<script type="text/javascript" src="style.js"></script>

<link rel="stylesheet" media="screen" title="podst"
href="lab.css" type="text/css" />
<link rel="stylesheet" media="print"
href="druk.css" type="text/css" />
<link rel="alternate stylesheet" media="screen"
href="alternat.css" type="text/css" title="alter" />



</head>
<body onload="pokazStyle();wstawDate()">
<div id="listaStyli"></div>

<?php
include 'menu.php';
pokaz_menu();
?>
<center>
<form action="wpis.php" method="post"
enctype="multipart/form-data">
Nazwa użytkownika:<br /><input type="text"
name="username"><br>

Hasło:<br /><input type="password"
name="haslo"><br>

Data (RRRR-MM-DD):<br/><input type="text"
name="data" id="data" value="" onchange="sprawdzDate()"><br>

Godzina (GG:MM):<br/><input type="text"
name="godzina" id="godzina" value="" onchange="sprawdzDate()"><br>


Wpis:<br/><textarea name="wpis" cols="50" rows="10" wrap="soft"></textarea><br/>
Dodaj pliki:<br/>
	<input type="file" name="plik1" onchange="dodajPlik()"/><br/>
	<div id="pliki"></div>
<input type="submit" value="Prześlij">
<input type="reset" value="Wyczyść">
</form>

<p><script type="text/javascript">

	function wstawDate() {
var aktualnaData = new Date();

var miesiac;
if(aktualnaData.getMonth()<9) miesiac="0"+(aktualnaData.getMonth()+1); 
if(aktualnaData.getMonth()>=9)miesiac=aktualnaData.getMonth()+1; 
var dzien;
if(aktualnaData.getDate()<10)dzien="0"+aktualnaData.getDate();
else dzien=aktualnaData.getDate()
document.getElementById('data').value=aktualnaData.getFullYear()+"-"+miesiac+"-"+dzien;


var godz;
	if(aktualnaData.getHours()<10) godz="0"+aktualnaData.getHours(); else godz=aktualnaData.getHours();
var min;
	if(aktualnaData.getMinutes()<10) min="0"+aktualnaData.getMinutes(); else min=aktualnaData.getMinutes();

	var godzina=aktualnaData.getHours()+":"+aktualnaData.getMinutes();
	document.getElementById('godzina').value=godz+":"+min;
	}


function sprawdzDate() {
var string=document.getElementById('data').value;
var podzial=string.split("-");
//document.getElementById('data').value=podzial.length;
if(string.length!=10) wstawDate();
if(podzial.length!=3) wstawDate() ;
if(podzial[0]<=0) wstawDate();
if((1>podzial[1])||(podzial[1]>12)) wstawDate();
if((0>podzial[2])||(podzial[2]>31)) wstawDate();

string=document.getElementById('godzina').value;
//document.getElementById('data').value=string.length;
var pod=string.split(':');
if(string.length!=5) wstawDate();
if(pod.length!=2) wstawDate();
if((pod[0]>23)||(pod[0]<0)) wstawDate();
if((pod[1]>59)||(pod[1]<0)) wstawDate();
	}

function dodajPlik(){
var pliki = document.getElementById("pliki"), 
ilosc  = pliki.children.length,
	files = ilosc / 2+2; 
	for (var i = 0; i <ilosc; i += 2) { 
		if (pliki.children[i].value == "") {
			return;
		}
	}

	var input = document.createElement("input"); 
	input.type = "file"; 
	input.name = "plik"+ files; 
	input.onchange = function() { dodajPlik(); }; 
	pliki.appendChild(input); 
	pliki.appendChild(document.createElement("br")); 
}

</script></p>

</center>
</body>
</html>
