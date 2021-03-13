package repository.security;

import model.Right;
import model.Role;
import model.User;

import java.util.List;

public interface RightsRolesRepository {
    List<Role> findRolesForUser(long userId);

    Role findRoleByTitle(String customer);

    void addRolesToUser(User user, List<Role> roles);

    void addRole(String role);

    void addRight(String right);

    Right findRightByTitle(String right);

    void addRoleRight(Long roleId, Long rightId);

}
