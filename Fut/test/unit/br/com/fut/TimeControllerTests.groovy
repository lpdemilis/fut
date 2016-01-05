package br.com.fut



import org.junit.*
import grails.test.mixin.*

@TestFor(TimeController)
@Mock(Time)
class TimeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/time/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.timeInstanceList.size() == 0
        assert model.timeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.timeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.timeInstance != null
        assert view == '/time/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/time/show/1'
        assert controller.flash.message != null
        assert Time.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/time/list'

        populateValidParams(params)
        def time = new Time(params)

        assert time.save() != null

        params.id = time.id

        def model = controller.show()

        assert model.timeInstance == time
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/time/list'

        populateValidParams(params)
        def time = new Time(params)

        assert time.save() != null

        params.id = time.id

        def model = controller.edit()

        assert model.timeInstance == time
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/time/list'

        response.reset()

        populateValidParams(params)
        def time = new Time(params)

        assert time.save() != null

        // test invalid parameters in update
        params.id = time.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/time/edit"
        assert model.timeInstance != null

        time.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/time/show/$time.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        time.clearErrors()

        populateValidParams(params)
        params.id = time.id
        params.version = -1
        controller.update()

        assert view == "/time/edit"
        assert model.timeInstance != null
        assert model.timeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/time/list'

        response.reset()

        populateValidParams(params)
        def time = new Time(params)

        assert time.save() != null
        assert Time.count() == 1

        params.id = time.id

        controller.delete()

        assert Time.count() == 0
        assert Time.get(time.id) == null
        assert response.redirectedUrl == '/time/list'
    }
}
