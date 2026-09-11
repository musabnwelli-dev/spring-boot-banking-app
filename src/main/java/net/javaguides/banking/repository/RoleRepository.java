package net.javaguides.banking.repository;

import net.javaguides.banking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides database operations for security roles.
 */
public interface RoleRepository extends JpaRepository<Role, Long>
{
    /**
     * Finds a role by its name.
     * @param name the role name
     * @return the matching role
     */
    Role findByName(String name);
}
