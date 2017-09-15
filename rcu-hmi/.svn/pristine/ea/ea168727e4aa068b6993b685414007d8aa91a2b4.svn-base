$(function () {	
	initial();	
});

function initial(){	
	setRouter();
}

function showIndex(){

	var scheduleData = getScheduleData();

	var obj = {
		_function : scheduleData.description.function,
		temperature : scheduleData.description.temperature,
		delay_close :  scheduleData.description.timer,
		start_time : scheduleData.executiontime
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');
	buildTemplate('tpl_hvac',obj,'body-model');

	if(scheduleData.status == 0){
		$('#schedule-switch').bootstrapSwitch('state', false);
	}else{
		$('#schedule-switch').bootstrapSwitch('state', true);
	}

	
	setBindAdd();
	
	$('.slider').slider();
	$('.clockpicker').clockpicker();
	$('.btn-switch').bootstrapSwitch();

	$("#myForm").validate();
}
function setBindAdd(){
	$("#main-content").unbind("slide.temperature");
	$("#main-content").on("slide.temperature", "#temperature", function(event){ 
    	$("#display-temperature").text(event.value);
    });
    $("#main-content").unbind("click.temperature");
	$("#main-content").on("click.temperature", "#temperature", function(event){ 
    	var value = $('#temperature .min-slider-handle').attr('aria-valuenow');
    	$("#display-temperature").text(value);
    });
    $("#main-content").unbind("slide.delay-close");
	$("#main-content").on("slide.delay-close", "#delay-close", function(event){ 
    	$("#display-delay-close").text(event.value);
    });
    $("#main-content").unbind("click.delay-close");
	$("#main-content").on("click.delay-close", "#delay-close", function(event){ 
    	var value = $('#delay-close .min-slider-handle').attr('aria-valuenow');
    	$("#display-delay-close").text(value);
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
		'':showIndex()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showIndex();
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

function submitProcess(){
	var obj = getHvacObjProcess();

	if(!_.isNull(obj)){
		doSubmit(obj);
	}
    
}
function getHvacObjProcess(){

	var _switch = $('#schedule-switch').bootstrapSwitch('state')?true:false;
	
	var _function = $('#hvac-body .function .active').children('input[name="function"]').val();
	
	var temperature = $('#display-temperature').text();

	var delay_close = $('#display-delay-close').text();

	var starttime = $('#starttime').val();

	var arr = [];
	
	var obj = {
		data : {
			openswitch : _switch,
			function : _function,
			temperature : temperature,
			delayclosetime : delay_close,
			starttime : starttime
		}
	}
	return obj;
}
function doSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/schedule/checkupdate',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status!=0){
				alert(obj.message);
				return false;
			}else{
				alert('成功!!');
				window.history.replaceState(null,null,'#');
				showIndex();
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
function getScheduleData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/schedule/checkupdateinfo',
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