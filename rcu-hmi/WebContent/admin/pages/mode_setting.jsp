<!DOCTYPE html>
<html>
<head>
  <%@include file="/admin/pages/include/head.jsp" %> 
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <header class="main-header">
    <%@include file="/admin/pages/include/header.jsp" %> 
  </header>
  <!-- Left side column. contains the logo and sidebar -->  
  <aside class="main-sidebar">
    <%@include file="/admin/pages/include/left_menu.jsp" %> 
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        Mode setting
      </h1>
      
    </section>
    <!-- Main content -->
    <section class="content" id="main-content">
      
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <%@include file="/admin/pages/include/footer.jsp" %> 
  </footer>
</div>
<!-- ./wrapper -->

</body>

<!-- main page -->
<script type="text/template" id="tpl_page_main">
  <div class="row">
    <div class="col-xs-12">
      <div class="box">
        <div class="box-header">
          <!-- <h3 class="box-title"></h3> -->
          <a class="btn btn-success" href="#add"><i class="fa fa-plus"></i></a>
          <div class="box-tools">
            <div class="input-group input-group-sm" style="width: 150px;">
              
            </div>
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body" id="table_list">
          
        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->
    </div>
  </div>
</script>

<!-- 新增頁面 -->
<script type="text/template" id="tpl_page_add">
  <div class="row">
    <div class="col-md-12">
      <form class="form-horizontal" id="myForm">
        <div class="box box-info">
          <div class="box-header with-border">
            <input type="hidden" id="mode_id" value="{{modeId}}" />
            <strong>{{modeName}}</strong>
          </div>
          <div class="box-body">
            <!-- Mode -->
            <div class="form-group" id="mode_group">

              <!--
              <label class="col-sm-2 control-label">Mode</label>
              <div class="col-sm-4">
                <div class="btn-group">
                  <button type="button" class="btn btn-success" id="mode" data-code="{{modeArr.[0].id}}">{{modeArr.[0].keyname}}</button>
                  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  
                  <ul class="dropdown-menu" role="menu">
                    {{#each modeArr}}
                      <li><a href="javascript:void(0);" class="type-mode" data-code="{{id}}">{{keyname}}</a></li>
                    {{/each}}
                  </ul>
                </div>
              </div>
              -->
            </div> 
            <!-- 房型 -->
            <div class="form-group">
              <label class="col-sm-2 control-label">Room Module</label>
              <div class="col-sm-4" id="room-module">
                <div class="btn-group">
                  <button type="button" class="btn btn-success" id="module" data-code="{{roomModuleArr.[0].id}}">{{roomModuleArr.[0].name}}</button>
                  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    {{#each roomModuleArr}}
                      <li><a href="javascript:void(0);" class="room-module" data-code="{{id}}">{{name}}</a></li>
                    {{/each}}
                  </ul>
                </div>
              </div>
            </div>  
            
          </div>
          <div class="box-footer">
          </div>
        </div>
        <div id="body-model">

        </div>
        <!-- submit -->
        <div class="box-footer">
          <button type="button" class="btn btn-info pull-left" id="submit">Submit</button>
        </div> 
      </form>
    </div>
  </div>
</script>
<!-- Mode -->
<script type="text/template" id="tpl_mode_select">
  <div class="form-group">
    <label class="col-sm-2 control-label">Mode</label>
    <div class="col-sm-4">
      <div class="btn-group">
        <button type="button" class="btn btn-success" id="mode" data-code="{{modeArr.[0].id}}">{{modeArr.[0].keyname}}</button>
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
          <span class="caret"></span>
          <span class="sr-only">Toggle Dropdown</span>
        </button>
        
        <ul class="dropdown-menu" role="menu">
          {{#each modeArr}}
            <li><a href="javascript:void(0);" class="type-mode" data-code="{{id}}">{{keyname}}</a></li>
          {{/each}}
        </ul>
      </div>
    </div>
  </div> 
</script>
<script type="text/template" id="tpl_mode_add">
  <label class="col-sm-2 control-label">Mode Name</label>
  <div class="col-sm-3">
    <input type="text" class="form-control" id="mode_name" placeholder="Mode Name" required/>
  </div>
</script>
<!-- HVAC -->
<script type="text/template" id="tpl_hvac">
  {{#each hvacArr}}  
    <div class="box box-info">   
      <div class="box-header with-border">
        <h3 class="box-title">Hvac</h3> 
      </div>    
      <!-- form start -->            
      <div class="box-body" id="hvac-body">
        <!-- 時間 Start -->
       <!--  <div class="form-group">  
          <label class="col-sm-2 text-center">Range</label>
          <div class="col-sm-2">
            <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                <input type="text" class="form-control" value="09:30">
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-time"></span>
                </span>
            </div>
          </div>
          <div class="col-sm-2">
            <div class="input-group clockpicker" data-placement="left" data-align="top" data-autoclose="true">
                <input type="text" class="form-control" value="19:30">
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-time"></span>
                </span>
            </div>
          </div>
        </div> -->
        <!-- 時間 END -->
        <div class="form-group">
          <label class="col-sm-2 text-center">Power</label>
          <div class="col-sm-10">
            <input class="form-control btn-switch" id="hvac_power" type="checkbox" keycode="{{keycode}}" {{#ifCond power 1}}checked="checked" {{/ifCond}} >
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 text-center">Mode</label>
          <div class="col-sm-10">
            <div data-toggle="buttons" class="function">
              <label class="btn btn-default {{#ifCond function '1'}}active{{/ifCond}}">
                <input name="function" value="1" type="radio" >冷氣
              </label>
              <label class="btn btn-default {{#ifCond function '2'}}active{{/ifCond}}" >
                <input name="function" value="2" type="radio" >暖氣
              </label>
              <label class="btn btn-default {{#ifCond function '3'}}active{{/ifCond}}" >
                <input name="function" value="3" type="radio" >送風
              </label>
            </div>
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 text-center">Wind</label>
          <div class="col-sm-10">
            <div data-toggle="buttons" class="speed">
              <label class="btn btn-default {{#ifCond speed 0}}active{{/ifCond}}">
                <input name="speed" value="0" type="radio" >Auto
              </label>
              <label class="btn btn-default {{#ifCond speed 1}}active{{/ifCond}}" >
                <input name="speed" value="1" type="radio">高
              </label>
              <label class="btn btn-default {{#ifCond speed 2}}active{{/ifCond}}">
                <input name="speed" value="2" type="radio">中
              </label>
              <label class="btn btn-default {{#ifCond speed 3}}active{{/ifCond}}">
                <input name="speed" value="3" type="radio">低
              </label>
            </div>
          </div>
        </div>

        <div class="form-group">  
          <label class="col-sm-2 text-center">Temperature</label>
          <div class="col-sm-5">
              <input id="temperature" data-slider-id='temperature' type="text" data-slider-min="16" data-slider-max="30" data-slider-step="1" data-slider-value="{{temperature}}" class="slider"/>
              <span>
                temperature:<span id="display-temperature">{{temperature}}</span>&#8451;
              </span>
          </div>
        </div>
        <!-- 延遲時間 Start -->
       <!--  <div class="form-group">  
          <label class="col-sm-2 text-center">TIME</label>
          <div class="col-sm-5">
              <input id="time" data-slider-id='time' type="text" data-slider-min="0" data-slider-max="60" data-slider-step="1" data-slider-value="{{timer}}" class="slider"/>
              <span>
                Time:<span id="display-time">{{timer}}</span>
              </span>
          </div>
        </div> -->
        <!-- 延遲時間 End -->
        <div class="form-group">
        </div>   
      </div>
      <!-- /.box-body -->   
    </div>
  {{/each}}
</script>
<!-- BULB -->
<script type="text/template" id="tpl_bulb">
  <div class="box box-info" id="bulb-body"> 
    <div class="box-header with-border">
      <h3 class="box-title">Bulb</h3> 
    </div>    
    <!-- form start -->            
    <div class="box-body">
      {{#each bulbArr}}
        <div class="form-group">
          <label class="col-sm-2 text-center">{{bulbName}}</label>
          <div class="col-sm-10">
            <input class="form-control btn-switch {{catalogue}}" type="checkbox" keycode="{{keycode}}" {{#ifCond status '1'}}checked="checked" {{/ifCond}}>
          </div>
        </div>
      {{/each}}
      <div class="form-group">
      </div>    
      <!-- <div class="box-footer">
        <button type="submit" class="btn btn-default">Cancel</button>
        <button type="submit" class="btn btn-info pull-right">Submit</button>
      </div>    -->   
    </div>
    <!-- /.box-body -->   
  </div>
</script>
<!-- Curtain -->
<script type="text/template" id="tpl_curtain">
  <div class="box box-info"> 
    <div class="box-header with-border">
      <h3 class="box-title">Curtain</h3> 
    </div>    
    <!-- form start -->            
    <div class="box-body">
      {{#each curtainArr}}
        <div class="form-group">
          <label class="col-sm-2 text-center">{{curtainName}}</label>
          <div class="col-sm-10">
            <input class="form-control btn-switch {{catalogue}}" type="checkbox" keycode="{{keycode}}" {{#ifCond status '1'}}checked="checked" {{/ifCond}}>
          </div>
        </div>
      {{/each}}
      <div class="form-group">
      </div>    
    </div>
    <!-- /.box-body -->   
  </div>
</script>
<!-- Room Module -->
<script type="text/template" id="tpl_roommodule">
  <div class="form-group">
    <label class="col-sm-2 control-label">Room Module</label>
    <div class="col-sm-2">
      <div class="btn-group">
        <button type="button" class="btn btn-success" id="module">{{roomModuleArr.[0].moduleName}}</button>
        <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
          <span class="caret"></span>
          <span class="sr-only">Toggle Dropdown</span>
        </button>
        <ul class="dropdown-menu" role="menu">
          {{#each roomModuleArr}}
            <li><a href="javascript:void(0);" class="room-module" data-code="checkin">{{moduleName}}</a></li>
          {{/each}}
        </ul>
      </div>
    </div>
  </div> 
</script>
<!-- 首頁list -->
<script type="text/template" id="tpl_mode_list">
  <table id="data-table" class="table table-bordered table-hover">
    <thead>
      <tr>
        <th>Name</th>
        
        <th></th>
      </tr>
    </thead>
    <tbody>
      {{#each arr}}
        <tr>
          <td>{{keyname}}</td>
          
          <td>
            <a href="#modify/{{id}}">
              <i class="fa fa-fw fa-gear" data-toggle="tooltip" title="mode setting"></i>
            </a>
            <a href="#modify-device/{{id}}">
              <i class="fa fa-fw fa-gears" data-toggle="tooltip" title="device setting"></i>
            </a>
            {{#ifCond status '0'}}
              <a href="javascript:void(0)" class="delete" value="{{id}}">
                <i class="fa fa-fw fa-trash-o"></i>
              </a>
            {{/ifCond}}
            
          </td>
        </tr> 
      {{/each}}
    </tbody>
  </table>
</script>
<!-- 新增device頁面 -->
<script type="text/template" id="tpl_device_add">
  <div class="row">
    <div class="col-md-12">
      <form class="form-horizontal" id="myForm">
        <div class="box box-info">
          <div class="box-header with-border">
            <input type="hidden" id="operation-type" value="{{operationType}}"/>
            <strong>{{modeName}}</strong>
          </div>
          <div class="box-body">
            <div class="form-group" id="div-mode-name">
              <label class="col-sm-2 control-label" style="text-align:center">Mode Name</label>
              <div class="col-sm-3">
                <input type="text" class="form-control" id="mode_name" placeholder="Mode Name" required/>
              </div>
            </div>
            <!--  -->
            <div class="form-group">
              <div class="col-sm-5" id="multiple-select">
                
              </div>
            </div> 
            <div class="box-footer">
              <button type="button" class="btn btn-info pull-left" id="submit-add">Submit</button>
            </div> 
          </div>
          <div class="box-footer">

          </div>
        </div>
        <div id="body-model">

        </div>
      </form>
    </div>
  </div>
</script>
<!-- 左右拉表單 -->
<script type="text/template" id="tpl_multiple_select">
  <select multiple="multiple" size="5" id="multiple_select" data-id="{{modeId}}">
    {{#each selectArr}}
      <option value="{{rcuDeviceId}}" {{#ifCond selected 'Y'}}selected="selected"{{/ifCond}}>
        ({{gouprName}})&nbsp;{{device}}
      </option>
    {{/each}}
  </select>
</script>
<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath() %>/admin/js/mode_setting.js"></script>
</html>
  