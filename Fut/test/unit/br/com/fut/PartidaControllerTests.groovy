package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(PartidaController)
@Mock(Partida)
class PartidaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/partida/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.partidaInstanceList.size() == 0
        assert model.partidaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.partidaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.partidaInstance != null
        assert view == '/partida/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/partida/show/1'
        assert controller.flash.message != null
        assert Partida.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/partida/list'

        populateValidParams(params)
        def partida = new Partida(params)

        assert partida.save() != null

        params.id = partida.id

        def model = controller.show()

        assert model.partidaInstance == partida
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/partida/list'

        populateValidParams(params)
        def partida = new Partida(params)

        assert partida.save() != null

        params.id = partida.id

        def model = controller.edit()

        assert model.partidaInstance == partida
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/partida/list'

        response.reset()

        populateValidParams(params)
        def partida = new Partida(params)

        assert partida.save() != null

        // test invalid parameters in update
        params.id = partida.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/partida/edit"
        assert model.partidaInstance != null

        partida.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/partida/show/$partida.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        partida.clearErrors()

        populateValidParams(params)
        params.id = partida.id
        params.version = -1
        controller.update()

        assert view == "/partida/edit"
        assert model.partidaInstance != null
        assert model.partidaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/partida/list'

        response.reset()

        populateValidParams(params)
        def partida = new Partida(params)

        assert partida.save() != null
        assert Partida.count() == 1

        params.id = partida.id

        controller.delete()

        assert Partida.count() == 0
        assert Partida.get(partida.id) == null
        assert response.redirectedUrl == '/partida/list'
    }
}
