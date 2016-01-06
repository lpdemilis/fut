package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured


@Secured(['ROLE_USER'])
class TipoCampoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoCampoInstanceList: TipoCampo.list(params), tipoCampoInstanceTotal: TipoCampo.count()]
    }

    def create() {
        [tipoCampoInstance: new TipoCampo(params)]
    }

    def save() {
        def tipoCampoInstance = new TipoCampo(params)
        if (!tipoCampoInstance.save(flush: true)) {
            render(view: "create", model: [tipoCampoInstance: tipoCampoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), tipoCampoInstance.id])
        redirect(action: "show", id: tipoCampoInstance.id)
    }

    def show(Long id) {
        def tipoCampoInstance = TipoCampo.get(id)
        if (!tipoCampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), id])
            redirect(action: "list")
            return
        }

        [tipoCampoInstance: tipoCampoInstance]
    }

    def edit(Long id) {
        def tipoCampoInstance = TipoCampo.get(id)
        if (!tipoCampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), id])
            redirect(action: "list")
            return
        }

        [tipoCampoInstance: tipoCampoInstance]
    }

    def update(Long id, Long version) {
        def tipoCampoInstance = TipoCampo.get(id)
        if (!tipoCampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoCampoInstance.version > version) {
                tipoCampoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoCampo.label', default: 'TipoCampo')] as Object[],
                          "Another user has updated this TipoCampo while you were editing")
                render(view: "edit", model: [tipoCampoInstance: tipoCampoInstance])
                return
            }
        }

        tipoCampoInstance.properties = params

        if (!tipoCampoInstance.save(flush: true)) {
            render(view: "edit", model: [tipoCampoInstance: tipoCampoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), tipoCampoInstance.id])
        redirect(action: "show", id: tipoCampoInstance.id)
    }

    def delete(Long id) {
        def tipoCampoInstance = TipoCampo.get(id)
        if (!tipoCampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), id])
            redirect(action: "list")
            return
        }

        try {
            tipoCampoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoCampo.label', default: 'TipoCampo'), id])
            redirect(action: "show", id: id)
        }
    }
}
