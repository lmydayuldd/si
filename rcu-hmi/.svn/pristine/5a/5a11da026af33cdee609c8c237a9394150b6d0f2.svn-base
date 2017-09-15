var data = null;
var websocket = null;
var connectionCount = 0;

$(function () {
	initial();
	
  	websocket = new WebSocket($.websocketurl + "/socketindexinfo" );
  	
    websocket.onerror = function(evt) {
    	alert('系統忙碌中!!');
    };
	
	// setInterval(sendMsg, 1000);

	sendMsg();
	// setInterval(updateCount, 5000);

});

function initial(){
	//about room service
	getSOSData();
	getDndData();
	getMakeUpData();
	getbulterseData();

	//about hotel
	getRoomTotalCountData();
	getVacantSuitesData();
}

function updateCount(){
	getDndData();
	getMakeUpData();
	getbulterseData();
}

function sendMsg(){

	var obj = [
        // {"keycode":"SOS"},
        // {"keycode":"MUR"}
    ];

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
function waitForSocketConnection(socket, callback){
	connectionCount++;

    if(connectionCount>=1000){
    	var timeout_id = setTimeout(function() {}, 0);
		while (timeout_id--) {
		    clearTimeout(timeout_id); 
		}

		var interval_id = setInterval(function() {}, 0);
		while (interval_id--) {
		    clearInterval(interval_id); 
		}
    	// clearInterval(myVar);
    	alert('Websocket time out!!');
    	return false;
    }

	setTimeout(function () {
	    if (socket.readyState === 1) {
	        connectionCount = 0;
	        if(callback != null){
	            callback();
	        }
	        return;
	    } else {
	        console.log("wait for connection...");
	        waitForSocketConnection(socket, callback);
	    }
	}, 10); 
}
function getSOSData(){
	
	var tpl_code = $('#tpl_sos_list').html();
	var tpl_bin = Handlebars.compile(tpl_code);

	var arr = [];

	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "SOS"){
				arr.push(data[i]["roomNo"]);
			}
		}
	}

	var obj = {
		'arr' : arr
	}
	
	var html = tpl_bin(obj);
	$('#list_sos').html(html);

	//綁定事件
	setBindSOS();
}
function getDndData(){
	var tpl_code = $('#tpl_dnd').html();
	var tpl_bin = Handlebars.compile(tpl_code);
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
	
	var html = tpl_bin(obj);
	$('#index_dnd').html(html);
}
function getMakeUpData(){
	var tpl_code = $('#tpl_make_up').html();
	var tpl_bin = Handlebars.compile(tpl_code);
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

	var html = tpl_bin(obj);
	$('#index_make_up').html(html);
}
function getbulterseData(){
	var tpl_code = $('#tpl_bulters').html();
	var tpl_bin = Handlebars.compile(tpl_code);
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
	var html = tpl_bin(obj);
	$('#index_bulters').html(html);
}

function getRoomTotalCountData(){
	var tpl_code = $('#tpl_room_total_count').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = {
		'roomCount':Math.floor((Math.random() * 100) + 1) 
	}
	var html = tpl_bin(obj);
	$('#index_room_total_count').html(html);
}
function getVacantSuitesData(){
	var tpl_code = $('#tpl_vacant_suites').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = {
		'vacantSuitesCount':Math.floor((Math.random() * 100) + 1) 
	}
	var html = tpl_bin(obj);
	$('#index_vacant_suites').html(html);
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
function removeArea(areaID){
	$(areaID).remove();
}
$(window).on('beforeunload', function(){
    websocket.close(); 
});



