<html>
<head>
	<meta name='layout' content='main'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>

<div id="create-usuario" class="content scaffold-create" role="main">
	<h1><g:message code="springSecurity.login.header"/></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>		
</div>	
		

<div id='login'>
	<div class='inner'>									
		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
			<div class="fieldcontain">
				<label for="username">
					<g:message code="springSecurity.login.username.label" default="Nome" />
					
				</label>				
				<input type='text' name='j_username' id='username'/>
			</div>
			
			<div class="fieldcontain">
				<label for="password">
					<g:message code="springSecurity.login.password.label" default="Nome" />
					
				</label>				
				<input type='password' name='j_password' id='password'/>
			</div>
			
			<div class="fieldcontain" id="remember_me_holder">
				<label></label>
				<input type='checkbox' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
				<g:message code="springSecurity.login.remember.me.label"/>
			</div>
			
			<div class="fieldcontain">
				<label></label>
				<sec:ifNotGranted roles="ROLE_USER">
					<facebookAuth:connect />
				</sec:ifNotGranted>
				<sec:ifAllGranted roles="ROLE_USER">
					Welcome <sec:username/>! (<g:link uri="/j_spring_security_logout">Logout</g:link>)
				</sec:ifAllGranted>
			</div>	
			
			<br>
						
			<fieldset class="buttons">
				<g:submitButton name="submit" id="submit" class="save" value="${message(code: 'springSecurity.login.button', default: 'Login')}" />
				<g:link controller="usuario" class="save" action="create"><g:message code="default.usuario.create.label" default="Registre-se" /></g:link>
			</fieldset>
		</form>		
	</div>
</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>

</body>
</html>
