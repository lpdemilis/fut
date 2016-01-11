<fieldset class="buttons">
	<div class="confirmar">				
		<g:if test="${partidaInstance.verificarConfirmacao() == null}">
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
	</div>
</fieldset>
				
<g:render template="/confirmacao/list" model="['confirmacaoInstanceList':confirmacaoInstanceList, 'confirmacao':'Confirmação']"/>
<g:render template="/confirmacao/list" model="['confirmacaoInstanceList':desconfirmacaoInstanceList, 'confirmacao':'Desconfirmação']"/>
<g:render template="/time/list" var="timeInstanceList"/>
<fieldset class="buttons">		
	<g:link class="create" action="sortearTimes" id="${partidaInstance?.id}"><g:message code="default.time.sortear.label" default="Sortear Times" /></g:link>			
</fieldset>