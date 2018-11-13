package de.zeltlagerimmenried.entity;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.zeltlagerimmenried.helper.ReturnMessage;


@Entity
public class Message extends ReturnMessage  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idMessage;
	
	@Column(nullable = false)
	private String msg;
		
	@Column(nullable = false)
	private Integer idTeam;

	public Integer getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Integer idMessage) {
		this.idMessage = idMessage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}
	
	
}
