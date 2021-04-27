package example9.grails.user

import example9.grails.user.Role
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import user.ERole

class RoleSpec extends Specification implements DomainUnitTest<Role> {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation"() {
        when:
        domain

        then:
        !domain.validate()
        domain.errors['name'].code == 'nullable'

        when:
        domain.name = ERole.ADMIN

        then:
        domain.validate()
    }
}
