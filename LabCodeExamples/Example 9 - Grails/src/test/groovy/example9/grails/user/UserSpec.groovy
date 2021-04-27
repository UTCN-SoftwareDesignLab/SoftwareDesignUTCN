package example9.grails.user

import example9.grails.user.User
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "test username validation"() {

        when:
        domain.username = null

        then:
        !domain.validate(['username'])
        domain.errors['username'].code == 'nullable'
    }

    void "test password validation"() {

        when:
        domain.password = "test"

        then:
        !domain.validate(['password'])
        domain.errors['password'].code == 'validation.password.short'

        when:
        domain.password = ""

        then:
        !domain.validate(['password'])
        domain.errors['password'].code == 'blank'

        when:
        domain.password = 'test123'

        then:
        domain.validate(['password'])

    }

    void "test overall validation"() {

        when:
        domain

        then:
        !domain.validate()
        domain.errors.errorCount == 4


        when:
        domain.username = 'whatever'
        domain.email = 'alex@email.com'
        domain.password = 'test123'
        domain.roles.add(new Role())

        then:
        domain.validate()
    }
}
