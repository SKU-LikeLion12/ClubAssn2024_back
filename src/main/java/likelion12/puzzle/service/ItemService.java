package likelion12.puzzle.service;

import likelion12.puzzle.domain.Item;
import likelion12.puzzle.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public Item findById(Long id){
        return itemRepository.findById(id);
    }

    @Transactional
    public Item save(String name, int count, byte[] image){
        Item item = new Item(name, count, image);
        return itemRepository.save(item);
    }

    public Item findByName(String name){
        return itemRepository.findByName(name);
    }

    // 관리자 페이지 전용 (물품 관리)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }


    @Transactional
    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }

    @Transactional
    public Item changeItem(Long itemId, String name, int count, MultipartFile image) throws IOException {

        byte[] imageBytes = null;
        // 사진 넣으면 바꾼 사진으로
        if (image != null) {
            imageBytes = image.getBytes();
        } else {
            // 사진 안넣으면 원래 있는 사진으로
            imageBytes = findById(itemId).getImage();
        }
        Item item = findById(itemId);
        item.setCount(count);
        item.setName(name);
        item.setImage(imageBytes);
        return item;
    }

}
