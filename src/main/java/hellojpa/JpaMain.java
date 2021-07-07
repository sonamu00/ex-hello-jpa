package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        /*EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /*EntityManager는 쓰레드간에 공유X (사용하고 버려야 한다).
        * DB Connection 같은 거*/
        EntityManager em =  emf.createEntityManager();

        /* JPA의 모든 데이터 변경은 트랜잭션 안에서 실행*/
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜젝션 시작

        try {
            Member member = new Member(200l, "pororo");
            em.persist(member);

            em.flush();

            System.out.println("==============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();





    }
}

