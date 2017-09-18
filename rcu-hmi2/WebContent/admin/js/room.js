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

	var arr = [
		{
			roomNo: Math.floor((Math.random() * 999) + 100),
			status:"Y"
		}
	];


	var obj = {
		arr : arr
	};
	// obj = getRandomData();
	
	var html = tpl_bin(obj);
	$('#table_list').html(html);

	// $('#tpl_room_list');

	// $('#example2').DataTable({
 //      "paging": true,
 //      "lengthChange": false,
 //      "searching": false,
 //      "ordering": true,
 //      "info": true,
 //      "autoWidth": false
 //    });

   	setBindMain();
}

function setBindMain(){
	$("#table_list").unbind("click.modify");
    $("#table_list").on("click.modify", ".modify", function(event){ 
        

        $.ajax({
			type: "POST",
			url: 'http://127.0.0.1:8080/sidc-ra-server/roommoduel',
			// data: data,
			success: function(obj){
				console.log(obj);
				sortBy('test',obj.data);
				
			},
			failure: function(errMsg) {
				alert(errMsg);
			},
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		});
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