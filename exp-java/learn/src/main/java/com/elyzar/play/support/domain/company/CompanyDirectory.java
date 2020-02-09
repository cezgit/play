package com.elyzar.play.support.domain.company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDirectory implements Associate{

    private List<Associate> associates = new ArrayList<>();


    @Override
    public void showDetails() {
        associates.forEach(a -> a.showDetails());
    }

    public void addAssociate(Associate a) {
        associates.add(a);
    }

    public void removeAssociate(Associate a) {
        associates.remove(a);
    }


}
