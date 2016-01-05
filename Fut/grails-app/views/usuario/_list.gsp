<div id="list-usuario" class="content scaffold-list" role="main">
	<h1><g:message code="default.list.label" args="['Usuários']" /></h1>
	<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
	</g:if>
	<table>
		<thead>
			<tr>
			
				<g:sortableColumn property="nome" title="${message(code: 'usuario.nome.label', default: 'Nome')}" />
					
				<th><g:message code="usuario.email.label" default="E-mail" /></th>

				<th><g:message code="usuario.nascimento.label" default="Nascimento" /></th>
					
			</tr>
		</thead>
		<tbody>
		<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
				<td><g:link controller="usuario" action="show" id="${usuarioInstance.id}">${fieldValue(bean: usuarioInstance, field: "nome")}<g:if test="${usuarioInstance.apelido != ""}"> (${fieldValue(bean: usuarioInstance, field: "apelido")})</g:if> </g:link></td>
					
				<td>${fieldValue(bean: usuarioInstance, field: "email")}</td>
					
				<td><g:formatDate format="dd/MM/yyyy" date="${usuarioInstance.nascimento}"/>  <g:message code="usuario.anos.label" default="(${usuarioInstance.getIdade()} anos)" /></td>
					
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${usuarioInstanceTotal}" />
	</div>
</div>	