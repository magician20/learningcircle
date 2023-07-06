package com.magician.greetingsapi;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/test")
public class DemoController {

    @GetMapping("/anonymous")
    public Mono<MessageDto> getAnonymous() {
         return Mono.just(new MessageDto("Hello Anonymous"));
        //return ResponseEntity.ok("Hello Anonymous");
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Mono<MessageDto> getUser() {
          return Mono.just(new MessageDto("Hello User"));
       // return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public Mono<MessageDto> getAdmin() {
         return Mono.just(new MessageDto("Hello Admin"));
        //return ResponseEntity.ok("Hello Admin");
    }

    // @GetMapping("/student")
    // public ResponseEntity<String> helloMember() {
    // return ResponseEntity.ok("Hello dear student");
    // }

    // @GetMapping("/teacher")
    // public ResponseEntity<String> helloModerator() {
    // return ResponseEntity.ok("Hello teacher");
    // }

    static record MessageDto(String body) {
    }

}
