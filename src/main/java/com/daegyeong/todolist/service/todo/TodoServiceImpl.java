package com.daegyeong.todolist.service.todo;

import org.springframework.stereotype.Service;

import com.daegyeong.todolist.domain.todo.Todo;
import com.daegyeong.todolist.domain.todo.TodoRepository;
import com.daegyeong.todolist.web.dto.todo.CreateTodoReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todoRepository;

	@Override
	public boolean createTodo(CreateTodoReqDto createTodoReqDto) throws Exception {
		Todo todoEntity = createTodoReqDto.toEntity();

		return todoRepository.save(todoEntity) > 0;
	}

}