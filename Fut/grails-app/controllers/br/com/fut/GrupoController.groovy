package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class GrupoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
        redirect(action: "list", params: params)
    }
	
	def springSecurityService

	def list(Integer max) {
		def usuario = springSecurityService.currentUser

		def grupoCriteria = Grupo.createCriteria()
		def grupoInstanceList = grupoCriteria.list(max: params.max?:10, offset: params.offset?:0){
			if(params.grupos != 'todos'){
				membros{
					eq('id', usuario?.id)
				}
			}
		}
		[grupoInstanceList: grupoInstanceList, grupoInstanceTotal: grupoInstanceList.size()]
	}

    def create() {
        [grupoInstance: new Grupo(params)]
    }

    def save() {
        def grupoInstance = new Grupo(params)
						
		def usuario = springSecurityService.currentUser
		
		grupoInstance.presidente = usuario
		grupoInstance.membros = new ArrayList<Usuario>()
		grupoInstance.membros.add(usuario)
		grupoInstance.administradores = new ArrayList<Usuario>()
		grupoInstance.administradores.add(usuario)
		
        if (!grupoInstance.save(flush: true)) {
            render(view: "create", model: [grupoInstance: grupoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupoInstance.id])
        redirect(action: "show", id: grupoInstance.id)
    }

    def show(Long id) {
        def grupoInstance = Grupo.get(id)
        if (!grupoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
            redirect(action: "list")
            return
        }
		
		def usuarioInstanceList
		
		usuarioInstanceList = grupoInstance.membros
		
		def partidaCriteria = Partida.createCriteria()
		def partidaInstanceList = partidaCriteria.list(max: params.max?:10, offset: params.offset?:0){
			and {
				grupo{
					eq('id', id)
				}
				
				gt("dataPartida", new Date())
			}			
		} 
				
        [grupoInstance: grupoInstance, usuarioInstanceList: usuarioInstanceList, usuarioInstanceTotal: usuarioInstanceList.size(), partidaInstanceList: partidaInstanceList, partidaInstanceTotal: partidaInstanceList.size()]
    }

    def edit(Long id) {
        def grupoInstance = Grupo.get(id)
        if (!grupoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
            redirect(action: "list")
            return
        }

        [grupoInstance: grupoInstance]
    }

    def update(Long id, Long version) {
        def grupoInstance = Grupo.get(id)
        if (!grupoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (grupoInstance.version > version) {
                grupoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'grupo.label', default: 'Grupo')] as Object[],
                          "Another user has updated this Grupo while you were editing")
                render(view: "edit", model: [grupoInstance: grupoInstance])
                return
            }
        }

        grupoInstance.properties = params

        if (!grupoInstance.save(flush: true)) {
            render(view: "edit", model: [grupoInstance: grupoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'grupo.label', default: 'Grupo'), grupoInstance.id])
        redirect(action: "show", id: grupoInstance.id)
    }

    def delete(Long id) {
        def grupoInstance = Grupo.get(id)
        if (!grupoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
            redirect(action: "list")
            return
        }

        try {
            grupoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'grupo.label', default: 'Grupo'), id])
            redirect(action: "show", id: id)
        }
    }
}
