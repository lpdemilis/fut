package br.com.fut

class Endereco {

    String logradouro
	String complemento
	Integer numero
	String bairro
	String cep
	Cidade cidade
	
    static constraints = {
		logradouro nullable:true
		complemento nullable:true
		numero nullable:true
		bairro nullable:true
		cep nullable:true
		cidade nullable:false	 
    }
	
	String toString() {
		logradouro + " " + numero + " " + complemento + " " + bairro + " " + cidade.toString() + "-" + cidade.estado.toString() + " " + cep 
	}
	
	String toHTML() {
		logradouro + ", " + numero + "<br>" + complemento + "<br>" + bairro + "<br>" + cidade.toString() + "-" + cidade.estado.uf + "<br>" + cep
	}
}
