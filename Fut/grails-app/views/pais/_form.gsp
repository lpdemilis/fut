<%@ page import="br.com.fut.Pais" %>



<div class="fieldcontain ${hasErrors(bean: paisInstance, field: 'estados', 'error')} ">
	<label for="estados">
		<g:message code="pais.estados.label" default="Estados" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${paisInstance?.estados?}" var="e">
    <li><g:link controller="estado" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="estado" action="create" params="['pais.id': paisInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'estado.label', default: 'Estado')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: paisInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="pais.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${paisInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paisInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="pais.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${paisInstance?.nome}"/>
</div>

