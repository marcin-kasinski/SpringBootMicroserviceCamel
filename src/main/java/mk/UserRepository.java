package mk;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserRepository extends CrudRepository<User, Long>{

//	public interface UserRepository {

	 /**
	   * This method will find an User instance in the database by its email.
	   * Note that this method is not implemented and its working code will be
	   * automagically generated from its signature by Spring Data JPA.
	   */
	  public User findByEmail(String email);
	  public List<User> findAllUsersByEmail(String email);

	
}
