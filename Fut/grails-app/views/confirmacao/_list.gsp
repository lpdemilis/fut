<div id="list-confirmacao" class="content scaffold-list" role="main">
	<h1><g:message code="default.list.label" args="['Confirmações']" /></h1>
	<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
	</g:if>
	
	<table>	
		<thead>
			<tr>
				<th><g:message code="confirmacoes.label" default="Confirmações" /></th>
				<th><g:message code="desconfirmacoes.label" default="Desconfirmações" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<table>
						<thead>
							<tr>
								
								<g:sortableColumn property="usuario" title="${message(code: 'confirmacao.usuario.label', default: 'Usuário')}" />	
											
								<g:sortableColumn property="dataConfirmacao" title="${message(code: 'confirmacao.dataConfirmacao.label', default: 'Data Confirmacao')}" />
																
								<th><g:message code="confirmacao.acao.label" default="Ação" /></th>
																									
							</tr>
						</thead>
						<tbody>
						<g:each in="${confirmacaoInstanceList}" status="i" var="confirmacaoInstance">
							<g:if test="${!partidaInstance.verificarTime(confirmacaoInstance.usuario) || !acoes}">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
									<td><g:link controller="usuario" action="show" id="${confirmacaoInstance.usuario.id}">${fieldValue(bean: confirmacaoInstance, field: "usuario")}<g:if test="${confirmacaoInstance.usuario.apelido != ""}"> (${fieldValue(bean: confirmacaoInstance.usuario, field: "apelido")})</g:if></g:link></td>
																
									<td><g:formatDate date="${confirmacaoInstance.dataConfirmacao}" format="dd/MM/yyyy HH:mm:ss"/></td>
									<g:if test="${acoes}">								
										<td>
											<div class="buttons-actions">
												<g:formRemote name="myForm" url="[controller: 'time', action: 'adicionarJogador']" update="partida">
													<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
													<g:hiddenField name="usuarioInstanceId" value="${confirmacaoInstance.usuario.id}"/>
													<g:hiddenField name="time" value="1"/>															
													<g:actionSubmit class="time-1" value=" " name="adicionarJogador" title="Adicionar Jogador ao Time 1"/>															
												</g:formRemote>
												
												<g:formRemote name="myForm" url="[controller: 'time', action: 'adicionarJogador']" update="partida">
													<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
													<g:hiddenField name="usuarioInstanceId" value="${confirmacaoInstance.usuario.id}"/>
													<g:hiddenField name="time" value="2"/>															
													<g:actionSubmit class="time-2" value=" " name="adicionarJogador" title="Adicionar Jogador ao Time 2"/>															
												</g:formRemote>
											</div>	
										</td>
									</g:if>
									<g:else>
										<td>
											<g:render template="/confirmacao/confirmarOutro" model="['partidaInstance':partidaInstance, 'usuarioid':confirmacaoInstance.usuario.id]"/>
										</td>
									</g:else>		
								</tr>
							</g:if>	
						</g:each>
						</tbody>
					</table>
				</td>
				<td>
					<table>
						<thead>
							<tr>
								
								<g:sortableColumn property="usuario" title="${message(code: 'confirmacao.usuario.label', default: 'Usuário')}" />	
											
								<g:sortableColumn property="dataConfirmacao" title="${message(code: 'confirmacao.dataConfirmacao.label', default: 'Data Confirmacao')}" />
								
								<th><g:message code="confirmacao.motivo.label" default="Motivo" /></th>
								
								<th><g:message code="confirmacao.acao.label" default="Ação" /></th>
																																	
							</tr>
						</thead>
						<tbody>
						<g:each in="${desconfirmacaoInstanceList}" status="i" var="confirmacaoInstance">
							<g:if test="${!partidaInstance.verificarTime(confirmacaoInstance.usuario) || !acoes}">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								
									<td><g:link controller="usuario" action="show" id="${confirmacaoInstance.usuario.id}">${fieldValue(bean: confirmacaoInstance, field: "usuario")}<g:if test="${confirmacaoInstance.usuario.apelido != ""}"> (${fieldValue(bean: confirmacaoInstance.usuario, field: "apelido")})</g:if></g:link></td>
																
									<td><g:formatDate date="${confirmacaoInstance.dataConfirmacao}" format="dd/MM/yyyy HH:mm:ss"/></td>
									
									<td>${fieldValue(bean: confirmacaoInstance, field: "motivo")}</td>
									
									<g:if test="${acoes}">								
										<td>
											<div class="buttons-actions">
												<g:formRemote name="myForm" url="[controller: 'time', action: 'adicionarJogador']" update="partida">
													<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
													<g:hiddenField name="usuarioInstanceId" value="${confirmacaoInstance.usuario.id}"/>
													<g:hiddenField name="time" value="1"/>															
													<g:actionSubmit class="time-1" value=" " name="adicionarJogador" title="Adicionar Jogador ao Time 1"/>															
												</g:formRemote>
												
												<g:formRemote name="myForm" url="[controller: 'time', action: 'adicionarJogador']" update="partida">
													<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
													<g:hiddenField name="usuarioInstanceId" value="${confirmacaoInstance.usuario.id}"/>
													<g:hiddenField name="time" value="2"/>															
													<g:actionSubmit class="time-2" value=" " name="adicionarJogador" title="Adicionar Jogador ao Time 2"/>															
												</g:formRemote>
											</div>	
										</td>
									</g:if>
									<g:else>
										<td>
											<g:render template="/confirmacao/confirmarOutro" model="['partidaInstance':partidaInstance, 'usuarioid':confirmacaoInstance.usuario.id]"/>
										</td>
									</g:else>											
								</tr>
							</g:if>	
						</g:each>
						</tbody>
					</table>
				</td>	
			</tr>	
		</tbody>
	</table>
	
	<table>	
		<thead>
			<tr>
				<th><g:message code="naoconfirmados.label" default="Usuários não confirmados" /></th>				
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
	
					<table>
						<thead>
							<tr>
								
								<g:sortableColumn property="usuario" title="${message(code: 'confirmacao.usuario.label', default: 'Usuário')}" />
								
								<th><g:message code="confirmacao.acao.label" default="Ação" /></th>	
																													
							</tr>
						</thead>
						<tbody>
						<g:each in="${naoconfirmadosInstanceList}" status="i" var="naoconfirmadosInstance">
							<g:if test="${!partidaInstance.verificarTime(naoconfirmadosInstance) || !acoes}">
								<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
												
									<td>
										<g:link controller="usuario" action="show" id="${naoconfirmadosInstance.id}">
											${fieldValue(bean: naoconfirmadosInstance, field: "nome")} <g:if test="${naoconfirmadosInstance.apelido != ""}"> (${fieldValue(bean: naoconfirmadosInstance, field: "apelido")})</g:if>
										</g:link>
									</td>
									
									<g:if test="${acoes}">								
										<td>
											<div class="buttons-actions">
												<g:formRemote name="myForm" url="[controller: 'time', action: 'adicionarJogador']" update="partida">
													<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
													<g:hiddenField name="usuarioInstanceId" value="${naoconfirmadosInstance.id}"/>
													<g:hiddenField name="time" value="1"/>															
													<g:actionSubmit class="time-1" value=" " name="adicionarJogador" title="Adicionar Jogador ao Time 1"/>															
												</g:formRemote>
												
												<g:formRemote name="myForm" url="[controller: 'time', action: 'adicionarJogador']" update="partida">
													<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
													<g:hiddenField name="usuarioInstanceId" value="${naoconfirmadosInstance.id}"/>
													<g:hiddenField name="time" value="2"/>															
													<g:actionSubmit class="time-2" value=" " name="adicionarJogador" title="Adicionar Jogador ao Time 2"/>															
												</g:formRemote>
											</div>	
										</td>
									</g:if>
									<g:else>
										<td>
											<g:render template="/confirmacao/confirmarOutro" model="['partidaInstance':partidaInstance, 'usuarioid':naoconfirmadosInstance.id]"/>
										</td>
									</g:else>						
															
								</tr>
							</g:if>	
						</g:each>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>	
<%--	<div class="pagination">--%>
<%--		<g:paginate total="${confirmacaoInstanceTotal}" />--%>
<%--	</div>--%>
</div>