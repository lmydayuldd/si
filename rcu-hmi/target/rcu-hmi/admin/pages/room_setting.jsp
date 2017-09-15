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
                    <th>NAME</th>
                    <th>DEVICE COUNT</th>
                    <th></th>
                   
                  </tr>
                  <tr>
                    <td>Room A</td>
                    <td>2</td>
                    <td>
                      <a href="javascript: void(0)" class="fa fa-list-ul" style="color:red" onclick="$('#myModal').modal('show');">
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td>Room B</td>
                    <td>10</td>
                    <td>
                      <a href="javascript: void(0)" class="fa fa-list-ul" style="color:red" onclick="$('#myModal').modal('show');">
                      </a>
                    </td>
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

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" id="myModal" >
  <div class="modal-dialog modal-lg" role="document" style="left: 5%;">
    <div class="modal-content">
      <div class="box">
        <div class="box-header">
          <h3 class="box-title"></h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body no-padding">
          <table class="table table-striped">
            <tbody><tr>
              <th style="width: 10px">#</th>
              <th>DEVICE Name</th>
              <th>Count</th>
              
            </tr>
            <tr>
              <td>1.</td>
              <td>DEVICE A</td>
              
              <td><span class="badge bg-red">5</span></td>
            </tr>
            <tr>
              <td>2.</td>
              <td>DEVICE B</td>
              
              <td><span class="badge bg-yellow">7</span></td>
            </tr>
            <tr>
              <td>3.</td>
              <td>DEVICE C</td>
             
              <td><span class="badge bg-light-blue">3</span></td>
            </tr>
            <tr>
              <td>4.</td>
              <td>DEVICE D</td>
              <td><span class="badge bg-green">9</span></td>
            </tr>
          </tbody></table>
        </div>
        <!-- /.box-body -->
      </div>

    </div>
  </div>
</div>
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
<script src="/ra-server/admin/js/room_setting.js"></script>
</html>
  