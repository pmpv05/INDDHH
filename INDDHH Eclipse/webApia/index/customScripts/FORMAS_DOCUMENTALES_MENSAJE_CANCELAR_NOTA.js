
function fnc_1001_2269(evtSource) { 
	var frmActual = "CANCELAR_NOTA_";

	var att = document.getElementById(frmActual + "NTA_CANCELAR_NOTA_STR").checked;
	if (att == false){
		alert('ATENCI�N: Si cancela la nota no podr� volver a tener acceso a la misma.');
	}	
}
catch(e)
{
	alert(e.description());
}

return true; // END
} // END
