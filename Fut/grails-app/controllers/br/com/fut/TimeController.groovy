package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class TimeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [timeInstanceList: Time.list(params), timeInstanceTotal: Time.count()]
    }

    def create() {
        [timeInstance: new Time(params)]
    }

    def save() {
        def timeInstance = new Time(params)
        if (!timeInstance.save(flush: true)) {
            render(view: "create", model: [timeInstance: timeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'time.label', default: 'Time'), timeInstance.id])
        redirect(action: "show", id: timeInstance.id)
    }

    def show(Long id) {
        def timeInstance = Time.get(id)
        if (!timeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'time.label', default: 'Time'), id])
            redirect(action: "list")
            return
        }

        [timeInstance: timeInstance]
    }

    def edit(Long id) {
        def timeInstance = Time.get(id)
        if (!timeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'time.label', default: 'Time'), id])
            redirect(action: "list")
            return
        }

        [timeInstance: timeInstance]
    }

    def update(Long id, Long version) {
        def timeInstance = Time.get(id)
        if (!timeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'time.label', default: 'Time'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (timeInstance.version > version) {
                timeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'time.label', default: 'Time')] as Object[],
                          "Another user has updated this Time while you were editing")
                render(view: "edit", model: [timeInstance: timeInstance])
                return
            }
        }

        timeInstance.properties = params

        if (!timeInstance.save(flush: true)) {
            render(view: "edit", model: [timeInstance: timeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'time.label', default: 'Time'), timeInstance.id])
        redirect(action: "show", id: timeInstance.id)
    }

    def delete(Long id) {
        def timeInstance = Time.get(id)
        if (!timeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'time.label', default: 'Time'), id])
            redirect(action: "list")
            return
        }

        try {
            timeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'time.label', default: 'Time'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'time.label', default: 'Time'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def marcarGol(){
		def usuarioInstance = Usuario.get(params.usuarioInstanceId)
		def partidaInstance = Partida.get(params.partidaInstanceId)
		def timeInstance
		
		if (Boolean.valueOf(params.contra)) {
			for (time in partidaInstance.times) {
				if (Long.valueOf(time.id) != Long.valueOf(params.timeInstanceId)){
					timeInstance = Time.get(time.id)
				}				
			}			
		}else{
			timeInstance = Time.get(params.timeInstanceId)
		}
				
		if (Boolean.valueOf(params.remover)){
			Gol golInstance = Gol.findByUsuarioAndTimeAndContra(usuarioInstance,timeInstance,Boolean.valueOf(params.contra),[max: 1, sort: "id", order: "desc"])
			golInstance.delete(flush: true)
		}else{
			Gol golInstance = new Gol()
			golInstance.usuario = usuarioInstance
			golInstance.time = timeInstance
			golInstance.contra = Boolean.valueOf(params.contra)
			golInstance.dataCriacao = new Date()
			golInstance.save(flush: true)
		}	
		
		PartidaController partidaController = new PartidaController()
		def confirmacaoInstanceList = partidaController.getConfirmacaoInstanceList(partidaInstance)
		def desconfirmacaoInstanceList = partidaController.getDesconfirmacaoInstanceList(partidaInstance)
		def naoconfirmadosInstanceList = partidaController.getNaoconfirmadosInstanceList(partidaInstance)
		
		render(template: "list",  model: [timeInstanceList:partidaInstance.times.sort{ it.id }, acoes:true, partidaInstanceId:partidaInstance.id, partidaInstance:partidaInstance, confirmacaoInstanceList: confirmacaoInstanceList, confirmacaoInstanceTotal: confirmacaoInstanceList.size, desconfirmacaoInstanceList: desconfirmacaoInstanceList, desconfirmacaoInstanceTotal: desconfirmacaoInstanceList.size, naoconfirmadosInstanceList:naoconfirmadosInstanceList.sort{ it.nome }, naoconfirmadosInstanceListTotal:naoconfirmadosInstanceList.count])
	}
	
	def trocarTime(){
		def usuarioInstance = Usuario.get(params.usuarioInstanceId)
		def partidaInstance = Partida.get(params.partidaInstanceId)
		def timeInstance = Time.get(params.timeInstanceId)
		
		timeInstance.usuarios.remove(usuarioInstance)
		
		for (time in partidaInstance.times) {
			if (Long.valueOf(time.id) != Long.valueOf(params.timeInstanceId)){
				timeInstance = Time.get(time.id)
				timeInstance.usuarios.add(usuarioInstance)
			}
		}
		
		partidaInstance.save(flush: true)
		
		def usuarios = 0		
		def desequilibrado = false
		def time_usuarios = 0
		
		for (time in partidaInstance.times) {
			usuarios += time.usuarios.size()
			if ((Math.abs(time_usuarios - time.usuarios.size()) > 1) && time_usuarios != 0) {
				desequilibrado = true
			}
			
			time_usuarios = time.usuarios.size()
		}
		
		PartidaController partidaController = new PartidaController()
		def confirmacaoInstanceList = partidaController.getConfirmacaoInstanceList(partidaInstance)
		def desconfirmacaoInstanceList = partidaController.getDesconfirmacaoInstanceList(partidaInstance)
		def naoconfirmadosInstanceList = partidaController.getNaoconfirmadosInstanceList(partidaInstance)
				
		render(template: "list",  model: [timeInstanceList:partidaInstance.times.sort{ it.id }, acoes:true, partidaInstanceId:partidaInstance.id, partidaInstance:partidaInstance, desequilibrado:desequilibrado, confirmacaoInstanceList: confirmacaoInstanceList, confirmacaoInstanceTotal: confirmacaoInstanceList.size, desconfirmacaoInstanceList: desconfirmacaoInstanceList, desconfirmacaoInstanceTotal: desconfirmacaoInstanceList.size, naoconfirmadosInstanceList:naoconfirmadosInstanceList.sort{ it.nome }, naoconfirmadosInstanceListTotal:naoconfirmadosInstanceList.count])
	}
	
	def removerJogador(){
		def usuarioInstance = Usuario.get(params.usuarioInstanceId)
		def partidaInstance = Partida.get(params.partidaInstanceId)
		def timeInstance = Time.get(params.timeInstanceId)
		
		timeInstance.usuarios.remove(usuarioInstance)
						
		partidaInstance.save(flush: true)
		
		def usuarios = 0
		def desequilibrado = false
		def time_usuarios = 0
		
		for (time in partidaInstance.times) {
			usuarios += time.usuarios.size()
			if ((Math.abs(time_usuarios - time.usuarios.size()) > 1) && time_usuarios != 0) {
				desequilibrado = true
			}
			
			time_usuarios = time.usuarios.size()
		}
		
		PartidaController partidaController = new PartidaController()
		def confirmacaoInstanceList = partidaController.getConfirmacaoInstanceList(partidaInstance)
		def desconfirmacaoInstanceList = partidaController.getDesconfirmacaoInstanceList(partidaInstance)
		def naoconfirmadosInstanceList = partidaController.getNaoconfirmadosInstanceList(partidaInstance)
				
		render(template: "list",  model: [timeInstanceList:partidaInstance.times.sort{ it.id }, acoes:true, partidaInstanceId:partidaInstance.id, partidaInstance:partidaInstance, desequilibrado:desequilibrado, confirmacaoInstanceList: confirmacaoInstanceList, confirmacaoInstanceTotal: confirmacaoInstanceList.size, desconfirmacaoInstanceList: desconfirmacaoInstanceList, desconfirmacaoInstanceTotal: desconfirmacaoInstanceList.size, naoconfirmadosInstanceList:naoconfirmadosInstanceList.sort{ it.nome }, naoconfirmadosInstanceListTotal:naoconfirmadosInstanceList.count])
	}
	
	def adicionarJogador(){
		def usuarioInstance = Usuario.get(params.usuarioInstanceId)
		def partidaInstance = Partida.get(params.partidaInstanceId)
		Time timeInstance
		if(params.time.toString() == "1"){
			timeInstance = partidaInstance.times.sort{ it.id }.toArray()[0]
		}else{
			timeInstance = partidaInstance.times.sort{ it.id }.toArray()[1]
		}
		
		timeInstance.usuarios.add(usuarioInstance)
						
		partidaInstance.save(flush: true)
		
		def usuarios = 0
		def desequilibrado = false
		def time_usuarios = 0
		
		for (time in partidaInstance.times) {
			usuarios += time.usuarios.size()
			if ((Math.abs(time_usuarios - time.usuarios.size()) > 1) && time_usuarios != 0) {
				desequilibrado = true
			}
			
			time_usuarios = time.usuarios.size()
		}
		
		PartidaController partidaController = new PartidaController()
		def confirmacaoInstanceList = partidaController.getConfirmacaoInstanceList(partidaInstance)
		def desconfirmacaoInstanceList = partidaController.getDesconfirmacaoInstanceList(partidaInstance)
		def naoconfirmadosInstanceList = partidaController.getNaoconfirmadosInstanceList(partidaInstance)
				
		render(template: "list",  model: [timeInstanceList:partidaInstance.times.sort{ it.id }, acoes:true, partidaInstanceId:partidaInstance.id, partidaInstance:partidaInstance, desequilibrado:desequilibrado, confirmacaoInstanceList: confirmacaoInstanceList, confirmacaoInstanceTotal: confirmacaoInstanceList.size, desconfirmacaoInstanceList: desconfirmacaoInstanceList, desconfirmacaoInstanceTotal: desconfirmacaoInstanceList.size, naoconfirmadosInstanceList:naoconfirmadosInstanceList.sort{ it.nome }, naoconfirmadosInstanceListTotal:naoconfirmadosInstanceList.count])
	}
}
