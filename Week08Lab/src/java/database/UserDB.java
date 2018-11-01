package database;

import javax.persistence.EntityManager;
import model.User;

public class UserDB {
    public static User getUserById(long userId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, userId);
            return user;
        }finally {
            em.close();
        }
    }
}
