package hr.tvz.dupan.dodklase;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;
import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
@RestResource(path="predavac", rel="predavaci")
public class Predavac {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Type(type="date")
	private Date datumUpisa;
	
	@NotEmpty(message="{msg.errors.name.empty}")
	@Size(min=5,max=50, message="{msg.errors.name.size}")
	private String ime;
	@NotNull(message="{msg.errors.position.empty}")
	private Pozicija pozicija;
	
	public static enum Pozicija{
		JUNIOR,MID,SENIOR
	}
	


}
