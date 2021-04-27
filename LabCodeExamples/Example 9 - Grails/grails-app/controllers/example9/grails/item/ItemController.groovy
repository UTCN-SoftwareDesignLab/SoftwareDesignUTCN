package example9.grails.item

import grails.rest.RestfulController

// https://docs.grails.org/latest/guide/REST.html
class ItemController extends RestfulController<Item> {
    static allowedMethods = [byName: "GET", byNameAndDescription: "GET"]

    ItemController(Class<Item> resource) {
        super(resource)
    }

    def byName(String name) {
        respond Item.findAllByNameLike(wildcard(name))
    }

    def byNameAndDescription(String n, String desc) {
        respond Item.whereAny {
            name.contains(n)
            description.contains(desc)
        }.findAll()
    }

    private String wildcard(String str) {
        '%' + str + '%'
    }

}
