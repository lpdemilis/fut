package br.com.fut

class Time {
	
	String nome
	
	static hasMany = [usuarios:Usuario, gols:Gol]
	
    static constraints = {
		nome blank:false, nullable: false
    }
}
