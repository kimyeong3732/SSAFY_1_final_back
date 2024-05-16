<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String contextPath=request.getContextPath();
	%>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="/index"><img
			src="assets/img/navbar-logo.svg" alt="..." /></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars ms-1"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
				<li class="nav-item"><a class="nav-link" href="/map">Map</a></li>
				<li class="nav-item"><a class="nav-link" href="/myPage">MyPage</a></li>
				<li class="nav-item"><a class="nav-link" href="/boardMain">Board</a></li>
				<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>
