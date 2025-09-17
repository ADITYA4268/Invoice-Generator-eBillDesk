package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ItemReqDto;
import com.cdac.dto.ItemRespDto;
import com.cdac.service.ItemService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

	private ItemService itemService;

	// ✅ Create Item for a specific Bill and Product
	@PostMapping("/bill/{billId}/product/{productId}")
	public ResponseEntity<ItemRespDto> createItem(@PathVariable Long billId, @PathVariable Long productId,
			@RequestBody @Valid ItemReqDto dto) {
		ItemRespDto response = itemService.createItem(dto, billId, productId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// ✅ Get Item by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getItem(@PathVariable Long id) {
		ItemRespDto response = itemService.getItemById(id);
		return ResponseEntity.ok(response);
	}

	// ✅ Get all Items for a Bill
	@GetMapping("/by-bill/{billId}")
	public ResponseEntity<List<ItemRespDto>> getItemsByBill(@PathVariable Long billId) {
		return ResponseEntity.ok(itemService.getItemsByBillId(billId));
	}

	// ✅ Update quantity of an Item
	@PutMapping("/{id}")
	public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody @Valid ItemReqDto dto) {
		ItemRespDto response = itemService.updateItem(id, dto);
		return ResponseEntity.ok(response);
	}

	// ✅ Delete Item by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}

}
