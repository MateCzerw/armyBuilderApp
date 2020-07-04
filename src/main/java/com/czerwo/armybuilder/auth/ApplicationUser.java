package com.czerwo.armybuilder.auth;

import com.czerwo.armybuilder.models.data.Roster;
import com.czerwo.armybuilder.models.data.Tournament;
import com.czerwo.armybuilder.security.ApplicationUserPermission;
import com.czerwo.armybuilder.security.ApplicationUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUseRole;

    @OneToMany(mappedBy = "applicationUser")
    private List<Roster> rosters = new ArrayList<>();

    @OneToMany(mappedBy = "host")
    private List<Tournament> tournamentsWithHostRole = new ArrayList<>();

    @ManyToMany(mappedBy = "participants")
    private List<Tournament> tournaments = new ArrayList<>();


    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private String email;

    public ApplicationUser() {
    }


    public List<Roster> getRosters() {
        return rosters;
    }

    public void addRoster(Roster roster) {
        rosters.add(roster);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return applicationUseRole.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public ApplicationUserRole getApplicationUseRole() {
        return applicationUseRole;
    }

    public void setApplicationUseRole(ApplicationUserRole applicationUseRole) {
        this.applicationUseRole = applicationUseRole;
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    public void removeTournament(Tournament tournament) {
        tournaments.remove(tournament);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
