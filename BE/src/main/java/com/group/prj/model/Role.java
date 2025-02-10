package com.group.prj.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> user = new HashSet<>();

    public void assignRoleToUser(User user) {
        user.getRoles().add(this);
        this.getUser().add(user);
    }

    public void removeUserFromRole(User user) {
        user.getRoles().remove(this);
        this.getUser().remove(user);
    }

    public void removeAllUsersFromRole() {
        if (this.getUser() != null) {
            List<User> roleUsers = this.getUser().stream().toList();
            roleUsers.forEach(this::removeUserFromRole);
        }
    }

    public String getName() {
        return this.name != null ? name : "";
    }
}
