<%@ page import="br.com.fut.Time" %>



<div class="fieldcontain ${hasErrors(bean: timeInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="time.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${timeInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: timeInstance, field: 'gols', 'error')} ">
	<label for="gols">
		<g:message code="time.gols.label" default="Gols" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${timeInstance?.gols?}" var="g">
    <li><g:link controller="gol" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="gol" action="create" params="['time.id': timeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'gol.label', default: 'Gol')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: timeInstance, field: 'usuarios', 'error')} ">
	<label for="usuarios">
		<g:message code="time.usuarios.label" default="Usuarios" />
		
	</label>
	<g:select name="usuarios" from="${br.com.fut.Usuario.list()}" multiple="multiple" optionKey="id" size="5" value="${timeInstance?.usuarios*.id}" class="many-to-many"/>
</div>

