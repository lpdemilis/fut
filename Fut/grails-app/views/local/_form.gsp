<%@ page import="br.com.fut.Local" %>



<div class="fieldcontain ${hasErrors(bean: localInstance, field: 'endereco', 'error')} required">
	<label for="endereco">
		<g:message code="local.endereco.label" default="Endereco" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="endereco" name="endereco.id" from="${br.com.fut.Endereco.list()}" optionKey="id" required="" value="${localInstance?.endereco?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="local.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${localInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: localInstance, field: 'telefones', 'error')} ">
	<label for="telefones">
		<g:message code="local.telefones.label" default="Telefones" />
		
	</label>
	
</div>

