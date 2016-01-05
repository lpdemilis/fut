<div id="list-confirmacao" class="content scaffold-list" role="main">
	<h1><g:message code="default.list.label" args="[confirmacao]" /></h1>
	<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
	</g:if>
		
	<table>
		<thead>
			<tr>
				
				<g:sortableColumn property="usuario" title="${message(code: 'confirmacao.usuario.label', default: 'Usuário')}" />	
							
				<g:sortableColumn property="dataConfirmacao" title="${message(code: 'confirmacao.dataConfirmacao.label', default: 'Data Confirmacao')}" />
				
				<g:if test="${confirmacao == 'Desconfirmação'}">
					<th><g:message code="confirmacao.motivo.label" default="Motivo" /></th>
				</g:if>	
																					
			</tr>
		</thead>
		<tbody>
		<g:each in="${confirmacaoInstanceList}" status="i" var="confirmacaoInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			
				<td><g:link controller="usuario" action="show" id="${confirmacaoInstance.usuario.id}">${fieldValue(bean: confirmacaoInstance, field: "usuario")}<g:if test="${confirmacaoInstance.usuario.apelido != ""}"> (${fieldValue(bean: confirmacaoInstance.usuario, field: "apelido")})</g:if></g:link></td>
											
				<td><g:formatDate date="${confirmacaoInstance.dataConfirmacao}" format="dd/MM/yyyy HH:mm:ss"/></td>
				
				<g:if test="${confirmacao == 'Desconfirmação'}">
					<td>${fieldValue(bean: confirmacaoInstance, field: "motivo")}</td>
				</g:if>					
						
			</tr>
		</g:each>
		</tbody>
	</table>
<%--	<div class="pagination">--%>
<%--		<g:paginate total="${confirmacaoInstanceTotal}" />--%>
<%--	</div>--%>
</div>