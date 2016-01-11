
<%@ page import="br.com.fut.Partida" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'partida.label', default: 'Partida')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<g:javascript library="jquery"/>
	</head>
	<body>
		<a href="#show-partida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-partida" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list partida">
			
				<g:if test="${partidaInstance?.grupo}">
				<li class="fieldcontain">
					<span id="grupo-label" class="property-label"><g:message code="partida.grupo.label" default="Grupo" /></span>
					
						<span class="property-value" aria-labelledby="grupo-label"><g:link controller="grupo" action="show" id="${partidaInstance?.grupo?.id}">${partidaInstance?.grupo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
				<g:if test="${partidaInstance?.local}">
				<li class="fieldcontain">
					<span id="local-label" class="property-label"><g:message code="partida.local.label" default="Local" /></span>
					
						<span class="property-value" aria-labelledby="local-label"><g:link controller="local" action="show" id="${partidaInstance?.local?.id}">${partidaInstance?.local?.toHTML()}</g:link></span>
					
				</li>
				</g:if>
							
				<g:if test="${partidaInstance?.dataPartida}">
				<li class="fieldcontain">
					<span id="dataPartida-label" class="property-label"><g:message code="partida.dataPartida.label" default="Data Partida" /></span>
					
						<span class="property-value" aria-labelledby="dataPartida-label"><g:formatDate date="${partidaInstance?.dataPartida}" format="dd/MM/yyyy HH:mm:ss"/></span>
					
				</li>
				</g:if>
						
				<g:if test="${partidaInstance?.dataLimiteConfirmacao}">
				<li class="fieldcontain">
					<span id="dataLimiteConfirmacao-label" class="property-label"><g:message code="partida.dataLimiteConfirmacao.label" default="Data Limite Confirmacao" /></span>
					
						<span class="property-value" aria-labelledby="dataLimiteConfirmacao-label"><g:formatDate date="${partidaInstance?.dataLimiteConfirmacao}" format="dd/MM/yyyy HH:mm:ss"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${partidaInstance?.valor}">
				<li class="fieldcontain">
					<span id="valor-label" class="property-label"><g:message code="partida.valor.label" default="Valor" /></span>
					
						<span class="property-value" aria-labelledby="valor-label"><g:formatNumber number="${partidaInstance.valor}" type="currency" currencyCode="BRL" /></span>
					
				</li>
				</g:if>
																					
				<g:if test="${partidaInstance?.maximoJogadores}">
				<li class="fieldcontain">
					<span id="maximoJogadores-label" class="property-label"><g:message code="partida.maximoJogadores.label" default="Maximo Jogadores" /></span>
					
						<span class="property-value" aria-labelledby="maximoJogadores-label"><g:fieldValue bean="${partidaInstance}" field="maximoJogadores"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${partidaInstance?.minimoJogadores}">
				<li class="fieldcontain">
					<span id="minimoJogadores-label" class="property-label"><g:message code="partida.minimoJogadores.label" default="Minimo Jogadores" /></span>
					
						<span class="property-value" aria-labelledby="minimoJogadores-label"><g:fieldValue bean="${partidaInstance}" field="minimoJogadores"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${partidaInstance?.tipoCampo}">
				<li class="fieldcontain">
					<span id="tipoCampo-label" class="property-label"><g:message code="partida.tipoCampo.label" default="Tipo Campo" /></span>
					
						<span class="property-value" aria-labelledby="tipoCampo-label"><g:link controller="tipoCampo" action="show" id="${partidaInstance?.tipoCampo?.id}">${partidaInstance?.tipoCampo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${partidaInstance?.usuarioMarcador}">
				<li class="fieldcontain">
					<span id="usuarioMarcador-label" class="property-label"><g:message code="partida.usuarioMarcador.label" default="Usuario Marcador" /></span>
					
						<span class="property-value" aria-labelledby="usuarioMarcador-label"><g:link controller="usuario" action="show" id="${partidaInstance?.usuarioMarcador?.id}">${partidaInstance?.usuarioMarcador?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
				<g:if test="${partidaInstance?.dataCriacao}">
				<li class="fieldcontain">
					<span id="dataCriacao-label" class="property-label"><g:message code="partida.dataCriacao.label" default="Data Criacao" /></span>
					
						<span class="property-value" aria-labelledby="dataCriacao-label"><g:formatDate date="${partidaInstance?.dataCriacao}" format="dd/MM/yyyy HH:mm:ss" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${partidaInstance?.id}" />
					<g:link class="edit" action="edit" id="${partidaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
		
		<br>

		<div id="confirmar">
			<g:render template="/confirmacao/confirmar" model="['confirmacaoInstanceList':confirmacaoInstanceList, 'desconfirmacaoInstanceList':desconfirmacaoInstanceList, 'partidaInstance':partidaInstance]"></g:render>
		</div>

</body>
</html>
