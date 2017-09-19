$(function () {	
	initial();	
});

function initial(){	
	setRouter();
	
}
function showMainContent(){
	$('#main-content').empty();
	buildTemplate('tpl_page_main',null,'main-content');
	
	showDataList();
	setBindMain();
}
function showDataList(){	
	
	var obj = {
		arr : getModeDate()
	};

	buildTemplate('tpl_mode_list',obj,'table_list');
}
function showModify(_id){

	if(!_.isNumber( parseInt(_id))){
		alert('Operation error');
		return false;
	}
	
	var obj = {
		
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');

	updateSelect(_id);
	
	setBindAdd();
}
function setBindMain(){
	//index delete button
	$("#table_list").unbind("click.delete");
    $("#table_list").on("click.delete", ".delete", function(event){ 
        if(confirm('Are you sure?')){
        	showMainContent();
        	return;
        }
    });
}
function setBindAdd(){
 	//module change
	$("#main-content").unbind("click.mode");
	$("#main-content").on("click.mode", ".mode", function(event){ 
    	$('#mode-text').text($(this).text());
    	$('#mode-text').attr('data-id',$(this).attr('data-code'));
    	updateSelect($(this).attr('data-code'));
    });

	$("#main-content").unbind("click.submit");
   	$("#main-content").on("click.submit", "#submit", function(event){ 
   		var deviceArr = [];

   		var modeId = $('#multiple_select').attr('data-id');

   		$('select[name="_helper2"] option').each(function() {
   			var data = {
   				deviceId :$(this).val()
   			}
		    deviceArr.push(data);
		});

		var obj = {
			list : deviceArr,
			modeId : modeId
		};

		if(!checkProcess){
			alert('error');
			return false;
		}

		doSubmit(obj);
    });
}
function getModeDate(){
	var data = null;

	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listmode',
		// data: 'data',
		success: function(obj){
			data = obj.data;
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});

	return data;
}
function getDeviceData(_id){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listmodedevicesetting',
		data: JSON.stringify({"modeid":_id}),
		success: function(obj){
			data = obj.data;
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});

	return data;
}
function updateSelect(_id){
	var deviceData = getDeviceData(_id);

	var allDeviceArr = deviceData.allDeviceList;
	var modeDeviceArr = deviceData.modeDeviceList;

	var selectArr = [];

	for(var x=0;x<allDeviceArr.length;x++){
		var selected = "N";
		for(var i=0;i<modeDeviceArr.length;i++){
			if(modeDeviceArr[i].rcuDeviceId == allDeviceArr[x].id){
				selected = "Y";
				break;
			}
		}
		var newJson = {
			rcuDeviceId : allDeviceArr[x].id,
			device : allDeviceArr[x].device,
			selected : selected,
			gouprName : allDeviceArr[x].gouprName
		}
		selectArr.push(newJson);
	}

	var obj = {
		dataId : _id,
		selectArr : selectArr
	};

	buildTemplate('tpl_multiple_select',obj,'multiple-select');

	$('#multiple_select').bootstrapDualListbox({
		nonSelectedListLabel: 'Non-selected',
		selectedListLabel: 'Selected',
		preserveSelectionOnMove: 'moved',
		moveOnSelect: false ,
		showFilterInputs :false
	});
}
function setRouter(){
	var routes = {
		'add': showModify,
		'add/:_id': showModify,
		'':showMainContent()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showMainContent();
  	};
    router.init('');
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function buildTemplateMultipleHtml(tpl_code,_obj,pageId){
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function sortById(array){
	array.sort(function(objA, objB){
        if (objA.id > objB.id) {
	    	return 1;
	  	}
  		if (objA.id < objB.id) {
	    	return -1;
	  	}
	  	return 0;
    });
    return array;
}
function doSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/insertrcumodedevice',
		data: JSON.stringify(data),
		success: function(obj){
			alert('success');
			window.history.replaceState(null,null,'#');
			showMainContent();
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function checkProcess(data){
	if(data.length <= 0){
		return false;
	}

	if(_.isNull(data)){
		return false;
	}

	if(_.isUndefined(data)){
		return false;
	}

	return true;
}