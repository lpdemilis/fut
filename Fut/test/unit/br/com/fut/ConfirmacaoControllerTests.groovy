package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(ConfirmacaoController)
@Mock(Confirmacao)
class ConfirmacaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/confirmacao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.confirmacaoInstanceList.size() == 0
        assert model.confirmacaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.confirmacaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.confirmacaoInstance != null
        assert view == '/confirmacao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/confirmacao/show/1'
        assert controller.flash.message != null
        assert Confirmacao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/confirmacao/list'

        populateValidParams(params)
        def confirmacao = new Confirmacao(params)

        assert confirmacao.save() != null

        params.id = confirmacao.id

        def model = controller.show()

        assert model.confirmacaoInstance == confirmacao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/confirmacao/list'

        populateValidParams(params)
        def confirmacao = new Confirmacao(params)

        assert confirmacao.save() != null

        params.id = confirmacao.id

        def model = controller.edit()

        assert model.confirmacaoInstance == confirmacao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/confirmacao/list'

        response.reset()

        populateValidParams(params)
        def confirmacao = new Confirmacao(params)

        assert confirmacao.save() != null

        // test invalid parameters in update
        params.id = confirmacao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/confirmacao/edit"
        assert model.confirmacaoInstance != null

        confirmacao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/confirmacao/show/$confirmacao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        confirmacao.clearErrors()

        populateValidParams(params)
        params.id = confirmacao.id
        params.version = -1
        controller.update()

        assert view == "/confirmacao/edit"
        assert model.confirmacaoInstance != null
        assert model.confirmacaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/confirmacao/list'

        response.reset()

        populateValidParams(params)
        def confirmacao = new Confirmacao(params)

        assert confirmacao.save() != null
        assert Confirmacao.count() == 1

        params.id = confirmacao.id

        controller.delete()

        assert Confirmacao.count() == 0
        assert Confirmacao.get(confirmacao.id) == null
        assert response.redirectedUrl == '/confirmacao/list'
    }
}
