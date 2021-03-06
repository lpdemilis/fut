		<div id="list-time" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="['Time']" /></h1>			
			<g:if test="${incompleto}">
				<div class="message" role="status">${message(code: 'default.times.incompetos')}</div>
			</g:if>
			<g:if test="${desequilibrado}">
				<div class="message" role="status">${message(code: 'default.times.desequilibrados')}</div>
			</g:if>
			
			<g:if test="${acoes}">
				<div class="placar">
					<g:each in="${timeInstanceList}" status="i" var="timeInstance">
						${timeInstance.gols.size()} <g:if test="${i < timeInstanceList.size() - 1}"> x </g:if>
					</g:each>
				</div>
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
								
						<td width="50%">
							<g:link controller="time" action="show" id="${timeInstance.id}">${fieldValue(bean: timeInstance, field: "nome")}</g:link>
							<table>
								<tbody>
									<g:each in="${timeInstance?.usuarios.sort{ it.id }}" status="k" var="usuarioInstance">
										<tr class="${(k % 2) == 0 ? 'even' : 'odd'}">
											<td>
												${usuarioInstance} <g:if test="${usuarioInstance.apelido != ""}"> (${fieldValue(bean: usuarioInstance, field: "apelido")})</g:if>
												<br>
												<g:if test="${partidaInstance.consultaGols(usuarioInstance.id, false) > 0}">
													<span class="gols">
<%--													<g:if test="${partidaInstance.consultaGols(usuarioInstance.id, false) > 1}">--%>
															(${partidaInstance.consultaGols(usuarioInstance.id, false)})
<%--													</g:if>--%>
													</span>
												</g:if>												
												<g:if test="${partidaInstance.consultaGols(usuarioInstance.id, true) > 0}">
													<span class="gols-contra">
<%--													<g:if test="${partidaInstance.consultaGols(usuarioInstance.id, true) > 1}">--%>
															(${partidaInstance.consultaGols(usuarioInstance.id, true)})
<%--													</g:if>--%>
													</span>													
												</g:if>
												<g:else>
													<span>&nbsp;</span>
												</g:else>
											</td>
											<g:if test="${acoes}">
												<td>
													<div class="buttons-actions">
														<g:formRemote name="myForm" url="[controller: 'time', action: 'marcarGol']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
															<g:hiddenField name="contra" value="false"/>
															<g:hiddenField name="remover" value="false"/>															
															<g:actionSubmit class="gols-add" value=" " name="marcarGol" title="Marcar Gol"/>															
														</g:formRemote>
													
														<g:formRemote name="myForm" url="[controller: 'time', action: 'marcarGol']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
															<g:hiddenField name="contra" value="false"/>
															<g:hiddenField name="remover" value="true"/>
															<g:if test="${partidaInstance.consultaGols(usuarioInstance.id, false) > 0}">															
																<g:actionSubmit class="gols-delete" value=" " name="marcarGol" title="Desmarcar Gol"/>
															</g:if>
															<g:else>
																<g:actionSubmit class="gols-delete-disabled" value=" " title="Desmarcar Gol" name="marcarGol" disabled=""/>
															</g:else>
														</g:formRemote>
														
														<g:formRemote name="myForm" url="[controller: 'time', action: 'marcarGol']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
															<g:hiddenField name="contra" value="true"/>
															<g:hiddenField name="remover" value="false"/>															
															<g:actionSubmit class="gols-contra-add" value=" " name="marcarGol" title="Marcar Gol Contra" />
														</g:formRemote>
													
														<g:formRemote name="myForm" url="[controller: 'time', action: 'marcarGol']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
															<g:hiddenField name="contra" value="true"/>
															<g:hiddenField name="remover" value="true"/>
															<g:if test="${partidaInstance.consultaGols(usuarioInstance.id, true) > 0}">															
																<g:actionSubmit class="gols-contra-delete" value=" " name="marcarGol" title="Desmarcar Gol Contra"/>
															</g:if>
															<g:else>
																<g:actionSubmit class="gols-contra-delete-disabled" value=" " name="marcarGol" disabled="" title="Desmarcar Gol Contra"/>
															</g:else>
														</g:formRemote>	
														
														<g:formRemote name="myForm" url="[controller: 'time', action: 'trocarTime']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
																														
															<g:if test="${i == 0}">
																<g:actionSubmit class="trocar-time-direita" value=" " name="trocarTime" title="Trocar de Time" />
															</g:if>
															<g:else>
																<g:actionSubmit class="trocar-time-esquerda" value=" " name="trocarTime" title="Trocar de Time" />
															</g:else>
														</g:formRemote>	
														
														<g:formRemote name="myForm" url="[controller: 'time', action: 'removerJogador']" update="partida">
															<g:hiddenField name="partidaInstanceId" value="${partidaInstanceId}"/>
															<g:hiddenField name="timeInstanceId" value="${timeInstance.id}"/>
															<g:hiddenField name="usuarioInstanceId" value="${usuarioInstance.id}"/>
															<g:actionSubmit class="exit" value=" " name="removerJogador" title="Remover Jogador" />															
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
			
			<g:if test="${acoes}">
				<g:render template="/confirmacao/list" model="['confirmacaoInstanceList':confirmacaoInstanceList, 'desconfirmacaoInstanceList':desconfirmacaoInstanceList, 'acoes':true]"/>
			</g:if>
		</div>