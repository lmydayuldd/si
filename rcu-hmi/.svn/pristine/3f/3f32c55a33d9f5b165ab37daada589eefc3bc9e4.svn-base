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
	var arr = [
		{
			name: Math.floor((Math.random() * 999) + 100)
		}
	];

	var obj = {
		arr : arr
	};

	buildTemplate('tpl_mode_list',obj,'table_list');
}
function showModify(_id){
	var data = getRcuDevicesData();
	
	var obj = {
		'roomModuleArr' : null,
		'rooms' : getRcuDevicesData(),
		'bulbArr' : bulbDataProcess(data),
		'hvacArr' : hvacDataProcess(data)
	}

	$('#main-content').empty();

	var tpl_code = $('#tpl_hvac').html() + $('#tpl_bulb').html() ;

	buildTemplate('tpl_page_add',obj,'main-content');
	buildTemplateMultipleHtml(tpl_code,obj,'body-model');
	// buildTemplate('tpl_hvac',obj,'body-model');

	$('.slider').slider();
	$('.input-curtain').bootstrapSwitch();
	$('.input-light').bootstrapSwitch();
	$('.input-aircon').bootstrapSwitch();
	$('.clockpicker').clockpicker();

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
 	$("#temperature").on("slide", function(slideEvt) {
		$("#display-temperature").text(slideEvt.value);
	});

	$("#time").on("slide", function(slideEvt) {
		$("#display-time").text(slideEvt.value);
	});

	//Type change
	$("#main-content").unbind("click.type-model");
	$("#main-content").on("click.type-model", ".type-model", function(event){ 
    	$('#model').text($(this).text());
    	changeModel($(this).attr('data-code'));
    });

    //module change
	$("#main-content").unbind("click.room-module");
	$("#main-content").on("click.room-module", ".room-module", function(event){ 
    	$('#module').text($(this).text());
    	
    });
   
	$("#main-content").unbind("click.remove-body");
	$("#main-content").on("click.remove-body", ".remove-body", function(event){ 
    	var index = $(this).attr('data-id');
    	$('#havc-group-' + index).remove();
    });

 	$("#main-content").unbind("click.hvac-open");
	$("#main-content").on("click.hvac-open", ".hvac-open", function(event){ 
		var item = $(this).parent('a');
		var isOpen = item.attr('aria-expanded');

		var item_i = item.find('i');

		collapseIconChange(isOpen,item_i,'hvac');
    });

    $("#main-content").unbind("click.bulb-open");
	$("#main-content").on("click.bulb-open", ".bulb-open", function(event){ 
		var item = $(this).parent('a');
		var isOpen = item.attr('aria-expanded');
		var item_i = item.find('i');
		collapseIconChange(isOpen,item_i,'bulb');
    });
    
}
function collapseIconChange(_isOpen,_obj,_name){
	if(_isOpen=='false' || _.isUndefined(_isOpen) ){
		_obj.attr('class','fa fa-minus '+ _name +'-open');
	}else{
		_obj.attr('class','fa fa-plus '+ _name +'-open');
	}
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
function changeModel(_model){
	var tpl_code = null;
	var bulbArr = null;
	var curtainArr = null;
	var roomModuleArr = null;

	switch(_model){
		case 'checkin':
			tpl_code = $('#tpl_hvac').html();
			break;
		case 'checkout':
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
function hvacDataProcess(_data){
	var data = [];

	//取出冷氣的資料
	for(var i=0;i<_data.length;i++){
		if(_data[i]["gouprName"] == 'AIR-CONDITION'){
			data.push(_data[i]);
		}
	}

	data = sortBy(data);

	return data;
}
function bulbDataProcess(_data){
	var data = [];

	//取出燈的資料
	for(var i=0;i<_data.length;i++){
		if(_data[i]["gouprName"] == 'BULB'){
			data.push(_data[i]);
		}
	}

	data = sortBy(data);

	return data;
}
function curtainDataProcess(){
	var arr = [];

	var obj = {
		'curtainName' : '窗簾A'
	}

	arr.push(obj);

	obj = {
		'curtainName' : '落地窗'
	}
	arr.push(obj);

	return arr;
}
function roomModuleDataProcess(){
	var arr = [];

	var obj = {
		'moduleName' : '總統套房'
	}

	arr.push(obj);

	obj = {
		'moduleName' : '雙人房'
	}
	arr.push(obj);

	obj = {
		'moduleName' : '海景第一排'
	}
	arr.push(obj);

	return arr;
}
function getRoomModuleData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listrcugroup',
		// data: data,
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
function getRcuDevicesData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listmodelsettingdevice',
		data: 'data',
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
function sortBy(array){
	array.sort(function(objA, objB){
        // var tempA = objA.id.toString().toLowerCase();
        // var tempB = objB.id.toString().toLowerCase();
        // if(tempA== tempB) return 0;
        // return tempA> tempB? 1: -1;
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