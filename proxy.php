<?php
$content = isset($_REQUEST['cnt']) ? $_REQUEST['cnt'] : '';
$sock = fsockopen($_REQUEST['srv'], $_REQUEST['prt']);
fwrite($sock, $_REQUEST['meth'] . " " . $_REQUEST['pth'] . " HTTP/1.0\n");
if(strlen($content)) fwrite($sock, "Content-Length: " . strlen($content) . "\n\n$content\n");

$resp = '';
while(!feof($sock)) $resp .= fread($sock, 100);
fclose($sock);
list(, $body) = explode("\n\n", $resp, 2);
echo $body;

