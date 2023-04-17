package com.fiap.Plant4U.authuser.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fiap.Plant4U.authuser.dtos.UserDto.UserView.ImagePut;
import com.fiap.Plant4U.authuser.dtos.UserDto.UserView.PasswordPut;
import com.fiap.Plant4U.authuser.dtos.UserDto.UserView.RegistrationPost;
import com.fiap.Plant4U.authuser.validations.UsernameConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

//@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface UserView{
        public static interface RegistrationPost{}
        public static interface UserPut{}
        public static interface PasswordPut{}

        public static interface ImagePut{}
    }

    private UUID userId;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
    @UsernameConstraint(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)//Username não poderá ser alterado, assim, somente no cadastro o username poderá ser criado
    private String username;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Email(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    private String password;

    @NotBlank(groups = UserView.PasswordPut.class)
    @Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView(UserView.ImagePut.class)
    private String imageUrl;

    public UserDto() {
    }
    
	public UserDto(UUID userId,
			@NotBlank(groups = RegistrationPost.class) @Size(min = 4, max = 50, groups = RegistrationPost.class) String username,
			@NotBlank(groups = RegistrationPost.class) @Email(groups = RegistrationPost.class) String email,
			@NotBlank(groups = { RegistrationPost.class, PasswordPut.class }) @Size(min = 6, max = 20, groups = {
					RegistrationPost.class, PasswordPut.class }) String password,
			@NotBlank(groups = PasswordPut.class) @Size(min = 6, max = 20, groups = PasswordPut.class) String oldPassword,
			String fullName, String phoneNumber, String cpf, @NotBlank(groups = ImagePut.class) String imageUrl) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.oldPassword = oldPassword;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.cpf = cpf;
		this.imageUrl = imageUrl;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
