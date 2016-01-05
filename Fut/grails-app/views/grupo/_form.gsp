<%@ page import="br.com.fut.Grupo" %>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="grupo.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${grupoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="grupo.descricao.label" default="Descrição" />
		
	</label>
	<g:textArea name="descricao" value="${grupoInstance?.descricao}"/>
</div>

<%--<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'administradores', 'error')} ">--%>
<%--	<label for="administradores">--%>
<%--		<g:message code="grupo.administradores.label" default="Administradores" />--%>
<%--		--%>
<%--	</label>--%>
<%--	<g:select name="administradores" from="${br.com.fut.Usuario.list()}" multiple="multiple" optionKey="id" size="5" value="${grupoInstance?.administradores*.id}" class="many-to-many"/>--%>
<%--</div>--%>

<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'dataCriacao', 'error')} required" hidden="true">
	<label for="dataCriacao">
		<g:message code="grupo.dataCriacao.label" default="Data Criacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataCriacao" precision="day"  value="${grupoInstance?.dataCriacao}"  />
</div>

<%--<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'membros', 'error')} ">--%>
<%--	<label for="membros">--%>
<%--		<g:message code="grupo.membros.label" default="Membros" />--%>
<%--		--%>
<%--	</label>--%>
<%--	<g:select name="membros" from="${br.com.fut.Usuario.list()}" multiple="multiple" optionKey="id" size="5" value="${grupoInstance?.membros*.id}" class="many-to-many"/>--%>
<%--</div>--%>

<%--<div class="fieldcontain ${hasErrors(bean: grupoInstance, field: 'presidente', 'error')} required">--%>
<%--	<label for="presidente">--%>
<%--		<g:message code="grupo.presidente.label" default="Presidente" />--%>
<%--		<span class="required-indicator">*</span>--%>
<%--	</label>--%>
<%--	<g:select id="presidente" name="presidente.id" from="${br.com.fut.Usuario.list()}" optionKey="id" required="" value="${grupoInstance?.presidente?.id}" class="many-to-one"/>--%>
<%--</div>--%>

<%--	<g:hiddenField id="presidente" name="presidente.id" value="${sec.loggedInUserInfo(field:'id')}"/>--%>
	

