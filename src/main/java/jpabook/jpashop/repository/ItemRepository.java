package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    //상품 저장
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); // item은 jpa저장 전까지 아이디 값이 없어서 새로운 값은 아이디가 null이므로 새로운 등록을 하기 위해 Persist
        }else{
            em.merge(item); // id가 있다는 것은 디비이ㅔ 해당 이름의 아이템이 있다는 뜻이므로 merge를 이용하여 update 비스무리하게
        }
    }
    //상품 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //상품 전체 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i ", Item.class)
                .getResultList();
    }

    //상품 수정

    //상품 삭제


}
