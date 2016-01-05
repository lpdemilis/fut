<%--			<g:if test="${flash.message}">--%>
<%--			<div class="message" role="status">${flash.message}</div>--%>
<%--			</g:if>--%>
			
				<g:if test="${enderecoInstance?.cidade}">
				<li class="fieldcontain">
					<span id="cidade-label" class="property-label"><g:message code="endereco.cidade.label" default="Cidade" /></span>
					
						<span class="property-value" aria-labelledby="cidade-label"><g:link controller="cidade" action="show" id="${enderecoInstance?.cidade?.id}">${enderecoInstance?.cidade?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
				<g:if test="${enderecoInstance?.cidade?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="endereco.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="estado" action="show" id="${enderecoInstance?.cidade?.estado?.id}">${enderecoInstance?.cidade?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
				<g:if test="${enderecoInstance?.logradouro}">
				<li class="fieldcontain">
					<span id="logradouro-label" class="property-label"><g:message code="endereco.logradouro.label" default="Endereço" /></span>
					
						<span class="property-value" aria-labelledby="logradouro-label"><g:fieldValue bean="${enderecoInstance}" field="logradouro"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${enderecoInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="endereco.numero.label" default="Número" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${enderecoInstance}" field="numero"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${enderecoInstance?.complemento}">
				<li class="fieldcontain">
					<span id="complemento-label" class="property-label"><g:message code="endereco.complemento.label" default="Complemento" /></span>
					
						<span class="property-value" aria-labelledby="complemento-label"><g:fieldValue bean="${enderecoInstance}" field="complemento"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${enderecoInstance?.bairro}">
				<li class="fieldcontain">
					<span id="bairro-label" class="property-label"><g:message code="endereco.bairro.label" default="Bairro" /></span>
					
						<span class="property-value" aria-labelledby="bairro-label"><g:fieldValue bean="${enderecoInstance}" field="bairro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${enderecoInstance?.cep}">
				<li class="fieldcontain">
					<span id="cep-label" class="property-label"><g:message code="endereco.cep.label" default="CEP" /></span>
					
						<span class="property-value" aria-labelledby="cep-label"><g:fieldValue bean="${enderecoInstance}" field="cep"/></span>
					
				</li>
				</g:if>
											
				
			
