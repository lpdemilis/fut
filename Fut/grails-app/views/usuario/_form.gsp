<%@ page import="br.com.fut.Usuario" %>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="usuario.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${usuarioInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'apelido', 'error')} ">
	<label for="apelido">
		<g:message code="usuario.apelido.label" default="Apelido" />
		
	</label>
	<g:textField name="apelido" value="${usuarioInstance?.apelido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="usuario.email.label" default="E-mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${usuarioInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="usuario.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	
	<g:if test="${usuarioInstance?.password}">
		<g:field type="password" name="password" required="" value="*****"/>
	</g:if>
	<g:else>
		<g:field type="password" name="password" required="" value=""/>
	</g:else>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nascimento', 'error')} required">
	<label for="nascimento">
		<g:message code="usuario.nascimento.label" default="Nascimento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="nascimento" precision="day"  value="${usuarioInstance?.nascimento}"  />
</div>

<g:render template="/endereco/form" model="[enderecoInstance:usuarioInstance.endereco]"/>