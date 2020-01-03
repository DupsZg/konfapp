package hr.tvz.dupan.dodklase;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@RestResource(path="predavanje", rel="predavanja")
public class Predavanje {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@CreationTimestamp
	private LocalDateTime datumUpisa;
	
	@Valid
	@OneToOne(targetEntity=Predavac.class, cascade=CascadeType.ALL)
	@JoinTable(name="Predavanje_Predavac",joinColumns = @JoinColumn(name="Predavanje"),inverseJoinColumns = @JoinColumn(name="Predavac"))
	private Predavac predavac;
	
	@NotEmpty(message="{msg.errors.theme.empty}")
	@Size(min=5,max=50, message="{msg.errors.theme.size}")
	private String tema;
	
	@NotEmpty(message="{msg.errors.shortcontent.empty}")
	@Size(min=25,max=250, message="{msg.errors.shortcontent.size}")
	private String kratkiSadrzaj;
	
	@NotNull(message="{msg.errors.type.empty}")
	private Vrsta vrsta;
	
	private boolean objavljeno=true;
	
	public static enum Vrsta{
		RADIONICA,PREZENTACIJA
	}
	


}
