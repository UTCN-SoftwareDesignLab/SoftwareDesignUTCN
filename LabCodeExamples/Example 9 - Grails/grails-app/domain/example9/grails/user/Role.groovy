package example9.grails.user

import user.ERole

class Role {

    ERole name

    static constraints = {
        name nullable: false, blank: false, unique: true
    }
}
