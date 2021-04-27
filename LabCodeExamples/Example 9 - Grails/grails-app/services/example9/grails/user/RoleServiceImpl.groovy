package example9.grails.user

import user.ERole

class RoleServiceImpl implements RoleService {

    @Override
    Role get(Serializable id) {
        return Role.get(id)
    }

    @Override
    List<Role> list(Map args) {
        return Role.list(args)
    }

    @Override
    Long count() {
        return Role.count
    }

    @Override
    void delete(Serializable id) {
        Role.get(id).delete()
    }

    @Override
    Role save(Role role) {
        role.save()
    }

    @Override
    Role getByName(ERole eRole) {
        return Role.findByName(eRole)
    }
}
