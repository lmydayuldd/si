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
	setBindMain();
}

function showDataList(){	
	var tpl_code = $('#tpl_mode_list').html();
	var tpl_bin = Handlebars.compile(tpl_code);

	var arr = [
		{
			name: Math.floor((Math.random() * 999) + 100)
		}
	];

	var obj = {
		arr : arr
	};
	
	var html = tpl_bin(obj);
	$('#table_list').html(html);
}

function showModify(_id){	
	var tpl_code = $('#tpl_page_add').html();
	var tpl_bin = Handlebars.compile(tpl_code);

	var obj = {
		name : _id
	};	

	var html = tpl_bin(obj);
	$('#main-content').empty();
	$('#main-content').html(html);

	$('.input-curtain').bootstrapSwitch();
	$('.input-light').bootstrapSwitch();
	$('.input-aircon').bootstrapSwitch();
	$('.clockpicker').clockpicker();
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
