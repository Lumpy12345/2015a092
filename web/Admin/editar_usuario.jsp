<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" href="img/favicon.png">

    <title>TT1 2015-A092</title>

   <!-- Bootstrap CSS -->    
    <link href="${pageContext.request.contextPath}/Recursos/admin/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/Recursos/admin/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${pageContext.request.contextPath}/Recursos/admin/elegant-icons-style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Recursos/admin/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${pageContext.request.contextPath}/Recursos/admin/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Recursos/admin/style-responsive.css" rel="stylesheet" />
   
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
  </head>

  <body>
  <!-- container section start -->
  <section id="container" class="">
      <!--header start-->
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
             <a href="index.html" class="logo"><span class="lite">SSGPE</span></a>
            <!--logo end-->

            <div class="nav search-row" id="top_menu">
                <!--  search form start -->

                <!--  search form end -->                
            </div>

            <div class="top-nav notification-row">                
                <!-- notificatoin dropdown start-->
                <ul class="nav pull-right top-menu">
                    
                    <!-- task notificatoin start -->
                    <li id="task_notificatoin_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="icon-task-l"></span>
                        </a>
                        <ul class="dropdown-menu extended tasks-bar">
                            <div class="notify-arrow notify-arrow-blue"></div>
                            

                            <li class="external">
                                <a href="#">See All Tasks</a>
                            </li>
                        </ul>
                    </li>
                    <!-- task notificatoin end -->
                    <!-- inbox notificatoin start-->
                    <li id="mail_notificatoin_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="icon-envelope-l"></i>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-blue"></div>

                            
                            
                            
                            
                            <li>
                                <a href="#">See all messages</a>
                            </li>
                        </ul>
                    </li>
                    <!-- inbox notificatoin end -->
                    <!-- alert notification start-->
                    <li id="alert_notificatoin_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                            <i class="icon-bell-l"></i>
                        </a>
                        <ul class="dropdown-menu extended notification">
                            <div class="notify-arrow notify-arrow-blue"></div>
                            
                                              
                            <li>
                                <a href="#">See all notifications</a>
                            </li>
                        </ul>
                    </li>
                    <!-- alert notification end-->
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                            </span>
                            <span class="username" onclick="Cerrar_Sesion()">Cerrar Sesion</span>
                            <b class="caret"></b>
                        </a>
                        
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>      
      <!--header end-->

      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">                
                  <li class="active">
                      <a class="" href="${pageContext.request.contextPath}/Admin/index.html">
                          <i class="icon_house_alt"></i>
                          <span>Inicio</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_desktop"></i>
                          <span>Sitema</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="${pageContext.request.contextPath}/Admin/cargar_base.html">Cargar Base de Datos</a></li>
                      </ul>
                  </li>
                  
                  <li>                     
                      <a class="" href="${pageContext.request.contextPath}/Admin/informacion_general.html">
                          <i class="icon_piechart"></i>
                          <span>Informaci�n</span>
                          
                      </a>
                                         
                  </li>
                             
                  <li class="sub-menu">
                      <a href="#" class="">
                          <i class="icon_table"></i>
                          <span>Usuarios</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="${pageContext.request.contextPath}/Admin/usuarios.html">Adminstrar Usuarios</a></li>
                          <li><a class="" href="${pageContext.request.contextPath}/Admin/crear_usuario.html">Crear Usuario</a></li>
                         
                      </ul>
                  </li>
                  
                  
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->

      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-files-o"></i> Crear Usuario</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
						<li><i class="fa fa-user"></i>Usuarios</li>
						<li><i class="fa fa-user"></i> Crear Usuario</li>
					</ol>
				</div>
			</div>
              <!-- Form validations -->              
              <div class="row">
                  
              </div>
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Crear un nuevo usuario
                          </header>
                          <div class="panel-body">
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
                                          <label class="control-label col-lg-2">Contrase�a <span class="required">*</span></label>
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
                                              <button class="btn btn-default" type="button">Cancelar</button>
                                          </div>
                                      </div>
                                  </form>
                          
                              </div>
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
  </section>
  <!-- container section end -->

    <!-- javascripts -->

    <!--custome script for all page-->
       

   <!-- javascripts -->
    <script src="${pageContext.request.contextPath}/Recursos/js/Funciones.js"></script>
    <script src="${pageContext.request.contextPath}/Recursos/admin/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/Recursos/admin/bootstrap.min.js"></script>
    <!-- nice scroll -->
    <script src="${pageContext.request.contextPath}/Recursos/admin/jquery.scrollTo.min.js"></script>
    <script src="${pageContext.request.contextPath}/Recursos/admin/jquery.nicescroll.js" type="text/javascript"></script><!--custome script for all page-->
    <script src="${pageContext.request.contextPath}/Recursos/admin/scripts.js"></script>
  </body>
</html>