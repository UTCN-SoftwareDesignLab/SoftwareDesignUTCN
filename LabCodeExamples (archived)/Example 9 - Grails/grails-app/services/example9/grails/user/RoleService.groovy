package example9.grails.user

import grails.gorm.services.Service
import user.ERole

@Service(Role)
interface RoleService {

    Role get(Serializable id)

    List<Role> list(Map args)

    Long count()

    void delete(Serializable id)

    Role save(Role role)

    Role getByName(ERole eRole)
}