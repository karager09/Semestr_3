<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Menu</title>
</head>
<body>
	<?php
function pokaz_menu() {
	echo " 
<div>
<ul>

<li style=\"display: inline; list-style-type: none; padding-right: 20px;\"><a href=\"blog.php\">Lista blogów</a></li>

<li style=\"display: inline; list-style-type: none; padding-right: 20px;\"><a href=\"form_wpis.php\">Dodaj wpis do bloga</a></li>

<li style=\"display: inline; list-style-type: none; padding-right: 20px;\"><a href=\"form_nowy.php\">Stwórz swój blog!</a></li>

</ul>
</div> 
";
}
?>
</body>
</html>
