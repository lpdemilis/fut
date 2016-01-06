package br.com.fut


class LoginTagLib {
	def springSecurityService
	
	def loginControl = {
		if(springSecurityService.isLoggedIn()){
			out << "${springSecurityService.currentUser} "
			out << """[${link(action:"index", controller:"logout"){"Logout"}}]"""
		} else {
		 	//out << """[${link(action:"index", controller:"usuario"){"Login"}}]"""
			out << """[${link(action:"auth", controller:"login"){"Login"}}]"""
		}
	}
}
