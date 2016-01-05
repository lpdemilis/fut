package br.com.fut

class Gol {
	
	Usuario usuario
	Usuario assistencia
	Boolean contra
	Date dataCriacao
	
	static belongsTo = [time:Time]
	
    static constraints = {
		usuario nullable:false 
		contra nullable:false 
		dataCriacao nullable:false 
    }
	
	def beforeInsert() {
		dataCriacao = new Date()
	}
}
