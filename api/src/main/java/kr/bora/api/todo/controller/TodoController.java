package kr.bora.api.todo.controller;

import kr.bora.api.common.aop.LogExecutionTime;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoServiceImpl service;

    @GetMapping("/list")
    @LogExecutionTime
    public ResponseEntity<List<Todo>> todoList() {

        return ResponseEntity.ok(service.getList());
    }

    @PostMapping("/save")
    @LogExecutionTime
    public ResponseEntity <String> todoSave(@RequestBody TodoRequestCommand.TodoRequest todoDto) {
        service.save(todoDto.toDto());
        String msg = "Save Success Todo";
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/read/{todoId}")
    @LogExecutionTime
    public ResponseEntity<TodoDto> todoRead(@PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.get(todoId));
    }

    @PutMapping("/modify/{todoId}")
    @LogExecutionTime
    public ResponseEntity<String> todoModify(@PathVariable("todoId") Long todoId, @RequestBody TodoDto todoDto) {

        service.modify(todoId, todoDto);

        return ResponseEntity.ok(todoId + "번 TODO가 수정되었습니다.");
    }

    @DeleteMapping("/remove/{todoId}")
    @LogExecutionTime
    public ResponseEntity<Map<String, Object>> todoRemove(@PathVariable("todoId") Long todoId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", todoId + " 번 Todo가 삭제되었습니다.");
        service.todoRemove(todoId);

        return ResponseEntity.ok(resultMap);
    }

}
