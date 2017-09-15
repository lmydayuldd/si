$(function () {	
	initial();	
});

function initial(){	
	setRouter();
}

function showIndex(){
	var obj = {
		
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');
	buildTemplate('tpl_hvac',obj,'body-model');
	
	setBindAdd();

	$('#enter-group').attr('style','display:none');
	$('.slider').slider();

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
	$("#main-content").unbind("change.item-input");
	$("#main-content").on('change.item-input', '#item-input', function(event){ 
    	var re = /^[0-9]*$/;
		if (!re.test($(this).val())){
	 		alert('Please enter only digits!!');
	 		$(this).val('');
			return false;
		}
    });
    $("#main-content").unbind("change.room-type");
	$("#main-content").on('change.room-type', 'input[name="room-type"]', function(event){ 
    	roomTypeProcess($(this).val());
    });
	$("#main-content").unbind("click.add-button");
	$("#main-content").on("click.add-button", "#add-button", function(event){ 
		var value = $('#item-input').val();
		if(_.isUndefined(value)){
			return false;
		}else if(_.isNull(value)){
			return false;
		}else if(value.length<=0){
			return false;
		}
		addItemProcess(value);
    });

    $("#main-content").unbind("change.select2");
	$("#main-content").on('change.select2', '.select2', function(event){ 
    	$('.select2-selection__choice').css("background-color","#3c8dbc");	
    });
    //submit
	$("#main-content").unbind("click.submit");
	$("#main-content").on("click.submit", "#submit", function(event){ 
		if($("#myForm").valid()){
			submitProcess();
		}
    });
}
function setBindAddItem(){
	$("#main-content").unbind("click.item-num");
	$("#main-content").on("click.item-num", ".item-num", function(event){ 
    	removeItemProcess($(this));
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
function roomTypeProcess(_value){
	switch(_value) {
	    case "all":
	    	$('#enter-group').attr('style','display:none');
	    	$('#room-tabs').attr('style','display:none');

	        break;
	    case "check-in":
	        $('#enter-group').attr('style','display:none');
	        $('#room-tabs').attr('style','display:none');
	        break;
        case "check-out":
	        $('#enter-group').attr('style','display:none');
	        $('#room-tabs').attr('style','display:none');
	        break;
        case "floor":
	        $('#enter-group').attr('style','display');
	        $('#room-tabs').attr('style','display:none');
	        break;
        case "room":
	        $('#enter-group').attr('style','display:none');
	        $('#room-tabs').attr('style','display');
	        roomTabProcess();
	        break;
	    default:
	    	$('#enter-group').attr('style','display:none');
	    	$('#room-tabs').attr('style','display:none');
	        break;
	}
	$('#item-group').empty();
}
function addItemProcess(_value){

	var re = /^[0-9]*$/;

	if (!re.test(_value)){
 		alert('Please enter only digits!!');
		return false;
	}

	var arr = [];

	$('.item-num').each(function(index) {
		var dataObj = {
			'number' : $(this).val()
		}
		arr.push(dataObj);
	});

	var dataObj = {
		'number' : _value
	}
	arr.push(dataObj);

	var obj = {
		'arr' : arr,
		'name' : '所選項目'
	}
	buildTemplate('tpl_item',obj,'item-group');
	setBindAddItem();
}
function removeItemProcess(_obj){
	_obj.remove();
	if($('.item-num').length<=0){
		$('#item-group').empty();
	}
}
function submitProcess(){
	var obj = getHvacObjProcess();

	if(!_.isNull(obj)){
		doSubmit(obj);
	}
    
}
function getHvacObjProcess(){
	
	var _function = $('#hvac-body .function .active').children('input[name="function"]').val();
	
	var temperature = $("#display-temperature").text();

	var delay_close = $("#display-delay-close").text();

	var type = $('#hvac-body .room-type .active').children('input[name="room-type"]').val();

	var arr = [];
	
	if(type == "floor"){
		$('.item-num').each(function(index) {
			arr.push($(this).val());
		});

		if(arr.length<=0){
			alert('請輸入樓層');
			return null;
		}
	}else if(type == "room"){
		$('input[name="roomNo"]').each(function(index) {
			if($(this).prop("checked")){
				arr.push($(this).val());
			}
		});

		if(arr.length<=0){
			alert('請選擇房號');
			return null;
		}
	}
	
	var obj = {
		data : {
			function : _function,
			temperature : temperature,
			delayclosetime : delay_close,
			type : type,
			itemlist : arr
		}
	}
	return obj;
}
function doSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/hvaccontrol',
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
function getRoomData(cb){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/rooms',
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
	return $.parseJSON(data);
}
function roomTabProcess(){
	var roomData = getRoomData();
	var floorArr = [];

	//先把樓層找出來
	for(var i=0;i<roomData.length;i++){
		var isExist = false;
		var floor = roomData[i].floor;

		for(var x=0;x<floorArr.length;x++){
			if(floor == floorArr[x]){
				isExist = true;
				break;
	       	}
		}

		if(!isExist){
			floorArr.push(floor);
		}
	}

	var arr = [];

	for(var i=0;i<floorArr.length;i++){

		//找到同樓層的資料
		var floorData = $.grep(roomData, function(obj) {
		    return obj.floor === floorArr[i];
		});

		var roomArr = [];

		//format 頁面要得格式
		for(var x=0;x<floorData.length;x++){
			var roomObj = {
				'roomNo' : floorData[x].roomNo
			}
			roomArr.push(roomObj);
		}
		
		var floorObj = {
			'floorNo' : floorArr[i],
			'roomArr' : roomArr
		}
		
		arr.push(floorObj);
	}

	var obj = {
		floorArr :arr
	}

	buildTemplate('tpl_tabs',obj,'tabs');
}