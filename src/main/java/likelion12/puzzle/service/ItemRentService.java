package likelion12.puzzle.service;

import static likelion12.puzzle.DTO.ItemRentDTO.*;
import likelion12.puzzle.domain.Item;
import likelion12.puzzle.domain.ItemRent;
import likelion12.puzzle.domain.Member;
import likelion12.puzzle.domain.RentStatus;
import likelion12.puzzle.repository.ItemRentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Collections;
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

    //대여예약
    @Transactional
    public BookDTO bookItem(String studentId, long itemId, int count){

        Member renter = memberService.findByStudentId(studentId);
        Item item = itemService.findById(itemId);

        if(isPenalty(studentId)){
            System.out.println("패널티");
            //패널티로 인해 못빌림
        }

        MemberRentingSize memberRentingSize = checkMemberRenting(studentId);
        memberRentingSize.variety.add(itemId);
        if(memberRentingSize.variety.size() > 3){
            System.out.println("종류초과");
            //3종류 초과라 못빌림
        }
        if(memberRentingSize.count+count > 5){
            System.out.println("개수초과");
            //5개 초과라 못빌림
        }

        if(count + checkItemBooking(itemId) + item.getRentingCount() > item.getCount()){
            System.out.println("개수부족");
            //물품개수가 부족해서 못빌림
        }

        ItemRent itemRent = itemRentRepository.save(new ItemRent(renter, item, count));
        return new BookDTO(itemRent, dateCheckService.needReceiveDate(itemRent.getOfferDate()));
    }

    @Transactional
    public ReceiveDTO receiveItem(long itemRentId){
        ItemRent itemRent = findById(itemRentId);
        itemRent.itemReceive(ItemRent.getNow());
        return new ReceiveDTO(itemRent,dateCheckService.needReturnDate(itemRent.getReceiveDate()));
    }

    @Transactional
    public ItemRent returnItem(long itemRentId){
        ItemRent itemRent = findById(itemRentId);
        LocalDateTime now = ItemRent.getNow();
        LocalDateTime needReturnDate = dateCheckService.needReturnDate(now);
        RentStatus status = RentStatus.RETURN;
        if(now.isAfter(needReturnDate)){
            if(now.isAfter(dateCheckService.needReturnDate(needReturnDate))){
                status = RentStatus.LONG_DELAY_RETURN;
            }else {
                status = RentStatus.DELAY_RETURN;
            }
        }
        itemRent.itemReturn(now, status);
        return itemRent;
    }

    public boolean isPenalty(String studentId){
        Member member = memberService.findByStudentId(studentId);
        int delay = 0;
        int longDelay = 0;
        for (ItemRent itemRent : itemRentRepository.findByMemberStatus(member,RentStatus.canPenaltyGroup)) {
            DelayState delayState = checkDelay(itemRent);
            if(delayState == DelayState.DELAY){
                delay++;
            }else if(delayState == DelayState.LONG_DELAY) {
                longDelay++;
            }
        }
        return delay >= 3 || longDelay >= 1;
    }

    public MemberRentingSize checkMemberRenting(String studentId) {
        Member member = memberService.findByStudentId(studentId);
        Set<Long> set = new HashSet<>();
        int count = 0;
        for (ItemRent itemRent : itemRentRepository.findByMemberStatus(member, RentStatus.usingGroup)) {
            if(itemRent.getStatus().isUsingGroup()) {
                set.add(itemRent.getId());
                count += itemRent.getCount();
            }
        }
        return new MemberRentingSize(set, count);
    }

    public int checkItemBooking(Long itemId){
        Item item = itemService.findById(itemId);
        int size = 0;
        for (ItemRent itemRent : itemRentRepository.findByItemStatus(item, Collections.singleton(RentStatus.BOOK))) {
            if(!isAutoCancel(itemRent)) {
                size += itemRent.getCount();
            }
        }
        return size;
    }

    @Transactional
    public Boolean isAutoCancel(ItemRent itemRent){
        if(itemRent.getStatus() != RentStatus.BOOK) return null;

        LocalDateTime now = ItemRent.getNow();
        LocalDateTime needReceiveDate = dateCheckService.needReceiveDate(itemRent.getOfferDate());
        if(now.isAfter(needReceiveDate)){
            itemRent.cancel(); // DateChecker 문제 해결 후 로직 변경시 삭제
            return true;
        }
        return false;
    }

    public DelayState checkDelay(ItemRent itemRent){
        if(itemRent.getStatus() == RentStatus.LONG_DELAY_RETURN) return DelayState.LONG_DELAY;
        if(itemRent.getStatus() == RentStatus.DELAY_RETURN) return DelayState.DELAY;

        LocalDateTime now = ItemRent.getNow();

        if(itemRent.getStatus() == RentStatus.RETURN){
            now = itemRent.getReturnDate();//만약 이미 반납했으면 반납시간으로 변경
        }

        LocalDateTime needReturnDate = now.with(dateCheckService.needReturnDate(now));
        LocalDateTime longReturnDate = now.with(dateCheckService.needReturnDate(needReturnDate));
        if (now.isAfter(needReturnDate)) {
            if (now.isAfter(longReturnDate)) {
                return DelayState.LONG_DELAY;
            } else {
                return DelayState.DELAY;
            }
        }
        return DelayState.NO_DELAY;
    }

    public ItemRent findById(long itemRentId){
        return itemRentRepository.findById(itemRentId);
    }


}
