		<div id="list-time" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="['Time']" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>				
						<g:each in="${timeInstanceList}" status="i" var="timeInstance">
							<th><g:message code="time.nome.label" default="Nome" /></th>
						</g:each>	
					
					</tr>
				</thead>
				<tbody>
				<tr>
					<g:each in="${timeInstanceList}" status="i" var="timeInstance">
								
						<td>
							<g:link controller="time" action="show" id="${timeInstance.id}">${fieldValue(bean: timeInstance, field: "nome")}</g:link>
							<table>
								<tbody>
									<g:each in="${timeInstance?.usuarios}" status="k" var="usuarioInstance">
										<tr class="${(k % 2) == 0 ? 'even' : 'odd'}">
											<td>
												${usuarioInstance} <g:if test="${usuarioInstance.apelido != ""}"> (${fieldValue(bean: usuarioInstance, field: "apelido")})</g:if>
											</td>
										</tr>
									</g:each>
								</tbody>
							</table>	
						</td>
					
					</g:each>
				</tr>
				</tbody>
			</table>
		</div>