package model.user.domain;

import model.common.domain.Role;

public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String name, String login, long passHash) {
        super(name, login, passHash, Role.ADMIN);
    }

}
