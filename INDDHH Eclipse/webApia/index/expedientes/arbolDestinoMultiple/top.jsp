<%response.setCharacterEncoding(com.dogma.Parameters.APP_ENCODING);%>
<html><head>
<title>Top</title>
</head>
<body topmargin="5" leftmargin="5">

<button id='btnDestino' title='Elegir Destino'>Elegir Destino</button>

</body>
</html>

<script LANGUAGE="javascript">
function btnDestino.onclick(){		
	var sDestino = showModalDialog( "armar.jsp","","dialogWidth:750px; dialogHeight:400px;status=no" );		
	if (sDestino == 'cancel' || sDestino == '' || sDestino == null){
		alert("No se ha elegido un destino valido");
	}
	else{			
		alert("El destino es " + sDestino);
	}
}
</script>
