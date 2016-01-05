<%@ page import="br.com.fut.Papel" %>



<div class="fieldcontain ${hasErrors(bean: papelInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="papel.authority.label" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${papelInstance?.authority}"/>
</div>

