package com.money.rich.domain.popularKeyword.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.money.rich.domain.popularKeyword.dto.PopularKeywordDto;
import com.money.rich.domain.popularKeyword.dto.PopularKeywordSaveDto;
import com.money.rich.domain.popularKeyword.entity.PopularKeywordEntity;
import com.money.rich.domain.popularKeyword.repository.PopularKeywordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopularKeywordService {

	private final PopularKeywordRepository repository;

	public Optional<PopularKeywordEntity> getByKeyword(final String keyword) {
		return repository.findByKeyword(keyword);
	}

	public List<PopularKeywordDto> getsTop10() {
		return repository.findTop10ByOrderByHitDesc().stream()
			.map(entity -> PopularKeywordDto.builder().popularKeyword(entity).build())
			.collect(Collectors.toList());
	}

	public PopularKeywordDto post(final PopularKeywordSaveDto popularKeywordDto) {
		return save(popularKeywordDto.toEntity());
	}

	@Transactional
	public PopularKeywordDto hits(final PopularKeywordEntity popularKeyword) {
		return PopularKeywordDto.of(popularKeyword.updateHit());
	}

	@Transactional
	public PopularKeywordDto save(final PopularKeywordEntity popularKeyword) {
		repository.save(popularKeyword);
		return PopularKeywordDto.of(popularKeyword);
	}

}
