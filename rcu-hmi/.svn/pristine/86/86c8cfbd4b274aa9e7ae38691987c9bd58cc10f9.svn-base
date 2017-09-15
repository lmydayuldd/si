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
        空調控制
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

<!-- 新增頁面 -->
<script type="text/template" id="tpl_page_add">
  <div class="row">
    <div class="col-md-12">
      <form class="form-horizontal" id="myForm">
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
<!-- HVAC -->
<script type="text/template" id="tpl_hvac">
  <div class="box box-info">   
    <!-- form start -->            
    <div class="box-body" id="hvac-body">
      <div class="form-group">
        <label class="col-sm-2 text-center">模式</label>
        <div class="col-sm-10">
          <div data-toggle="buttons" class="function">
            <label class="btn btn-default active">
              <input name="function" value="1" type="radio" >製冷
            </label>
            <label class="btn btn-default" >
              <input name="function" value="2" type="radio" >製熱
            </label>
          </div>
        </div>
      </div>
      <div class="form-group">  
        <label class="col-sm-2 text-center">溫度</label>
        <div class="col-sm-5">
            <input id="temperature" data-slider-id='temperature' type="text" data-slider-min="16" data-slider-max="30" data-slider-step="1" data-slider-value="25" class="slider"/>
            <span>
              <span id="display-temperature">25</span>&#8451;
            </span>
        </div>
      </div>
      <div class="form-group">  
        <label class="col-sm-2 text-center">自動停止時間</label>
        <div class="col-sm-5">
            <input id="delay-close" data-slider-id='delay-close' type="text" data-slider-min="0" data-slider-max="4" data-slider-step="1" data-slider-value="1" class="slider"/>
            <span>
              <span id="display-delay-close">1</span>hour
            </span>
        </div>
      </div>
      <div class="form-group"  id="type-group">
        <label class="col-sm-2 text-center">類型</label>
        <div class="col-sm-10">
          <div data-toggle="buttons" class="room-type">
            <!-- <label class="btn btn-default active">
              <input name="room-type" value="all" type="radio" >所有房間
            </label> -->
            <label class="btn btn-default active" >
              <input name="room-type" value="check-in" type="radio" >Check in
            </label>
            <label class="btn btn-default" >
              <input name="room-type" value="check-out" type="radio" >Check out
            </label>
            <label class="btn btn-default" >
              <input name="room-type" value="floor" type="radio" >樓層
            </label>
            <label class="btn btn-default" >
              <input name="room-type" value="room" type="radio" >個別房號
            </label>
          </div>
        </div>
      </div>
      <div class="form-group" id="enter-group">
        <label class="col-sm-2 text-center"></label>
        <div class="col-sm-1">
          <div class="form-group has-success">
            <input type="text" class="form-control" id="item-input" placeholder="Enter ..." maxlength="6">
          </div>
        </div>
        <div class="col-sm-1">
          <button type="button" class="btn btn-success" id="add-button">
            <span class="fa fa-plus"></span>
          </button>
        </div>
      </div>
      <div>
      <div class="form-group" id="room-tabs">
        <label class="col-sm-2 text-center"></label>
        <div class="col-sm-9" id="tabs">
          
        </div>
      </div>
      <div>

      </div>
      <div id="item-group">

      </div>
    </div>
    <!-- /.box-body -->   
  </div>
</script>
<script type="text/template" id="tpl_item">
  <div class="form-group" id="room-item">
    <label class="col-sm-2 text-center">{{name}}</label>
    <div class="col-sm-5">
      {{#each arr}}
         <button class="btn btn-primary item-num" type="button" value="{{number}}"> {{number}} <span class="badge fa fa-trash-o"> </span> </button>
      {{/each}}
    </div>
  </div>
</script>
<script type="text/template" id="tpl_tabs">
  <div class="nav-tabs-custom">
    <ul class="nav nav-tabs">
      {{#each floorArr}}
        
          <li {{#ifCond @index 0}} class="active" {{/ifCond}} >
            <a href="#tab_{{@index}}" data-toggle="tab">{{floorNo}}</a>
          </li>
        
      {{/each}}
    <!--   <li class="active"><a href="#tab_1" data-toggle="tab">Tab 1</a></li>
      <li><a href="#tab_2" data-toggle="tab">Tab 2</a></li>
      <li><a href="#tab_3" data-toggle="tab">Tab 3</a></li> -->
    </ul>
    <div class="tab-content">
      {{#each floorArr}}
        <div class="tab-pane {{#ifCond @index 0}}active{{/ifCond}}" id="tab_{{@index}}">
          {{#each roomArr}}
            <label>
              <input type="checkbox" name="roomNo" value="{{roomNo}}"> {{roomNo}}</input>
            </label>
          {{/each}}
        </div>
      {{/each}}
  </div>
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath() %>/admin/js/hvac_control.js"></script>
</html>
  