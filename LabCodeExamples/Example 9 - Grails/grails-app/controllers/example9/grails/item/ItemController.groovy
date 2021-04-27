package example9.grails.item

import grails.rest.RestfulController

// https://docs.grails.org/latest/guide/REST.html
class ItemController extends RestfulController<Item> {

    ItemController(Class<Item> resource) {
        super(resource)
    }

}
