package likelion12.puzzle.service;

import jdk.jshell.Snippet;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.domain.ItemRent;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RentStatus;
import likelion12.puzzle.repository.ItemRentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

        if(isPenalty(studentId)){
            //패널티로 인해 못빌림
        }

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

        ItemRent itemRent = new ItemRent(renter, item, count);
        return itemRentRepository.save(itemRent);
    }

    public boolean isPenalty(int studentId){
        Member member = memberService.findByStudentId(studentId);
        int delay = 0;
        int longDelay = 0;
        for (ItemRent itemRent : itemRentRepository.findByMember(member)) {
            checkStatus(itemRent);
            if(itemRent.getStatus().isDelayGroup()){
                delay++;
            }
            if(itemRent.getStatus().isLongDelayGroup()){
                longDelay++;
            }
        }
        return delay >= 3 || longDelay >= 1;
    }

    public MemberRentingSize checkMemberRenting(int studentId) {
        Member member = memberService.findByStudentId(studentId);
        Set<Long> set = new HashSet<>();
        int count = 0;
        for (ItemRent itemRent : itemRentRepository.findByMemberStatus(member, RentStatus.usingGroup)) {
            checkStatus(itemRent);
            if(itemRent.getStatus().isUsingGroup()) {
                set.add(itemRent.getId());
                count += itemRent.getCount();
            }
        }
        return new MemberRentingSize(set, count);
    }

    public ItemRentingSize checkItemRenting(Long itemId){
        Item item = itemService.findById(itemId);
        ItemRentingSize irs = new ItemRentingSize();
        for (ItemRent itemRent : itemRentRepository.findByItemStatus(item, Collections.singleton(RentStatus.BOOK))) {
            checkStatus(itemRent);
            if(itemRent.getStatus()==RentStatus.BOOK) {
                irs.booking += itemRent.getCount();
            }
        }
        for (ItemRent itemRent : itemRentRepository.findByItemStatus(item, Collections.singleton(RentStatus.RENT))) {
            irs.renting += itemRent.getCount();
        }
        return irs;
    }

    @Transactional
    void checkStatus(ItemRent itemRent){
        if(itemRent.getStatus() == RentStatus.BOOK){
            itemRent.checkAutoCancel(dateCheckService.needReceiveDate(itemRent.getRentOfferDate()));
        }if(itemRent.getStatus().isRentGroup()){
            itemRent.checkDelay(dateCheckService.needReturnDate(itemRent.getRentStartDate()));
        }
    }

    @AllArgsConstructor
    public static class MemberRentingSize {
        Set<Long> variety;
        int count;
    }

    public static class ItemRentingSize{
        int booking = 0;
        int renting = 0;
    }
}
