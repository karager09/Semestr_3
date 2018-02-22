<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Komentarz PHP</title>

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
//URL w postaci ...cośtam.php?nazwabloga/RRRRMMDDGGmmSSUU
$url = urldecode($_SERVER['HTTP_REFERER']); // Zeby otrzymać nazwę bloga i wpisu
//print_r($url);
$blog_wpis= explode("?", $url); // Dzielimy względem ?
//$count_url = count($blog_wpis);//sprawdzamy ilość elementów
//print_r($blog_wpis[1]);
//$data_split = explode("/", $blog_wpis[1]);
//$blog = $data_split[0];
//$wpis = $data_split[1];
//print_r("Nazwa bloga itp to: ".$bolg."/".$wpis);

/*} else {
	$blog = $url_split[1]."?";
	for ($i = 2; $i < ($count_url - 1); $i++) {
		$blog .= $url_split[$i]."?";
	}
	$wpis_split = explode("/", $url_split[$count_url - 1]);
	$blog .= $wpis_split[0];
	$wpis = $wpis_split[1];
}*/


define(KLUCZ,123456);
$sem=sem_get(KLUCZ);

sem_acquire($sem);
$sciezka=$blog_wpis[1].".k";
if(!is_dir($sciezka)) mkdir($sciezka,0755);

$i=-1;
do{
++$i;
}while(is_file($sciezka."/".$i));

$plik=fopen($sciezka."/".$i,"w");
chmod($plik,0755);
fputs($plik,$_REQUEST['rodzaj']."\n");
fputs($plik,date("Y")."-".date("m")."-".date("d").", ".date("H").":".date("i").":".date("s")."\n");
fputs($plik,$_REQUEST['username']."\n");
fputs($plik,$_REQUEST['komentarz']."\n");
fclose($plik);
sem_release($sem);

print_r("Dodano ".$_REQUEST['rodzaj']." komentarz:<br>".$_REQUEST['komentarz']."<br>Uzytkownik: ".$_REQUEST['username']);








?>
</body>
</html>
