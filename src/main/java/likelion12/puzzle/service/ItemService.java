package likelion12.puzzle.service;

import likelion12.puzzle.domain.Item;
import likelion12.puzzle.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public Item findById(Long id){
        return itemRepository.findById(id);
    }

    public Item save(String name, int size){
        Item item = new Item(name, size);
        itemRepository.save(item);
        return item;
    }

    public Item findByName(String name){
        return itemRepository.findByName(name);
    }
}
