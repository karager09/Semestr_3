<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Lab PHP</title>
</head>
<body>
	<?php
	//print_r($_GET);
	//print_r($_POST);

	function witaj($imie='Yo') {
	if($imie=="piotr")
 	 {return 'Cześć ' . $imie . ', jesteś moim panem i władcą!';}
	return 'Cześć ' . $imie . '!';
	}

	print_r(witaj($_REQUEST['username']));
	/*for($i=0;$i<10;++$i){
		print_r(witaj($_REQUEST['username']));
	}*/
	//header('Content-type: text/plain');
/*
$plik = fopen($_REQUEST['id'].'.txt', 'r');
if($plik==NULL){echo 'nie ma';}//print_r(" Nie ma takiego pliku");}
else{
while (!feof($plik)) {
  $s = fgets($plik);
  echo $s;

}
fclose($plik);
}*/


$plik = fopen('slownik'.'.txt', 'r');
if($plik==NULL){print_r("Nie ma slownika");}
else{
while (!feof($plik)) {

  $s = fgets($plik);
	
	$czy=1;
	$i=0;
while(strlen($_REQUEST['slowo'])>$i)
	{
	
	if(strlen($_REQUEST['slowo'])!=(strlen($s)-1)){$czy=0;break;}
	if($_REQUEST['slowo'][$i]=='_'){++$i;continue;}
	if($_REQUEST['slowo'][$i]!=$s[$i]){$czy=0;}
	++$i;
	}
if($czy==1) echo $s;

}
fclose($plik);
}


	






?>
</body>
</html>
