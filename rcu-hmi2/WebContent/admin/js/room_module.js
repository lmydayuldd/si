$(function () {
	initial();
});

function initial(){
	setRouter();
}

function showMainContent(){	
	var tpl_code = $('#tpl_page_main').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	
	var html = tpl_bin();
	$('#main-content').empty();
	$('#main-content').html(html);
	showDataList();
}

function showDataList(){	
	var tpl_code = $('#tpl_room_list').html();
	var tpl_bin = Handlebars.compile(tpl_code);

	var arr = sendPost('http://127.0.0.1:8080/sidc-ra-server/roommoduel');
	arr = sortBy('test',arr);

	var obj = {
		arr : arr
	};
	// obj = getRandomData();
	
	var html = tpl_bin(obj);
	$('#table_list').html(html);
	
   	setBindMain();
}

function setBindMain(){
	$("#table_list").unbind("click.modify");
    $("#table_list").on("click.modify", ".modify", function(event){        

        
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
	array.sort(function(objA, objB){
		var tempA = null;
		var tempB = null;

		if(str == "test"){
	       	tempA = objA.id.toString().toLowerCase();
	       	tempB = objB.id.toString().toLowerCase();
        }

        if(tempA== tempB) return 0;
        return tempA> tempB? 1: -1;
    });
	
	return array;
}

function sendPost(url){
	var dataset = null;
	$.ajax({
		type: "POST",
		async: false,
		url: url,
		// data: data,
		success: function(response){	
			dataset = response.data;	
		},
		failure: function(errMsg) {
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return dataset;
}