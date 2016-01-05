
<%@ page import="br.com.fut.Confirmacao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'confirmacao.label', default: 'Confirmacao')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-confirmacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-confirmacao" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="confirmacao" title="${message(code: 'confirmacao.confirmacao.label', default: 'Confirmacao')}" />
					
						<g:sortableColumn property="dataConfirmacao" title="${message(code: 'confirmacao.dataConfirmacao.label', default: 'Data Confirmacao')}" />
					
						<g:sortableColumn property="motivo" title="${message(code: 'confirmacao.motivo.label', default: 'Motivo')}" />
					
						<th><g:message code="confirmacao.partida.label" default="Partida" /></th>
					
						<th><g:message code="confirmacao.usuario.label" default="Usuario" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${confirmacaoInstanceList}" status="i" var="confirmacaoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${confirmacaoInstance.id}">${fieldValue(bean: confirmacaoInstance, field: "confirmacao")}</g:link></td>
					
						<td><g:formatDate date="${confirmacaoInstance.dataConfirmacao}" /></td>
					
						<td>${fieldValue(bean: confirmacaoInstance, field: "motivo")}</td>
					
						<td>${fieldValue(bean: confirmacaoInstance, field: "partida")}</td>
					
						<td>${fieldValue(bean: confirmacaoInstance, field: "usuario")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${confirmacaoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
