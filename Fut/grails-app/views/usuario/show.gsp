
<%@ page import="br.com.fut.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuário')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-usuario" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list usuario">
			
				<g:if test="${usuarioInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="usuario.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${usuarioInstance}" field="nome"/><g:if test="${usuarioInstance.apelido != ""}"> (${fieldValue(bean: usuarioInstance, field: "apelido")})</g:if></span>
					
				</li>
				</g:if>
			
				<g:if test="${usuarioInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="usuario.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${usuarioInstance}" field="username"/></span>
					
				</li>
				</g:if>
										
				<g:if test="${usuarioInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="usuario.email.label" default="E-mail" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${usuarioInstance}" field="email"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${usuarioInstance?.endereco}">
					
					<g:render template="/endereco/show" model="[enderecoInstance:usuarioInstance.endereco]" />	

				</g:if>
			
				<g:if test="${usuarioInstance?.nascimento}">
				<li class="fieldcontain">
					<span id="nascimento-label" class="property-label"><g:message code="usuario.nascimento.label" default="Nascimento" /></span>
					
						<span class="property-value" aria-labelledby="nascimento-label"><g:formatDate format="dd/MM/yyyy" date="${usuarioInstance?.nascimento}" /> <g:message code="usuario.anos.label" default="(${usuarioInstance.getIdade()} anos)" /></span>
					
				</li>
				</g:if>
									
				<g:if test="${usuarioInstance?.dataCadastro}">
				<li class="fieldcontain">
					<span id="dataCadastro-label" class="property-label"><g:message code="usuario.dataCadastro.label" default="Data do Cadastro" /></span>
					
						<span class="property-value" aria-labelledby="dataCadastro-label"><g:formatDate format="dd/MM/yyyy HH:mm:ss" date="${usuarioInstance?.dataCadastro}" /></span>
					
				</li>
				</g:if>	
				
				<g:if test="${usuarioInstance?.telefones}">
				<li class="fieldcontain">
					<span id="telefones-label" class="property-label"><g:message code="usuario.telefones.label" default="Telefones" /></span>
					
						<g:each in="${usuarioInstance.telefones}" var="a">
						<span class="property-value" aria-labelledby="telefones-label"><g:link controller="usuario" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
							
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${usuarioInstance?.id}" />
					<g:link class="edit" action="edit" id="${usuarioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
