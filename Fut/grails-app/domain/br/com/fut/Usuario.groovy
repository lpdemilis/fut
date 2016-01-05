package br.com.fut

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

import groovy.time.TimeCategory;

class Usuario {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String nome
	String apelido
	String email
	Endereco endereco
	Date nascimento
	Date dataCadastro
	
	static hasMany = [telefones:String]

	static constraints = {
		username blank: false, unique: true, nullable: false
		password blank: false, password: true, nullable: false
		endereco nullable: true 
		email blank: false, email: true, nullable: false
		dataCadastro nullable: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Papel> getAuthorities() {
		UsuarioPapel.findAllByUsuario(this).collect { it.papel } as Set
	}

	def beforeInsert() {
		encodePassword()
		username = email
		dataCadastro = new Date()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
		username = email
		//dataCadastro = new Date()
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
	String toString() {
		nome
	}
	
	int getIdade(){
		def nowCalendar = Calendar.getInstance()
		
		def nascimentoCalendar = Calendar.getInstance()
		nascimentoCalendar.setTime(nascimento)
		
		
		def curYear = nowCalendar.get(Calendar.YEAR)
		def curMonth = nowCalendar.get(Calendar.MONTH)
		def curDay = nowCalendar.get(Calendar.DAY_OF_MONTH)
		
		def year = nascimentoCalendar.get(Calendar.YEAR)
		def month = nascimentoCalendar.get(Calendar.MONTH)
		def day = nascimentoCalendar.get(Calendar.DAY_OF_MONTH)
		
		def age = curYear - year
		
		if (curMonth < month || (month == curMonth && curDay < day)) {
			age--
		}
		
		age		
	} 
}
