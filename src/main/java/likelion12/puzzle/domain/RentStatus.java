package likelion12.puzzle.domain;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum RentStatus {
    BOOK, RENT, RETURN, CANCEL;

    public boolean isUsingGroup() {
        return usingGroup.contains(this);
    }
    public static final Set<RentStatus> usingGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus.RENT, RentStatus.BOOK));


}
