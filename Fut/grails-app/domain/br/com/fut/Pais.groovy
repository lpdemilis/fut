package br.com.fut

class Pais {

	String nome
	String name

	static hasMany = [estados:Estado]

	static constraints = {
		nome nullable:false 
		name nullable:false 
	}

	String toString() {
		nome
	}
}
