<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Blog PHP</title>

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




if ((!isset($_GET["nazwa"]))||(!is_dir($_GET['nazwa']))) { //podany blog nie istnieje lub nie został podany
	if($_GET["nazwa"]!=null)
	echo "Podany blog nie istnieje!!<br/>";
	echo "Lista dostępnych blogów:";

$blogi = glob('*', GLOB_ONLYDIR); //Otrzymuje tylko katalogi, a wnich są blogi
	echo "<ul>";
	foreach ($blogi as &$blog) {
	echo "<li><a href=blog.php?nazwa=$blog>$blog</a></li>";
	}
	echo "</ul>";

}else{//jeśli znaleziono blog
echo "<h1>Blog: ".$_REQUEST['nazwa']." <br/></h1>";
$blog=$_REQUEST['nazwa'];
echo "<h2>Opis bloga</h2><p>";//Wypisani opisu bloga z pliku info.txt
$info=file($blog."/info.txt");
for($i=2;$i<count($info);$i++)
echo $info[$i];
echo "</p>";


$pliki =array_filter(glob($blog.'/*'), 'is_file'); // tablica zawierajaca tylko pliki

foreach ($pliki as $plik) {
	
	//print_r($plik."<br>");
	$info=pathinfo($plik);
	//print_r($info["extension"]);	
	if($info["extension"]==""){
		if($otwarty_plik=fopen($plik,"r")){
			if(flock($otwarty_plik, LOCK_EX)){
			echo "<h2>Wpis:</h2><br/><p>";
			$dane=fread($otwarty_plik,filesize($plik));
			print_r($dane."</p><br><br>");
			flock($otwarty_plik, LOCK_UN);
			}else "Nie mam dostępu do pliku.<br/>";
			fclose($otwarty_plik);
		}else echo "Nie udało się otworzyć wpisu...<br>";

$i = 0;
	foreach($pliki as $przeslane){
	/*$info_przeslany=pathinfo($przeslane);
	//echo $info_przeslany['filename']."<br>";

		if($info_przeslany['filename']==($info['filename'].'1')){
		echo "<h4>Załączone pliki:</h4>";
		echo "<a href=\"$przeslane\">".$info_przeslany['basename']."</a><br/>";	
		}

		if($info_przeslany['filename']==($info['filename'].'2')){
		echo "<a href=\"$przeslane\">".$info_przeslany['basename']."</a><br/>";	
		}
		
		if($info_przeslany['filename']==($info['filename'].'3')){
		echo "<a href=\"$przeslane\">".$info_przeslany['basename']."</a><br/>";	
		}*/

		if (preg_match('/^'.preg_quote($plik, '/').'[1-9]?[0-9]\..*$/', $przeslane)) { 
					if ($i == 0) { 
					echo "<h4>Załączone pliki:</h4>";
					$i++;
					}
				echo "<a href=\"$przeslane\">".basename($przeslane)."</a><br/>";
				}
	}


	$komentarze=$plik.".k";
	if(is_dir($komentarze)){
		echo "<h3>Komentarze:</h3><br/>";
		$i=0;
		while(is_file($komentarze."/".$i)){
			if($koment=fopen($komentarze."/".$i,"r")){
			$dane=fread($koment,filesize($komentarze."/".$i));
			print_r("Komentarz ".$dane."<br>");
			fclose($otwarty_plik);


			}			
			++$i;
		}


	}

	
	echo "<a href =\"form_kom.php?$plik\">Dodaj komentarz.</a><br>";
	}
	


	} 

}


?>
<a href="komunikator.html">Komunikator</a>


</body>
</html>
