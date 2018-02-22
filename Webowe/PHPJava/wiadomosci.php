<?php
$nazwa_pliku= "wiadomosci.txt";
if (!file_exists($nazwa_pliku)) { // Jak nie ma pliku
	$plik= fopen($nazwa_pliku, "w");
	fwrite($plik, "Zaczynamy rozmowę:\n");
	fclose($plik);
} else { 
$plik = fopen($nazwa_pliku, "r");
$text = fread($plik, filesize($nazwa_pliku));
fclose($plik);
echo $text;
}
?>
