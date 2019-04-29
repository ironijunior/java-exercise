package com.hackerrank.github.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Actor implements Comparable<Actor> {
    
	@Id
	private Long id;
	
    private String login;
    
    @JsonProperty(value="avatar_url")
    private String avatarUrl;

    public Actor() {
    }

    public Actor(Long id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

	@Override
	public int compareTo(Actor o) {
		return this.login.compareTo(o.login);
	}
}
