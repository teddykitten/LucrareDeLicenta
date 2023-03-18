package ucv.ro.catalina.accesingdatasql;


import org.springframework.data.repository.CrudRepository;
import ucv.ro.catalina.movie.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Integer> {
}

