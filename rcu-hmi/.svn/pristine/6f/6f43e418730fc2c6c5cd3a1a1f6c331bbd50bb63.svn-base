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
        Mode Device Setting
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
          <!-- <a class="btn btn-success" href="#add"><i class="fa fa-plus"></i></a> -->
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
            <!-- mode list -->
            <!-- <div class="form-group">
              <label class="col-sm-1 control-label">Mode</label>
              <div class="col-sm-4" >
                <div class="btn-group">
                  <button type="button" class="btn btn-success" id="mode-text" data-id="{{modeArr.[0].id}}">
                    {{modeArr.[0].keyname}}
                  </button>
                  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    {{#each modeArr}}
                      <li><a href="javascript:void(0);" class="mode" data-code="{{id}}">{{keyname}}</a></li>
                    {{/each}}
                  </ul>
                </div>
              </div>
            </div>     -->

            <!--  -->
            <div class="form-group">
              <div class="col-sm-5" id="multiple-select">
                
              </div>
            </div> 
            <div class="box-footer">
              <button type="button" class="btn btn-info pull-left" id="submit">Submit</button>
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
  <select multiple="multiple" size="5" id="multiple_select" data-id="{{dataId}}">
    {{#each selectArr}}
      <option value="{{rcuDeviceId}}" {{#ifCond selected 'Y'}}selected="selected"{{/ifCond}}>
        ({{gouprName}})&nbsp;{{device}}
      </option>
    {{/each}}
  </select>
</script>

<!-- 首頁list -->
<script type="text/template" id="tpl_mode_list">
  <table class="table table-bordered table-hover">
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
            <a href="#add/{{id}}">
              <i class="fa fa-fw fa-gear"></i>
            </a>
          </td>
        </tr> 
      {{/each}}
    </tbody>
  </table>
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/js/mode_device_setting.js"></script>
</html>
  