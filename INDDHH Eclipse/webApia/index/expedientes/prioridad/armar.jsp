<%response.setCharacterEncoding(com.dogma.Parameters.APP_ENCODING);%>
<%@page import="com.dogma.Parameters"%>
<%@page import="com.dogma.EnvParameters"%>
<%@page import="uy.com.st.adoc.expedientes.constantes.Mensajes"%>
<%@page import="com.dogma.UserData"%>
<%@page import="uy.com.st.adoc.expedientes.conf.ConfigurationManager"%>
<%@page import="com.dogma.Configuration"%>
<%@page import="uy.com.st.adoc.mensajes.MensajeDAO"%>
<%@page import="uy.com.st.adoc.expedientes.constantes.Values"%>
<%@page import="com.dogma.controller.ThreadData"%>
<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-store");
response.setDateHeader("Expires",-1); 

//**************THIS IS USED TO GET THE LABEL SET OF THE USER***************//
String styleDirectory = "default";
Integer environmentId = null;
MensajeDAO mensajeDao = new MensajeDAO();
int currentLanguage = 1;
//com.dogma.UserData uData = (com.dogma.UserData)session.getAttribute("sessionAttribute");
UserData uData = ThreadData.getUserData();
if (uData!=null) {
	environmentId = uData.getEnvironmentId();
	currentLanguage = uData.getLangId();
	styleDirectory = EnvParameters.getEnvParameter(environmentId,EnvParameters.ENV_STYLE);
}

boolean arteeDeshabilitado = true;
%>

<HTML>
<HEAD>
	<script TYPE="text/javascript" LANGUAGE="javacript" SRC="toc.js"></script>
	<script TYPE="text/javascript" LANGUAGE="javascript" SRC="<%=Parameters.ROOT_PATH%>/expedientes/scripts/image.js"></script>
	<link href="toc.css" type="text/css" rel="styleSheet">

<title><%=mensajeDao.obtenerMensajePorCodigo("VAL_TITULO_SELECCIONAR_DESTINO_PASE_JSP_TXT", currentLanguage, environmentId)%></title>

<SCRIPT LANGUAGE=javascript>
var MSIE = window.navigator.appVersion.indexOf("MSIE")>=0;

var URL_ROOT_PATH	 	= "<%=Parameters.ROOT_PATH%>";
var URL_STYLE_PATH		= "<%=Parameters.ROOT_PATH%>/styles/<%= styleDirectory %>"

var FORCE_SYNC = true;
var TAB_ID_REQUEST = "<%="&tabId=" + request.getParameter("tabId").toString() +"&tokenId="+ request.getParameter("tokenId").toString()%>"

function fnFlash(oTitle){
	oList=document.all[oTitle.sourceIndex + 1];
	if(oList.className=="tocItemHide"){
		oList.className="tocItemShow";
		document.frmMain.tipPagina.value=oTitle.tipPag
	}else{
		oList.className="tocItemHide";
		document.frmMain.tipPagina.value=oTitle.tipPag
	}
	
}
var sep1;

/*function cancel(){
	window.returnValue="cancel";
	window.close();
}*/

function whenLoad(){  
	document.getElementById("waittoload").style.display="none";
	document.getElementById("treefield").style.display="";		
	sep1 = document.getElementById("textSeparador1").value;	
}

function getModalReturnValue(modal) {
	debugger;
	var destino = "";	
	destino = document.getElementById("organismoDestino").value 
		+ sep1 + document.getElementById("textNombOrganismoDestino").value
		+ sep1 + document.getElementById("textIdMesaDestino").value
		+ sep1 + document.getElementById("textMesaDestino").value
		+ sep1 + document.getElementById("modoEnvio").value;
	debugger;
	if (destino == "" ){
		alert("<%= mensajeDao.obtenerMensajePorCodigo("MSG_ELEGIR_UN_DESTINO_JSP", currentLanguage, environmentId)%>"); //alert("Debe elegir un destino");
	}else if (document.getElementById("modoEnvio").value == '<%=Values.VAL_FORMA_ENVIO_ARTEE_OE_STR%>' && document.getElementById("textMesaDestino").value == ""){
		alert("<%= mensajeDao.obtenerMensajePorCodigo("MSG_ELEGIR_MESA_ENTRADA_JSP", currentLanguage, environmentId)%>"); //alert("Debe elegir una mesa de entrada.");
	}else{
		if (document.getElementById("modoEnvio").value == '<%=Values.VAL_FORMA_ENVIO_ARTEE_OE_STR%>' && document.getElementById("textHayMEenOE").value == "0"){
			destino = "";
			alert("<%= mensajeDao.obtenerMensajePorCodigo("MSG_ORGANISMO_SIN_MESA_ENTRADA_JSP", currentLanguage, environmentId)%>"); //alert("No hay mesa de entrada para ese organismo!");
		}else{
			//var truthBeTold = window.confirm("�Realmente desea realizar el pase?");
			//if (truthBeTold) {		
				modal.setearDestino(destino);
				return true;
			//}
		}
	}  	
}

var ajax;

function funcionCallBackCargarUsuarios(){
	// Comprobamos si la peticion se ha completado (estado 4)
	if( ajax.readyState == 4 ){
		// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
		if( ajax.status == 200 ){
			// Escribimos el resultado en la pagina HTML mediante DHTML
			var result = ajax.responseText;
			//alert(result);			
			document.getElementById("oPrimaryTOCUsr").innerHTML = result;
			if (result.indexOf("<table><table>") != -1){
				document.getElementById("textHayUsrOficina").value = "0";				
			}else{
				document.getElementById("textHayUsrOficina").value = "1";
			}
		}
	}	
}

function cargarUsuarios(flag){
	// Creamos el control XMLHttpRequest segun el navegador en el que estemos
	if( window.XMLHttpRequest )
		ajax = new XMLHttpRequest(); // No Internet Explorer
	else
		ajax = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
		// Almacenamos en el control al funcion que se invocara cuando la peticion
		// cambie de estado
	
	ajax.onreadystatechange = funcionCallBackCargarUsuarios;
	// Enviamos la peticion	
	var URL = "<%=ConfigurationManager.getServerAddress(environmentId,currentLanguage) + Configuration.ROOT_PATH%>/expedientes/arbolDestino/cargarUsuarios.jsp?unidad=" + document.getElementById("textIdOficinaDestino").value + "&flag=" + flag;					
	//alert(URL);
	ajax.open( "GET", URL, true );	
	ajax.send( "" );	
}

//Funciones para cargar Mesa de entrada
function funcionCallBackCargarMesasEntrada(){
	// Comprobamos si la peticion se ha completado (estado 4)
	if( ajax.readyState == 4 ){
		// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
		if( ajax.status == 200 ){
			funcionCallBackCargarMesasEntradaLoaded()
		}
	}	
}

function funcionCallBackCargarMesasEntradaLoaded(){
	// Escribimos el resultado en la pagina HTML mediante DHTML
	var result = ajax.responseText;
	document.getElementById("oPrimaryTOCUsr").style.display="block";
	document.getElementById("oPrimaryTOCUsr").innerHTML = result;
	if (result.indexOf("<table><table>") != -1){
		document.getElementById("textHayMEenOE").value = "0";				
	}else{
		document.getElementById("textHayMEenOE").value = "1";
	}
}

function cargarMesasEntrada(flag){
	
	// Creamos el control XMLHttpRequest segun el navegador en el que estemos
	if( !MSIE ){
		ajax = new XMLHttpRequest(); // No Internet Explorer
		ajax.onload = function(){
			funcionCallBackCargarMesasEntradaLoaded();
		}
	}else{
		ajax = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
		// Almacenamos en el control al funcion que se invocara cuando la peticion
		// cambie de estado
		ajax.onreadystatechange = funcionCallBackCargarMesasEntrada;
	}
	// Enviamos la peticion	
	var URL = "<%=ConfigurationManager.getServerAddress(environmentId,currentLanguage) + Configuration.ROOT_PATH%>/expedientes/arbolOrganismoExternoDestino/cargarMesasEntrada.jsp?orgext=" + document.getElementById("organismoDestino").value + "&flag=" + flag;					
	//alert(URL);
	ajax.open( "POST", URL, false );	
	ajax.send( "" );	
	
}


function funcionCallbackFoto(){
	// Comprobamos si la peticion se ha completado (estado 4)
	if( ajax.readyState == 4 ){
		// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
		if( ajax.status == 200 ){
			// Escribimos el resultado en la pagina HTML mediante DHTML
			var result = ajax.responseText;
						
			var vec = result.split("<%=Mensajes.EXP_SEPARADOR_PARAMETROS1%>");
			
			if (vec[1] == 'true'){
				if (vec[2] == ''){			
					alert("El usuario se encuentra asuente de la oficina hasta el d�a: " + vec[4]);
				}else{
					alert("El usuario se encuentra ausente hasta el d�a: " + vec[4] + ".\nMensaje del usuario: " + vec[2]);
					//document.getElementById("textUsuarioDestino").value = vec[2];
				}
			}else{
				//alert("El usuario no esta de licencia");
			}	
			//document.getElementById("imgFoto").width = 200;
			//document.getElementById("imgFoto").height = 200;				
			document.getElementById("imgFoto").src=vec[0];			
			//ajustarIMG(document.getElementById("imgFoto"));								
		}
	}	
}

function cargarFOTO(usr){	
	// Creamos el control XMLHttpRequest segun el navegador en el que estemos
	if( window.XMLHttpRequest )
		ajax = new XMLHttpRequest(); // No Internet Explorer
	else
		ajax = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
		// Almacenamos en el control al funcion que se invocara cuando la peticion
		// cambie de estado
	
	ajax.onreadystatechange = funcionCallbackFoto;
	// Enviamos la peticion		
	var URL = "<%=ConfigurationManager.getServerAddress(environmentId,currentLanguage) + Configuration.ROOT_PATH%>/expedientes/arbolDestino/cargarImg.jsp?usr=" + usr + TAB_ID_REQUEST;
	//alert(URL);	
	ajax.open( "GET", URL, true );
	ajax.send( "" );	
}


function recorrerNodos(el, texto){
	
	var x = el.childNodes;
	if (x == undefined){
	  return;
	  }
	  
	for (var i=0, len=x.length; i<len; ++i){
		
	  if(x[i].title != undefined){
	    if( x[i].title != ''){
	       itemText = x[i].title.toUpperCase();
	         if(itemText.indexOf(texto.toUpperCase())>-1){
	           // mostrarSubtree (x[i]);
	            //x[i].scrollIntoView();
	           // seleccionarNodo(x[i]);
	           // agregarNodo(x[i].title);
	            //found = true;
	            // return;
	            x[i].style.display = "block";
	         }else{
	  		   x[i].style.display = "none";
	  	  	 }   

	     }else{
			  // x[i].style.display = "none";
		 }   

	   }else{
		   //if (x[i].style != undefined){
			//   x[i].style.display = "none";
		   //}
	   }   

	  recorrerNodos(x[i], texto);
	  //if (found){
	  // return;
	  //}
	}
}

function busquedaOnKeyPress(){


	var el2;
	
	if (document.getElementById("modoEnvio").value == '<%=Values.VAL_FORMA_ENVIO_PAPEL_OE_STR%>'){
		el2 = document.getElementById('oPrimaryTOC2');
	
	}else{
		el2 = document.getElementById('oPrimaryTOC');
	}
	var texto2 = document.getElementById('busqueda').value;
	
	recorrerNodos(el2, texto2);
	return false;
	
}


function show(zap) {
	 if (document.getElementById) {
	  var abra = document.getElementById(zap).style;
	  abra.display = "block";
	  return false;
	  } else {
	  return true;
	 }
}

function hide(zap) {

	if (document.getElementById) {
	  var abra = document.getElementById(zap).style;
	  abra.display = "none";
	   
	  return false;
	} else {
	  return true;
	}
}

function displayEntOE(){

	alert('Eligi� pasar el expediente en forma manual. �Verific� que el destino no se encuentra en la lista de organismos habilitados para recibir expedientes en forma electr�nica?');
	show('oPrimaryTOC2');
	hide('oPrimaryTOC');
	document.getElementById("modoEnvio").value = '<%=Values.VAL_FORMA_ENVIO_PAPEL_OE_STR%>';	
	document.getElementById("oTocDivMesaEntradaG").style.display = "none";
	document.getElementById("btnEntOE").disabled = "true";
	document.getElementById("btnARTEE").disabled = "";

}

function displayARTEE(){
		
	show('oPrimaryTOC');
	hide('oPrimaryTOC2');
	document.getElementById("modoEnvio").value = '<%=Values.VAL_FORMA_ENVIO_ARTEE_OE_STR%>';
	document.getElementById("oTocDivMesaEntradaG").style.display = "block";
	document.getElementById("btnARTEE").disabled = "true";
	document.getElementById("btnEntOE").disabled = "";
}

</script>


</HEAD>

<BODY onLoad="whenLoad()">
	<form name="frmMain" id="frmMain" method="post">
		
		<input id="organismoDestino" type="hidden">		
		<input id=textSeparador1 name=textSeparador1 type="hidden" value="<%=Mensajes.EXP_SEPARADOR_PARAMETROS1%>">
		<input id=textSeparador2 name=textSeparador2 type="hidden" value="<%=Mensajes.EXP_SEPARADOR_PARAMETROS2%>">
		<input id=textHayMEenOE name=textHayMEenOE type="hidden">
		<input id=textNombOrganismoDestino name=textNombOrganismoDestino type="hidden">
		<input id=textMesaDestino name=textMesaDestino type="hidden">
		<input id=textIdMesaDestino name=textIdMesaDestino type="hidden">
		<% if (!arteeDeshabilitado) {%>
		<input id=modoEnvio name=modoEnvio type="hidden" value="<%=Values.VAL_FORMA_ENVIO_ARTEE_OE_STR%>">
		<%} else { %>
		<input id=modoEnvio name=modoEnvio type="hidden" value="<%=Values.VAL_FORMA_ENVIO_PAPEL_OE_STR%>">		
		<%} %>
		<input readonly id="waittoload" value="Espere un momento.." style="background-color:yellow;color:navy;position:absolute;top:120px;left:130px;display:none">
		
<table border=0>
<tr style="HEIGHT:90%">
	<td>
		<div id="tree" onclick="treeClick(event)" style="width:100%; HEIGHT:100%">	
		<fieldset id="treefield" name="treefield" style="display:none"> <!-- ; HEIGHT: 287px; WIDTH: 300px -->
			<legend><font style="FONT-FAMILY: verdana; FONT-SIZE: 8pt;"><%=mensajeDao.obtenerMensajePorCodigo("VAL_LBL_SELECCIONAR_PRIORIDAD_JSP_TXT", currentLanguage, environmentId)%></font></legend>
			<BLOCKQUOTE CLASS="body">
				<DIV id="oTocDiv" style="BACKGROUND-COLOR: <%=Mensajes.com_st_apia_expedientes_style_arbol_destino%>; BORDER-BOTTOM: #505050 1px solid; BORDER-LEFT: #505050 1px solid; BORDER-RIGHT: #505050 1px solid; BORDER-TOP: #505050 1px solid; FONT-FAMILY: verdana; FONT-SIZE: 8pt; HEIGHT: 290px; OVERFLOW: auto; WIDTH: 441px">
				<UL ID="oPrimaryTOC" <%if (arteeDeshabilitado){ %> style="display:none" <% }%>>   
				<%try{ 																							
					if (uData!=null){
						//UserData uData = (UserData)session.getAttribute(Parameters.SESSION_ATTRIBUTE);
						if (uData.getUserAttributes().get("ESTRUCTURA_JERARQUICA_2")!=null){
							String htmlData = (String)uData.getUserAttributes().get("ESTRUCTURA_JERARQUICA_2");
							out.print( htmlData );
						}else{
							System.out.println( "NO HAY DATOS EN SESSION 1" );
						}
					}else{
						System.out.println( "NO HAY DATOS EN SESSION 2 " );
					}
					System.out.println("FIN");
				}
				catch(Exception e){e.printStackTrace();}
				%>
				</UL>
				<UL ID="oPrimaryTOC2" <%if (!arteeDeshabilitado){ %> style="display:none" <% }%>> 
				<%try{ 																							
					if (uData!=null){
						//UserData uData = (UserData)session.getAttribute(Parameters.SESSION_ATTRIBUTE);
						if (uData.getUserAttributes().get("ESTRUCTURA_JERARQUICA")!=null){
							String htmlData2 = (String)uData.getUserAttributes().get("ESTRUCTURA_JERARQUICA");
							out.print( htmlData2 );
						}else{
							System.out.println( "NO HAY DATOS EN SESSION 1.2" );
						}
					}else{
						System.out.println( "NO HAY DATOS EN SESSION 2.2 " );
					}
					System.out.println("FIN");
				}
				catch(Exception e){e.printStackTrace();}
				%>
				</UL>
				</DIV>
				<% if (!arteeDeshabilitado) {%>
				<BUTTON type="BUTTON" onclick="displayARTEE()" ID=btnARTEE NAME=btnARTEE disabled="true"><%=mensajeDao.obtenerMensajePorCodigo("VAL_BTN_ARTEE_JSP_TXT", currentLanguage, environmentId)%></BUTTON><BUTTON type="BUTTON" onclick="displayEntOE();" id=btnEntOE name=btnEntOE><%=mensajeDao.obtenerMensajePorCodigo("VAL_BTN_ENT_OE_JSP_TXT", currentLanguage, environmentId)%></BUTTON>
				<%} %>
			</BLOCKQUOTE>
		</fieldset> 
	</div>
</td>
<td>&nbsp;&nbsp;</td>
<td>
</td>
</tr>
<tr style="HEIGHT:10%">
<td colspan=3 align="center">
	<div id="field2" >
		<!--  <BUTTON type="BUTTON" onclick="ok()" ID=btnOk NAME=btnOk><%=mensajeDao.obtenerMensajePorCodigo("VAL_BTN_SELECCIONAR_JSP_TXT", currentLanguage, environmentId)%></BUTTON>&nbsp;
		<BUTTON type="BUTTON" onclick="cancel();" id=btnCancel name=btnCancel><%= mensajeDao.obtenerMensajePorCodigo("VAL_BTN_CANCELAR_JSP_TXT", currentLanguage, environmentId)%></BUTTON>-->
	</div>
</td>
</tr>		
</table>
	
	</form>
</BODY>


</HTML>
