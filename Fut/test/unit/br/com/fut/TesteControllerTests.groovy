package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(TesteController)
@Mock(Teste)
class TesteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/teste/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.testeInstanceList.size() == 0
        assert model.testeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.testeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.testeInstance != null
        assert view == '/teste/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/teste/show/1'
        assert controller.flash.message != null
        assert Teste.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/teste/list'

        populateValidParams(params)
        def teste = new Teste(params)

        assert teste.save() != null

        params.id = teste.id

        def model = controller.show()

        assert model.testeInstance == teste
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/teste/list'

        populateValidParams(params)
        def teste = new Teste(params)

        assert teste.save() != null

        params.id = teste.id

        def model = controller.edit()

        assert model.testeInstance == teste
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/teste/list'

        response.reset()

        populateValidParams(params)
        def teste = new Teste(params)

        assert teste.save() != null

        // test invalid parameters in update
        params.id = teste.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/teste/edit"
        assert model.testeInstance != null

        teste.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/teste/show/$teste.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        teste.clearErrors()

        populateValidParams(params)
        params.id = teste.id
        params.version = -1
        controller.update()

        assert view == "/teste/edit"
        assert model.testeInstance != null
        assert model.testeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/teste/list'

        response.reset()

        populateValidParams(params)
        def teste = new Teste(params)

        assert teste.save() != null
        assert Teste.count() == 1

        params.id = teste.id

        controller.delete()

        assert Teste.count() == 0
        assert Teste.get(teste.id) == null
        assert response.redirectedUrl == '/teste/list'
    }
}
