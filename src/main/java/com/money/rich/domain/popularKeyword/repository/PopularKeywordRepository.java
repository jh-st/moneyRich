package com.money.rich.domain.popularKeyword.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.money.rich.domain.popularKeyword.entity.PopularKeywordEntity;

public interface PopularKeywordRepository extends JpaRepository<PopularKeywordEntity, Long> {

	Optional<PopularKeywordEntity> findByKeyword(final String keyword);

	List<PopularKeywordEntity> findTop10ByOrderByHitDesc();

}