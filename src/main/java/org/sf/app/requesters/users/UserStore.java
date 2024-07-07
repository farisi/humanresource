package org.sf.app.requesters.users;

import java.time.LocalDate;

import org.sf.app.validates.ConfirmPassword;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@ConfirmPassword(message="Password and confirm password tidak sama!")
public class UserStore implements ConfirmInterface {
	
	@NotBlank(message = "Nama depan wajib diisikan!")
    private String firstName;
    private String lastName;  
    
    @Email(message="Anda harus memasukan format email yang lengkap!")
    @NotBlank(message="Email wajib diiskan!")
	private String email;
    
    @NotBlank(message="Password tidak boleh kosong!")
	private String password;
    
	private String confirmPassword;
	
	private MultipartFile foto;
	
	private String address;
	
	@NotBlank(message="Nomor Kontak tidak boleh kosong!")
	private String mobile;
	
	private String placeOfBirth;
    
	@Past
	private LocalDate birthDate;
	
	@Past
    private LocalDate joinDate;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
		
	public MultipartFile getFoto() {
		return foto;
	}
	
	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public LocalDate getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String getConfirmPassword() {
		// TODO Auto-generated method stub
		return confirmPassword;
	}
}
