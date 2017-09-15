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
      <form class="form-horizontal">
        <div class="box box-info">
          <div class="box-header with-border">
          </div>
          <div class="box-body">
            <!-- Type設定 -->
            <!-- 
            <div class="form-group">
              <label class="col-sm-2 control-label">Type</label>
              <div class="col-sm-4">
                <div class="btn-group">
                  <button type="button" class="btn btn-success" id="model">Check in</button>
                  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="javascript:void(0);" class="type-model" data-code="checkin">Check in</a></li>
                    <li><a href="javascript:void(0);" class="type-model" data-code="checkinnon">Check in 沒人</a></li>
                    <li><a href="javascript:void(0);" class="type-model" data-code="checkout">Check out</a></li>
                    <li><a href="javascript:void(0);" class="type-model" data-code="welcome">Welcome</a></li>
                    <li class="divider"></li>
                    <li><a href="javascript:void(0);" class="type-model" data-code="psi">PSI 超標</a></li>
                    <li><a href="javascript:void(0);" class="type-model" data-code="humidity">濕度 超標</a></li>
                  </ul>
                </div>
              </div>
            </div> 
            -->
            <!-- 房型 -->
            <!-- 
            <div class="form-group">
              <label class="col-sm-2 control-label">Room Module</label>
              <div class="col-sm-4" id="room-module">
                <div class="btn-group">
                  <button type="button" class="btn btn-success" id="module">{{roomModuleArr.[0].name}}</button>
                  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    {{#each roomModuleArr}}
                      <li><a href="javascript:void(0);" class="room-module" data-code="checkin">{{name}}</a></li>
                    {{/each}}
                  </ul>
                </div>
              </div>
            </div>      
             -->
            <div class="form-group">
              <label class="col-sm-2 control-label">Room Module</label>
              <label class="col-sm-2 control-label"></label>
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
<!-- HVAC -->
<script type="text/template" id="tpl_hvac">
  {{#each hvacArr}}
    <div class="box box-info" id="havc-group-{{@index}}">   
      <div class="box-header with-border">
        <h3 class="box-title">{{device}}</h3> 
        <a href="#hvac-body-{{@index}}" data-toggle="collapse">
          <i class="fa fa-plus hvac-open"></i>
        </a>
        <a href="javascript:void(0);" class="remove-body" data-id="{{@index}}"><i class="fa fa-times"></i></a>

      </div>    
      <!-- form start -->            
      <div class="box-body collapse" id="hvac-body-{{@index}}">
        <div class="form-group">  
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
        </div>

        <div class="form-group">
          <label class="col-sm-2 text-center">Power</label>
          <div class="col-sm-10">
            <input class="form-control input-aircon" type="checkbox" checked>
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 text-center">Mode</label>
          <div class="col-sm-10">
            <div data-toggle="buttons">
              <label class="btn btn-default active">
                <input name="mode" value="2" type="radio">冷氣
              </label>
              <label class="btn btn-default" >
                <input name="mode" value="3" type="radio">送風
              </label>
            </div>
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 text-center">Wind</label>
          <div class="col-sm-10">
            <div data-toggle="buttons">
              <label class="btn btn-default active">
                <input name="year" value="2" type="radio">A
              </label>
              <label class="btn btn-default" >
                <input name="year" value="3" type="radio">高
              </label>
              <label class="btn btn-default">
                <input name="year" value="2" type="radio">中
              </label>
              <label class="btn btn-default">
                <input name="year" value="2" type="radio">低
              </label>
            </div>
          </div>
        </div>

        <div class="form-group">  
          <label class="col-sm-2 text-center">Temperature</label>
          <div class="col-sm-5">
              <input id="temperature" data-slider-id='temperature' type="text" data-slider-min="16" data-slider-max="35" data-slider-step="1" data-slider-value="22" class="slider"/>
              <span>
                temperature:<span id="display-temperature"> </span>&#8451;
              </span>
          </div>
        </div>
        <div class="form-group">  
          <label class="col-sm-2 text-center">TIME</label>
          <div class="col-sm-5">
              <input id="time" data-slider-id='time' type="text" data-slider-min="0" data-slider-max="12" data-slider-step="0.5" data-slider-value="1" class="slider"/>
              <span>
                Time:<span id="display-time"> </span>
              </span>
          </div>
        </div>
        <div class="form-group">
        </div>    
        <!-- <div class="box-footer">
          <button type="submit" class="btn btn-default">Cancel</button>
          <button type="submit" class="btn btn-info pull-right">Submit</button>
        </div>    -->   
      </div>
      <!-- /.box-body -->   
    </div>
  {{/each}}
</script>
<!-- BULB -->
<script type="text/template" id="tpl_bulb">
  <div class="box box-info"> 
    <div class="box-header with-border">
      <h3 class="box-title">Bulb</h3> 
      <a href="#bulb-body-0" data-toggle="collapse">
        <i class="fa fa-plus bulb-open"></i>
      </a>
    </div>    
    <!-- form start -->            
    <div class="box-body collapse" id="bulb-body-0">
      {{#each bulbArr}}
        <div class="form-group">
          <label class="col-sm-2 text-center">{{device}}</label>
          <div class="col-sm-10">
            <input class="form-control input-aircon" type="checkbox">
            <button type="button" class="btn btn-danger"><i class="fa fa-remove"></i></button>
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
            <input class="form-control input-aircon" type="checkbox" checked>
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
  <table class="table table-bordered table-hover">
    <thead>
      <tr>
        <th>Name</th>
        <th>Status</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      {{#each arr}}
        <tr>
          <td>{{name}}</td>
          <td>
            <span class="label label-danger">off</span>
          </td>
          <td>
            <a href="#add/{{name}}">
              <i class="fa fa-fw fa-gear"></i>
            </a>
            <a href="#">
              <i class="fa fa-fw fa-trash-o delete"></i>
            </a>
          </td>
        </tr> 
      {{/each}}
    </tbody>
  </table>
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/js/mode_setting.js"></script>
</html>
  