<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType=" text/html" pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<title>Magic Store</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="${contextPath}/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
		<link href="${contextPath}/resources/themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="${contextPath}/resources/themes/css/flexslider.css" rel="stylesheet"/>
		<link href="${contextPath}/resources/themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="${contextPath}/resources/themes/js/jquery-1.7.2.min.js"></script>
		<script src="${contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>				
		<script src="${contextPath}/resources/themes/js/superfish.js"></script>	
		<script src="${contextPath}/resources/themes/js/jquery.scrolltotop.js"></script>
		<script src="${contextPath}/resources/js/magicStore.js"></script>
		<!--[if lt IE 9]>			
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
			<script src="js/respond.min.js"></script>
		<![endif]-->
	</head>
    <body>		
		<div id="top-bar" class="container">
			<div class="row">
				<div class="span4">
					<form method="POST" class="search_form">
						<!-- <input type="text" class="input-block-level search-query" Placeholder="eg. T-sirt"> -->
					</form>
				</div>
				<div class="span8">
					<div class="account pull-right">
						<ul class="user-menu">				
							<li><a href="cart.html">Seu carrinho</a></li>
							<li><a href="register.html">Login</a></li>		
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper" class="container">
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="index.html" class="logo pull-left"><img src="${contextPath}/resources/themes/images/magic_store.jpg" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
							<li><a href="./products.html">Home</a></li>															
							<li><a href="./products.html">Categorias</a></li>			
							<li><a href="./products.html">Acompanhe seu pedido</a></li>
							<li><a href="./products.html">Contato</a></li>
						</ul>
					</nav>
				</div>
			</section>			
			<section class="header_text sub">
			<img class="pageBanner" src="${contextPath}/resources/themes/images/magic_store.jpg" alt="New products" >
				<h4><span>Login ou Cadastro</span></h4>
			</section>			
			<section class="main-content">				
				<div class="row">
					<div class="span5">					
						<h4 class="title"><span class="text"><strong>Login</strong></span></h4>
						<form id="loginUsuario" action="${contextPath}/login" method="post">
							<input type="hidden" name="next" value="/">
							<fieldset>
								<div class="control-group">
									<label class="control-label">E-mail</label>
									<div class="controls">
										<input type="text" placeholder="Digite seu e-mail" name="email" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Senha</label>
									<div class="controls">
										<input type="password" placeholder="Digite sua senha" name="senha" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<input tabindex="3" class="btn btn-inverse large" type="submit" value="Entrar">
									<hr>
								</div>
							</fieldset>
						</form>				
					</div>
					<div class="span7">					
						<h4 class="title"><span class="text"><strong>Cadastro</strong> Form</span></h4>
						<!-- <form action="${contextPath}/usuario" method="post" class="form-stacked"> -->
						<form id="cadastroUsuario" class="form-stacked">
							<fieldset>
								<div class="control-group">
									<label class="control-label">Nome completo</label>
									<div class="controls">
										<input name="nome" type="text" placeholder="Digite seu nome" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">E-mail</label>
									<div class="controls">
										<input name="email" type="text" placeholder="Digite seu e-mail" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Senha</label>
									<div class="controls">
										<input name="senha" type="password" placeholder="Digite sua senha" class="input-xlarge">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">CPF</label>
									<div class="controls">
										<input name="cpf" type="text" placeholder="Digite seu cpf" class="input-xlarge">
									</div>
								</div>		
								<div class="control-group">
									<label class="control-label">RG</label>
									<div class="controls">
										<input name="rg" type="text" placeholder="Digite seu rg" class="input-xlarge">
									</div>
								</div>				                            
								<hr>
								<div class="actions"><input tabindex="9" class="btn btn-inverse large" onclick="cadastrarUsuario()" value="Criar sua conta"></div>
							</fieldset>
						</form>					
					</div>				
				</div>
			</section>			
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>NavegaÃ§Ã£o</h4>
						<ul class="nav">
							<li><a href="./index.html">Home</a></li>  
							<li><a href="./about.html">Categoria</a></li>
							<li><a href="./contact.html">Contato</a></li>
							<li><a href="./cart.html">Acompanhe seu pedido</a></li>
							<li><a href="./cart.html">Seu carrinho</a></li>
							<li><a href="./register.html">Login</a></li>							
						</ul>				
					</div>
					<div class="span4">
						<!-- <h4>My Account</h4>
						<ul class="nav">
							<li><a href="#">My Account</a></li>
							<li><a href="#">Order History</a></li>
							<li><a href="#">Wish List</a></li>
							<li><a href="#">Newsletter</a></li>
						</ul> -->
					</div>
					<div class="span5">
						<p class="logo">
							<h3>MAGIC STORE</h3>
							<!-- <img src="themes/images/logo.png" class="site_logo" alt=""> -->
						</p>
						<p>O seu e-commerce de roupas.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2021 Magic Store - Todos direitos reservados.</span>
			</section>
		</div>
		<script src="${contextPath}/resources/themes/js/common.js"></script>
		<script>
			$(document).ready(function() {
				$('#checkout').click(function (e) {
					document.location.href = "checkout.html";
				})
			});
		</script>		
    </body>
</html>