		<div id="list-time" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="['Time']" /></h1>			
			<g:if test="${incompleto}">
				<div class="message" role="status">${message(code: 'default.times.incompetos')}</div>
			</g:if>
			<g:if test="${desequilibrado}">
				<div class="message" role="status">${message(code: 'default.times.desequilibrados')}</div>
			</g:if>
			
			<g:if test="${acoes}">
				<g:each in="${timeInstanceList}" status="i" var="timeInstance">
					${timeInstance.gols.size()} <g:if test="${i < timeInstanceList.size() - 1}"> X </g:if>
				</g:each>
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
											<g:if test="${acoes}">
												<td>
													<div class="buttons-outros">
														<g:formRemote name="myForm" url="[controller: 'time', action: 'marcarGol']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
															<g:hiddenField name="contra" value="false"/>															
															<g:actionSubmit class="cancel" value="Desconfirmar" name="desconfirmar" />
														</g:formRemote>													
													</div>	
												</td>
											</g:if>
											
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