		<div class="buttons-outros">	
			<g:if test="${partidaInstance.verificarConfirmacao(usuarioid) == null}">
				<g:formRemote name="myForm" url="[controller: 'confirmacao', action: 'confirmar']" update="confirmar">
					<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>
					<g:hiddenField name="confirmacao" value="true"/>
					<g:hiddenField name="motivo" value=""/>
					<g:hiddenField name="usuarioid" value="${usuarioid}"/>		
					<g:actionSubmit class="confirm" value=" " title="Confirmar" name="confirmar" />	
				</g:formRemote>
			
				<g:formRemote name="myForm" url="[controller: 'confirmacao', action: 'confirmar']" update="confirmar">
					<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>	
					<g:hiddenField name="confirmacao" value="false"/>
					<g:hiddenField name="motivo" value=""/>
					<g:hiddenField name="usuarioid" value="${usuarioid}"/>
					<g:actionSubmit class="cancel" value=" " title="Desconfirmar" name="desconfirmar" />
				</g:formRemote>
			</g:if>
			<g:else>
				<g:formRemote name="myForm" url="[controller: 'confirmacao', action: 'confirmar']" update="confirmar">
					<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>
					<g:hiddenField name="remover" value="true"/>
					<g:hiddenField name="usuarioid" value="${usuarioid}"/>
					<g:actionSubmit class="cancel" value=" " title="Remover Confirmação" name="remover" />
				</g:formRemote>
			</g:else>
		</div>	