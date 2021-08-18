package com.ssafy.db.repository;

import com.ssafy.db.entity.Basket;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket,String> {

	List<Basket> findAllByUserId(String userId);

	Optional<Basket> findByItemIdAndUserId(String itemId, String userId);

	//영속성 컨텍스트도 함꼐 삭제
	@Modifying(clearAutomatically = true)
	//id를 통한 벌크 쿼리 작성
	@Query("delete from Basket c where c.id in :ids")
	void deleteAllByIdInQuery(@Param("ids") List<String> ids);

}
