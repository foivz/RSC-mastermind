<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>donor</title>
    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/main.css" rel="stylesheet"/>
    <link href="css/plugins/dataTables.bootstrap.css" rel="stylesheet"/>
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

</head>

<body >
    <div id="wrapper">

        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.php"><span class="icon-bar"><img src="img/logo_small.png"/> </span><p class="donor"> DO<b style="color:#E50000; ">NATION</b> </p></a>
                </div>
            
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>Mario PAvic</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                                            
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    
                
               
                </li>
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    
                </li>
                
            </ul>
            

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search..."  >
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            
                        </li>
                        <li>
                            <a class="active" href="index.php"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                            <a class="active" href="blooddonationmanagement.php"><i class="fa fa-smile-o fa-fw"></i> Blood supply management</a>
                            <a class="active" href="statistics.html"><i class="fa fa-list fa-fw"></i> Statistics</a>
                            <a class="active" href="registration.php"><i class="fa fa-list fa-fw"></i> Registration</a>
                        </li>
                        <li>
                            
                            
                        </li>
 
                    </ul>
                </div>
                
            </div>
           
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Registration</h1>
                </div>
                
            </div>
                    <div class="container">
    <div class="row">
        <form role="form" id="registration_form" >
            <div class="col-lg-6">
                <div class="well well-sm"><strong><span class="fa fa-gear fa-fw"></span>Required Field</strong></div>
                <div class="form-control-group">
                    <label class="control-label" for="firstname">Enter Firstname</label>
                    <div class="input-group">
                        <input type="text" class="form-control" name="firstname" id="firstname" placeholder="Enter Firstname" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                <div class="form-control-group">
                    <label class="control-label" for="tname">Enter Lastname</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Enter Lastname" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                <div class="form-control-group">
                    <label class="control-label" for="name">Enter Username</label>
                    <div class="input-group">
                        <input type="password" class="form-control" name="username" id="username" placeholder="Enter Name" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                <div class="form-control-group">
                    <label class="control-label" for="name">Enter Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" name="password" id="password" placeholder="Enter Password" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                <div class="form-control-group">
                    <label class="control-label" for="name">Confirm Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" name="confirm_password" id="confirm_password" placeholder="Confirm Password" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                
                <div class="form-control-group">
                    <label class="control-label" for="name">Enter Phone</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="InputPhone" name="InputPhone" placeholder="Enter Phone" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                <div class="form-control-group">
                    <label class="control-label" for="name">Enter Institution</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="InputInstitution" name="InputInstitution" placeholder="Enter Institution" required>
                        <span class="input-group-addon"><span class="fa fa-gear fa-fw"></span></span>
                    </div>
                </div>
                <br />
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
                <br />
            </div>
        </form>
        <!--<div class="col-lg-5 col-md-push-1">
            <div class="col-md-12">
                <div class="alert alert-success">
                    <strong><span class="fa fa-gear fa-fw"></span> Success! Message sent.</strong>
                </div>
                <div class="alert alert-danger">
                    <span class="fa fa-gear fa-fw"></span><strong> Error! Please check all page inputs.</strong>
                </div>
            </div>
        </div>-->
    </div>
</div>
   
                </div>
                
            </div>
            
        </div>
        

    </div>
 
     
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    
    <script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
    <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"/>
    <script src="js/bootstrap.min.js"></script>
    
    
    
    
</body>

</html>
