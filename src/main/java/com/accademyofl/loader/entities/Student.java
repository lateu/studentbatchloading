package com.accademyofl.loader.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Student  {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;

	@Column(nullable = false)
	private Date DOB;
	private String seriebacc;
	@Column(nullable = false)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date annee_bacc;
	private String maxdegree;
	private String marital_status;
	private String sexe;
	private String email;
	private boolean active;
	private int level;
	@CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

	/*
	@OneToMany
	private Collection<Payment> payments;
	
*/
	

	public Student() {
		// TODO Auto-generated constructor stub
		this.createdDate = LocalDateTime.now();
	}
	

	public Student(String firstname, String lastname, Date dOB, String seriebacc, Date annee_bacc, String maxdegree,
			String marital_status, String sexe, String email, boolean active, int level, LocalDateTime createdDate,
			LocalDateTime lastModifiedDate) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		DOB = dOB;
		this.seriebacc = seriebacc;
		this.annee_bacc = annee_bacc;
		this.maxdegree = maxdegree;
		this.marital_status = marital_status;
		this.sexe = sexe;
		this.email = email;
		this.active = active;
		this.level = level;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate() {
		this.createdDate = LocalDateTime.now();
	}


	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}




	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}




	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}




	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the dOB
	 */
	public Date getDOB() {
		return DOB;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	/**
	 * @return the seriebacc
	 */
	public String getSeriebacc() {
		return seriebacc;
	}

	/**
	 * @param seriebacc the seriebacc to set
	 */
	public void setSeriebacc(String seriebacc) {
		this.seriebacc = seriebacc;
	}

	/**
	 * @return the annee_bacc
	 */
	public Date getAnnee_bacc() {
		return annee_bacc;
	}

	/**
	 * @param annee_bacc the annee_bacc to set
	 */
	public void setAnnee_bacc(Date annee_bacc) {
		this.annee_bacc = annee_bacc;
	}

	/**
	 * @return the maxdegree
	 */
	public String getMaxdegree() {
		return maxdegree;
	}

	/**
	 * @param maxdegree the maxdegree to set
	 */
	public void setMaxdegree(String maxdegree) {
		this.maxdegree = maxdegree;
	}

	/**
	 * @return the marital_status
	 */
	public String getMarital_status() {
		return marital_status;
	}

	/**
	 * @param marital_status the marital_status to set
	 */
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}




	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}




	public boolean isActive() {
		return active;
	}




	public void setActive(boolean active) {
		this.active = active;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student_new [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", DOB=" + DOB + ", seriebacc=" + seriebacc
				+ ", annee_bacc=" + annee_bacc + ", maxdegree=" + maxdegree
				+ ", marital_status=" + marital_status + ", sexe=" + sexe
				+ ", email=" + email + "]";
	}




	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

}
