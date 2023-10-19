package com.taubel.budget.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.dtos.LineItemDto;
import com.taubel.budget.entities.LineItem;

@RestController
@RequestMapping("/{username}/lineitem")
@PreAuthorize("#username == authentication.principal.username")
public class LineItemController {
    
    @PostMapping()
    public ResponseEntity<LineItemDto> createNewLineItem(@PathVariable("username") String username, @RequestBody LineItemDto lineItem) {
        return ResponseEntity.ok(new LineItemDto()); //TODO
    }

    @PutMapping()
    public ResponseEntity<LineItemDto> updateLineItem(@PathVariable("username") String username, @RequestBody LineItemDto lineItem) {
        return ResponseEntity.ok(new LineItemDto()); //TODO
    }

    @DeleteMapping("/{lineitem}")
    public ResponseEntity<LineItemDto> deleteLineItem(@PathVariable("username") String username, @PathVariable("lineitem") int lineItemId) {
        return ResponseEntity.ok(null); //TODO
    }

}
