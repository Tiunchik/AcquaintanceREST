/**
 * Package rest.database for
 *
 * @author Maksim Tiunchik
 */

package rest.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.models.Person;

/**
 * Interface PersonRepository -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 07.05.2020
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
