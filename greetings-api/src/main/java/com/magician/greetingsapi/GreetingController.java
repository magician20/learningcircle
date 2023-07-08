package com.magician.greetingsapi;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/v1")
public class GreetingController {

    @GetMapping("/greet")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Mono<MessageDto> getGreeting(JwtAuthenticationToken  auth) {
        return Mono.just(
                new MessageDto("Hi %s! You are granted with: %s.".formatted(auth.getName(), auth.getAuthorities())));
    }

    @GetMapping("/restricted")
    @PreAuthorize("hasAuthority('admin')")
    public Mono<MessageDto> getRestricted() {
        return Mono.just(new MessageDto("You are so nice!"));
    }

    @GetMapping("/greet2")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public Mono<String> greet(JwtAuthenticationToken who) {
        return Mono.just(String.format(
                "Hello %s! You are granted with %s.",
                who.getTokenAttributes().get(StandardClaimNames.PREFERRED_USERNAME),
                who.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()));
    }

    static record MessageDto(String body) {
    }
}