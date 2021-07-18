package library.dao.impl;

import library.dao.AuthorDao;
import library.exception.DataProcessingException;
import library.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final SessionFactory sessionFactory;

    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Author create(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert author " + author, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Author update(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not update author " + author, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Author> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Author> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Author.class);
            criteriaQuery.from(Author.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all authors", e);
        }
    }

    @Override
    public Optional<Author> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Author.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a author by id: " + id, e);
        }
    }

    @Override
    public List<Author> getByBookTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            Query<Author> query = session.createQuery("FROM Author a "
                    + "JOIN FETCH a.books b "
                    + "WHERE b.title LIKE :title", Author.class);
            query.setParameter("title", title);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find authors by keyword: " + title, e);
        }
    }
}
