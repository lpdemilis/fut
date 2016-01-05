package br.com.fut

class TipoCampo {
	
	String tipo

    static constraints = {
		tipo nullable: false
    }
	
	String toString() {
		tipo
	}
}
