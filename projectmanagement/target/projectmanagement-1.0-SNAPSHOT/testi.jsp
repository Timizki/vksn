<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<meta name="author" content="Timo 'Timii' Paananen" />
	<meta name="keywords" content="Projektienhallinta project management" />
	<meta name="description" content="" />	
	<title>Projektienhallinta</title>
 	<link href="/css/style.css" rel="stylesheet" type="text/css" />
	<link href="/js/ext-2.2/resources/css/ext-all.css" ref="stylesheet" type="text/css" />

    	<!-- base library -->
		<script type="text/javascript" src="/js/ext-2.2/adapter/ext/ext-base.js"> </script>
		<script type="text/javascript" src="/js/ext-2.2/ext-all-debug.js"> </script>
		<script type="text/javascript" src="/js/ext-2.2/ext-all.js"> </script>
		<script type="text/javascript" src="/js/login.js"> </script> 
         <!-- overrides to base library -->
 
        <!-- extensions -->
 
        <!-- page specific -->
 
        <script type="text/javascript">
        Ext.BLANK_IMAGE_URL = '../../resources/images/default/s.gif';
 
        Ext.onReady(function(){
 
           console.info('woohoo!!!');
 
        }); //end onReady
        </script> 
    </head>
    <body>
	<input type="button" id="nappi" value="nappi"/>
<div id="hello-win" class="x-hidden">
    <div class="x-window-header">Hello Dialog</div>
    <div id="hello-tabs">
        <div class="x-tab" title="Hello World 1">
        </div>
        <div class="x-tab" title="Hello World 2">

        </div>
    </div>
</div>

    </body>
</html>