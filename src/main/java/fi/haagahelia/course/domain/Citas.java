package fi.haagahelia.course.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Citas {
	private long id;	 
	private String firstName;	
	private String lastName;
	private String reason;    
    private String email;    
    
	private Set<Motivo> motivo = new HashSet<Motivo>(0);    
    
    public Citas() {
    }

	public Citas(String firstName, String lastName, String reason, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.reason = reason;
		this.email = email;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    @Column(name = "firstname")   	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    @Column(name = "lastname")	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    @Column(name = "reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

    @Column(name = "email")	
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "paciente_motivo", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "motivoid") })
	public Set<Motivo> getMotivo() {
		return this.motivo;
	}

	public void setMotivo(Set<Motivo> motivo) {
		this.motivo = motivo;
	}
	
	public boolean hasMotivo(Motivo motivo) {
		for (Motivo pacienteMotivo: getMotivo()) {
			if (pacienteMotivo.getMotivoid() == motivo.getMotivoid()) {
				return true;
			}
		}
		return false;
	}	
}
