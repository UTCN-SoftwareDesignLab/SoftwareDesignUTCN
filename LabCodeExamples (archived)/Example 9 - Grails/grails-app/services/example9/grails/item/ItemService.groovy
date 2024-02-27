package example9.grails.item

import grails.gorm.services.Service

@Service(Item)
interface ItemService {

    Item get(Serializable id)

    List<Item> list(Map args)

    Long count()

    void delete(Serializable id)

    Item save(Item item)

}