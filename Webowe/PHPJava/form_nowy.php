<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Formularz do zakładania bloga</title>
	<script type="text/javascript" src="style.js"></script>

<link rel="stylesheet" media="screen" title="podst"
href="lab.css" type="text/css" />
<link rel="stylesheet" media="print"
href="druk.css" type="text/css" />
<link rel="alternate stylesheet" media="screen"
href="alternat.css" type="text/css" title="alter" />



</head>
<body onload="pokazStyle()";>
<div id="listaStyli"></div>
<?php
include 'menu.php';
pokaz_menu();
?>
<center>

<form action="nowy.php" method="post">
Nazwa użytkownika:<br /><input type="text"
name="username"><br>

Hasło:<br /><input type="text"
name="haslo"><br>

Nazwa bloga:<br /><input type="text"
name="nazwabloga"><br>

Opis bloga:<br/><textarea name="opisblogu" cols="50" rows="10" wrap="soft"></textarea><br/>

<input type="submit" value="Prześlij">
<input type="reset" value="Wyczyść">
</form>
</center>
</body>
</html>

