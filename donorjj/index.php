<?php
if(isset($_GET['message']))
    $msg= $_GET['message'];
if(isset($_GET['message']))
    $msgreg=$_GET['msreg'];

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Donacija krvi | Početna</title>
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link rel="icon" href="favicon.ico" type="image/x-icon">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
    <link href="font-awesome-4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
<!-- FB login skripte -->
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=303357739855104&version=v2.0";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>



    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">
                    <i class="fa fa-plus-circle fa-1x"></i>
                </a>
            </div>

            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">O nama</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#download">Preuzimanje</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Kontakt</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle" id="login_drop">Login<b class="caret"></b></a>

                        <ul class="dropdown-menu" id="login_forma">
                            <form id="contact-form" name="login" method="post" action="scripts/login.php">
                                <div>Prijavite se ili registrirajte novi račun!</br></br>
                                
                                <!--login form-->
                                
                                <div class="form-group">
                                    <input type="text" name="username" class="form-control" required="required" placeholder="Email">
                                </div> 
                                <div class="form-group">
                                    <input type="password" name="password" class="form-control" required="required" placeholder="Password">
                                </div>       
                                <div class="form-group2">
                                    <button type="submit" class="btn btn-danger" id="prijavabtn">Prijava</button>
									<a href="https://www.facebook.com/dialog/oauth?client_id=303357739855104&redirect_uri=www.google.com" class="fb-login-button" data-max-rows="1" data-size="large" data-show-faces="false" data-auto-logout-link="true">FB</a>
                                    <p id="msg"><?php echo $msg; ?></p>
                                </div>
                            </form> 
                            </div>
                            <?php echo "<p>".$msgreg."</p>"; ?>
                        </ul>
                    </li>
                    
                </ul>
               
            </div>
             
        </div>
    </nav>

    <header class="intro">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        </br></br></br></br>
                        <h1 class="brand-heading" style="transform: translateX(-25%);">Donirajte krv!</h1>
                        <p class="intro-text">Spasite nekome život!<br>Pročitajte više...</p>
                        <a class="page-scroll" href="#about">
                            <i class="fa fa-angle-double-down animated"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <section id="about" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>O nama</h2>
                <p>Znate li da samo &nbsp<span style="font-size: 32px; font-weight: 600;">3.8%</span>&nbsp hrvatske populacije donira krv i među najslabijim smo donatorima u Europi?<br>Stvorili smo aplikaciju sa ciljem širenja svijesti o važnosti darivanja krvi te davanju iste. <br>Budite dio statistike, pomozite onima kojima je potrebno!</p>
                <p>Kako? Jednostavno! Preuzmite aplikaciju za Vaš Android telefon.</p>
                <a class="page-scroll" href="#download">
                    <i class="fa fa-angle-double-down animated" style="display:inline-block; width:100%;"></i>
                </a>
            </div>
        </div>
    </section>

    <section id="download" class="content-section text-center">
        <div class="download-section">
            <div class="container">
                <div class="col-lg-8 col-lg-offset-2">
                    <h2>Preuzimanje aplikacije</h2>
                    <p>Postani donor krvi i preuzemi mobilnu aplikaciju sa sljedeće stranice</p>
                    <a href="http://play.google.com/donacijakrvi" class="btn btn-danger">Na stranicu za preuzimanje</a>
                </div>
            </div>
        </div>
    </section>
	
		<section class="content-section text-center" style="background-position: 50% 257px;">
		<div class="parallax-content">
			<div class="color-overlay"></div>
			<div class="container">
				<div class="row text-center section-title">
					<div class="col-sm-8 col-sm-offset-2">
						<h2>Kontaktirajte nas!</h2>

                        <!--Testiranje fb javascripta 
                        <div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>-->
                        
						<p>Koristite donje forme kako biste nam slali pitanja, preporuke, komentare i kritike! Drago nam je čuti svako Vaše mišljenje! </p>
					</div>
				</div>
				<div class="col-sm-6">
					<form id="contact-form" name="contact-form" method="post" action="http://demo.themeregion.com/octopus/sendemail.php">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<input type="text" name="name" class="form-control" required="required" placeholder="Ime">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<input type="email" name="email" class="form-control" required="required" placeholder="Email">
								</div>
							</div>
						</div>
						<div class="form-group">
							<input type="text" name="subject" class="form-control" required="required" placeholder="Naslov">
						</div>
						<div class="form-group">
							<textarea name="message" id="message" required="required" class="form-control" rows="10" placeholder="Poruka"></textarea>
						</div>                        
						<div class="form-group">
                            <button type="submit" id="sendmessage" class="btn btn-danger">Pošalji poruku</button>
						</div>
					</form>
				</div>
				<div class="col-sm-6">
					<div class="contact-info" id="contact">
						<h3>Kontakt informacije</h3>
						<ul>
							<i class="fa fa-phone"></i> &nbsp Telefon: 097 00 77 007<br>
							<i class="fa fa-map-marker"></i> &nbspVaraždinska 22, Varaždin<br>
							<i class="fa fa-envelope"></i> &nbsp podrska@mastermind.hr<br>
						</ul>
						<br><br><br>
						<div class="social-icons">
							<h3>Povežite se!</h3>
							<ul>
								<li><a href="http://demo.themeregion.com/octopus/#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="http://demo.themeregion.com/octopus/#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="http://demo.themeregion.com/octopus/#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="http://demo.themeregion.com/octopus/#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="http://demo.themeregion.com/octopus/#"><i class="fa fa-youtube"></i></a></li>
							</ul>
						</div>
					</div>
				</div>   
			</div>
		</div>
	</section>
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; Mastermind 2014</p>
        </div>
    </footer>
    <!-- include na kraju za brže učitavanje -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/grayscale.js"></script>
</body>
</html>