package kr.bora.api.common.controller;

import kr.bora.api.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/duplicate")
@RequiredArgsConstructor
public class DuplicateController {

    private final AuthService authService;

    @GetMapping("/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok(authService.checkUsername(username));
    }

}
