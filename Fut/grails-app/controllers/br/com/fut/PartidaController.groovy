package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class PartidaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
        redirect(action: "list", params: params)
    }
	
	def springSecurityService

    def list(Integer max) {
		
		def usuario = springSecurityService.currentUser
		
		def partidaCriteria = Partida.createCriteria()
		def partidaInstanceList = partidaCriteria.list(max: params.max?:10, offset: params.offset?:0){
			and {
				grupo{
					membros{
						eq('id', usuario.id)
					}
				}
				
				gt("dataPartida", new Date())
			}			
		}
        
        [partidaInstanceList: partidaInstanceList, partidaInstanceTotal: partidaInstanceList.size()]
    }

    def create() {
        [partidaInstance: new Partida(params)]
    }

    def save() {
        def partidaInstance = new Partida(params)
        if (!partidaInstance.save(flush: true)) {
            render(view: "create", model: [partidaInstance: partidaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'partida.label', default: 'Partida'), partidaInstance.id])
        redirect(action: "show", id: partidaInstance.id)
    }

    def show(Long id) {
        def partidaInstance = Partida.get(id)
        if (!partidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'partida.label', default: 'Partida'), id])
            redirect(action: "list")
            return
        }
		
				
		def confirmacaoCriteria = Confirmacao.createCriteria()
		def confirmacaoInstanceList = confirmacaoCriteria.list(){
			and {
				eq('partida', partidaInstance)
				eq('confirmacao', true)
			}
		}
		
		def desconfirmacaoCriteria = Confirmacao.createCriteria()
		def desconfirmacaoInstanceList = desconfirmacaoCriteria.list(){
			and {
				eq('partida', partidaInstance)
				eq('confirmacao', false)
			}
		}
		
		def usuarios = 0
		def incompleto = false
		def desequilibrado = false
		def time_usuarios = 0
		
		for (time in partidaInstance.times) {
			usuarios += time.usuarios.size()
			if ((Math.abs(time_usuarios - time.usuarios.size()) > 1) && time_usuarios != 0) {
				desequilibrado = true
			}
			
			time_usuarios = time.usuarios.size()
		}
		
		if (usuarios != confirmacaoInstanceList.size()){
			incompleto = true
		}		 
						
        [partidaInstance: partidaInstance, confirmacaoInstanceList: confirmacaoInstanceList, confirmacaoInstanceTotal: confirmacaoInstanceList.size, desconfirmacaoInstanceList: desconfirmacaoInstanceList, desconfirmacaoInstanceTotal: desconfirmacaoInstanceList.size, timeInstanceList: partidaInstance.times.sort{ it.id }, timeInstanceTotal: partidaInstance.times.size(), incompleto:incompleto, desequilibrado:desequilibrado]
    }

    def edit(Long id) {
        def partidaInstance = Partida.get(id)
        if (!partidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'partida.label', default: 'Partida'), id])
            redirect(action: "list")
            return
        }

        [partidaInstance: partidaInstance]
    }

    def update(Long id, Long version) {
        def partidaInstance = Partida.get(id)
        if (!partidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'partida.label', default: 'Partida'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (partidaInstance.version > version) {
                partidaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'partida.label', default: 'Partida')] as Object[],
                          "Another user has updated this Partida while you were editing")
                render(view: "edit", model: [partidaInstance: partidaInstance])
                return
            }
        }

        partidaInstance.properties = params

        if (!partidaInstance.save(flush: true)) {
            render(view: "edit", model: [partidaInstance: partidaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'partida.label', default: 'Partida'), partidaInstance.id])
        redirect(action: "show", id: partidaInstance.id)
    }

    def delete(Long id) {
        def partidaInstance = Partida.get(id)
        if (!partidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'partida.label', default: 'Partida'), id])
            redirect(action: "list")
            return
        }

        try {
            partidaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'partida.label', default: 'Partida'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'partida.label', default: 'Partida'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def sortearTimes() {
		def partidaInstance = Partida.get(params.partidaInstanceId)
						
		for (time in partidaInstance.times) {
			time.usuarios = new ArrayList<Usuario>()
		}
		
		partidaInstance.save()
		
		def confirmacaoCriteria = Confirmacao.createCriteria()
		def confirmacaoInstanceList = confirmacaoCriteria.list(){
			and {
				eq('partida', partidaInstance)
				eq('confirmacao', true)
			}
		}
		
		Collections.shuffle(confirmacaoInstanceList)
		
		ArrayList<Time> times = new ArrayList<Time>()
		
		for (int i = 0; i < partidaInstance.times.size(); i++) {
			Time time = new Time()
			time.usuarios = new ArrayList<Usuario>()
			times.add(time)
		}
						
		for (int i = 0; i < confirmacaoInstanceList.size(); i++) {
			times.get(i % partidaInstance.times.size()).usuarios.add(confirmacaoInstanceList.get(i).usuario)
		}
		
		int i = 0
		for (time in partidaInstance.times) {
			time.usuarios = times.get(i).usuarios
			i++
		}
		
		partidaInstance.save()
		
		def usuarios = 0
		def incompleto = false
		def desequilibrado = false
		def time_usuarios = 0
		
		for (time in partidaInstance.times) {
			usuarios += time.usuarios.size()
			
			if ((Math.abs(time_usuarios - time.usuarios.size()) > 1) && time_usuarios != 0) {
				desequilibrado = true
			}
			
			time_usuarios = time.usuarios.size()			
		}
		
		if (usuarios != confirmacaoInstanceList.size()){
			incompleto = true
		}
								
		render(template: "/time/list", model: [timeInstanceList:partidaInstance.times.sort{ it.id }, incompleto:incompleto, desequilibrado:desequilibrado])
	}
			
	def verificarConfirmacao(Long usuarioid, Long partidaid){
					
		def usuarioInstance
		def partidaInstance = Partida.get(partidaid)
		def confirmacaoInstance
		
		if(usuarioid == null){
			usuarioInstance = springSecurityService.currentUser			
		}else{
			usuarioInstance = Usuario.get(usuarioid)			
		}	
		
		confirmacaoInstance = Confirmacao.findByUsuarioAndPartida(usuarioInstance, partidaInstance)
		
		return confirmacaoInstance?.confirmacao  
	}	
}
