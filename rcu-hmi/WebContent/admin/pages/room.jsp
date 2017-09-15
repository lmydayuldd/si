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
          Room  
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
</body>

<!-- main page -->
<script type="text/template" id="tpl_page_main">
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
        <div class="box-body" id="table_list">
          
        </div>
        <!-- /.box-body -->
      </div>
      <!-- /.box -->
    </div>
  </div>
</script>

<script type="text/template" id="tpl_room_list">
  <table id="data-table" class="table table-bordered table-hover">
    <thead>
      <tr>
        <th class="text-center">Room No</th>
        <th class="text-center">Check in Status</th>
        <!-- <th class="text-center">Room Module</th> -->
        <th class="text-center">Other Status</th>
        <!-- <th></th> -->
      </tr>
    </thead>
    <tbody>
      {{#each arr}}
        <tr>
          <td class="text-center">{{roomNo}}</td>
          {{#ifCond isCheckin 'Y'}}
            <td class="text-center bg-green">
                <i class="glyphicon glyphicon-ok"></i>
            </td>
          {{/ifCond}}
          {{#ifCond isCheckin 'N'}}
            <td class="text-center bg-blue">
                <i class="glyphicon glyphicon-remove"></i>
            </td>
          {{/ifCond}}
          <!-- {{#if roomModuleArr}}
            <td class="text-center">
                <select class="room-module select-module-{{@index}}" style="display: none">
                 
                </select>

                <div class="checkbox checkbox-{{@index}}">
                  <label>
                    {{roomModuleArr.[0].moduleName}}&nbsp;
                    <input type="checkbox" class="module-modify" index="{{@index}}" value="{{roomModuleArr.[0].moduleName}}">Modify
                  </label>
                </div>
            </td>
          {{/if}} -->
          <td>
            {{#ifCond sosStatus 'Y'}}
               <i class="fa fa-exclamation-circle"></i>
            {{/ifCond}}
            {{#ifCond cardStatus 'Y'}}
               <i class="fa fa-fw fa-key"></i>
            {{/ifCond}}
            {{#ifCond hvacStatus 'Y'}}
                <i class="glyphicon glyphicon-asterisk"></i>
            {{/ifCond}}
            {{#ifCond rcuStatus 'Y'}}
               <i class="ion-cube"></i>
            {{/ifCond}}
           
            <!-- <span class="label label-danger"><i class="fa fa-fw fa-key"></i></span> -->
          </td>
         <!--  <td>
            <a href="javascript:void(0);">
              <i class="fa fa-fw fa-gear modify"></i>
            </a>
          </td> -->
        </tr> 
      {{/each}}
    </tbody>
  </table>
</script>

<%@include file="/admin/pages/include/initial_script.jsp" %> 
<script src="<%=request.getContextPath() %>/admin/js/room.js"></script>
</html>
