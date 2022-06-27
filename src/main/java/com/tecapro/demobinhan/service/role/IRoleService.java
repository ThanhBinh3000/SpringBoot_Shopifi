package com.tecapro.demobinhan.service.role;


import com.tecapro.demobinhan.model.auth.Role;
import com.tecapro.demobinhan.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
