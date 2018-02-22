<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Lab PHP</title>

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
	//print_r(md5($_REQUEST['haslo']));

define(KLUCZ,123456);
$sem=sem_get(KLUCZ);
sem_acquire($sem);
if(!is_dir($_REQUEST['nazwabloga'])){
mkdir($_REQUEST['nazwabloga'], 0777);
$plik = fopen($_REQUEST['nazwabloga'].'/info.txt',"w");
	fputs($plik,$_REQUEST['username']."\n");
	//print_r(md5($_REQUEST['haslo']));
	fputs($plik,md5($_REQUEST['haslo'])."\n");
	fputs("Opis bloga:\n");
	fputs($plik,$_REQUEST['opisblogu']."\n");
	chmod($plik,0755);
print_r("Utworzono blog!\nNazwa użytkownika to:".$_REQUEST['username']."\n");
print_r("Blog o nazwie:".$_REQUEST['nazwabloga']);
}
else print_r("Utworzenie bloga nie powiodło się.<br>Blog o takiej nazwie już istnieje!");
sem_release($sem);

?>
</body>
</html>
