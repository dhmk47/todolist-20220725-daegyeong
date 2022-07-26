package com.daegyeong.todolist.web.controller.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daegyeong.todolist.service.todo.TodoService;
import com.daegyeong.todolist.web.dto.CMRespDto;
import com.daegyeong.todolist.web.dto.todo.CreateTodoReqDto;
import com.daegyeong.todolist.web.dto.todo.TodoListRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/todolist")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getTodoList(@RequestParam int page, @RequestParam int contentCount) {
		List<TodoListRespDto> list = null;
		
		try {
			list = todoService.getTodoList(page, contentCount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, page + "page list on load failed", list));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, page + "page list success to load", list));
	}

	@PostMapping("/todo")
	public ResponseEntity<?> addTodo(@RequestBody CreateTodoReqDto createTodoReqDto) {
		
		try {
			if(!todoService.createTodo(createTodoReqDto)) {
				throw new RuntimeException("DataBase Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "Adding todo failed", createTodoReqDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success", createTodoReqDto));
	}
}