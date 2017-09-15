$(function () {
	initial();
});

var data = null;

function initial(){
	setRouter();
}

function showMainContent(){
	var tpl_code = $('#tpl_page_main').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var html = tpl_bin();
	
	//清空
	$('#main-content').empty();
	$('#main-content').html(html);

	//讀資料
	getData(showDataList);
}
function showDataList(){	
	var arr = dataProcess();
	
	var obj = {
		'arr' : arr
	}
	
	buildTemplate('tpl_room_list',obj,'table_list');
	
	$('#data-table').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": true,
		"info": false,
		"autoWidth": true,
		"displayLength": 10
		// "aoColumns": [
		// 	null,
		// 	{ "bSortable": false },
		// 	{ "bSortable": false }
		// ]
    });
   	setBindMain();
}
function setBindMain(){
	$("#table_list").unbind("click.modify");
    $("#table_list").on("click.modify", ".modify", function(event){ 
        
    });

    $("#table_list").unbind("change.room-module");
    $("#table_list").on("change.room-module", ".room-module", function(event){ 
		
    });
    $("#table_list").unbind("click.module-modify");
    $("#table_list").on("click.module-modify", ".module-modify", function(event){ 
		modifyModule($(this).attr('index'),$(this).val());
    });
}
function setRouter(){
	var routes = {		
		'':showMainContent()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showMainContent();
  	};
    router.init('');
}
function sortBy(str,array){
	if(str == "test"){
		array.sort(function(objA, objB){
	        var tempA = objA.id.toString().toLowerCase();
	        var tempB = objB.id.toString().toLowerCase();
	        if(tempA== tempB) return 0;
	        return tempA> tempB? 1: -1;
	    });
	}
}
function getData(cb){
	$.ajax({
		type: "POST",
		url: $.serverurl + '/roominfo',
		// data: data,
		success: function(obj){
			data = $.parseJSON(obj.data);
			if(_.isFunction(cb)){
				cb();
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
function dataProcess(){
	var arr = [];
	if(!_.isNull(data)){
		for(var i=0;i<data.length;i++){
			var sos = 'N';
			var card = 'N';
			var hvac = 'N';
			var rcu = 'N';
			var person = 'N';//暫無這個功能(房間內是否有人)

			// process about Service info
			if(!_.isNull(data[i]['service']) && !_.isUndefined(data[i]['service'])){
				for(var x=0;x<data[i]['service'].length;x++){
					var tempStr = data[i]['service'][x]['keycode'];
					if(tempStr=='SOS'){
						sos = 'Y';
						break;
					}
				}
			}

			if(!_.isNull(data[i]['card']) && !_.isUndefined(data[i]['card'])){
				if(data[i]['card']['status']){
					card = 'Y';
				}
			}

			if(!_.isNull(data[i]['hvac']) && !_.isUndefined(data[i]['hvac'])){
				//頁面 暫時只顯示有無空調 2016/12/21
				for(var x=0;x<data[i]['hvac'].length;x++){
					hvac = 'Y';
					break;
				}
			}
			
			if(!_.isNull(data[i]['rcu']) && !_.isUndefined(data[i]['rcu'])){
				rcu = 'Y';
			}

			var roomModuleObj = {
				'moduleName' : ''
			}
			var roomModuleArr = [];

			roomModuleArr.push(roomModuleObj);
			roomModuleArr.push(roomModuleObj);

			var roomObj = {
				'roomNo' : data[i]["roomNo"],
				'isCheckin' : data[i]["isCheckin"]==0?'N':'Y',
				'sosStatus' : sos,
				'cardStatus' : card,
				'hvacStatus' : hvac,
				'rcuStatus': rcu,
				'personStatus' : person,
				'roomModuleArr' : roomModuleArr
			}
			arr.push(roomObj)
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
function modifyModule(_index,_str){
	$('.checkbox-' + _index).attr('style','display:none');

	var obj = $('.select-module-' + _index);
	obj.attr('style','');
	obj.find('option').remove();
	obj.append($("<option></option>").attr("value", "值").text(_str));
}