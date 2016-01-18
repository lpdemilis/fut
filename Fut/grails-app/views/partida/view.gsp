
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
		
		<div id="partida">			
			<div id="times">
				<g:render template="/time/list" model="['partidaInstanceId':partidaInstance.id,'timeInstanceList':timeInstanceList, 'acoes':true]" />
			</div>
		</div>	
		
		<fieldset class="buttons">
			<g:formRemote name="myForm" url="[controller: 'partida', action: 'sortearTimes']" update="times">
				<g:hiddenField name="partidaInstanceId" value="${partidaInstance.id}"/>
				<g:actionSubmit class="create" value="Sortear Times" name="remover" />
			</g:formRemote>
		</fieldset>		
</body>
</html>
