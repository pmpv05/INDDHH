
function fnc_1001_4165(evtSource) { 
//Se crean las variables
var modal =  ModalController.openWinModal(getUrlApp() + "/expedientes/adminUsuarios/armar.jsp?" + TAB_ID_REQUEST, 710, 400, null, null, null, true, true);

var indice = evtSource.index;
var boton;
var att;

modal.onclose=function(){
   //Se obtiene el valor seleciconado y se controla sea v�lido
   var sDestino=this.returnValue;
   if (sDestino == 'cancel' || sDestino == '' || sDestino == null){			        
      alert("No se ha elegido un destino v�lido.");
   }
   else{
      if (sDestino.length > 2){	
            //Se setea el atributo auxiliar para la carga de ofcinas con la oficina seleccionada							
	    var frmActual = ApiaFunctions.getForm("UNIDAD_ORGANIZACIONAL_ALTA");
	    frmActual.getField("UOS_OCULTO_STR").setValue(sDestino);
                
            //Se despliega el mensaje de confirmaci�n
            var conf = confirm("Los administradores de segundo nivel asociados a la UO ser�n modificados, as� como los nombres de las UO que se hayan editado. �Desea modificar igualmente?")
            if (conf){
             //Se presiona el bot�n que dispara la clase java
			  
              boton = frmActual.getFieldColumn("MODIFICAR_UO")[indice];
              boton.fireClickEvent();
	    }						
         }				
      }
   }
return false;







return true; // END
} // END
