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
		arr : getRoomMouduleData()
	};

	buildTemplate('tpl_mode_list',obj,'table_list');

	$('#data-table').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": false,
		"info": false,
		"autoWidth": true,
		"displayLength": 10
    });
}
function showModify(_id){

	if(!_.isNumber( parseInt(_id))){
		alert('Operation error');
		return false;
	}
	
	var obj = {
		
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_modify',obj,'main-content');

	updateSelect(_id);
	
	setBindAdd();
}
function showAdd(){
	var obj = {}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');

	var allDeviceArr = getAllDeviceData();

	updateSelectWithAdd(allDeviceArr);
	
	setBindAdd();
}
function setBindMain(){
	//index delete button
	$("#table_list").unbind("click.delete");
    $("#table_list").on("click.delete", ".delete", function(event){ 
        if(confirm('Are you sure?')){
			deleteProcess($(this).attr('value'));
        	window.history.replaceState(null,null,'#');
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
   		var obj = getSubmitObj();
		doSubmit(obj);
    });

    $("#main-content").unbind("click.add-submit");
   	$("#main-content").on("click.add-submit", "#add-submit", function(event){ 

   		if($("#myForm").valid()){
			var obj = getSubmitObj();
			if(obj.devices.length<=0){
				$('#select-error').attr('style','display');
				return;
			}else{
				$('#select-error').attr('style','display:none');
			}
	   		var groupName = $('#input-group-name').val();
	   		obj.groupname = groupName;
			doSubmitAdd(obj);
			window.history.replaceState(null,null,'#');
			showMainContent();
		}
    });
}
function getSubmitObj(){
	var deviceArr = [];

	var groupId = $('#multiple_select').attr('data-id');

	$('select[name="_helper2"] option').each(function() {
		var data = {
			deviceId :$(this).val()
		}
	    deviceArr.push($(this).val());
	});

	var obj = {
		devices : deviceArr,
		groupid : groupId
	};

	return obj;
}
function getRoomMouduleData(){
	var data = null;

	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listrcugroup',
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
function setRouter(){
	var routes = {
		'add': showAdd,
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
		url: $.serverurl + '/rcu/group/device/insert',
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
function doSubmitAdd(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/group/insert',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status !=0){
				alert(obj.message);
			}else{
				alert('success');
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function updateSelect(_id){
	var deviceData = getDeviceData(_id);

	if(_.isUndefined(deviceData)){
		alert('not find data');
		window.history.replaceState(null,null,'#');
		showMainContent();
		return;
	}

	var allDeviceArr = deviceData.alldevices;
	var groupDeviceArr = deviceData.groupdevices;

	var selectArr = [];

	for(var x=0;x<allDeviceArr.length;x++){
		var selected = "N";
		for(var i=0;i<groupDeviceArr.length;i++){
			for(var j=0;j<groupDeviceArr[i].devices.length;j++){
				if(groupDeviceArr[i].devices[j].deviceid == allDeviceArr[x].id){
					selected = "Y";
					break;
				}
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

	$('#group-name').text(deviceData.groupname);
}
function updateSelectWithAdd(_deviceData){

	if(_.isUndefined(_deviceData)){
		alert('not find data');
		window.history.replaceState(null,null,'#');
		showMainContent();
		return;
	}
	
	var selectArr = [];

	for(var x=0;x<_deviceData.length;x++){
		var newJson = {
			rcuDeviceId : _deviceData[x].id,
			device : _deviceData[x].device,
			selected : "N",
			gouprName : _deviceData[x].gouprName
		}
		selectArr.push(newJson);
	}

	var obj = {
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

	$("#myForm").validate();
}
function getDeviceData(_id){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/group/device',
		data: JSON.stringify({"groupid":_id}),
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
function getAllDeviceData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listalldevice',
		//data: JSON.stringify({}),
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
function deleteProcess(_id){
	var data = {
		groupid : _id
	}

	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/group/delete',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status !=0){
				alert(obj.message);
			}else{
				alert('success');
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}