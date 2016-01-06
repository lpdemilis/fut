package br.com.fut

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured

class EnderecoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	@Secured(['ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [enderecoInstanceList: Endereco.list(params), enderecoInstanceTotal: Endereco.count()]
    }

    def create() {
        [enderecoInstance: new Endereco(params)]
    }

    def save() {
        def enderecoInstance = new Endereco(params)
        if (!enderecoInstance.save(flush: true)) {
            render(view: "create", model: [enderecoInstance: enderecoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'endereco.label', default: 'Endereco'), enderecoInstance.id])
        redirect(action: "show", id: enderecoInstance.id)
    }

    def show(Long id) {
        def enderecoInstance = Endereco.get(id)
        if (!enderecoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'endereco.label', default: 'Endereco'), id])
            redirect(action: "list")
            return
        }

        [enderecoInstance: enderecoInstance]
    }

    def edit(Long id) {
        def enderecoInstance = Endereco.get(id)
        if (!enderecoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'endereco.label', default: 'Endereco'), id])
            redirect(action: "list")
            return
        }

        [enderecoInstance: enderecoInstance]
    }

    def update(Long id, Long version) {
        def enderecoInstance = Endereco.get(id)
        if (!enderecoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'endereco.label', default: 'Endereco'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (enderecoInstance.version > version) {
                enderecoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'endereco.label', default: 'Endereco')] as Object[],
                          "Another user has updated this Endereco while you were editing")
                render(view: "edit", model: [enderecoInstance: enderecoInstance])
                return
            }
        }

        enderecoInstance.properties = params

        if (!enderecoInstance.save(flush: true)) {
            render(view: "edit", model: [enderecoInstance: enderecoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'endereco.label', default: 'Endereco'), enderecoInstance.id])
        redirect(action: "show", id: enderecoInstance.id)
    }

    def delete(Long id) {
        def enderecoInstance = Endereco.get(id)
        if (!enderecoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'endereco.label', default: 'Endereco'), id])
            redirect(action: "list")
            return
        }

        try {
            enderecoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'endereco.label', default: 'Endereco'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'endereco.label', default: 'Endereco'), id])
            redirect(action: "show", id: id)
        }
    }
}
