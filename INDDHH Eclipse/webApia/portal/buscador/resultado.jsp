<%@page import="com.dogma.Configuration"%>
<%@page import="st.access.tramites.TramiteDao"%>
<%@page import="st.access.tramites.Tramite"%>
<%@page import="java.util.ArrayList"%>

<% 
String texto = request.getParameter("q");
String pagina = request.getParameter("pagina");
TramiteDao tdao = new TramiteDao();
ArrayList<Tramite> arr = tdao.getTramites(texto, 1);

int CANTIDAD_REGISTROS = 10;
int PAGINA_ACTUAL = Integer.parseInt(pagina);
%>

<html class=" js" lang="es">

<head>
    
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<title>
	 resultado | Apia Tr�mites en L�nea
	
	</title>
	<meta name="description" content="Gu�a completa y organizada de tr�mites del Estado Uruguayo.">	
	
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	
	 <!-- SCRIPTS 
	<script async="" src="//www.google-analytics.com/analytics.js"></script>
	<script type="text/javascript" src="http://tramites.gub.uy/wps/wcm/connect/PEU/PEU/JSArea/jquery.min.js?subtype=javascript"></script>
	<script type="text/javascript" src="http://tramites.gub.uy/wps/wcm/connect/PEU/PEU/JSArea/scripts-responsive.js?subtype=javascript"></script>
	-->
  
    <!-- STYLES -->
	<link href="estilos-resultado.css?subtype=css" rel="stylesheet" type="text/css">
	
</head>

<body>

<div class="contenedor buscadorResultados cfx">
	<div class="top"><span class="topRg"></span></div>
	<div class="contenido cfx">
					  
		<%if (arr==null || arr.size()==0){ %>
			<div class="resultadosContenido">
				<!--tro2--><span class="p">
				<br>
				  Su b�squeda - <strong><%=texto%></strong> - no produjo ning�n documento.
				  <br>
				<br>
				  Sugerencias:
				  <ul>
				<li>Aseg�rese de que todas las palabras est�n escritas correctamente.</li>
				<li>Intente usar otras palabras.</li>
				<li>Intente usar palabras m�s generales.</li>
				</ul>
				</span>
			</div>
		<%}else{ %>
		
			<%
			int cantRegistros = arr.size();
			int cantPaginas = cantRegistros / CANTIDAD_REGISTROS;
			if ((cantRegistros % CANTIDAD_REGISTROS) > 0){
				if (cantPaginas>0){
					cantPaginas = cantPaginas + 1;
				}
			}
			
			int inicio = (PAGINA_ACTUAL * CANTIDAD_REGISTROS) - CANTIDAD_REGISTROS; 
			int fin = (PAGINA_ACTUAL * CANTIDAD_REGISTROS) - 1;
			
			if (inicio>cantRegistros){
				inicio = cantRegistros - 1;
			}
			if (fin>cantRegistros){
				fin = cantRegistros - 1;
			}
			%>
			<div class="left">
				<h3>Resultados de b�squeda para: <strong><%=texto%></strong></h3>
			</div>
			<div class="right">Resultados <strong><%=(inicio+1)%></strong> - <strong><%=(fin+1)%></strong> de aproximadamente <strong><%=cantRegistros%></strong></div>
		
			<div class="paginado">
				<ul class="cfx">
					<%if (PAGINA_ACTUAL > 1){
						String url = %><%Configuration.ROOT_PATH %><%;
							url = url + "/portal/buscador/resultado.jsp";
							url = url + "?q=" + texto;		
							url = url + "&pagina=" + (PAGINA_ACTUAL -1);%>	
					<li class="pagSg"><a href="<%=url%>"> &lt;&lt; P�gina Anterior </a></li>
					<%}%>											
					<%for (int i=1; i<(cantPaginas+1); i++){
						if (i==PAGINA_ACTUAL){%>
							<li class="pagActual"><%=PAGINA_ACTUAL%></li>%
						<%}else{
							String url = %><%Configuration.ROOT_PATH %><%;
							url = url + "/portal/buscador/resultado.jsp";
							url = url + "?q=" + texto;		
							url = url + "&pagina=" + (i);
							%>
							<li><a href="<%=url%>"><%=(i)%></a></li>
						<%}%>		
					<%}%>				
					<%if (PAGINA_ACTUAL < cantPaginas){
						String url = %><%Configuration.ROOT_PATH %><%;
						url = url + "/portal/buscador/resultado.jsp";
							url = url + "?q=" + texto;		
							url = url + "&pagina=" + (PAGINA_ACTUAL + 1);%>	
						<li class="pagSg"><a href="<%=url%>"> P�gina Siguiente &gt;&gt;</a></li>
					<%}%>					
				</ul>
			</div>
			
			<div class="resultadosContenido">
			
				<div class="resultadosListado">
					<ul>
						<%
							/*
							System.out.println("cantRegistros: " + cantRegistros);
							System.out.println("cantPaginas: " + cantPaginas);
							System.out.println("PAGINA_ACTUAL: " + PAGINA_ACTUAL);
							System.out.println("inicio: " + inicio);
							System.out.println("fin: " + fin);
							*/
							for (int i=inicio; i<=fin; i++){
								//if (i<CANTIDAD_REGISTROS){
									Tramite t = arr.get(i);%>					
									<li >
										<h4>
											<span>
											<strong></strong>
											</span> 
											<!--<a href="<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_STR_TRM_NOMBRE_STR=<%=t.getNomb()%>"><strong><%=t.getNomb()%></strong></a> -->
											<a target="_blank" href='<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_STR_TRM_COD_TRAMITE_STR=<%=t.getId()%>'><strong><%=t.getNomb()%></strong></a>
										</h4>
										<p><%=t.getDesc()%>...</p>
									</li>
							<%}
						%>
					</ul>
					<br>
				</div>
			</div>
		<%}%>
                
       </div>
   	<div class="bottom cfx"><span class="botRg"></span></div>
</div>

</body>
</html>	