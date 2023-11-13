package com.taubel.budget.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.Dtos.LineItemDto;
import com.taubel.budget.exceptions.LineItemAlreadyExistsException;
import com.taubel.budget.exceptions.NullFieldNotAllowedException;
import com.taubel.budget.services.LineItemService;

@RestController
@RequestMapping("/{username}/lineitem")
@PreAuthorize("#username == authentication.principal.username")
public class LineItemController {

    @Autowired
    private LineItemService lineItemServ;

    @GetMapping("/{budget}")
    public ResponseEntity<List<LineItemDto>> getLineItemsByBudget (@PathVariable("username") String username, @PathVariable("budget") Long budgetId){
        List<LineItemDto> lineItems = lineItemServ.getLineItemsByBudget(username, budgetId);
        return ResponseEntity.ok(lineItems);
    }
    
    @PostMapping()
    public ResponseEntity<LineItemDto> createNewLineItem(@PathVariable("username") String username, @RequestBody LineItemDto lineItem) {
        if (lineItem.getId() != null) throw new LineItemAlreadyExistsException("LineItem " + lineItem.getId() + " already exists");
        LineItemDto updatedLineItem = lineItemServ.updateCreateLineItem(lineItem, username);
        return ResponseEntity.ok(updatedLineItem);
    }

    @PutMapping()
    public ResponseEntity<LineItemDto> updateLineItem(@PathVariable("username") String username, @RequestBody LineItemDto lineItem) {
        if (lineItem.getId() == null) throw new NullFieldNotAllowedException("LineItem is missing an ID");
        LineItemDto updatedLineItem = lineItemServ.updateCreateLineItem(lineItem, username);
        return ResponseEntity.ok(updatedLineItem);
    }

    @DeleteMapping("/{lineitem}")
    public ResponseEntity<LineItemDto> deleteLineItem(@PathVariable("username") String username, @PathVariable("lineitem") long lineItemId) {
        lineItemServ.deleteLineItem(lineItemId, username);
        return ResponseEntity.ok(null);
    }

}
