<%@ page import="br.com.fut.Estado" %>



<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="estado.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${estadoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'pais', 'error')} required">
	<label for="pais">
		<g:message code="estado.pais.label" default="Pais" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="pais" name="pais.id" from="${br.com.fut.Pais.list()}" optionKey="id" required="" value="${estadoInstance?.pais?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'uf', 'error')} ">
	<label for="uf">
		<g:message code="estado.uf.label" default="Uf" />
		
	</label>
	<g:textField name="uf" value="${estadoInstance?.uf}"/>
</div>

