
<%@ page import="br.com.fut.Grupo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupo.label', default: 'Grupo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-grupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<g:if test="${params.grupos == 'todos'}">
				     <li><g:link class="list" action="list" ><g:message code="default.meus.grupos.label" args="[entityName]" default="Meus Grupos" /></g:link></li>
				</g:if>
				<g:else>
				     <li><g:link class="list" action="list" params="[grupos:'todos']"><g:message code="default.todos.grupos.label" args="[entityName]" default="Todos os Grupos" /></g:link></li>
				</g:else>									
			</ul>
		</div>
		<div id="list-grupo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'grupo.nome.label', default: 'Nome')}" />
						
						<th><g:message code="grupo.descricao.label" default="Descrição" /></th>
						
						<g:sortableColumn property="dataCriacao" title="${message(code: 'grupo.dataCriacao.label', default: 'Data Criacao')}" />
										
						<th><g:message code="grupo.presidente.label" default="Presidente" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${grupoInstanceList}" status="i" var="grupoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${grupoInstance.id}">${fieldValue(bean: grupoInstance, field: "nome")}</g:link></td>
						
						<td>${fieldValue(bean: grupoInstance, field: "descricao")}</td>
						
<%--						<td>${fieldValue(bean: grupoInstance, field: "dataCriacao")}</td>--%>
						<td><g:formatDate format="dd/MM/yyyy HH:mm:ss" date="${grupoInstance.dataCriacao}"/></td>
																
						<td><g:link controller="usuario" action="show" id="${grupoInstance?.presidente?.id}">${fieldValue(bean: grupoInstance, field: "presidente")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${grupoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
