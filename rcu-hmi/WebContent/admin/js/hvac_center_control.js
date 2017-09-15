$(function () {	
	initial();	
});

function initial(){	
	setRouter();
}

function showModify(){
	var obj = {
		
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');
	buildTemplate('tpl_hvac',obj,'body-model');
	

	setBindAdd();

	$('.slider').slider();

	$("#myForm").validate();
}

function setBindAdd(){
	$("#main-content").unbind("slide.temperature");
	$("#main-content").on("slide.temperature", "#temperature", function(event){ 
    	$("#display-temperature").text(event.value);
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
		'':showModify()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showModify();
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
	var obj = getHvacObjProcess()
    doSubmit(obj);
}
function getHvacObjProcess(){
	
	var _function = $('#hvac-body .function .active').children('input[name="function"]').val();
	
	var temperature = $("#display-temperature").text();
	
	var obj = {
		function : _function,
		temperature : temperature
	}
	return obj;
}	

function doSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/hvacentercontrol',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status!=0){
				alert(obj.message);
				return false;
			}
			alert('成功!!');
			window.history.replaceState(null,null,'#');
			showModify();
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}