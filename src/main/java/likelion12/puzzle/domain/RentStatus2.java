package likelion12.puzzle.domain;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum RentStatus2 {
    BOOK, RENT, RETURN, CANCEL, DELAY_RENT, LONG_DELAY_RENT, DELAY_RETURN, LONG_DELAY_RETURN;

    public boolean isRentGroup() {
        return rentGroup.contains(this);
    }

    public boolean isLongDelayGroup() {
        return longDelayGroup.contains(this);
    }

    public boolean isDelayGroup() {
        return delayGroup.contains(this);
    }

    public boolean isReturnGroup() {
        return returnGroup.contains(this);
    }

    public boolean isUsingGroup() {
        return usingGroup.contains(this);
    }

    public static final Set<RentStatus2> rentGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus2.RENT, RentStatus2.DELAY_RENT, RentStatus2.LONG_DELAY_RENT));

    public static final Set<RentStatus2> returnGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus2.RETURN, RentStatus2.DELAY_RETURN, RentStatus2.LONG_DELAY_RETURN));

    public static final Set<RentStatus2> delayGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus2.DELAY_RENT, RentStatus2.DELAY_RETURN));

    public static final Set<RentStatus2> longDelayGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus2.LONG_DELAY_RENT, RentStatus2.LONG_DELAY_RETURN));

    public static final Set<RentStatus2> usingGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus2.RENT, RentStatus2.DELAY_RENT, RentStatus2.LONG_DELAY_RENT, RentStatus2.BOOK));

}
