<%@ page import="br.com.fut.FacebookUser" %>



<div class="fieldcontain ${hasErrors(bean: facebookUserInstance, field: 'uid', 'error')} required">
	<label for="uid">
		<g:message code="facebookUser.uid.label" default="Uid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="uid" type="number" value="${facebookUserInstance.uid}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: facebookUserInstance, field: 'accessToken', 'error')} ">
	<label for="accessToken">
		<g:message code="facebookUser.accessToken.label" default="Access Token" />
		
	</label>
	<g:textField name="accessToken" value="${facebookUserInstance?.accessToken}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facebookUserInstance, field: 'accessTokenExpires', 'error')} required">
	<label for="accessTokenExpires">
		<g:message code="facebookUser.accessTokenExpires.label" default="Access Token Expires" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="accessTokenExpires" precision="day"  value="${facebookUserInstance?.accessTokenExpires}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: facebookUserInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="facebookUser.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${br.com.fut.Usuario.list()}" optionKey="id" required="" value="${facebookUserInstance?.user?.id}" class="many-to-one"/>
</div>

