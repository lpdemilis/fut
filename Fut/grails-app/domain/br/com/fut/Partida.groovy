package br.com.fut

import java.util.Formatter.DateTime

class Partida {
	
	Grupo grupo
	Local local
	Usuario usuarioMarcador
	
	Date dataLimiteConfirmacao
	Date dataPartida
	Date dataCriacao
	
	BigDecimal valor
	
	Integer minimoJogadores
	Integer maximoJogadores
	
	TipoCampo tipoCampo
	
	static hasMany = [times:Time]

    static constraints = {
		valor scale: 2
		grupo nullable: false
		local nullable:false
		usuarioMarcador nullable:false
		dataCriacao nullable:false 
		dataLimiteConfirmacao nullable:false
		dataPartida nullable:false
		minimoJogadores nullable:false
		tipoCampo nullable:false			 
    }
	
	def beforeInsert() {
		dataCriacao = new Date()
	}

	def beforeUpdate() {
		//dataCriacao = new Date()
	}
	
	def afterInsert() {
		times = new ArrayList<Time>()
		
		Time time = new Time()
		time.setNome("Time 1")
		time.save()
		times.add(time)		
		
		time = new Time()
		time.setNome("Time 2")
		time.save()
		times.add(time)
		
		this.save() 
	}
	
	String toString() {
		grupo.toString() + " " + dataPartida.toString() + " " + local.toString() 
	}
	
	Boolean verificarConfirmacao(id){
		PartidaController partidaController = new PartidaController()
		partidaController.verificarConfirmacao(id, this.id)		
	}
	
	Integer consultaGols(Long usuarioInstanceId, Boolean contra){
		PartidaController partidaController = new PartidaController()
		partidaController.consultaGols(usuarioInstanceId, contra, this.id)
	}
}
