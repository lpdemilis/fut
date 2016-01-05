			<ol class="property-list local">	
				<g:if test="${localInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="local.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${localInstance}" field="nome"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${localInstance?.endereco}">
				<li class="fieldcontain">
					<span id="endereco-label" class="property-label"><g:message code="local.endereco.label" default="Endereco" /></span>
					
						<span class="property-value" aria-labelledby="endereco-label"><g:link controller="endereco" action="show" id="${localInstance?.endereco?.id}">${localInstance?.endereco?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
						
				<g:if test="${localInstance?.telefones}">
				<li class="fieldcontain">
					<span id="telefones-label" class="property-label"><g:message code="local.telefones.label" default="Telefones" /></span>
					
						<span class="property-value" aria-labelledby="telefones-label"><g:fieldValue bean="${localInstance}" field="telefones"/></span>
					
				</li>
				</g:if>
			</ol>	