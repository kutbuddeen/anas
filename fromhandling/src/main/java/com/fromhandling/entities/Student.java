package com.fromhandling.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	protected int studentId;
	@Column(name = "student_name")
	protected String studentName;
	@Column(name = "address_line1")
	protected String addressLine1;
	@Column(name = "address_line2")
	protected String addressLine2;
	protected String city;
	protected String state;
	protected String country;
	protected int zip;

}
