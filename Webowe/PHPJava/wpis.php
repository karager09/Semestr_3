<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Wpis PHP</title>
	<script type="text/javascript" src="style.js"></script>

<link rel="stylesheet" media="screen" title="podst"
href="lab.css" type="text/css" />
<link rel="stylesheet" media="print"
href="druk.css" type="text/css" />
<link rel="alternate stylesheet" media="screen"
href="alternat.css" type="text/css" title="alter" />


</head>
<body onload="pokazStyle()"">
<div id="listaStyli"></div>

	<?php
include 'menu.php';
pokaz_menu();


$katalogi = glob('*', GLOB_ONLYDIR);

foreach($katalogi as $blog){
$plik=fopen($blog."/info.txt","r");//otwieram pliki po kolei
if($plik!=null){
$zawartosc=fread($plik,filesize($blog."/info.txt"));//czytam z pliku
$podzial=explode("\n",$zawartosc); //dziele zawartosc nowymi linijkami
//print_r($podzial[0]."");
//print_r($podzial[1]."    ");
if(($podzial[0]==$_REQUEST['username'])&&($podzial[1]==md5($_REQUEST['haslo']))){print_r("Znaleziono odpowieni katalog.<br>");//Nazwa użytkownika i hasło się zgadzają
//if($podzial[0]==$_REQUEST['username'])print_r("Nazwa uzytkownika ok<br>");
//if($podzial[1]==md5($_REQUEST['haslo']))print_r("Haslo tez!");

$data=explode("-",$_REQUEST['data']);//dziele date
//print_r($data[1]);

$godzina=explode(":",$_REQUEST['godzina']);//dziele godzine

//print_r(date(s));
$i=-1;
$sekundy=date(s);
//if($sekundy<10)$sekundy="0".$sekundy;


define(KLUCZ,123123);
$sem=sem_get(KLUCZ);
sem_acquire($sem);
do {
++$i;
$unikalny_kod=$i;
if($unikalny_kod<10)$unikalny_kod="0".$unikalny_kod;

}while(is_file($blog."/".$data[0].$data[1].$data[2].$godzina[0].$godzina[1].$sekundy.$unikalny_kod));//sprawdzam czy istnieją już pliki o takiej nazwie

$nazwa_utworzonego=$blog."/".$data[0].$data[1].$data[2].$godzina[0].$godzina[1].$sekundy.$unikalny_kod;//sciezka do prawidłowego pliku

print_r("<br>Utworzono plik:<br>".$nazwa_utworzonego);
print_r("<br>Treść<br>".$_REQUEST['wpis']."<br>");


$szukany_plik=fopen($nazwa_utworzonego,"w");
chmod($szukany_plik,0755);
fputs($szukany_plik,$_REQUEST['wpis']);
fclose($szukany_plik);
sem_release($sem);
/*
$plik1=basename($_FILES['plik1']['name']);//wyciągam samą nazwę pliku
//print_r($plik1."<br>");
$plik2=basename($_FILES['plik2']['name']);
$plik3=basename($_FILES['plik3']['name']);
//var_dump($_FILES);
	if ($plik1 != "") { 
	//print_r("<br>WSZEDŁEM");
	$info = pathinfo($plik1);//informacje o pliku
	move_uploaded_file($_FILES['plik1']['tmp_name'], $nazwa_utworzonego."1.".$info['extension']);
	}

	if ($plik2 != "") {
	$info=pathinfo($plik2);
	move_uploaded_file($_FILES['plik2']['tmp_name'],$nazwa_utworzonego."2.".$info['extension']);
	}
	if ($plik3 != "") {
	$info=pathinfo($plik3);
	move_uploaded_file($_FILES['plik3']['tmp_name'],$nazwa_utworzonego."3.".$info['extension']);
	}*/
//print_r($_FILES);
$i=1;

while(isset($_FILES['plik'.$i])){

$plik=basename($_FILES['plik'.$i]['name']);
if ($plik != "") { 
	$info = pathinfo($plik);//informacje o pliku
	move_uploaded_file($_FILES['plik'.$i]['tmp_name'], $nazwa_utworzonego.$i.".".$info['extension']);
}
$i++;
}
}
}
}if($nazwa_utworzonego==null)print_r("Nie udało się stworzyć katalogu. Podano nieprawidłowe dane");

?>
</body>
</html>
