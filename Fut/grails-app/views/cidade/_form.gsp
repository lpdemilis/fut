<%@ page import="br.com.fut.Cidade" %>



<div class="fieldcontain ${hasErrors(bean: cidadeInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="cidade.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${br.com.fut.Estado.list()}" optionKey="id" required="" value="${cidadeInstance?.estado?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cidadeInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="cidade.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${cidadeInstance?.nome}"/>
</div>

