package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class LocalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [localInstanceList: Local.list(params), localInstanceTotal: Local.count()]
    }

    def create() {
        [localInstance: new Local(params)]
    }

    def save() {
        def localInstance = new Local(params)
        if (!localInstance.save(flush: true)) {
            render(view: "create", model: [localInstance: localInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'local.label', default: 'Local'), localInstance.id])
        redirect(action: "show", id: localInstance.id)
    }

    def show(Long id) {
        def localInstance = Local.get(id)
        if (!localInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'local.label', default: 'Local'), id])
            redirect(action: "list")
            return
        }

        [localInstance: localInstance]
    }

    def edit(Long id) {
        def localInstance = Local.get(id)
        if (!localInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'local.label', default: 'Local'), id])
            redirect(action: "list")
            return
        }

        [localInstance: localInstance]
    }

    def update(Long id, Long version) {
        def localInstance = Local.get(id)
        if (!localInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'local.label', default: 'Local'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (localInstance.version > version) {
                localInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'local.label', default: 'Local')] as Object[],
                          "Another user has updated this Local while you were editing")
                render(view: "edit", model: [localInstance: localInstance])
                return
            }
        }

        localInstance.properties = params

        if (!localInstance.save(flush: true)) {
            render(view: "edit", model: [localInstance: localInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'local.label', default: 'Local'), localInstance.id])
        redirect(action: "show", id: localInstance.id)
    }

    def delete(Long id) {
        def localInstance = Local.get(id)
        if (!localInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'local.label', default: 'Local'), id])
            redirect(action: "list")
            return
        }

        try {
            localInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'local.label', default: 'Local'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'local.label', default: 'Local'), id])
            redirect(action: "show", id: id)
        }
    }
}
