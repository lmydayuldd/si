var data = null;
var websocket = null;
var connectionCount = 0;
var langs = 'zh_TW';
var roomarray = null;
var timerID = 0;

$(function () {
	initial();
	
  	websocket = new WebSocket($.websocketurl + "/socketindexinfo" );
  	
    websocket.onerror = function(evt) {
    	alert('系統忙碌中!!');
    };

	sendMsg();
});

function initial(){
	//about room service
	getSOSData();
	getSosTotalData();
	getDndTotalData();
	getMakeUpTotalData();
	getbulterTotalData();

	//about hotel
	getOccupiedCountData();
	getVacantData();

	setBindDND();
	setBindMUR();
	
	//alarm audio
	playAlarm();
}

function updateCount(){
	getDndTotalData();
	getMakeUpTotalData();
	getbulterTotalData();
	getOccupiedCountData();
	getVacantData();
}

function sendMsg(){
	var obj = [
        // {"keycode":"SOS"},
        // {"keycode":"MUR"}
    ];


	websocket.onopen = function() {
		console.log('onopen');
		keepAlive();
	};

    websocket.onmessage  = function(evt) { 
    	// console.log(evt.data);
    	data = $.parseJSON( evt.data ); 
    	getSOSData();
    	updateCount();
    };
    
    waitForSocketConnection(websocket, function(){
        // console.log("message sent!!!");
    	websocket.send(JSON.stringify(obj)); 
    });
}
function keepAlive() {
    var timeout = 120000; 

    var obj = "logopen";

    if (websocket.readyState == websocket.OPEN) {  
    	console.log('keepAlive-OPEN '+ new Date());
        // websocket.send(JSON.stringify(obj)); 
        websocket.send(obj); 

	 	var timerId = setTimeout(keepAlive, timeout);  

	    while (timerId--) {
			window.clearTimeout(timerId); 
		}
    }else{
    	console.log('websocket.fail '+ new Date());

    	waitForSocketConnection(websocket, function(){
	    	sendMsg();
	    });
    }	
   
} 
// function reconnect (){
// 	console.log('reconnect');
//     websocket = new WebSocket($.websocketurl + "/socketindexinfo" );
//     sendMsg();
// }
function waitForSocketConnection(socket, callback){
	connectionCount++;

    if(connectionCount>=5000){
    	cleanSetTimeOut();
    	// clearInterval(myVar);
    	alert('Websocket time out!!');
    	location.reload();
    	return false;
    }

	setTimeout(function () {
	    if (socket.readyState === 1) {
	        connectionCount = 0;

	        cleanSetTimeOut();

	       	websocket = socket;

	       	keepAlive();

	        if(callback != null){
	            callback();
	        }
	        return;
	    } else {
	        console.log("wait for connection...");
	        try{
	        	socket = new WebSocket($.websocketurl + "/socketindexinfo" );
	        }finally{
	        	waitForSocketConnection(socket, callback);
	        }
	    }
	}, 10); 
}
function cleanSetTimeOut(){
	var timeoutId = setTimeout(function() {}, 0);
	while (timeoutId--) {
	    clearTimeout(timeoutId); 
	}

	var intervalId = setInterval(function() {}, 0);
	while (intervalId--) {
	    clearInterval(intervalId); 
	}
}
function getSOSData(){
	var arr = [];
	roomarray = new Array();

	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "SOS"){
				var alertFlag = "N";

				//和心跳失聯 超過五分鐘顯示提示
				if(data[i]["lostMinute"] >= 5){
					alertFlag = "Y";
				}
				var sosObj = {
					'roomNo' : data[i]["roomNo"],
					'intervalTime' : data[i]["lostMinute"],
					'alertFlag' : alertFlag
				}
				roomarray.push(data[i]["roomNo"]);
				arr.push(sosObj);
			}
		}
	}

	var obj = {
		'arr' : arr
	}
	
	buildTemplate('tpl_sos_list',obj,'list_sos');
	//綁定事件
	setBindSOS();
}
function getSosTotalData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "SOS"){
				count++;
			}
		}
	}

	var obj = {
		'sosCount':count 
	}

	buildTemplate('tpl_sos',obj,'index_sos');
}
function getDndTotalData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "DND"){
				count++;
			}
		}
	}

	var obj = {
		'dndCount':count 
	}

	buildTemplate('tpl_dnd',obj,'index_dnd');
}
function getMakeUpTotalData(){
	var count = 0;
	
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "MUR"){
				count++;
			}
		}
	}

	var obj = {
		'makeUpRoomCount':count 
	}

	buildTemplate('tpl_make_up',obj,'index_make_up');
}
function getbulterTotalData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "BUTLER"){
				count++;
			}
		}
	}

	var obj = {
		'bulterCount':count
	}

	// buildTemplate('tpl_bulters',obj,'index_bulters');
}
function getOccupiedCountData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "CHECKIN"){
				count = data[i]["status"];
			}
		}
	}

	var obj = {
		'occupiedCount' : count
	}

	buildTemplate('tpl_occupied',obj,'index_occupied');
}
function getVacantData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "CHECKOUT"){
				count = data[i]["status"];
			}
		}
	}
	var obj = {
		'vacantCount' : count
	}
	buildTemplate('tpl_vacant',obj,'index_vacant');
}
function getMakeUpData(){
	var obj = {
		'arr': getServiceData('MUR') ,
		'title': '請打掃'
	}
	return obj;	
}
function getDndData(){
	var obj = {
		'arr': getServiceData('DND') ,
		'title': '請勿打擾'
	}
	return obj;	
}
function setBindSOS(){
	//resolve sos
	$("#list_sos").unbind("click.resolve-sos");
    // $("#list_sos").on("click.resolve-sos", ".resolve-sos", function(event){ 
    //     if(confirm('Are you sure?')){
    //     	var index = $(this).attr('data-index');
    //     	$('.area-sos-' + index).remove();      

    //     	//沒有SOS訊息就把 區塊移除
    //     	if($('.resolve-sos').length <=0){
    //     		removeArea('#list_sos');
    //     	}  	
    //     }        
    // });
}
function setBindDND(){
	$("#service_content").unbind("click.show-dnd");
	$("#service_content").on("click.show-dnd", ".show-dnd", function(event){ 
		showModel('tpl_model_service',getDndData);
	});
}
function setBindMUR(){
	$("#service_content").unbind("click.show-mur");
	$("#service_content").on("click.show-mur", ".show-mur", function(event){ 
		showModel('tpl_model_service',getMakeUpData);
	});
}
function showModel(tpl_id,cb){
	var tpl_code = $('#' + tpl_id).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = cb();
	var html = tpl_bin(obj);
	$('#index_modal-content').html(html);
	$('#myModal').modal('show');
}
function getServiceData(keycode){
	var arr = [];
	if(!_.isNull(data)){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == keycode && data[i]["status"] == 1){
				var tempTime = new Date(data[i]["time"]);

				var triggerTime = 
					tempTime.getFullYear() + '/' + (tempTime.getMonth()+1) + '/' + tempTime.getDate() +
					' ' + tempTime.getHours() + ':' + tempTime.getMinutes() + ':' + tempTime.getSeconds();

				var dndObj = {
					'roomNo' : data[i]["roomNo"],
					'triggerTime' : triggerTime
				}
				arr.push(dndObj);
			}
		}
	}
	return arr;
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function removeArea(areaID){
	$(areaID).remove();
}

function playAlarm() {
	var playing = false;
  	var audio = $("#alarm")[0];
	var alarmroomarr = [];
	setInterval(function() {
		if (getUnique(alarmroomarr, roomarray)) {
			if (playing == false) {
				audio.play();
				playing = true;
				alarmroomarr = roomarray;
			}
		} else {
			alarmroomarr = roomarray;
		}
		if (alarmroomarr.length == 0) {
			$(".button-style").hide();
			audio.pause();
			playing = false;
		} else {
			$(".button-style").show();
		}
	}, 1000);
	
}
function getUnique(oldArray, newArray) {
	
	var flag = false;
	for (var i = 0; i < newArray.length; i++) {
		if ((jQuery.inArray(newArray[i], oldArray)) == -1) {
			oldArray.push(newArray[i]);
			flag = true;
		}
	}
	return flag;
}
$(window).on('beforeunload', function(){
    websocket.close(); 
});