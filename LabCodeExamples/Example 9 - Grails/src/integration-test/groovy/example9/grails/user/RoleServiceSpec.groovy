package example9.grails.user

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RoleServiceSpec extends Specification {

    RoleService roleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Role(...).save(flush: true, failOnError: true)
        //new Role(...).save(flush: true, failOnError: true)
        //Role role = new Role(...).save(flush: true, failOnError: true)
        //new Role(...).save(flush: true, failOnError: true)
        //new Role(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //role.id
    }

    void "test get"() {
        setupData()

        expect:
        roleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Role> roleList = roleService.list(max: 2, offset: 2)

        then:
        roleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        roleService.count() == 5
    }

    void "test delete"() {
        Long roleId = setupData()

        expect:
        roleService.count() == 5

        when:
        roleService.delete(roleId)
        sessionFactory.currentSession.flush()

        then:
        roleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Role role = new Role()
        roleService.save(role)

        then:
        role.id != null
    }
}
