package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured

class ConfirmacaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	//@Secured(['ROLE_USER'])
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
}
