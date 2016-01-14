			<g:if test="${partidaInstance.verificarConfirmacao(usuarioid) == null}">
				<g:formRemote name="myForm" url="[controller: 'confirmacao', action: 'confirmar']" update="confirmar">
					<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>
					<g:hiddenField name="confirmacao" value="true"/>
					<g:hiddenField name="motivo" value=""/>		
					<g:actionSubmit class="confirm" value="Confirmar" name="confirmar" />	
				</g:formRemote>
			
				<g:formRemote name="myForm" url="[controller: 'confirmacao', action: 'confirmar']" update="confirmar">
					<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>	
					<g:hiddenField name="confirmacao" value="false"/>
					<g:hiddenField name="motivo" value=""/>
					<g:actionSubmit class="cancel" value="Desconfirmar" name="desconfirmar" />
				</g:formRemote>
			</g:if>
			<g:else>
				<g:formRemote name="myForm" url="[controller: 'confirmacao', action: 'confirmar']" update="confirmar">
					<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>
					<g:hiddenField name="remover" value="true"/>
					<g:actionSubmit class="cancel" value="Remover Confirmação" name="remover" />
				</g:formRemote>
			</g:else>