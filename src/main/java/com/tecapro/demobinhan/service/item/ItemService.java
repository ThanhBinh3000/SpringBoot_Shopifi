package com.tecapro.demobinhan.service.item;

import com.tecapro.demobinhan.model.Item;
import com.tecapro.demobinhan.model.ShoppingCart;
import com.tecapro.demobinhan.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ItemService implements IItemService {
    @Autowired
    private IItemRepository itemRepository;

    @Override
    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void remove(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Iterable<Item> findAllByShoppingCart(ShoppingCart shoppingCart) {
        return itemRepository.findAllByShoppingCart(shoppingCart);
    }
}
