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
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        RCU Status  
      </h1>
      
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"></h3>

              <div class="box-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                  <!-- <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                  <div class="input-group-btn">
                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                  </div> -->
                </div>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding" id="table_list">
              <table class="table table-hover">
                <tbody>
                  <tr>
                    <th>Room No.</th>
                    <th>FLOOR</th>
                    <th>RCU Status</th>
                    <th>BEDSIDE CONTROLLER</th>
                    <th>THERMOSTAT</th>
                    <th>MEMORY</th>
                  </tr>
                  <tr>
                    <td>183</td>
                    <td>1</td>
                    <td><div class="fa fa-toggle-off" style="color:red"></div></td>
                    <td><div class="fa fa-toggle-off" style="color:red"></div></td>
                    <td><div class="fa fa-toggle-off" style="color:red"></div></td>
                    <td><div class="fa fa-toggle-off" style="color:red"></div></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
      </div>
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

<script type="text/template" id="tpl_room_list">
  <table class="table table-hover">
    <tbody>
      <tr>
       <!--  <th>Room No.</th>
        <th>FLOOR</th>
        <th>RCU Status</th>
        <th>DOOR STATUS</th>
        <th>SOS</th> -->
      </tr>
      <tr>
        <!-- <td>{{arr}}</td>
        <td>John Doe</td>
        <td>11-7-2014</td>
        <td><div class="label label-primary glyphicon glyphicon-ok-sign">&nbsp;</div></td>
        <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
      </tr> -->
    </tbody>
  </table>
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="/ra-server/admin/js/room.js"></script>
</html>
  