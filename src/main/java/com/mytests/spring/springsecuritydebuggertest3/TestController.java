package com.mytests.spring.springsecuritydebuggertest3;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class TestController {

    // anyRequest:

   // matches anyRequest.permitAll() - but navigates to all except this
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<String> rootGet(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("root: " + details);
    }
    // anyRequest.permitAll()
    @RequestMapping(path = "/some")
    public ResponseEntity<String> some(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("some: " + details);
    }
    // anyRequest.permitAll()
    @RequestMapping(path = "/any", method = RequestMethod.GET)
    public ResponseEntity<String> anyGet(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("any: " + details);
    }
    // anyRequest.permitAll()
    @GetMapping(path = "/another")
    public ResponseEntity<String> another(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("another: " + details);
    }

    // admin:

    // .requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
    // unlock from controller works
    @GetMapping(path = "/admin/test0")
    public ResponseEntity<String> adminTest0(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin test0: " + details);
    }
    // .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("SUPER")
    // unlock from controller works
    @PostMapping(path = "/admin/test0")
    public ResponseEntity<String> adminTest0Post(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin test0 post: " + details + ", body: " + body);
    }
    // .requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
    // unlock from controller works
    @RequestMapping(path = "/admin/test1", method = RequestMethod.GET)
    public ResponseEntity<String> adminTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin test1: " + details);
    }
    // .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("SUPER")
    // unlock from controller works
    @RequestMapping(path = "/admin/test1", method = RequestMethod.POST)
    public ResponseEntity<String> adminTest1Post(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin test1 post: " + details + ", body: " + body);
    }
    // .requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
    // or
    // .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("SUPER")
    // no inlay at all, it is impossible to unlock from controller
    @RequestMapping(path = "/admin/test2")
    public ResponseEntity<String> adminTest2(@RequestBody(required = false) String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("admin test2: " + details + ", body: " + body);
    }

    // users:

    // .requestMatchers("/users/**").hasRole("USER")
    // unlock from controller works
    @RequestMapping(path = "/user/test1", method = RequestMethod.GET)
    public ResponseEntity<String> userTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("user test1: " + details);
    }
    // .requestMatchers("/users/**").hasRole("USER")
    // unlock from controller works
    @RequestMapping(path = "/user/test1", method = RequestMethod.POST)
    public ResponseEntity<String> userTest1Post(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("user test1: " + details + ", body: " + body);
    }
    // .requestMatchers("/users/**").hasRole("USER")
    // unlock from controller is enabled, but doesn't work
    @RequestMapping(path = "/user/test2")
    public ResponseEntity<String> userTest2(@RequestBody(required = false) String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("user test2: " + details + ", body: " + body);
    }

    // all:

    // .requestMatchers(HttpMethod.GET,"/all/**").authenticated()
    // unlock from controller works
    @GetMapping(path = "/all/test0")
    public ResponseEntity<String> allTest0(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("all test0: " + details);
    }
    // .requestMatchers(HttpMethod.GET,"/all/**").authenticated()
    // unlock from controller works
    @RequestMapping(path = "/all/test1", method = RequestMethod.GET)
    public ResponseEntity<String> allTest1(@CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("all test1: " + details);
    }
    // .requestMatchers(HttpMethod.GET,"/all/**").authenticated()
    // or
    // .requestMatchers(HttpMethod.POST,"/all/**").hasAnyRole("USER","ADMIN","SUPER")
    // no inlay at all, it is impossible to unlock from controller
    @RequestMapping(path = "/all/test2")
    public ResponseEntity<String> allTest2(@RequestBody(required = false) String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("all test2: " + details + ", body: " + body);
    }
    // .requestMatchers(HttpMethod.POST,"/all/**").hasAnyRole("USER","ADMIN","SUPER")
    @PostMapping(path = "/all/test0")
    public ResponseEntity<String> allTest0Post(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("all test0: " + details + ", body: " + body);
    }
    // .requestMatchers(HttpMethod.POST,"/all/**").hasAnyRole("USER","ADMIN","SUPER")
    @RequestMapping(path = "/all/test1", method = RequestMethod.POST)
    public ResponseEntity<String> allTest1Post(@RequestBody String body, @CurrentSecurityContext SecurityContext context) {
        String details = Objects.requireNonNull(context.getAuthentication()).toString();
        return ResponseEntity.ok("all test1: " + details + ", body: " + body);
    }

}
