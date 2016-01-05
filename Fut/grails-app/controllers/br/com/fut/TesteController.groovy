package br.com.fut

import org.springframework.dao.DataIntegrityViolationException

class TesteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [testeInstanceList: Teste.list(params), testeInstanceTotal: Teste.count()]
    }

    def create() {
        [testeInstance: new Teste(params)]
    }

    def save() {
        def testeInstance = new Teste(params)
        if (!testeInstance.save(flush: true)) {
            render(view: "create", model: [testeInstance: testeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'teste.label', default: 'Teste'), testeInstance.id])
        redirect(action: "show", id: testeInstance.id)
    }

    def show(Long id) {
        def testeInstance = Teste.get(id)
        if (!testeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'teste.label', default: 'Teste'), id])
            redirect(action: "list")
            return
        }

        [testeInstance: testeInstance]
    }

    def edit(Long id) {
        def testeInstance = Teste.get(id)
        if (!testeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'teste.label', default: 'Teste'), id])
            redirect(action: "list")
            return
        }

        [testeInstance: testeInstance]
    }

    def update(Long id, Long version) {
        def testeInstance = Teste.get(id)
        if (!testeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'teste.label', default: 'Teste'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (testeInstance.version > version) {
                testeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'teste.label', default: 'Teste')] as Object[],
                          "Another user has updated this Teste while you were editing")
                render(view: "edit", model: [testeInstance: testeInstance])
                return
            }
        }

        testeInstance.properties = params

        if (!testeInstance.save(flush: true)) {
            render(view: "edit", model: [testeInstance: testeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'teste.label', default: 'Teste'), testeInstance.id])
        redirect(action: "show", id: testeInstance.id)
    }

    def delete(Long id) {
        def testeInstance = Teste.get(id)
        if (!testeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'teste.label', default: 'Teste'), id])
            redirect(action: "list")
            return
        }

        try {
            testeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'teste.label', default: 'Teste'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'teste.label', default: 'Teste'), id])
            redirect(action: "show", id: id)
        }
    }
}
