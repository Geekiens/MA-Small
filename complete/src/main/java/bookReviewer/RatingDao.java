/*
package hello;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RatingDao {
    public void saveRating(Rating rating) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(rating);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Rating> getRatings() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Rating", Rating.class).list();
        }
    }
}

 */