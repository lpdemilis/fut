<%@ page import="br.com.fut.Partida" %>



<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'valor', 'error')} required">
	<label for="valor">
		<g:message code="partida.valor.label" default="Valor" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="valor" value="${fieldValue(bean: partidaInstance, field: 'valor')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'dataCriacao', 'error')} required" hidden="true">
	<label for="dataCriacao">
		<g:message code="partida.dataCriacao.label" default="Data Criacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataCriacao" precision="day"  value="${partidaInstance?.dataCriacao}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'dataLimiteConfirmacao', 'error')} required">
	<label for="dataLimiteConfirmacao">
		<g:message code="partida.dataLimiteConfirmacao.label" default="Data Limite Confirmacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataLimiteConfirmacao" precision="day"  value="${partidaInstance?.dataLimiteConfirmacao}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'dataPartida', 'error')} required">
	<label for="dataPartida">
		<g:message code="partida.dataPartida.label" default="Data Partida" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataPartida" precision="day"  value="${partidaInstance?.dataPartida}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'grupo', 'error')} required">
	<label for="grupo">
		<g:message code="partida.grupo.label" default="Grupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="grupo" name="grupo.id" from="${br.com.fut.Grupo.list()}" optionKey="id" required="" value="${partidaInstance?.grupo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'local', 'error')} required">
	<label for="local">
		<g:message code="partida.local.label" default="Local" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="local" name="local.id" from="${br.com.fut.Local.list()}" optionKey="id" required="" value="${partidaInstance?.local?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'maximoJogadores', 'error')} required">
	<label for="maximoJogadores">
		<g:message code="partida.maximoJogadores.label" default="Maximo Jogadores" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maximoJogadores" type="number" value="${partidaInstance.maximoJogadores}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'minimoJogadores', 'error')} required">
	<label for="minimoJogadores">
		<g:message code="partida.minimoJogadores.label" default="Minimo Jogadores" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minimoJogadores" type="number" value="${partidaInstance.minimoJogadores}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'tipoCampo', 'error')} required">
	<label for="tipoCampo">
		<g:message code="partida.tipoCampo.label" default="Tipo Campo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoCampo" name="tipoCampo.id" from="${br.com.fut.TipoCampo.list()}" optionKey="id" required="" value="${partidaInstance?.tipoCampo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: partidaInstance, field: 'usuarioMarcador', 'error')} required">
	<label for="usuarioMarcador">
		<g:message code="partida.usuarioMarcador.label" default="Usuario Marcador" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuarioMarcador" name="usuarioMarcador.id" from="${br.com.fut.Usuario.list()}" optionKey="id" required="" value="${partidaInstance?.usuarioMarcador?.id}" class="many-to-one"/>
</div>

