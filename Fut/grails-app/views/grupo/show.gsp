
<%@ page import="br.com.fut.Grupo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupo.label', default: 'Grupo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-grupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-grupo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupo">
			
				<g:if test="${grupoInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="grupo.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${grupoInstance}" field="nome"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${grupoInstance?.descricao}">
				<li class="fieldcontain">
					<span id="descricao-label" class="property-label"><g:message code="grupo.descricao.label" default="Descrição" /></span>
					
						<span class="property-value" aria-labelledby="descricao-label"><g:fieldValue bean="${grupoInstance}" field="descricao"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${grupoInstance?.dataCriacao}">
				<li class="fieldcontain">
					<span id="dataCriacao-label" class="property-label"><g:message code="grupo.dataCriacao.label" default="Data Criacao" /></span>
					
						<span class="property-value" aria-labelledby="dataCriacao-label"><g:formatDate date="${grupoInstance?.dataCriacao}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${grupoInstance?.presidente}">
				<li class="fieldcontain">
					<span id="presidente-label" class="property-label"><g:message code="grupo.presidente.label" default="Presidente" /></span>
					
						<span class="property-value" aria-labelledby="presidente-label"><g:link controller="usuario" action="show" id="${grupoInstance?.presidente?.id}">${grupoInstance?.presidente?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
<%--				<g:if test="${grupoInstance?.administradores}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="administradores-label" class="property-label"><g:message code="grupo.administradores.label" default="Administradores" /></span>--%>
<%--					--%>
<%--						<g:each in="${grupoInstance.administradores}" var="a">--%>
<%--						<span class="property-value" aria-labelledby="administradores-label"><g:link controller="usuario" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>--%>
<%--						</g:each>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
<%--										--%>
<%--				<g:if test="${grupoInstance?.membros}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="membros-label" class="property-label"><g:message code="grupo.membros.label" default="Membros" /></span>--%>
<%--					--%>
<%--						<g:each in="${grupoInstance.membros}" var="m">--%>
<%--						<span class="property-value" aria-labelledby="membros-label"><g:link controller="usuario" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>--%>
<%--						</g:each>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>				--%>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${grupoInstance?.id}" />
					<g:link class="edit" action="edit" id="${grupoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
			
			<g:render template="/partida/list" var="partidaInstanceList"/>
			
			<g:render template="/usuario/list" var="usuarioInstanceList"/>
			
		</div>
	</body>
</html>
