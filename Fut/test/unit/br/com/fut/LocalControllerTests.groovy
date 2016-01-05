package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(LocalController)
@Mock(Local)
class LocalControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/local/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.localInstanceList.size() == 0
        assert model.localInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.localInstance != null
    }

    void testSave() {
        controller.save()

        assert model.localInstance != null
        assert view == '/local/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/local/show/1'
        assert controller.flash.message != null
        assert Local.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/local/list'

        populateValidParams(params)
        def local = new Local(params)

        assert local.save() != null

        params.id = local.id

        def model = controller.show()

        assert model.localInstance == local
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/local/list'

        populateValidParams(params)
        def local = new Local(params)

        assert local.save() != null

        params.id = local.id

        def model = controller.edit()

        assert model.localInstance == local
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/local/list'

        response.reset()

        populateValidParams(params)
        def local = new Local(params)

        assert local.save() != null

        // test invalid parameters in update
        params.id = local.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/local/edit"
        assert model.localInstance != null

        local.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/local/show/$local.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        local.clearErrors()

        populateValidParams(params)
        params.id = local.id
        params.version = -1
        controller.update()

        assert view == "/local/edit"
        assert model.localInstance != null
        assert model.localInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/local/list'

        response.reset()

        populateValidParams(params)
        def local = new Local(params)

        assert local.save() != null
        assert Local.count() == 1

        params.id = local.id

        controller.delete()

        assert Local.count() == 0
        assert Local.get(local.id) == null
        assert response.redirectedUrl == '/local/list'
    }
}
