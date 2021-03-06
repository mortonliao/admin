package com.longhum.admin.model;

public class SysUser {
    private Long id;

    private Long roleId;

    private String username;

    private String password;

    private String salt;

    private Boolean locked;

    private Boolean status;

    private String roleName;
    
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", roleId=" + roleId + ", username=" + username + ", password=" + password
				+ ", salt=" + salt + ", locked=" + locked + ", status=" + status + ", roleName=" + roleName + "]";
	}

	public String generateSalt() {
		return  username+salt;
	}

    
}