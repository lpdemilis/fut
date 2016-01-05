package br.com.fut

import java.util.Formatter.DateTime

class Grupo {
	
	String nome
	String descricao
	Usuario presidente
	Date dataCriacao
	 
	static hasMany = [administradores:Usuario, membros:Usuario]

    static constraints = {
		nome nullable:false
		presidente nullable:false 
		dataCriacao nullable:false 
    }
	
	def beforeInsert() {
		dataCriacao = new Date()
	}

	def beforeUpdate() {
		//dataCriacao = new Date()
	}
	
	String toString() {
		nome
	}
}
