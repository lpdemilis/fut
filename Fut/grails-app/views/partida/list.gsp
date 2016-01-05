
<%@ page import="br.com.fut.Partida" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'partida.label', default: 'Partida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-partida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-partida" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dataPartida" title="${message(code: 'partida.dataPartida.label', default: 'Data da Partida')}" />
						
						<th><g:message code="partida.grupo.label" default="Grupo" /></th>
					
						<th><g:message code="partida.local.label" default="Local" /></th>
						
						<g:sortableColumn property="valor" title="${message(code: 'partida.valor.label', default: 'Valor')}" />
															
						<g:sortableColumn property="dataLimiteConfirmacao" title="${message(code: 'partida.dataLimiteConfirmacao.label', default: 'Data Limite Confirmacao')}" />
																
						
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${partidaInstanceList}" status="i" var="partidaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${partidaInstance.id}"><g:formatDate date="${partidaInstance.dataPartida}" format="dd/MM/yyyy HH:mm:ss" /></g:link></td>
						
						<td><g:link controller="grupo" action="show" id="${partidaInstance.grupo.id}">${fieldValue(bean: partidaInstance, field: "grupo")}</g:link></td>
					
						<td><g:link controller="local" action="show" id="${partidaInstance.local.id}">${partidaInstance.local.toHTML()}</g:link></td>
						
						<td><g:formatNumber number="${partidaInstance.valor}" type="currency" currencyCode="BRL" /></td>
										
					
						<td><g:formatDate date="${partidaInstance.dataLimiteConfirmacao}" format="dd/MM/yyyy HH:mm:ss" /></td>
										
						
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${partidaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
