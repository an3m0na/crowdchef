package com.crowdchef.datamodel.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AllUsers",
                query = "SELECT * FROM app_user",
                resultClass = User.class
        ),
        @NamedNativeQuery(
                name = "OneUserById",
                query = "SELECT * FROM app_user WHERE id = :id",
                resultClass = User.class
        ),
        @NamedNativeQuery(
                name = "OneUserByUsername",
                query = "SELECT * FROM app_user WHERE username = :username",
                resultClass = User.class
        )
})
@Entity
@Table(name = "app_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="app_user_id_seq")
    @SequenceGenerator(name="app_user_id_seq", sequenceName="app_user_id_seq", allocationSize=1)
    private Long id;

    private String username;

    private String password;

    @Column(name = "create_time", columnDefinition = "datetime", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.setUserInfo(new UserInfo(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User[" + getId() + "] = " + getUsername();
    }
}
