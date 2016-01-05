import br.com.fut.Estado;
import br.com.fut.Pais;
import br.com.fut.TipoCampo;
import br.com.fut.Usuario;

class BootStrap {

    def init = { servletContext ->
/*
		//Criar usuários 		
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
		estado.setNome("Amapá") 	AP
		estado.setNome("Amazonas") 	AM
		estado.setNome("Bahia") 	BA
		estado.setNome("Ceará") 	CE
		estado.setNome("Distrito Federal") 	DF
		estado.setNome("Espírito Santo") 	ES
		estado.setNome("Goiás") 	GO
		estado.setNome("Maranhão") 	MA
		estado.setNome("Mato Grosso") 	MT
		estado.setNome("Mato Grosso do Sul") 	MS
		estado.setNome("Minas Gerais") 	MG
		estado.setNome("Pará") 	PA
		estado.setNome("Paraíba") 	PB
		estado.setNome("Paraná") 	PR
		estado.setNome("Pernambuco") 	PE
		estado.setNome("estado.setNome("Piauí 	PI
		estado.setNome("Rio de Janeiro 	RJ
		estado.setNome("Rio Grande do Norte 	RN
		estado.setNome("Rio Grande do Sul 	RS
		estado.setNome("Rondônia 	RO
		estado.setNome("estado.setNome("Roraima 	RR
		estado.setNome("Santa Catarina 	SC
		estado.setNome("estado.setNome("São Paulo 	SP
		estado.setNome("Sergipe 	SE
		estado.setNome("Tocantins 	TO
*/			
		
    }
    def destroy = {
    }
}
