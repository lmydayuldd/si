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
          <!-- form start -->            
          <div class="box-body">
            <div class="form-group">
              <label class="col-sm-1 control-label">Model name</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" placeholder="Model name" value="{{name}}">
              </div>
            </div>
           
            <div class="form-group">
              <label class="col-sm-1 control-label">clock</label>
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
              <label class="col-sm-1 control-label">Aircon</label>
              <div class="col-sm-2">
                <input class="form-control input-aircon" type="checkbox" checked>
              </div>             
              <div class="col-sm-2">
                <select class="form-control">
                  <option>18 &#8451;</option>
                  <option>19 &#8451;</option>
                  <option>20 &#8451;</option>
                  <option>21 &#8451;</option>
                  <option>22 &#8451;</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1 control-label">Light</label>
              <div class="col-sm-2">
                <input class="form-control input-light" type="checkbox" checked>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1 control-label">Curtain</label>
              <div class="col-sm-2">
                <input class="form-control input-curtain" type="checkbox" checked>
              </div>
            </div>  
            <div class="form-group">
            </div>          
          </div>

          <!-- /.box-body -->        

        </div>
        <div class="box box-info">
          <div class="box-header with-border">
            <h3 class="box-title">night</h3> 
          </div>
          <div class="box-body">
            
            <div class="form-group">
              <label class="col-sm-1 control-label">clock</label>
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
              <label class="col-sm-1 control-label">Aircon</label>
              <div class="col-sm-2">
                <input class="form-control input-aircon" type="checkbox" checked>
              </div>              
              <div class="col-sm-2">
                <select class="form-control">
                  <option>18 &#8451;</option>
                  <option>19 &#8451;</option>
                  <option>20 &#8451;</option>
                  <option>21 &#8451;</option>
                  <option>22 &#8451;</option>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1 control-label">Light</label>
              <div class="col-sm-2">
                <input class="form-control input-light" type="checkbox" checked>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-1 control-label">Curtain</label>
              <div class="col-sm-2">
                <input class="form-control input-curtain" type="checkbox" checked>
              </div>
            </div>                
          </div>
          <div class="box-footer">
            <!-- <button type="submit" class="btn btn-default">Cancel</button> -->
            <button type="submit" class="btn btn-info pull-right">Submit</button>
          </div>
        </div>
      </form>
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
  