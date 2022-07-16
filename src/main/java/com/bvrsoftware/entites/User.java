package com.bvrsoftware.entites;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    private String firstname;
	    private String lastname;
	    private String email;
	    private String gender;
	    private String password;
	    private String dob;
	    private String contactNo;
	    private String address;
	    private String state;
	    private String country;
	    private String pinCode;
	    private String image;
	    private String regDate;
	    private String updatationDate;
	    private String status;
	    
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<Complaints> complaint = new ArrayList<>();
	    
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<ComplaintRemark> complaintRemark = new ArrayList<>();
	    
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name="users_role", 
		joinColumns =@JoinColumn(name="user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
		private Set<Role> roles = new HashSet<>();
}
