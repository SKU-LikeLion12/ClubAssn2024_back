package likelion12.puzzle.service;

import likelion12.puzzle.domain.Item;
import likelion12.puzzle.domain.ItemRent;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RentStatus;
import likelion12.puzzle.repository.ItemRentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemRentService {

    private final ItemRentRepository itemRentRepository;
    private final MemberService memberService;
    private final ItemService itemService;
    private final DateCheckService dateCheckService;

    //물품은 프론트에서도 그냥 물품번호로 관리해주세요... 이름으로 쿼리할 필요 없을듯..
    @Transactional
    public ItemRent rentBook(int studentId, long itemId, int count){
        //빌리는 개수가 유효한지 체크하는 로직이 필요함ㅋㅋ

        Member renter = memberService.findByStudentId(studentId);
        Item item = itemService.findById(itemId);

        MemberRentingSize memberRentingSize = checkMemberRenting(studentId);
        memberRentingSize.variety.add(itemId);
        if(memberRentingSize.variety.size() > 3){
            //3종류 초과라 못빌림
        }
        if(memberRentingSize.count+count > 5){
            //5개 초과라 못빌림
        }

        ItemRentingSize itemRentingSize = checkItemRenting(itemId);
        if(count+itemRentingSize.renting+itemRentingSize.booking > item.getCount()){
            //물품개수가 부족해서 못빌림
        }

        ItemRent itemRent = new ItemRent(renter, item, count, dateCheckService.expireBookDate());
//        itemRentRepository.findByItem()
        return itemRentRepository.save(itemRent);
    }

    public MemberRentingSize checkMemberRenting(int studentId) {
        Member member = memberService.findByStudentId(studentId);
        Set<Long> set = new HashSet<>();
        int count = 0;
        for (ItemRent itemRent : itemRentRepository.findByMemberStatus(member, RentStatus.BOOK)) {
            set.add(itemRent.getId());
            count += itemRent.getCount();
        }
        for (ItemRent itemRent : itemRentRepository.findByMemberStatus(member, RentStatus.RENT)) {
            set.add(itemRent.getId());
            count += itemRent.getCount();
        }
        return new MemberRentingSize(set, count);
    }

    public ItemRentingSize checkItemRenting(Long itemId){
        Item item = itemService.findById(itemId);
        ItemRentingSize irs = new ItemRentingSize();
        for (ItemRent itemRent : itemRentRepository.findByItemStatus(item, RentStatus.BOOK)) {
            irs.booking += itemRent.getCount();
        }
        for (ItemRent itemRent : itemRentRepository.findByItemStatus(item, RentStatus.RENT)) {
            irs.renting += itemRent.getCount();
        }
        return irs;
    }

    @AllArgsConstructor
    public class MemberRentingSize {
        Set<Long> variety;
        int count;
    }

    public class ItemRentingSize{
        int booking = 0;
        int renting = 0;
    }
}
