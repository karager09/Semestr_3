<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Formularz do komentarzy</title>

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
?>

<center>
<form action="koment.php" method="post">
Nazwa użytkownika:<br /><input type="text"
name="username"><br>

Rodzaj komentarza:<br />
<select name="rodzaj">
  <option value="pozytywny">Pozytywny</option>
  <option value="neutralny">Neutralny</option>
  <option value="negatywny">Negatywny</option>
</select>
<br>


Komentarz:<br/><textarea name="komentarz" cols="50" rows="10" wrap="soft"></textarea><br/>
<input type="submit" value="Prześlij">
<input type="reset" value="Wyczyść">
</form>
</center>
</body>
</html>
