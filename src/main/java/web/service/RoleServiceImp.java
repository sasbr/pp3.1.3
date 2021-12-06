package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRep;

    @Autowired
    public RoleServiceImp(RoleRepository roleRep) {
        this.roleRep = roleRep;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Role> getRoles() {
        return roleRep.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Set<Role> getRolesByName(String[] roles) {
        return new HashSet<Role>(roleRep.findRolesByRolenamesArray(roles));
    }
    @Override
    public void addNewRole(Role role) {
         roleRep.save(role);
    }

}

