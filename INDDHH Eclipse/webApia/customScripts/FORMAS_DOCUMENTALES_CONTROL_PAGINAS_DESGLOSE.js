
function fnc_1001_1558(evtSource) { 
	//El signo de "+" es para convertir el valor a num�rico
	//y que no haga la comparasi�n como strings 
	var myForm = ApiaFunctions.getForm("DESGLOSE_DESGLOSAR");
	var pagDesde = (myForm.getField("DSGL_PAG_DESDE_CBO_FRM").getValue());
	var pagHasta = (myForm.getField("DSGL_PAG_HASTA_CBO_FRM").getValue());
	
	if(pagDesde > pagHasta){
		alert("La p�gina desde es mayor que la p�gina hasta");
		return false;
	} 
	return true; // END

return true; // END
} // END
