<%@ page import="br.com.fut.Confirmacao" %>



<div class="fieldcontain ${hasErrors(bean: confirmacaoInstance, field: 'confirmacao', 'error')} ">
	<label for="confirmacao">
		<g:message code="confirmacao.confirmacao.label" default="Confirmacao" />
		
	</label>
	<g:checkBox name="confirmacao" value="${confirmacaoInstance?.confirmacao}" />
</div>

<div class="fieldcontain ${hasErrors(bean: confirmacaoInstance, field: 'dataConfirmacao', 'error')} required" hidden="true">
	<label for="dataConfirmacao">
		<g:message code="confirmacao.dataConfirmacao.label" default="Data Confirmacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataConfirmacao" precision="day"  value="${confirmacaoInstance?.dataConfirmacao}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: confirmacaoInstance, field: 'motivo', 'error')} ">
	<label for="motivo">
		<g:message code="confirmacao.motivo.label" default="Motivo" />
		
	</label>
	<g:textField name="motivo" value="${confirmacaoInstance?.motivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: confirmacaoInstance, field: 'partida', 'error')} required">
	<label for="partida">
		<g:message code="confirmacao.partida.label" default="Partida" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="partida" name="partida.id" from="${br.com.fut.Partida.list()}" optionKey="id" required="" value="${confirmacaoInstance?.partida?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: confirmacaoInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="confirmacao.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${br.com.fut.Usuario.list()}" optionKey="id" required="" value="${confirmacaoInstance?.usuario?.id}" class="many-to-one"/>
</div>

