package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured

class PapelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	@Secured(['ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [papelInstanceList: Papel.list(params), papelInstanceTotal: Papel.count()]
    }

    def create() {
        [papelInstance: new Papel(params)]
    }

    def save() {
        def papelInstance = new Papel(params)
        if (!papelInstance.save(flush: true)) {
            render(view: "create", model: [papelInstance: papelInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'papel.label', default: 'Papel'), papelInstance.id])
        redirect(action: "show", id: papelInstance.id)
    }

    def show(Long id) {
        def papelInstance = Papel.get(id)
        if (!papelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'papel.label', default: 'Papel'), id])
            redirect(action: "list")
            return
        }

        [papelInstance: papelInstance]
    }

    def edit(Long id) {
        def papelInstance = Papel.get(id)
        if (!papelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'papel.label', default: 'Papel'), id])
            redirect(action: "list")
            return
        }

        [papelInstance: papelInstance]
    }

    def update(Long id, Long version) {
        def papelInstance = Papel.get(id)
        if (!papelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'papel.label', default: 'Papel'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (papelInstance.version > version) {
                papelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'papel.label', default: 'Papel')] as Object[],
                          "Another user has updated this Papel while you were editing")
                render(view: "edit", model: [papelInstance: papelInstance])
                return
            }
        }

        papelInstance.properties = params

        if (!papelInstance.save(flush: true)) {
            render(view: "edit", model: [papelInstance: papelInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'papel.label', default: 'Papel'), papelInstance.id])
        redirect(action: "show", id: papelInstance.id)
    }

    def delete(Long id) {
        def papelInstance = Papel.get(id)
        if (!papelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'papel.label', default: 'Papel'), id])
            redirect(action: "list")
            return
        }

        try {
            papelInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'papel.label', default: 'Papel'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'papel.label', default: 'Papel'), id])
            redirect(action: "show", id: id)
        }
    }
}
