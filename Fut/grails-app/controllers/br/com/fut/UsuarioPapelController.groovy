package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured

class UsuarioPapelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	@Secured(['ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [usuarioPapelInstanceList: UsuarioPapel.list(params), usuarioPapelInstanceTotal: UsuarioPapel.count()]
    }

    def create() {
        [usuarioPapelInstance: new UsuarioPapel(params)]
    }

    def save() {
        def usuarioPapelInstance = new UsuarioPapel(params)
        if (!usuarioPapelInstance.save(flush: true)) {
            render(view: "create", model: [usuarioPapelInstance: usuarioPapelInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapelInstance.id])
        redirect(action: "show", id: usuarioPapelInstance.id)
    }

    def show(Long id) {
        def usuarioPapelInstance = UsuarioPapel.get(id)
        if (!usuarioPapelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
            redirect(action: "list")
            return
        }

        [usuarioPapelInstance: usuarioPapelInstance]
    }

    def edit(Long id) {
        def usuarioPapelInstance = UsuarioPapel.get(id)
        if (!usuarioPapelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
            redirect(action: "list")
            return
        }

        [usuarioPapelInstance: usuarioPapelInstance]
    }

    def update(Long id, Long version) {
        def usuarioPapelInstance = UsuarioPapel.get(id)
        if (!usuarioPapelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (usuarioPapelInstance.version > version) {
                usuarioPapelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'usuarioPapel.label', default: 'UsuarioPapel')] as Object[],
                          "Another user has updated this UsuarioPapel while you were editing")
                render(view: "edit", model: [usuarioPapelInstance: usuarioPapelInstance])
                return
            }
        }

        usuarioPapelInstance.properties = params

        if (!usuarioPapelInstance.save(flush: true)) {
            render(view: "edit", model: [usuarioPapelInstance: usuarioPapelInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapelInstance.id])
        redirect(action: "show", id: usuarioPapelInstance.id)
    }

    def delete(Long id) {
        def usuarioPapelInstance = UsuarioPapel.get(id)
        if (!usuarioPapelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
            redirect(action: "list")
            return
        }

        try {
            usuarioPapelInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), id])
            redirect(action: "show", id: id)
        }
    }
}
