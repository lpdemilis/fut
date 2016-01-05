package br.com.fut

class Local {
	
	String nome
	Endereco endereco
	static hasMany = [telefones:String]

    static constraints = {
		nome nullable:false		
    }
	
	String toString() {
		nome
	}
	
	String toHTML() {
		nome + "<br>" + endereco.toHTML()
	}
}
