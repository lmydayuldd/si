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
        中央空調控制
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
    </div>
    <!-- /.box-body -->   
  </div>
</script>


<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/plugins/jquery-validate/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath() %>/admin/js/hvac_center_control.js"></script>
</html>
  