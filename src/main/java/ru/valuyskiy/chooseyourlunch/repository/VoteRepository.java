package ru.valuyskiy.chooseyourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.valuyskiy.chooseyourlunch.model.Vote;

@Transactional(readOnly = true)
public interface VoteRepository  extends JpaRepository<Vote, Integer> {
}
