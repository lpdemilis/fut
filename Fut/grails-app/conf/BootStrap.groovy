import br.com.fut.Estado;
import br.com.fut.Pais;
import br.com.fut.TipoCampo;
import br.com.fut.Usuario;

class BootStrap {

    def init = { servletContext ->
/*
		//Criar usu�rios 		
		for (int i = 1; i <= 20; i++) {
			Usuario usuario = new Usuario()
			usuario.setNome("Usu\u00e1rio " + i)
			usuario.setEmail("usuario" + i + "@email.com")
			usuario.setPassword("usuario" + i)
			usuario.save()
		}
		
		//Tipo campo
		TipoCampo tipoCampo = new TipoCampo()
		tipoCampo.setTipo("Campo sint\u00e9tico")
		tipoCampo.save()
		
		tipoCampo = new TipoCampo()
		tipoCampo.setTipo("Campo grama")
		tipoCampo.save()
		
		tipoCampo = new TipoCampo()
		tipoCampo.setTipo("Quadra futsal")
		tipoCampo.save()
		
		//Pais
		Pais pais = new Pais()
		pais.setNome("Brasil")
		pais.setName("Brazil")
		pais.save()
		
/*		//Estados
		Estado estado = new Estado()
		estado.setNome("Acre")
				AC
		estado.setNome("Alagoas") 	AL
		estado.setNome("Amap�") 	AP
		estado.setNome("Amazonas") 	AM
		estado.setNome("Bahia") 	BA
		estado.setNome("Cear�") 	CE
		estado.setNome("Distrito Federal") 	DF
		estado.setNome("Esp�rito Santo") 	ES
		estado.setNome("Goi�s") 	GO
		estado.setNome("Maranh�o") 	MA
		estado.setNome("Mato Grosso") 	MT
		estado.setNome("Mato Grosso do Sul") 	MS
		estado.setNome("Minas Gerais") 	MG
		estado.setNome("Par�") 	PA
		estado.setNome("Para�ba") 	PB
		estado.setNome("Paran�") 	PR
		estado.setNome("Pernambuco") 	PE
		estado.setNome("estado.setNome("Piau� 	PI
		estado.setNome("Rio de Janeiro 	RJ
		estado.setNome("Rio Grande do Norte 	RN
		estado.setNome("Rio Grande do Sul 	RS
		estado.setNome("Rond�nia 	RO
		estado.setNome("estado.setNome("Roraima 	RR
		estado.setNome("Santa Catarina 	SC
		estado.setNome("estado.setNome("S�o Paulo 	SP
		estado.setNome("Sergipe 	SE
		estado.setNome("Tocantins 	TO
*/			
		
    }
    def destroy = {
    }
}
