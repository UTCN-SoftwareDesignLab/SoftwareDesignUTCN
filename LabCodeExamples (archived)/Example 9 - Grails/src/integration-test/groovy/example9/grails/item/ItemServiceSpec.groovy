package example9.grails.item

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ItemServiceSpec extends Specification {

    ItemService itemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Item(...).save(flush: true, failOnError: true)
        //new Item(...).save(flush: true, failOnError: true)
        //Item item = new Item(...).save(flush: true, failOnError: true)
        //new Item(...).save(flush: true, failOnError: true)
        //new Item(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //item.id
    }

    void "test get"() {
        setupData()

        expect:
        itemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Item> itemList = itemService.list(max: 2, offset: 2)

        then:
        itemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        itemService.count() == 5
    }

    void "test delete"() {
        Long itemId = setupData()

        expect:
        itemService.count() == 5

        when:
        itemService.delete(itemId)
        sessionFactory.currentSession.flush()

        then:
        itemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Item item = new Item()
        itemService.save(item)

        then:
        item.id != null
    }
}
