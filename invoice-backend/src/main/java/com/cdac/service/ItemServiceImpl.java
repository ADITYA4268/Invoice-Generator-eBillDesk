package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.BillDao;
import com.cdac.dao.ItemDao;
import com.cdac.dao.ProductDao;
import com.cdac.dto.ItemReqDto;
import com.cdac.dto.ItemRespDto;
import com.cdac.entities.Item;
import com.cdac.entities.Product;
import com.cdac.exceptions.ItemNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	private ProductDao productDao;

	private BillDao billDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItemRespDto createItem(ItemReqDto dto, Long billId, Long productId) {
		Item item = modelMapper.map(dto, Item.class);
		Item saved = itemDao.save(item);
		return modelMapper.map(saved, ItemRespDto.class);
	}

	@Override
	public ItemRespDto getItemById(Long id) {
		Item item = itemDao.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found"));
		return modelMapper.map(item, ItemRespDto.class);
	}

	@Override
	public List<ItemRespDto> getItemsByBillId(Long billId) {
		List<Item> items = itemDao.findByBill_Id(billId);
		return items.stream().map(item -> {
			ItemRespDto resp = modelMapper.map(item, ItemRespDto.class);
			resp.setId(billId);
			return resp;
		}).collect(Collectors.toList());
	}

	@Override
	public ItemRespDto updateItem(Long id, ItemReqDto dto) {
		Item item = itemDao.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found"));

		item.setQuantity(dto.getQuantity());
		// if product is allowed to change in update
		if (dto.getProductId() != null) {
			Product product = productDao.findById(dto.getProductId())
					.orElseThrow(() -> new RuntimeException("Product not found"));
			item.setProduct(product);
		}

		Item updated = itemDao.save(item);

		// Map to response DTO
		ItemRespDto resp = new ItemRespDto();
		resp.setId(updated.getId());
		resp.setQuantity(updated.getQuantity());
		if (updated.getProduct() != null) {
			resp.setProductName(updated.getProduct().getName());
			resp.setProductPrice(updated.getProduct().getSellingPrice());
		}

		return resp;
	}

	@Override
	public void deleteItem(Long id) {
		if (!itemDao.existsById(id)) {
			throw new ItemNotFoundException("Item not found");
		}
		itemDao.deleteById(id);
	}

}
