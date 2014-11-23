<?php

include_once 'scripts/donation_script.php';

?>

<!DOCTYPE html>
<html data-ng-app="myApp" ng-app lang="en" >

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>donor</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
                                    <strong>Mario Pavic</strong>
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
                        <li><a href="scripts/logout_script.php"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    
                </li>
                
            </ul>
            

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search..." id="searchTerm" >
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="Search()">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            
                        </li>
                        <li>
                            <a class="active" href="index.php"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                            <a class="active" href="blooddonationmanagement.php"><i class="fa fa-smile-o fa-fw"></i> Blood supply management</a>
                            <a class="active" href="statistics.html"><i class="fa fa-list fa-fw"></i> Statistics</a>
                            <a class="active" href="registration.php"><i class="fa fa-check-square fa-fw"></i> Registration</a>
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
                    <h1 class="page-header">Blood donation management</h1>
                </div>
                
            </div>
           
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    
                </div>
                
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Donators
                        </div>
                        
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>Firstname</th>
                                            <th>Lastname</th>
                                            <th>Institution name</th>
                                            <th>Time</th>
                                           
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                                            
                                        <?php drawrowsdonation(); ?>
                                        
                                           
                                           
                                        </tr>
                                        
                                    </tbody>
                                    
                                </table>
                               
                            </div>
                            
                            
                        </div>
                       
                    </div>
                    
                </div>
                
            </div>
            
        </div>
        

    </div>
    <script src="js/search.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="bower_components/angular/angular.min.js"></script>
     <script src="bower_components/angular/angular-animate.js"></script>
    <script src="bower_components/angular-route/angular-route.min.js"></script>
    <script src="app/app.js"></script>
</body>

</html>
