<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
 <title>REST GET echo service</title>
</head>
<body>
<p>
This simple PHP script will output all the get variables which where sent in the last HTTP request.
</p>
<p>
GET variables received: <br/>
</p>
<?php
print_r($_GET);
?>
</body>
</html>