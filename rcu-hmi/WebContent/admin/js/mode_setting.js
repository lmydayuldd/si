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

	// console.log($('#main-content').css('min-height'));
}
function showDataList(){	
	var obj = {
		arr : getModeDate()
	};

	buildTemplate('tpl_mode_list',obj,'table_list');
}
function showAdd(){
	var roomModuleArr = getRoomModuleData();
	
	var obj = {
		'roomModuleArr' : roomModuleArr
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');
	buildTemplate('tpl_mode_add',obj,'mode_group');
	buildTemplate('tpl_hvac',obj,'body-model');

	if(roomModuleArr.length){
		changeEvent(0,roomModuleArr[0].id);
	}

	$('.slider').slider();
	$('.input-curtain').bootstrapSwitch();
	$('.input-light').bootstrapSwitch();
	$('.input-aircon').bootstrapSwitch();
	$('.clockpicker').clockpicker();

	setBindAdd();

	$("#myForm").validate();

}
function showModify(_id){
	var roomModuleArr = getRoomModuleData();
	var modeArr = getModeDate();

	var modeName = '';

	for(var i=0;i<modeArr.length;i++){
		if(modeArr[i].id == _id){
			modeName = modeArr[i].keyname;
			break;
		}
	}
	
	var obj = {
		'roomModuleArr' : roomModuleArr,
		'modeId' : _id,
		'modeName' : modeName
		//'modeArr' : modeArr
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');
	// buildTemplate('tpl_mode_add',obj,'mode_group');
	buildTemplate('tpl_hvac',obj,'body-model');


	if(roomModuleArr.length){
		changeEvent(_id,roomModuleArr[0].id);
	}

	$('.slider').slider();
	$('.input-curtain').bootstrapSwitch();
	$('.input-light').bootstrapSwitch();
	$('.input-aircon').bootstrapSwitch();
	$('.clockpicker').clockpicker();

	setBindAdd();

	$("#myForm").validate();
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
	$("#main-content").unbind("slide.temperature");
	$("#main-content").on("slide.temperature", "#temperature", function(event){ 
    	$("#display-temperature").text(event.value);
    });

    $("#main-content").unbind("slide.time");
	$("#main-content").on("slide.time", "#time", function(event){ 
    	$("#display-time").text(event.value);
    });

    //module change
	$("#main-content").unbind("click.room-module");
	$("#main-content").on("click.room-module", ".room-module", function(event){ 
    	$('#module').text($(this).text());
    	$('#module').attr('data-code',$(this).attr('data-code'));
    	changeEvent($('#mode_id').val(),$(this).attr('data-code'));
    });

    //submit
	$("#main-content").unbind("click.submit");
	$("#main-content").on("click.submit", "#submit", function(event){ 
		if($("#myForm").valid()){
			submitProcess();
		}
    });
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
function changeEvent(_modeId,_groupId){
	//房型對應到模式 應該要設定甚麼
	var data = getModeSettingData(_groupId);

	if(_.isUndefined(data)){
		alert('This mode does not hava device info.');
		// window.history.replaceState(null,null,'#');
		// showMainContent();
		var obj = {};
		buildTemplateMultipleHtml('',obj,'body-model');
		return false;
	}

	//已經設定過的資料
	var settingData = getGruopModeSettingData(_modeId,_groupId);
	
	var tpl_code = '';

	var hvacArr = [];
	var bulbArr = [];
	var curtainArr = [];

	for(var i=0;i<data.length;i++){
		var catalogue = data[i].catalogue;
		switch(catalogue){
			case 'AIR-CONDITION':
				var deviceData = [];

				if(!_.isUndefined(settingData)){
					deviceData = $.grep(settingData.mode, function(obj) {
					    return obj.catalogue === "AIR-CONDITION";
					});

					deviceData = deviceData[0].devices;
				}

				for(var u=0;u<data[i].devices.length;u++){
					for(var j=0;j<deviceData.length;j++){
						if(deviceData[j].keycode == data[i].devices[u].device){
							data[i].devices[0].condition = deviceData[j].condition;
						}
					}
				}

				
				//function 1冷,2熱,3風
				//temperature 16~30
				//speed 0自動 1高 2中 3低
				//timer 分鐘
				if(_.isUndefined(data[i].devices[0].condition)){
					data[i].devices[0].condition = {
						function : 1,
						temperature : 25,
						power : 0,
						speed : 0
					}
				}
				hvacArr.push(data[i].devices[0].condition);
				tpl_code += $('#tpl_hvac').html();
			break;
			case 'BULB':
				var deviceData = [];

				if(!_.isUndefined(settingData)){
					deviceData = $.grep(settingData.mode, function(obj) {
					    return obj.catalogue === "BULB";
					});

					deviceData = deviceData[0].devices;
				}

				for(var x=0;x<data[i].devices.length;x++){
					
					var bublData = data[i].devices[x];

					bublData.condition = {
						status : 0
					};

					//如果有設定的資料 就帶入
					for(var j=0;j<deviceData.length;j++){
						if(deviceData[j].keycode === bublData.device){
							bublData.condition.status = deviceData[j].condition.status;
						}
					}
				
					var newObj = {
						bulbName : bublData.device,
						catalogue : catalogue,
						keycode : bublData.device,
						status : bublData.condition.status
					}
					bulbArr.push(newObj);
				}
				
				tpl_code += $('#tpl_bulb').html();
			break;
			/*
			case 'SERVICE':
				var deviceData = [];
				if(settingData.length>0){
					deviceData = $.grep(settingData.mode, function(obj) {
					    return obj.catalogue === "SERVICE";
					});

					deviceData = deviceData[0].devices;
				}
				

				for(var x=0;x<data[i].devices.length;x++){
					
					var serviceData = data[i].devices[x];

					serviceData.condition = {
						status : 0
					};

					//如果有設定的資料 就帶入
					for(var j=0;j<deviceData.length;j++){
						if(deviceData[j].keycode === serviceData.device){
							serviceData.condition.status = deviceData[j].condition.status;
						}
					}
				
					var newObj = {
						bulbName : serviceData.device,
						catalogue : catalogue,
						keycode : serviceData.device,
						status : serviceData.condition.status
					}
					bulbArr.push(newObj);
				}
				
				tpl_code += $('#tpl_curtain').html();
			break;
			*/
		}
	}

	var obj = {
		hvacArr : hvacArr,
		bulbArr : bulbArr,
		curtainArr : curtainArr
	}

	buildTemplateMultipleHtml(tpl_code,obj,'body-model');
	
	$('.slider').slider();
	$('.btn-switch').bootstrapSwitch();
	$('.clockpicker').clockpicker();
	setBindAdd();
}
function changeModel(_model){
	
	var tpl_code = null;
	var bulbArr = null;
	var curtainArr = null;
	var roomModuleArr = null;

	switch(_model){
		case '1':
			tpl_code = $('#tpl_hvac').html();
			break;
		case '2':
			tpl_code = $('#tpl_hvac').html() + $('#tpl_bulb').html() + $('#tpl_curtain').html();
			curtainArr = curtainDataProcess();
			bulbArr = bulbDataProcess();
			break;
		case 'checkinnon':
			tpl_code = $('#tpl_hvac').html() + $('#tpl_bulb').html() + $('#tpl_curtain').html();
			curtainArr = curtainDataProcess();
			bulbArr = bulbDataProcess();
			break;
		case 'welcome':
			tpl_code = $('#tpl_hvac').html() + $('#tpl_bulb').html() 
				+ $('#tpl_curtain').html();
			curtainArr = curtainDataProcess();
			bulbArr = bulbDataProcess();
		
			break;
		case 'psi':
			tpl_code = $('#tpl_hvac').html();
			break;
		case 'humidity':
			tpl_code = $('#tpl_hvac').html();
			break;
	}

	var obj = {
		'bulbArr' : bulbArr,
		'curtainArr' : curtainArr
	}
		

	buildTemplateMultipleHtml(tpl_code,obj,'body-model');
	
	$('.slider').slider();
	$('.input-curtain').bootstrapSwitch();
	$('.input-light').bootstrapSwitch();
	$('.input-aircon').bootstrapSwitch();
	$('.clockpicker').clockpicker();
	setBindAdd();
}
function bulbDataProcess(){
	var arr = [];

	var obj = {
		'bulbName' : '浴室燈'
	}

	arr.push(obj);

	obj = {
		'bulbName' : '歡迎燈'
	}
	arr.push(obj);

	return arr;
}
// function curtainDataProcess(){
// 	var arr = [];

// 	var obj = {
// 		'curtainName' : '窗簾A'
// 	}

// 	arr.push(obj);

// 	obj = {
// 		'curtainName' : '落地窗'
// 	}
// 	arr.push(obj);

// 	return arr;
// }
//取得房型資料
function getRoomModuleData(){
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

	return sortById(data);
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
function getModeSettingData(_groupId){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/group/mode',
		data: JSON.stringify({
			"groupid" : _groupId
		}),
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
function getGruopModeSettingData(_modeId,_groupId){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/group/mode/setting',
		data: JSON.stringify({
			"groupid" : _groupId,
			"modeid" : _modeId
		}),
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
function submitProcess(){
	var modeId = $('#mode_id').val();
	// var modeId = $('#mode').attr('data-code');
	var groupId = $('#module').attr('data-code');
	var arr = [];

	if($('#hvac-body').length>=1)
		arr = getHvacObjProcess(arr);

	if($('#bulb-body').length>=1)
		arr = getBlubObjProcess(arr);

	if(arr.length<=0){
		alert('DATA ILLEGAL!!');
		return false;
	}

	var obj = {
		"modeid": modeId,
		// "modeName": $('#mode_name').val(),
		"groupid": groupId,
		"devices": arr
    }

    doSubmit(obj);
}
function getHvacObjProcess(arr){
	var power = $('#hvac-body #hvac_power').bootstrapSwitch('state')

	var _function = $('#hvac-body .function .active').children('input[name="function"]').val();
	var speed = $('#hvac-body .speed .active').children('input[name="speed"]').val();

	var temperature = $("#display-temperature").text();
	// var _time = $("#display-time").text();
	
	arr.push(
		{
			"keycode":"HVAC-ALL",
			"data":{
				"power": power,
				"function": _function,
				"temperature": temperature,
				"address" : 8,
				"speed": speed
				// "timer": _time
			}
		}
	);
	
	return arr;
}	
function getBlubObjProcess(arr){
	var deviceArr = [];

	$('.BULB').each(function(index) {
		var bulbObj = {
			keycode : $(this).attr('keycode'),
			data : {
				status : $(this).bootstrapSwitch('state')?'1':'0'
			}
		};

  		arr.push(bulbObj);
	});

	var obj = {
		catalogue : 'BULB',
		devices : deviceArr
	};

	return arr;
}
function doSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/mode/update',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status!=0){
				alert(obj.message);
				return false;
			}
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