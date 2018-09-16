package com.ZTI2018.securitytest.models;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "items")

//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "itemID")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
	@SequenceGenerator(name="item_generator", sequenceName = "item_seq", initialValue=3, allocationSize=1)
	private Long ItemID;
	
	@Column
	private String Name;
	
	@Column
	private Long Amount;
	
	@Column(nullable = false)
	private Timestamp CreatedDate;
	
	@ManyToOne
	@JsonIgnore
//	@JsonIgnoreProperties("items")
//	@JsonBackReference
	private ItemList itemList;
 
	
	
	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long itemID) {
		ItemID = itemID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Long getAmount() {
		return Amount;
	}

	public void setAmount(Long amount) {
		Amount = amount;
	}

	public Timestamp getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		CreatedDate = createdDate;
	}

	public ItemList getItemList() {
		return itemList;
	}

	public void setItemList(ItemList itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "Item [ItemID=" + ItemID + ", Name=" + Name + ", Amount=" + Amount + ", CreatedDate=" + CreatedDate
				+ ", itemList=" + itemList + "]";
	}
	
	
}
