package helpers;

public enum EAverageLifeExpectancy {

    DOG_YEARS(13),
    WOLF_YEARS(8),
    SHARK_YEARS(25),
    CAT_YEARS(15);

    int years;

    EAverageLifeExpectancy(int years) {
        this.years = years;
    }

    public int getYears(){
        return years;
    }
}
