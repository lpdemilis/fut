package br.com.fut

class Gol {
	
	Usuario usuario
	Usuario assistencia
	Boolean contra
	Date dataCriacao
	Time time
	
    static constraints = {
		usuario nullable:false 
		contra nullable:false 
		dataCriacao nullable:false
		assistencia nullable:true  
    }
	
	def beforeInsert() {
		dataCriacao = new Date()
	}
}
