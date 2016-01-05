package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoCampoController)
@Mock(TipoCampo)
class TipoCampoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipoCampo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoCampoInstanceList.size() == 0
        assert model.tipoCampoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tipoCampoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoCampoInstance != null
        assert view == '/tipoCampo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipoCampo/show/1'
        assert controller.flash.message != null
        assert TipoCampo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCampo/list'

        populateValidParams(params)
        def tipoCampo = new TipoCampo(params)

        assert tipoCampo.save() != null

        params.id = tipoCampo.id

        def model = controller.show()

        assert model.tipoCampoInstance == tipoCampo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCampo/list'

        populateValidParams(params)
        def tipoCampo = new TipoCampo(params)

        assert tipoCampo.save() != null

        params.id = tipoCampo.id

        def model = controller.edit()

        assert model.tipoCampoInstance == tipoCampo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoCampo/list'

        response.reset()

        populateValidParams(params)
        def tipoCampo = new TipoCampo(params)

        assert tipoCampo.save() != null

        // test invalid parameters in update
        params.id = tipoCampo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipoCampo/edit"
        assert model.tipoCampoInstance != null

        tipoCampo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipoCampo/show/$tipoCampo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipoCampo.clearErrors()

        populateValidParams(params)
        params.id = tipoCampo.id
        params.version = -1
        controller.update()

        assert view == "/tipoCampo/edit"
        assert model.tipoCampoInstance != null
        assert model.tipoCampoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipoCampo/list'

        response.reset()

        populateValidParams(params)
        def tipoCampo = new TipoCampo(params)

        assert tipoCampo.save() != null
        assert TipoCampo.count() == 1

        params.id = tipoCampo.id

        controller.delete()

        assert TipoCampo.count() == 0
        assert TipoCampo.get(tipoCampo.id) == null
        assert response.redirectedUrl == '/tipoCampo/list'
    }
}
