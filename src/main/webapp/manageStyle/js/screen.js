// JavaScript Document
    total = document.documentElement.clientHeight;
	colHeight = total-document.getElementById("col1").offsetTop;
	document.getElementById("col1").style.height=colHeight+"px";
	document.getElementById("col2").style.height=colHeight+"px";