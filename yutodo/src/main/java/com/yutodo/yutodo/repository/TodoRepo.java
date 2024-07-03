package com.yutodo.yutodo.repository;

import com.yutodo.yutodo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findAllByOrderByPriorityAsc();
}

