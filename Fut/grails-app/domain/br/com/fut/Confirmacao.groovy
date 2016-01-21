package br.com.fut

class Confirmacao {
	
	Usuario usuario
	Partida partida
	 
	Date dataConfirmacao
	Boolean confirmacao
	String motivo

    static constraints = {
		usuario nullable: false
		partida nullable: false
		dataConfirmacao nullable:false 
		confirmacao nullable:false
		motivo nullable:true   		 
    }
	
	def beforeInsert() {
		dataConfirmacao = new Date()
	}

	def beforeUpdate() {
		dataConfirmacao = new Date()
	}

	
	String toString() {
		usuario.toString() + " (" + dataConfirmacao.toString() + ")"
	}	
}
