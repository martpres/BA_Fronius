package dto;

public class CurrentAcDto {
    private Float acPhase1;
    private Float acPhase2;
    private Float acPhase3;

    public CurrentAcDto(Float acPhase1, Float acPhase2, Float acPhase3) {
        this.acPhase1 = acPhase1;
        this.acPhase2 = acPhase2;
        this.acPhase3 = acPhase3;
    }


    public Float getAcPhase1() {
        return acPhase1;
    }

    public void setAcPhase1(Float acPhase1) {
        this.acPhase1 = acPhase1;
    }

    public Float getAcPhase2() {
        return acPhase2;
    }

    public void setAcPhase2(Float acPhase2) {
        this.acPhase2 = acPhase2;
    }

    public Float getAcPhase3() {
        return acPhase3;
    }

    public void setAcPhase3(Float acPhase3) {
        this.acPhase3 = acPhase3;
    }
}
