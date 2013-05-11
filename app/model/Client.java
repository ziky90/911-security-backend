package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

	@Id
	private Long id;
	
	@NotNull
	private Boolean allowed;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
	private List<Crime> crimes = new ArrayList<Crime>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAllowed() {
		return allowed;
	}

	public void setAllowed(Boolean allowed) {
		this.allowed = allowed;
	}

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}
	
	
	
	
}
