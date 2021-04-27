package example9.grails

import example9.grails.user.Role
import example9.grails.user.RoleService
import user.ERole

class BootStrap {

    RoleService roleService

    def init = { servletContext ->
        if (roleService.count() == 0) {
            ERole.values().each {
                roleService.save(new Role(name: it))
            }
        }
    }
    def destroy = {
    }
}
