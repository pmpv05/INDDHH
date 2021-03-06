<%@page import="uy.com.st.adoc.log.LogDocumentum"%>
<%response.setCharacterEncoding(com.dogma.Parameters.APP_ENCODING);%>
<%@page import="com.dogma.Parameters"%>
<%@page import="com.dogma.EnvParameters"%>
<%@page import="uy.com.st.adoc.expedientes.constantes.Mensajes"%>
<%@page import="com.dogma.UserData"%>
<%@page import="uy.com.st.adoc.expedientes.conf.ConfigurationManager"%>
<%@page import="com.dogma.Configuration"%>
<%@page import="uy.com.st.adoc.mensajes.MensajeDAO"%>
<%@page import="uy.com.st.adoc.log.LogDocumentum"%>
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
UserData uData = ThreadData.getUserData();
if (uData!=null) {
	environmentId = uData.getEnvironmentId();
	currentLanguage = uData.getLangId();
	styleDirectory = EnvParameters.getEnvParameter(environmentId,EnvParameters.ENV_STYLE);
}
%>

<HTML>
	<HEAD>
	
		<title><%=mensajeDao.obtenerMensajePorCodigo("VAL_TITULO_SELECCIONAR_DESTINO_PASE_JSP_TXT", currentLanguage, environmentId)%></title>
		<link href="toc.css" type="text/css" rel="styleSheet">
		
		<script LANGUAGE="javascript" SRC="toc.js"></script>
		<script LANGUAGE="javascript" SRC="<%=Parameters.ROOT_PATH%>/expedientes/scripts/image.js"></script>
		<script>
			var MSIE = window.navigator.appVersion.indexOf("MSIE")>=0;
			var sep1;
			var FORCE_SYNC = true;
			var TAB_ID_REQUEST = "<%="&tabId=" + request.getParameter("tabId").toString() +"&tokenId="+ request.getParameter("tokenId").toString()%>"
			var URL_ROOT_PATH	 	= "<%=Parameters.ROOT_PATH%>";
			var URL_STYLE_PATH		= "<%=Parameters.ROOT_PATH%>/styles/<%= styleDirectory %>"
			
			
			function getModalReturnValue(modal) {
				var destino = "";
				destino = document.getElementById("textLstUsuarioDestino").value;
				destino = sacarSeperadorDeLosExtremos(destino, document.getElementById("textSeparador2").value);
				if (destino == "" ){
					alert("<%= mensajeDao.obtenerMensajePorCodigo("MSG_ELEGIR_UN_DESTINO_JSP", currentLanguage, environmentId)%>"); //alert("Debe elegir un destino");
				}else{	
					modal.setearDestino(destino);
					return true;
				} 
			}				
			
			function whenLoad(){  	
				document.getElementById("waittoload").style.display="none";
				document.getElementById("treefield").style.display="block";
				HideAll('UL');	
				sep1 = document.getElementById("textSeparador1").value;
			}			
			
			var ajax;
			var ajax2;
			function funcionCallBackCargarUsuarios(){
				// Comprobamos si la peticion se ha completado (estado 4)
				if( ajax.readyState == 4 ){
					// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
					if( ajax.status == 200 ){
						funcionCallBackCargarUsuariosLoaded()
					}
				}	
			}
			
			function funcionCallBackCargarUsuariosLoaded(){
				// Escribimos el resultado en la pagina HTML mediante DHTML
				var result = ajax.responseText;
				document.getElementById("oPrimaryTOCUsr").style.display="block";
				document.getElementById("oPrimaryTOCUsr").innerHTML = result;
				if (result.indexOf("<table><table>") != -1){
					document.getElementById("textHayUsrOficina").value = "0";				
				}else{
					document.getElementById("textHayUsrOficina").value = "1";
				}
				
				//Chequeo los usuarios seleccionados
				var tmp = document.getElementById("textLstUsuarioDestino").value;			
				var sep2 = document.getElementById("textSeparador2").value;			
				var vec = tmp.split(sep2);			
				var iNumElems = vec.length;			
				for (var i=1;i<iNumElems;i++) {						
					if (vec[i] != ""){
						var obj = document.getElementById(vec[i]);
						if (obj!=null){						
							obj.checked = true
						}
					}
				}
				
			}
			
			function cargarUsuarios(flag){
				// Creamos el control XMLHttpRequest segun el navegador en el que estemos
				if( !MSIE ){
					ajax = new XMLHttpRequest(); // No Internet Explorer
					ajax.onload = function(){
						funcionCallBackCargarUsuariosLoaded();
					}
				}else{
					ajax = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
					// Almacenamos en el control al funcion que se invocara cuando la peticion
					// cambie de estado
					ajax.onreadystatechange = funcionCallBackCargarUsuarios;
				}
				// Enviamos la peticion	
				var URL = "<%=ConfigurationManager.getServerAddress(environmentId,currentLanguage) + Configuration.ROOT_PATH%>/expedientes/arbolDestinoMultipleConUsuarios/cargarUsuarios.jsp?unidad=" + document.getElementById("textIdOficinaDestino").value + "&flag=" + flag + "&envId=" + <%=environmentId%>;					
				//alert(URL);
				ajax.open( "POST", URL, false );	
				ajax.send( "" );	
			}
			
			function funcionCallbackAusente(){
				// Comprobamos si la peticion se ha completado (estado 4)
				if( ajax.readyState == 4 ){
					// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
					if( ajax.status == 200 ){
						// Escribimos el resultado en la pagina HTML mediante DHTML
						var result = ajax.responseText;
						
						var vec = result.split("<%=Mensajes.EXP_SEPARADOR_PARAMETROS1%>");
						
						if (vec[1] == 'true'){
							if (vec[2] == ''){
								if (vec[4] == null || (vec[4].indexOf("-") == -1)) {
									alert("<%=mensajeDao.obtenerMensajePorCodigo("MSG_USUARIO_AUSENTE_JSP", currentLanguage, environmentId)%>");
								}else{
									alert("<%=mensajeDao.obtenerMensajePorCodigo("MSG_USUARIO_AUSENTE_HASTA_DIA_JSP", currentLanguage, environmentId)%>".replace('<DIA>',vec[4]));
									//alert("El usuario se encuentra asuente de la oficina hasta el d�a: " + vec[4]);
								}
							}else{
								if (vec[4] == null || (vec[4].indexOf("-") == -1)) {
									alert("<%=mensajeDao.obtenerMensajePorCodigo("MSG_USUARIO_AUSENTE_CON_MENSAJE_JSP", currentLanguage, environmentId)%>");
								}else{
									alert("<%=mensajeDao.obtenerMensajePorCodigo("MSG_USUARIO_AUSENTE_HASTA_DIA_CON_MENSAJE_JSP", currentLanguage, environmentId)%>".replace('<DIA>',vec[4]).replace('<MENSAJE>',vec[2]));									
								}
							}
							
							
							var cElems = document.getElementsByTagName('INPUT');
							var iNumElems = cElems.length;
							for (var i=1;i<iNumElems;i++) {
								if (cElems[i].id == "checkbox2"){
									cElems[i].checked=false;
								}
							}
							document.getElementById("textUsuarioDestino").value=""; 
						}else{
							//alert("El usuario no esta de licencia");
						}
					}
				}	
			}
			
			function funcionCallbackFoto(){
				// Comprobamos si la peticion se ha completado (estado 4)
				if( ajax2.readyState == 4 ){
					// Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
					if( ajax2.status == 200 ){
						// Escribimos el resultado en la pagina HTML mediante DHTML
						var result = ajax2.responseText;						
						var vec = result.split("<%=Mensajes.EXP_SEPARADOR_PARAMETROS1%>");						
						document.getElementById("imgFoto").src=vec[0];			
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
				
				ajax.onreadystatechange = funcionCallbackAusente;
				// Enviamos la peticion		
				var URL = "<%=ConfigurationManager.getServerAddress(environmentId,currentLanguage) + Configuration.ROOT_PATH%>/expedientes/arbolDestino/cargarImg.jsp?usr=" + usr+ "&op=1" + TAB_ID_REQUEST;
				ajax.open( "GET", URL, true );
				ajax.send( "" );	
				
				// Creamos el control XMLHttpRequest segun el navegador en el que estemos
				if( window.XMLHttpRequest )
					ajax2 = new XMLHttpRequest(); // No Internet Explorer
				else
					ajax2 = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
					// Almacenamos en el control al funcion que se invocara cuando la peticion
					// cambie de estado
				
				ajax2.onreadystatechange = funcionCallbackFoto;
				// Enviamos la peticion		
				var URL2 = "<%=ConfigurationManager.getServerAddress(environmentId,currentLanguage) + Configuration.ROOT_PATH%>/expedientes/arbolDestino/cargarImg.jsp?usr=" + usr+ "&op=2" + TAB_ID_REQUEST;
				//alert(URL);	
				ajax2.open( "GET", URL2, true );
				ajax2.send( "" );	
			}
						
			var nodosAnteriores = [];
			function mostrarSubtree(el){
				var x;
				if(el.parentNode == null){
				     return;
				}
				//esto es para firefox
				if(el.id =='oPrimaryTOC'){
					return;
				}
				x = el.parentNode;
				mostrarSubtree(x);
				
				if ("clsHasKids" == x.className ){
					if (x.getAttribute("tp")=='C'){
						x.getElementsByTagName("IMG")[0].src="images/folder_open.gif"					
				    }
				      
				    if (x.getAttribute("tp")=='PN'){
				    	x.getElementsByTagName("IMG")[0].src="images/folder_open.gif"					
				    }
				}
				 
				el.style.display = "block";
				
			}
			
			var found = false;
			function recorrerNodos(el, texto){
				var x = el.childNodes;
				if (x == undefined){
					return;
				}				  
				
				for (var i = 0, len = x.length; i < len; ++i) {
						if (x[i].title != undefined) {
							if (x[i].title != '') {
								itemText = x[i].title.toUpperCase();
								if (!enNodosAnteriores(itemText)) {
									if (itemText.indexOf(texto.toUpperCase()) > -1) {
										mostrarSubtree(x[i]);
										x[i].scrollIntoView();
										seleccionarNodo(x[i]);
										agregarNodo(x[i].title);
										found = true;
										return;
									}
								}
							}
						}
	
						recorrerNodos(x[i], texto);
						if (found) {
							return;
						}
					}
			}
	
			function buscarNodo(comando) {
				found = false;
				el = document.getElementById('oPrimaryTOC');
				texto = document.getElementById('busqueda').value;
				if (comando == "") {
					nodosAnteriores = [];
				}
				if (comando == "ant") {
					nodosAnteriores.pop();
					nodosAnteriores.pop();
				}
	
				recorrerNodos(el, texto);
				return false;
	
			}
			
			function busquedaOnKeyPress() {
				nodosAnteriores = [];
				buscarNodo("");
			}
			
			function agregarNodo(texto) {
				nodosAnteriores.push(texto);
			}
			
			function enNodosAnteriores(texto) {
				for (i = 0; i < nodosAnteriores.length; i++) {
					if (nodosAnteriores[i].toUpperCase() == texto) {
						return true;
					}
				}
				return false;
			}
			
			function seleccionarNodo(el) {
				var x = el.childNodes;
				var newEvt;
				for (var i = 0, len = x.length; i < len; ++i) {
					if (x[i].id == 'checkbox1') {
						if (document.createEventObject) {
							// dispatch for IE
							var newEvt = document.createEventObject();
							newEvt.button = 3;
							return x[i].fireEvent("onclick", newEvt)
						} else {
							// dispatch for firefox + others
							var newEvt = document.createEvent("HTMLEvents");
							newEvt.initEvent("click", true, true); // event type,bubbling,cancelable
							return !x[i].dispatchEvent(newEvt);
						}
					}
				}
			}
	
			function fnFlash(oTitle) {
				oList = document.all[oTitle.sourceIndex + 1];
				if (oList.className == "tocItemHide") {
					oList.className = "tocItemShow";
					document.frmMain.tipPagina.value = oTitle.tipPag
				} else {
					oList.className = "tocItemHide";
					document.frmMain.tipPagina.value = oTitle.tipPag
				}
	
			}
		</script>
		
		<style type="text/css">
			#imgFoto{ top: 75px; left: 12px; width: 125px; height: 125px; }
			#oTocDiv { position: relative; top: 5%; width: 438px; height: 243px; border: #505050 1px solid; background-color: #f5f5f5;  font-family: verdana; font-size: 8pt;  overflow: auto; }
			#tree { width: 398px; }
			#treefield { height: 287px; width: 440px; }
			#treefieldusr {     HEIGHT: 287px; WIDTH: 225px; position: relative; left: 9%; }
			#usrImg { position: relative; left: 0%; HEIGHT: 287px; WIDTH: 150px; }
			#oTocDivUsr{ position: relative; left: 0.5%; top: 5%; width: 220px; height: 242px; border: #505050 1px solid; background-color: #f5f5f5; font-family: verdana; font-size: 8pt; overflow: auto; }
		</style>
		
	</HEAD>
	
	<BODY onload="whenLoad()">
		
		<form name="frmMain" id="frmMain" method="post">
			<input id="tipPagina" type="hidden">
			<input id=textIdOficinaDestino name=textIdOficinaDestino type="hidden">
			<input id=textOficinaDestino name=textOficinaDestino type="hidden">
			<input id=textUsuarioDestino name=textUsuarioDestino type="hidden">		
			<input id=textLstUsuarioDestino name=textLstUsuarioDestino type="hidden" value="">
			<input id=textSeparador1 name=textSeparador1 type="hidden" value="<%=Mensajes.EXP_SEPARADOR_PARAMETROS1%>">
			<input id=textSeparador2 name=textSeparador2 type="hidden" value="<%=Mensajes.EXP_SEPARADOR_PARAMETROS2%>">
			<input id=textNombOficinaDestino name=textNombOficinaDestino type="hidden">
			<input readonly id="waittoload" value="Espere un momento.." style="background-color:yellow;color:navy;position:absolute;top:120px;left:130px;display:none">
			<input id=textHayUsrOficina name=textHayUsrOficina type="hidden">
					
			<table border="0" width="100%">			
				<tr>
					<td colspan =2>
						<font style="FONT-FAMILY: verdana; FONT-SIZE: 8pt;">
							<legend><%=mensajeDao.obtenerMensajePorCodigo("VAL_LBL_BUSCAR_OFICINA_JSP_TXT", currentLanguage, environmentId)%> </legend>
							<input type=text id='busqueda' onkeyup='return busquedaOnKeyPress();'>
							<button onclick='return buscarNodo("sig");'><%=mensajeDao.obtenerMensajePorCodigo("VAL_BTN_SIGUIENTE_BUSQUEDA_JSP_TXT", currentLanguage, environmentId)%></button>
							<button onclick='return buscarNodo("ant");'><%=mensajeDao.obtenerMensajePorCodigo("VAL_LBL_ANTERIOR_BUSQUEDA_JSP_TXT", currentLanguage, environmentId)%></button>
						</font>
					</td>
				</tr>
				
				<tr>
					<td>
						<div id="tree" onclick="treeClick(event)">	
							<fieldset id="treefield" name="treefield" style="display:none;">
								<legend><font style="FONT-FAMILY: verdana; FONT-SIZE: 8pt;"><%=mensajeDao.obtenerMensajePorCodigo("VAL_LBL_SELECCIONAR_OFICINA_JSP_TXT", currentLanguage, environmentId)%></font></legend>
							
									<DIV id="oTocDiv" style="BACKGROUND-COLOR: <%=Mensajes.com_st_apia_expedientes_style_arbol_destino%>; BORDER-BOTTOM: #505050 1px solid; BORDER-LEFT: #505050 1px solid; BORDER-RIGHT: #505050 1px solid; BORDER-TOP: #505050 1px solid; FONT-FAMILY: verdana; FONT-SIZE: 8pt; OVERFLOW: auto;">
										<UL ID="oPrimaryTOC">   
										<% 									
										LogDocumentum.debug("SESSION_ATTRIBUTE: " + uData);
																			
										if (uData!=null){
											if (uData.getUserAttributes().get("ESTRUCTURA_JERARQUICA")!=null){
												String htmlData = (String)uData.getUserAttributes().get("ESTRUCTURA_JERARQUICA");												
												out.print( htmlData );
											}else{
												LogDocumentum.debug("NO HAY DATOS EN SESSION 1" );
											}
										}else{
											LogDocumentum.debug("NO HAY DATOS EN SESSION 2 " );
										}
										LogDocumentum.debug("FIN");
													
										%>
										</UL>
									</DIV>
							</fieldset>
						</div>
					</td>
					
					<td>&nbsp;&nbsp;</td>
					
					<td>
					<fieldset id="treefieldusr" name="treefieldusr">
						<legend><font style="FONT-FAMILY: verdana; FONT-SIZE: 8pt;"><%=mensajeDao.obtenerMensajePorCodigo("VAL_LBL_USUARIO_DESTINO_JSP_TXT", currentLanguage, environmentId)%></font></legend>
					
							<DIV id="oTocDivUsr" style="BACKGROUND-COLOR: <%=Mensajes.com_st_apia_expedientes_style_arbol_destino%>; BORDER-BOTTOM: #505050 1px solid; BORDER-LEFT: #505050 1px solid; BORDER-RIGHT: #505050 1px solid; BORDER-TOP: #505050 1px solid; FONT-FAMILY: verdana; FONT-SIZE: 8pt; OVERFLOW: auto;">
								<UL ID="oPrimaryTOCUsr"></UL>
							</DIV>
					</fieldset>				
					</td>
					
					<td>&nbsp;&nbsp;</td>
					
					<td>
					<fieldset id="usrImg" name="usrImg">
						<legend><font style="FONT-FAMILY: verdana; FONT-SIZE: 8pt;"><%=mensajeDao.obtenerMensajePorCodigo("VAL_LBL_FOTO_JSP_TXT", currentLanguage, environmentId)%></font></legend>
						
							<img onload="resizeImg(this, 150, 150)"  id="imgFoto" name="imgFoto" src="<%=Parameters.ROOT_PATH%>/images/fotos/NoPicture.gif">
				
					</fieldset> 
					</td>				
				</tr>				
			</table>
						
		</form>
				
	</BODY>	

</HTML>