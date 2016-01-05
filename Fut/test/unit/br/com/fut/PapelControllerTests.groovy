package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(PapelController)
@Mock(Papel)
class PapelControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/papel/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.papelInstanceList.size() == 0
        assert model.papelInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.papelInstance != null
    }

    void testSave() {
        controller.save()

        assert model.papelInstance != null
        assert view == '/papel/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/papel/show/1'
        assert controller.flash.message != null
        assert Papel.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/papel/list'

        populateValidParams(params)
        def papel = new Papel(params)

        assert papel.save() != null

        params.id = papel.id

        def model = controller.show()

        assert model.papelInstance == papel
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/papel/list'

        populateValidParams(params)
        def papel = new Papel(params)

        assert papel.save() != null

        params.id = papel.id

        def model = controller.edit()

        assert model.papelInstance == papel
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/papel/list'

        response.reset()

        populateValidParams(params)
        def papel = new Papel(params)

        assert papel.save() != null

        // test invalid parameters in update
        params.id = papel.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/papel/edit"
        assert model.papelInstance != null

        papel.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/papel/show/$papel.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        papel.clearErrors()

        populateValidParams(params)
        params.id = papel.id
        params.version = -1
        controller.update()

        assert view == "/papel/edit"
        assert model.papelInstance != null
        assert model.papelInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/papel/list'

        response.reset()

        populateValidParams(params)
        def papel = new Papel(params)

        assert papel.save() != null
        assert Papel.count() == 1

        params.id = papel.id

        controller.delete()

        assert Papel.count() == 0
        assert Papel.get(papel.id) == null
        assert response.redirectedUrl == '/papel/list'
    }
}
