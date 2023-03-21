package com.money.rich;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.money.rich.domain.popularKeyword.entity.PopularKeywordEntity;
import com.money.rich.domain.popularKeyword.repository.PopularKeywordRepository;
import com.money.rich.domain.popularKeyword.service.PopularKeywordService;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class RichApplicationRunner implements ApplicationRunner {

	@Autowired
	EntityManager entityManager;

	@Autowired
	PopularKeywordRepository repository;

	@Autowired
	PopularKeywordService keywordService;

	@Transactional
	@Override
	public void run(ApplicationArguments args) {
		entityManager.persist(PopularKeywordEntity.builder().keyword("고양이").hit(10).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("귀여워").hit(11).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("팝송").hit(5).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("추천").hit(100).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("부동산").hit(1000).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("주식").hit(6).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("java").hit(77).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("spring").hit(23).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("카지노").hit(69).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("대한민국").hit(999).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("드라마").hit(1).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("미드추천").hit(2).build());
		entityManager.persist(PopularKeywordEntity.builder().keyword("오늘날씨").hit(11111).build());

		entityManager.flush();
		entityManager.clear();
	}

}
