package br.com.fut

import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class ConfirmacaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [confirmacaoInstanceList: Confirmacao.list(params), confirmacaoInstanceTotal: Confirmacao.count()]
    }

    def create() {
        [confirmacaoInstance: new Confirmacao(params)]
    }

    def save() {
        def confirmacaoInstance = new Confirmacao(params)
        if (!confirmacaoInstance.save(flush: true)) {
            render(view: "create", model: [confirmacaoInstance: confirmacaoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), confirmacaoInstance.id])
        redirect(action: "show", id: confirmacaoInstance.id)
    }

    def show(Long id) {
        def confirmacaoInstance = Confirmacao.get(id)
        if (!confirmacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), id])
            redirect(action: "list")
            return
        }

        [confirmacaoInstance: confirmacaoInstance]
    }

    def edit(Long id) {
        def confirmacaoInstance = Confirmacao.get(id)
        if (!confirmacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), id])
            redirect(action: "list")
            return
        }

        [confirmacaoInstance: confirmacaoInstance]
    }

    def update(Long id, Long version) {
        def confirmacaoInstance = Confirmacao.get(id)
        if (!confirmacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (confirmacaoInstance.version > version) {
                confirmacaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'confirmacao.label', default: 'Confirmacao')] as Object[],
                          "Another user has updated this Confirmacao while you were editing")
                render(view: "edit", model: [confirmacaoInstance: confirmacaoInstance])
                return
            }
        }

        confirmacaoInstance.properties = params

        if (!confirmacaoInstance.save(flush: true)) {
            render(view: "edit", model: [confirmacaoInstance: confirmacaoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), confirmacaoInstance.id])
        redirect(action: "show", id: confirmacaoInstance.id)
    }

    def delete(Long id) {
        def confirmacaoInstance = Confirmacao.get(id)
        if (!confirmacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), id])
            redirect(action: "list")
            return
        }

        try {
            confirmacaoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'confirmacao.label', default: 'Confirmacao'), id])
            redirect(action: "show", id: id)
        }
    }
			
	def confirmar(){			
		def usuarioInstance
		 
		if(params.usuarioid == null){
			usuarioInstance = springSecurityService.currentUser			
		}else{
			usuarioInstance = Usuario.get(params.usuarioid)			
		}
		
		def partidaInstance = Partida.get(params.partidaInstanceId)
		
		def confirmacaoInstance = Confirmacao.findByUsuarioAndPartida(usuarioInstance, partidaInstance)
		
		if(confirmacaoInstance != null && Boolean.valueOf(params.remover)){
			confirmacaoInstance.delete(flush: true)	
			for (time in partidaInstance.times) {
				if (time.usuarios.remove(usuarioInstance)){
					time.save(flush: true)
				}	
			}			
		}else{
			if(confirmacaoInstance == null){
				confirmacaoInstance = new Confirmacao()
			}
			
			confirmacaoInstance.usuario = usuarioInstance 
			confirmacaoInstance.partida = partidaInstance
			confirmacaoInstance.dataConfirmacao = new Date()
			confirmacaoInstance.confirmacao = Boolean.valueOf(params.confirmacao)
			confirmacaoInstance.motivo = params.motivo
					
			confirmacaoInstance.save(flush: true)
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
		
		ArrayList<Usuario> naoconfirmadosInstanceListAux = partidaInstance.grupo.membros
		ArrayList<Usuario> naoconfirmadosInstanceList = new ArrayList<Usuario>()
		ArrayList<Long> idsConfirmados = new ArrayList<Long>()
						
		for (naoconfirmadosInstance in naoconfirmadosInstanceListAux) {
			for (confirmacaoInstance1 in confirmacaoInstanceList) {				
				if (naoconfirmadosInstance.id == confirmacaoInstance1.usuario.id){					
					idsConfirmados.add(naoconfirmadosInstance.id)
				}
			}
			
			for (desconfirmacaoInstance in desconfirmacaoInstanceList) {				
				if (naoconfirmadosInstance.id == desconfirmacaoInstance.usuario.id){
					idsConfirmados.add(naoconfirmadosInstance.id)
				}
			}
			
			if (!idsConfirmados.contains(naoconfirmadosInstance.id)){
				naoconfirmadosInstanceList.add(naoconfirmadosInstance)
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
		
	    render(template: "confirmar", model: [confirmacaoInstanceList:confirmacaoInstanceList, desconfirmacaoInstanceList:desconfirmacaoInstanceList, naoconfirmadosInstanceList:naoconfirmadosInstanceList, partidaInstance:partidaInstance, timeInstanceList:partidaInstance.times.sort{ it.id }, incompleto:incompleto, desequilibrado:desequilibrado])		
	}	
}
