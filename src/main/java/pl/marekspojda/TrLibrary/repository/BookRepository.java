package pl.marekspojda.TrLibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.marekspojda.TrLibrary.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("select b from Book b order by b.title asc")
	List<Book> findAllOrderByTitleAsc();
	
	@Query("select b from Book b where b.isAvailable=1 order by b.title asc")
	List<Book> findAllAvailableOrderByTitleAsc();
}