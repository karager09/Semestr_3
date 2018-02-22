<?php
$nazwa_pliku = "wiadomosci.txt";
$plik = fopen($nazwa_pliku, "a");
$text = $_GET["nick"].": ".$_GET["message"]."\n";
fwrite($plik, $text);
$linijki_plik = count(file($nazwa_pliku));
fclose($plik);

while ($linijki_plik> 25) { 
	$plik = file($nazwa_pliku);
	unset($plik[0]);
	file_put_contents($nazwa_pliku, $plik);
	$linijki_plik = count(file($nazwa_pliku));
}
?>

