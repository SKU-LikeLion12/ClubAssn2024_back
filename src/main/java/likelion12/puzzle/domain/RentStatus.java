package likelion12.puzzle.domain;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum RentStatus {
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

    public static final Set<RentStatus> rentGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus.RENT, RentStatus.DELAY_RENT, RentStatus.LONG_DELAY_RENT));

    public static final Set<RentStatus> returnGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus.RETURN, RentStatus.DELAY_RETURN, RentStatus.LONG_DELAY_RETURN));

    public static final Set<RentStatus> delayGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus.DELAY_RENT, RentStatus.DELAY_RETURN));

    public static final Set<RentStatus> longDelayGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus.LONG_DELAY_RENT, RentStatus.LONG_DELAY_RETURN));

    public static final Set<RentStatus> usingGroup = Collections.unmodifiableSet(EnumSet.of(RentStatus.RENT, RentStatus.DELAY_RENT, RentStatus.LONG_DELAY_RENT, RentStatus.BOOK));


}
