<%@page import="com.dogma.Configuration"%>
<html>
<head>
	<meta charset="utf-8">
	<title>
		tramites.en.linea.gub.uy
	</title>
	
    <!-- STYLES -->
	<link href="<%=Configuration.ROOT_PATH %>/portal/css/estilos-responsive.css?subtype=css" rel="stylesheet" type="text/css">
	
	<style type="text/css">
		.layout span {
			background: transparent url('portal/img/sprite_categorias.png') center 0;
			height: 90px;
			display: block;
			background-repeat: no-repeat;
		}
		
		.layout li {
			height: 150px;
		}
		
		.layout li:hover {
			background-color: #f9f9f9;
			cursor: pointer;
		}
		
		#img1 span{
			background-position: center -384px; 
		}
		
		#img2 span{
			background-position: center -480px; 
		}
		
		#img3 span{
			background-position: center -290px; 
		}
		
		#img4 span{
			background-position: center -7px; 
		}
		
		#img5 span{
			background-position: center -197px; 
		}
		
		#img6 span{
			background-position: center -100px; 
		}
		
		#img7 span{
			background-position: center -576px; 
		}
		
		#img8 span{
			background-position: center -671px; 
		}
		
		#img9 span{
			background-position: center -863px; 
		}
		
		#img10 span{
			background-position: center -959px; 
		}
		
		#img11 span{
			background-position: center -768px; 
		}

		@media (min-width: 1000px) and (max-width: 1199px) { 
			.categorias ul li {
				width: 15% !important;
			}
		}


	</style>
	
	<script type="text/javascript">
	function loadURL(url) {
		document.id('iframe_pnlId_1_1013_1232').getChildren('iframe').set('src', url);
	}
	</script>
</head>

<body>



<div class="box categorias" style="border: none; margin-bottom: 0px; padding-bottom: 0px;">
    <h5>Explorar trámites</h5>	
    <div class="layout">
        <ul>
            <li id="img1" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=1')">
				<center>
					<span></span>
					Hogar y familia
				</center>
            </li>
            <li id="img2" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=2')">
            	<center>
					<span></span>
					Uruguayos en el exterior
				</center>
            </li>
            <li id="img3" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=3')">
            	<center>
					<span></span>
					Documentación
				</center>
            </li>
            <li id="img4" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=4')">
            	<center>
					<span></span>
					Actividad productiva y empresarial
				</center>
            </li>
            <li id="img5" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=5')">
            	<center>
					<span></span>
					Cultura
				</center>
            </li>
            <li id="img6" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=6')">
            	<center>
					<span></span>
					Beneficios sociales
				</center>
            </li>
             <li id="img7" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=7')">  
            <!--<li id="img7" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1045&eatt_STR_TRM_COD_TRAMITE_STR=243')"> -->
            	<center>
					<span></span>
					Discapacidad
				</center>
            </li>
            <li id="img8" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1006&proCode=1033&eatt_NUM_TRM_CATEGORIA_NUM=8')">
            	<center>
					<span></span>
					En Línea
				</center>
            </li>
        	<li id="img9" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/query.jsp?logFromFile=true&onFinish=1&env=1&qryId=1010')">
            	<center>
					<span></span>
					Consultar trámite
				</center>
            </li>
            <li id="img10" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1010&proCode=1027')">
            	<center>
					<span></span>
					Consultar expediente
				</center>
            </li>
            <li id="img11" onclick="loadURL('<%=Configuration.ROOT_PATH %>/page/externalAccess/open.jsp?logFromFile=true&onFinish=2&env=1&type=P&entCode=1011&proCode=1025')">
            	<center >
					<span></span>
					Registrar nuevo usuario
				</center>
            </li>
        </ul>
    </div>
</div>

</body>
</html>