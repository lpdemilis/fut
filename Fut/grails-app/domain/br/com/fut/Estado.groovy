package br.com.fut

class Estado {

	String nome
	String uf

	static belongsTo = [pais:Pais]

	static constraints = {
		nome nullable:false 
		uf nullable:false 
	}

	String toString() {
		nome
	}
}
