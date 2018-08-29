<%@page import="com.dogma.Configuration"%>
<%@page import="st.access.tramites.TramiteDao"%>
<%@page import="st.access.tramites.Tramite"%>
<%@page import="java.util.ArrayList"%>

<% 
String pagina = "1";
if ((request.getParameter("pagina")!=null) && (!request.getParameter("pagina").equals(""))){
	pagina = request.getParameter("pagina");
}

TramiteDao tdao = new TramiteDao();
ArrayList<Tramite> arr = tdao.getTramites(1);

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
	<link href="estilos-resultado.css?subtype=css" rel="stylesheet" type="text/css">
	
</head>

<body style="background-color: white">

<div class="contenedor buscadorResultados cfx">
	<div class="top"><span class="topRg"></span></div>
	<div class="contenido cfx">
					  
		<%if (arr==null || arr.size()==0){ %>
			<div class="resultadosContenido">
				<span class="p">
				<br>
				  Su b�squeda - no produjo ning�n resultado.
				<br>				
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
				<h3>Listado de tr�mites</h3>
			</div>
			<div class="right">Resultados <strong><%=(inicio+1)%></strong> - <strong><%=(fin+1)%></strong> de aproximadamente <strong><%=cantRegistros%></strong></div>
		
			<div class="paginado">
				<ul class="cfx">
					<%if (PAGINA_ACTUAL > 1){
						String url = %><%Configuration.ROOT_PATH %><%;
						url = url + "/portal/buscador/listarTramites.jsp";							
							url = url + "?pagina=" + (PAGINA_ACTUAL -1);%>	
					<li class="pagSg"><a href="<%=url%>"> &lt;&lt; P�gina Anterior </a></li>
					<%}%>											
					<%for (int i=1; i<(cantPaginas+1); i++){
						if (i==PAGINA_ACTUAL){%>
							<li class="pagActual"><%=PAGINA_ACTUAL%></li>
						<%}else{
							String url = %><%Configuration.ROOT_PATH %><%;
							url = url + "/portal/buscador/listarTramites.jsp";	
							url = url + "?pagina=" + (i);
							%>
							<li><a href="<%=url%>"><%=(i)%></a></li>
						<%}%>		
					<%}%>				
					<%if (PAGINA_ACTUAL < cantPaginas){
						String url = %><%Configuration.ROOT_PATH %><%;
						url = url + "/portal/buscador/listarTramites.jsp";
							url = url + "?pagina=" + (PAGINA_ACTUAL + 1);%>	
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
											<!-- <a href="<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=100&env=1&type=P&entCode=1006&proCode=1033&eatt_STR_TRM_NOMBRE_STR=<%=t.getNomb()%>"><strong><%=t.getNomb()%></strong></a> -->
											<a  href="<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=100&env=1&type=P&entCode=1006&proCode=1033&eatt_STR_TRM_COD_TRAMITE_STR=<%=t.getId()%>"><strong><%=t.getNomb()%></strong></a>
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