
<%@ page import="br.com.fut.Confirmacao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'confirmacao.label', default: 'Confirmacao')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-confirmacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-confirmacao" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list confirmacao">
			
				<g:if test="${confirmacaoInstance?.confirmacao}">
				<li class="fieldcontain">
					<span id="confirmacao-label" class="property-label"><g:message code="confirmacao.confirmacao.label" default="Confirmacao" /></span>
					
						<span class="property-value" aria-labelledby="confirmacao-label"><g:formatBoolean boolean="${confirmacaoInstance?.confirmacao}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${confirmacaoInstance?.dataConfirmacao}">
				<li class="fieldcontain">
					<span id="dataConfirmacao-label" class="property-label"><g:message code="confirmacao.dataConfirmacao.label" default="Data Confirmacao" /></span>
					
						<span class="property-value" aria-labelledby="dataConfirmacao-label"><g:formatDate date="${confirmacaoInstance?.dataConfirmacao}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${confirmacaoInstance?.motivo}">
				<li class="fieldcontain">
					<span id="motivo-label" class="property-label"><g:message code="confirmacao.motivo.label" default="Motivo" /></span>
					
						<span class="property-value" aria-labelledby="motivo-label"><g:fieldValue bean="${confirmacaoInstance}" field="motivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${confirmacaoInstance?.partida}">
				<li class="fieldcontain">
					<span id="partida-label" class="property-label"><g:message code="confirmacao.partida.label" default="Partida" /></span>
					
						<span class="property-value" aria-labelledby="partida-label"><g:link controller="partida" action="show" id="${confirmacaoInstance?.partida?.id}">${confirmacaoInstance?.partida?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${confirmacaoInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="confirmacao.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${confirmacaoInstance?.usuario?.id}">${confirmacaoInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${confirmacaoInstance?.id}" />
					<g:link class="edit" action="edit" id="${confirmacaoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
