package br.com.fut

class Cartao {
	
	Usuario usuario
	Boolean vermelho
	Boolean amarelo
	Date dataCriacao
	
	static belongsTo = [time:Time]
	
    static constraints = {
		usuario nullable:false 
		vermelho nullable:false 
		amarelo nullable:false 
		dataCriacao nullable:false 
    }
	
	def beforeInsert() {
		dataCriacao = new Date()
	}
}
