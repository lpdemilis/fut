<%@ page import="br.com.fut.TipoCampo" %>



<div class="fieldcontain ${hasErrors(bean: tipoCampoInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="tipoCampo.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${tipoCampoInstance?.tipo}"/>
</div>

