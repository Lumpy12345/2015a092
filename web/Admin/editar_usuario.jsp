<%-- 
    Document   : editar_usuario
    Created on : 21-may-2016, 14:48:29
    Author     : Lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SSGPE</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Recursos/admin/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Recursos/admin/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Recursos/admin/dist/css/skins/_all-skins.min.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Recursos/admin/plugins/morris/morris.css">
  

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="index.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>SSGPE</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>SSGPE</b>/Admin</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              
              <span class="hidden-xs">Administrador</span>
            </a>
            <ul class="dropdown-menu">
              
              <!-- Menu Footer-->
              <li class="user-footer">
                 <span class="username" onclick="Cerrar_Sesion()">Cerrar Sesion</span>
              </li>
            </ul>
          </li>
          
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">Herramientas</li>
        
<!--       <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Consulta</span>
            <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="../Admin/general.html"><i class="fa fa-circle-o"></i>Datos Electorales</a></li>
           </ul>
        </li>-->
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Usuarios</span>
            <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="../Admin/usuarios.html"><i class="fa fa-circle-o"></i> Administrar Usuario</a></li>
              <li><a href="../Admin/crear_usuario.html"><i class="fa fa-circle-o"></i> Crear Usuario</a></li>
          </ul>
        </li>
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        
        <small> </small>
      </h1>
      <ol class="breadcrumb">
        <li>Administrador</li>
        <li class="active">Herramientas</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     
      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
       
        <div class="col-md-12">

          <div class="box box-warning">
            <div class="box-header">
              <h3 class="box-title">Editar Usuario</h3>
               
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
             
                <div class="form">
                                <form  onsubmit="return actualizarUsuario('${user.VMstrCorreo}')">
                                  
                                      <div class="form-group ">
                                          <label class="control-label col-lg-2">Nombre <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="Nombre" name="Nombre" type="text" value="${user.VMstrNombre}"/>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label class="control-label col-lg-2">Apellido Paterno <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="APaterno" name="APaterno" type="text" value="${user.VMstrAPaterno}"/>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label class="control-label col-lg-2">Apellido Materno <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class=" form-control" id="AMaterno" name="AMaterno" type="text" value="${user.VMstrAMaterno}"/>
                                          </div>
                                      </div>       
                                     <div class="form-group ">
                                          <label class="control-label col-lg-2">Contraseña <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="Password" name="Password" type="password" value="${user.VMstrPassword}"/>
                                          </div>
                                      </div>
                                        <div class="form-group ">
                                          <label class="control-label col-lg-2">Correo <span class="required">*</span></label>
                                          <div class="col-lg-10">
                                              <input class="form-control " id="Correo" name="Correo" type="email" value="${user.VMstrCorreo}"/>
                                          </div>
                                      </div>
                                      <div class="form-group ">
                                          <label class="control-label col-lg-2">Tipo de Perfil <span class="required">*</span></label>
                                          <div class="col-lg-10">                                             
                                              <select class="select-style" name="Perfil" id="Perfil" value="${user.VMstrPerfil}">
                                                <option>Cliente</option>
                                                <option>Admin</option>                                                
                                              </select>
                                          </div>
                                      </div>
                                      <div class="form-group">
                                          <div class="col-lg-offset-2 col-lg-10">
                                              <input class="btn btn-primary" type="submit"/>                                         
                                              <button class="btn btn-default" type="button"><a  href="/TT/Admin/usuarios.html">Cancelar</a></button>
                                          </div>
                                      </div>
                                  </form>
                          
                              </div>
              
              
              
               </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
       
          
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
nterval(listaUsuarios, 1000);
</script>
  
</div>
<!-- ./wrapper -->

<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="${pageContext.request.contextPath}/Recursos/admin/bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/Recursos/admin/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/Recursos/admin/dist/js/app.min.js"></script>

 <script src="${pageContext.request.contextPath}/Recursos/js/Funciones.js"></script>
</body>
</html>
