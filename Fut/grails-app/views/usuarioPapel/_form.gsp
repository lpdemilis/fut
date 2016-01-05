<%@ page import="br.com.fut.UsuarioPapel" %>



<div class="fieldcontain ${hasErrors(bean: usuarioPapelInstance, field: 'papel', 'error')} required">
	<label for="papel">
		<g:message code="usuarioPapel.papel.label" default="Papel" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="papel" name="papel.id" from="${br.com.fut.Papel.list()}" optionKey="id" required="" value="${usuarioPapelInstance?.papel?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioPapelInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="usuarioPapel.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${br.com.fut.Usuario.list()}" optionKey="id" required="" value="${usuarioPapelInstance?.usuario?.id}" class="many-to-one"/>
</div>

