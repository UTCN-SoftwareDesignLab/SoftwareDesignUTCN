package example9.grails

import example9.grails.item.Item
import example9.grails.item.ItemService
import example9.grails.user.Role
import example9.grails.user.RoleService
import user.ERole

class BootStrap {

    RoleService roleService
    ItemService itemService

    def init = { servletContext ->
        if (roleService.count() == 0) {
            ERole.values().each {
                roleService.save(new Role(name: it))
            }
        }

        itemService.save(new Item().with {
            name = "Item 1"
            description = "Desc"
            it
        })
    }
    def destroy = {
    }
}
