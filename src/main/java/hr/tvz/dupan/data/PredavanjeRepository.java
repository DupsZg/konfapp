package hr.tvz.dupan.data;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hr.tvz.dupan.dodklase.Predavanje;

public interface PredavanjeRepository extends CrudRepository<Predavanje,Long> {
	
	List<Predavanje> findByTema(String tema);

}
