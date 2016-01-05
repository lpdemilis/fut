package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(UsuarioPapelController)
@Mock(UsuarioPapel)
class UsuarioPapelControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/usuarioPapel/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.usuarioPapelInstanceList.size() == 0
        assert model.usuarioPapelInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.usuarioPapelInstance != null
    }

    void testSave() {
        controller.save()

        assert model.usuarioPapelInstance != null
        assert view == '/usuarioPapel/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/usuarioPapel/show/1'
        assert controller.flash.message != null
        assert UsuarioPapel.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioPapel/list'

        populateValidParams(params)
        def usuarioPapel = new UsuarioPapel(params)

        assert usuarioPapel.save() != null

        params.id = usuarioPapel.id

        def model = controller.show()

        assert model.usuarioPapelInstance == usuarioPapel
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioPapel/list'

        populateValidParams(params)
        def usuarioPapel = new UsuarioPapel(params)

        assert usuarioPapel.save() != null

        params.id = usuarioPapel.id

        def model = controller.edit()

        assert model.usuarioPapelInstance == usuarioPapel
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/usuarioPapel/list'

        response.reset()

        populateValidParams(params)
        def usuarioPapel = new UsuarioPapel(params)

        assert usuarioPapel.save() != null

        // test invalid parameters in update
        params.id = usuarioPapel.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/usuarioPapel/edit"
        assert model.usuarioPapelInstance != null

        usuarioPapel.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/usuarioPapel/show/$usuarioPapel.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        usuarioPapel.clearErrors()

        populateValidParams(params)
        params.id = usuarioPapel.id
        params.version = -1
        controller.update()

        assert view == "/usuarioPapel/edit"
        assert model.usuarioPapelInstance != null
        assert model.usuarioPapelInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/usuarioPapel/list'

        response.reset()

        populateValidParams(params)
        def usuarioPapel = new UsuarioPapel(params)

        assert usuarioPapel.save() != null
        assert UsuarioPapel.count() == 1

        params.id = usuarioPapel.id

        controller.delete()

        assert UsuarioPapel.count() == 0
        assert UsuarioPapel.get(usuarioPapel.id) == null
        assert response.redirectedUrl == '/usuarioPapel/list'
    }
}
