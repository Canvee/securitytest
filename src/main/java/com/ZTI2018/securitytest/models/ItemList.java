package com.ZTI2018.securitytest.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lists")
public class ItemList {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "list_generator")
	@SequenceGenerator(name="list_generator", sequenceName = "list_seq", initialValue=2, allocationSize=1)
	private Long ListID;
	
	@Column(nullable = false)
	private String Name;
	
	@Column
	private Double Value;
	
	@Column
	private Timestamp CompletedDate;
	
	@Column(nullable = false)
	private Timestamp CreatedDate;
	
	@ManyToOne
	@JsonIgnore
	private AppUser appuser; 
	
	@OneToMany(mappedBy = "itemList")
	@JsonIgnoreProperties("itemList")
	private List<Item> items = new ArrayList<>();


	public Long getListID() {
		return ListID;
	}


	public void setListID(Long listID) {
		ListID = listID;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public Double getValue() {
		return Value;
	}


	public void setValue(Double value) {
		Value = value;
	}


	public Timestamp getCompletedDate() {
		return CompletedDate;
	}


	public void setCompletedDate(Timestamp completedDate) {
		CompletedDate = completedDate;
	}


	public Timestamp getCreatedDate() {
		return CreatedDate;
	}


	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	

	public AppUser getAppuser() {
		return appuser;
	}


	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}


	@Override
	public String toString() {
		return "ItemList [ListID=" + ListID + ", Name=" + Name + ", Value=" + Value + ", CompletedDate=" + CompletedDate
				+ ", CreatedDate=" + CreatedDate + ", appuser=" + appuser + ", items="
				+ items + "]";
	}

	
}
