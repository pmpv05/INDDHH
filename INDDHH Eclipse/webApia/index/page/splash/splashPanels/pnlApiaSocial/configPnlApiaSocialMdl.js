var spModalConfigPnlApiaSocial;

var MAX_PARAMETER_SIZE = 255;

function initConfigPnlApiaSocialMdl(){
	var mdlConfigPnlApiaSocialContainer = $('mdlConfigPnlApiaSocialContainer');
	if (mdlConfigPnlApiaSocialContainer.initDone) return;
	mdlConfigPnlApiaSocialContainer.initDone = true;

	mdlConfigPnlApiaSocialContainer.blockerModal = new Mask();
	
	spModalConfigPnlApiaSocial = new Spinner($('mdlBodyConfigPnlApiaSocial'),{message:WAIT_A_SECOND});
	
	$('addEnvironment').addEvent("click",function(e){
		e.stop();
		ENVIRONMENTMODAL_SELECTONLYONE = false;
		var filterIn = $('paramChnEnvAva').value;		
		ENVIRONMENTMODAL_ADT_SQL = filterIn != '' && filterIn != null ? ' env_id_auto in (' + filterIn + ')' : '';
		showEnvironmentsModal(processRetMdlEnvironment);
	});
	
	$('addProcess').addEvent("click",function(e){
		e.stop();
		PROCESSMODAL_SHOWGLOBAL = false;
		PROCESSMODAL_SELECTONLYONE = false;
		PROCESSMODAL_IS_SCENARIO = false;
		PROCESSMODAL_SHOW_ALL = false;
		var filterIn = $('paramChnProAva').value;		
		PROCESSMODAL_ADT_SQL = filterIn != '' && filterIn != null ? ' AND pro_id_auto in (' + filterIn + ')' : '';
		showProcessModal(processRetMdlProcess);
	});
	
	$('addTask').addEvent("click",function(e){
		e.stop();
		TASKMODAL_SELECTONLYONE = false;
		var filterIn = $('paramChnTskAva').value;		
		TASKMODAL_ADT_SQL = filterIn != '' && filterIn != null ? ' AND tsk_id_auto in (' + filterIn + ')' : '';
		showTaskModal(processRetMdlTask);
	});
	
	$('addPool').addEvent("click",function(e){
		e.stop();
		POOLMODAL_SHOWAUTOGENERATED = true;
		POOLMODAL_SHOWNOTAUTOGENERATED = true;
		POOLMODAL_FROMENVS = "";
		POOLMODAL_SHOWCURRENTENV = false;
		POOLMODAL_SHOWGLOBAL = true;
		POOLMODAL_EXACTMATCH = ""; 
		POOLMODAL_SELECTONLYONE	= false;
		POOLMODAL_FOR_HIERARCHY = false;
		showPoolsModal(processRetMdlPool);
	});
	
	$('addUser').addEvent("click",function(e){
		e.stop();
		USERMODAL_EXTERNAL = false;
		USERMODAL_SELECTONLYONE	= false;
		USERMODAL_GLOBAL_AND_ENV = true;
		showUsersModal(processRetMdlUser);
	});
	
	$('closeConfigPnlApiaSocialMdl').addEvent("click", function(e) {
		e.stop();
		closeConfigPnlApiaSocialMdl(true);
	});
	
	$('btnConfirmConfigPnlApiaSocialMdl').addEvent("click", function(e){
		e.stop();
		var mdlConfigPnlApiaSocialContainer = $('mdlConfigPnlApiaSocialContainer');
		if (mdlConfigPnlApiaSocialContainer.onModalConfirm){
			var paramRefreshTime = $('paramRefreshTime').value;
			if (paramRefreshTime != '' && Number.from(paramRefreshTime) < MIN_REFRESH_TIME){
				showMessage(MSG_MIN_REFRESH.replace("<TOK1>",MIN_REFRESH_TIME), GNR_TIT_WARNING, 'modalWarning');
				return;
			}			
			var paramViewMode = $('paramViewMode').value;
			var paramDays = $('paramDays').value;			
			var paramChnEnv = '';
			$('chnEnvContainer').getElements("div.optionRemove").each(function(chn){
				if (paramChnEnv != '') paramChnEnv += PARAM_SEPARATOR;
				paramChnEnv += chn.getAttribute("objId");
			});
			var paramChnPro = '';
			$('chnProContainer').getElements("div.optionRemove").each(function(chn){
				if (paramChnPro != '') paramChnPro += PARAM_SEPARATOR;
				paramChnPro += chn.getAttribute("objId");
			});
			var paramChnTsk = '';
			$('chnTskContainer').getElements("div.optionRemove").each(function(chn){
				if (paramChnTsk != '') paramChnTsk += PARAM_SEPARATOR;
				paramChnTsk += chn.getAttribute("objId");
			});
			var paramChnPool = '';
			$('chnPoolContainer').getElements("div.optionRemove").each(function(chn){
				if (paramChnPool != '') paramChnPool += PARAM_SEPARATOR;
				paramChnPool += chn.getAttribute("objId");
			});
			var paramChnUsr = '';
			$('chnUsrContainer').getElements("div.optionRemove").each(function(chn){
				if (paramChnUsr != '') paramChnUsr += PARAM_SEPARATOR;
				paramChnUsr += chn.getAttribute("objId");
			});
						
			var newConfig = {
				'paramRefreshTime': paramRefreshTime,
				'paramViewMode': paramViewMode,
				'paramDays': paramDays,
				'paramChnEnv': paramChnEnv,
				'paramChnPro': paramChnPro,
				'paramChnTsk': paramChnTsk,
				'paramChnPool': paramChnPool,
				'paramChnUsr': paramChnUsr
			};
			jsCaller(mdlConfigPnlApiaSocialContainer.onModalConfirm,newConfig);
		}			
		closeConfigPnlApiaSocialMdl(false);
	});
	
	//Modales
	initEnvMdlPage();
	initProcMdlPage();
	initTaskMdlPage();
	initPoolMdlPage();
	initUsrMdlPage();
}


function showConfigPnlApiaSocialMdl(objParams,retFunction,closeFunction){
	var mdlConfigPnlApiaSocialContainer = $('mdlConfigPnlApiaSocialContainer');
	mdlConfigPnlApiaSocialContainer.removeClass('hiddenModal');
	mdlConfigPnlApiaSocialContainer.blockerModal.show();
	mdlConfigPnlApiaSocialContainer.setStyle('zIndex', SYS_PANELS.getNewZIndex());
	mdlConfigPnlApiaSocialContainer.objParams = objParams;
	mdlConfigPnlApiaSocialContainer.onModalConfirm = retFunction;
	mdlConfigPnlApiaSocialContainer.onModalClose = closeFunction;
	
	spModalConfigPnlApiaSocial.show(true);
	cleanModal();	
	setModal(objParams);
	mdlConfigPnlApiaSocialContainer.position();
	spModalConfigPnlApiaSocial.hide(true);
}

function cleanModal(){
	$('paramRefreshTime').value = '';
	$('paramViewMode').selectedIndex = 0;
	$('paramDays').value = '';
	$('chnEnvContainer').getElements("div.optionRemove").each(function(chn){ chn.destroy(); });
	$('paramChnEnvAva').value = '';
	$('chnProContainer').getElements("div.optionRemove").each(function(chn){ chn.destroy(); });
	$('paramChnProAva').value = '';
	$('chnTskContainer').getElements("div.optionRemove").each(function(chn){ chn.destroy(); });
	$('paramChnTskAva').value = '';
	$('chnPoolContainer').getElements("div.optionRemove").each(function(chn){ chn.destroy(); });
	$('chnUsrContainer').getElements("div.optionRemove").each(function(chn){ chn.destroy(); });	
	
	maxParamEnv = false;
	maxParamPro = false;
	maxParamTsk = false;
	maxParamPool = false;
	maxParamUsr = false;
}

function setModal(objParams){
	if (objParams){
		$('paramRefreshTime').value = objParams.paramRefreshTime;
		$('paramViewMode').value = objParams.paramViewMode;
		$('paramDays').value = objParams.paramDays;
		$('paramChnEnvAva').value = objParams.paramChnEnvAva;
		for (var i = 0; i < objParams.paramChnEnv.length; i++){
			var objId = objParams.paramChnEnv[i].objId;
			var chn = new Element("div.option.optionRemove",{'id':'env_'+objId, html: objParams.paramChnEnv[i].text}).inject($('addEnvironment'),"before");
			chn.setAttribute("objId",objId);
			chn.addEvent("click",function(e){this.destroy(); });
		}
		$('paramChnProAva').value = objParams.paramChnProAva;
		for (var i = 0; i < objParams.paramChnPro.length; i++){
			var objId = objParams.paramChnPro[i].objId;
			var chn = new Element("div.option.optionRemove",{'id':'pro_'+objId, html: objParams.paramChnPro[i].text}).inject($('addProcess'),"before");
			chn.setAttribute("objId",objId);
			chn.addEvent("click",function(e){ this.destroy(); });
		}
		$('paramChnTskAva').value = objParams.paramChnTskAva;
		for (var i = 0; i < objParams.paramChnTsk.length; i++){
			var objId = objParams.paramChnTsk[i].objId;
			var chn = new Element("div.option.optionRemove",{'id':'tsk_'+objId, html: objParams.paramChnTsk[i].text}).inject($('addTask'),"before");
			chn.setAttribute("objId",objId);
			chn.addEvent("click",function(e){ this.destroy(); });
		}
		for (var i = 0; i < objParams.paramChnPool.length; i++){
			var objId = objParams.paramChnPool[i].objId;
			var chn = new Element("div.option.optionRemove",{'id':'pool_'+objId, html: objParams.paramChnPool[i].text}).inject($('addPool'),"before");
			chn.setAttribute("objId",objId);
			chn.addEvent("click",function(e){ this.destroy(); });
		}
		for (var i = 0; i < objParams.paramChnUsr.length; i++){
			var objId = objParams.paramChnUsr[i].objId;
			var chn = new Element("div.option.optionRemove",{'id':'usr_'+objId, html: objParams.paramChnUsr[i].text}).inject($('addUser'),"before");
			chn.setAttribute("objId",objId);
			chn.addEvent("click",function(e){ this.destroy(); });
		}
	}
}

function closeConfigPnlApiaSocialMdl(callFnc){
    var mdlConfigPnlApiaSocialContainer = $('mdlConfigPnlApiaSocialContainer');
    mdlConfigPnlApiaSocialContainer.addClass('hiddenModal');
    mdlConfigPnlApiaSocialContainer.blockerModal.hide();
    if (callFnc && mdlConfigPnlApiaSocialContainer.onModalClose) mdlConfigPnlApiaSocialContainer.onModalClose();
}

function processRetMdlEnvironment(ret){
	for (var i = 0; i < ret.length; i++){
		var env = ret[i];
		var id = env.getRowId();
		if (!$('env_'+id)){
			if (paramExcededSize('E', id)){
				showParamSizeExceded('E');
				return;
			} else {
				var chn = new Element("div.option.optionRemove",{'id':'env_'+id, html: env.getRowContent()[0]}).inject($('addEnvironment'),"before");
				chn.setAttribute("objId",id);
				chn.addEvent("click",function(e){ maxParamEnv = false; this.destroy(); });
			}			
		}
	}	
}

function processRetMdlProcess(ret){
	for (var i = 0; i < ret.length; i++){
		var pro = ret[i];
		var id = pro.getRowId();
		if (!$('pro_'+id)){
			if (paramExcededSize('P', id)){
				showParamSizeExceded('P');
				return;
			} else {
				var chn = new Element("div.option.optionRemove",{'id':'pro_'+id, html: pro.getRowContent()[0]}).inject($('addProcess'),"before");
				chn.setAttribute("objId",id);
				chn.addEvent("click",function(e){ maxParamPro = false; this.destroy(); });
			}			
		}
	}	
}

function processRetMdlTask(ret){
	for (var i = 0; i < ret.length; i++){
		var tsk = ret[i];
		var id = tsk.getRowId();
		if (!$('tsk_'+id)){
			if (paramExcededSize('T', id)){
				showParamSizeExceded('T');
				return;
			} else {
				var chn = new Element("div.option.optionRemove",{'id':'tsk_'+id, html: tsk.getRowContent()[0]}).inject($('addTask'),"before");
				chn.setAttribute("objId",id);
				chn.addEvent("click",function(e){ maxParamTsk = false; this.destroy(); });
			}			
		}
	}	
}

function processRetMdlPool(ret){
	for (var i = 0; i < ret.length; i++){
		var pool = ret[i];
		var id = pool.getRowId();
		if (!$('pool_'+id)){
			if (paramExcededSize('G', id)){
				showParamSizeExceded('G');
				return;
			} else {
				var chn = new Element("div.option.optionRemove",{'id':'pool_'+id, html: pool.getRowContent()[0]}).inject($('addPool'),"before");
				chn.setAttribute("objId",id);
				chn.addEvent("click",function(e){ maxParamPool = false; this.destroy(); });
			}			
		}
	}	
}

function processRetMdlUser(ret){
	for (var i = 0; i < ret.length; i++){
		var usr = ret[i];
		var id = usr.getRowId();
		if (!$('usr_'+id)){
			if (paramExcededSize('U', id)){
				showParamSizeExceded('U');
				return;
			} else {
				var chn = new Element("div.option.optionRemove",{'id':'usr_'+id, html: usr.getRowContent()[0]}).inject($('addUser'),"before");
				chn.setAttribute("objId",id);
				chn.addEvent("click",function(e){ maxParamUsr = false, this.destroy(); });
			}			
		}
	}		
}

function paramExcededSize(type,newValue){
	var str = '';
	var container = null;
	if (type == 'E'){
		container = $('chnEnvContainer');
	} else if (type == 'P'){
		container = $('chnProContainer');
	} else if (type == 'T'){
		container = $('chnTskContainer');
	} else if (type == 'G'){
		container = $('chnPoolContainer');
	} else if (type == 'U'){
		container = $('chnUsrContainer');
	}
	if (container != null){
		container.getElements("div.optionRemove").each(function(e){
			if (str != '') str += PARAM_SEPARATOR;
			str += e.getAttribute("objId");
		});
	}
	if (newValue != null){
		if (str != '') str += PARAM_SEPARATOR;
		str += newValue;
	}
	
	return str.length >= MAX_PARAMETER_SIZE;
}

function showParamSizeExceded(type){
	var lblType = '';
	if (type == 'E'){
		lblType = LBL_ENV;		 
	} else if (type == 'P'){
		lblType = LBL_PRO;		
	} else if (type == 'T'){
		lblType = LBL_TSK;		
	} else if (type == 'G'){
		lblType = LBL_POOL;		
	} else if (type == 'U'){
		lblType = LBL_USR;
	}
	showMessage(MSG_MAX_PAR_SIZE.replace("<TOK1>",lblType), GNR_TIT_WARNING, 'modalWarning');
}
