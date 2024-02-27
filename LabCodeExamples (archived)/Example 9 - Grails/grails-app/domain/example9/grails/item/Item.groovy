package example9.grails.item

import grails.rest.Resource

import java.time.LocalDateTime

@Resource(uri = "/items")
class Item {

    String name

    String description

    LocalDateTime dateCreated

    static constraints = {
        name maxSize: 512, nullable: false
        description maxSize: 1024, nullable: true
        dateCreated nullable: true
    }
}
