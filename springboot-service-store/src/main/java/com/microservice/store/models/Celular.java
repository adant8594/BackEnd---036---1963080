package com.microservice.store.models;

import java.util.Date;

public class Celular {
	
	private Long Id;
	
	private String name;
	
	private String marca;
	
	private Date createdAt;
	
	private Integer port;
	
	public Celular(Long id, String name, String marca) {
		this.Id = id;
		this.name = name;
		this.marca = marca;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
}
